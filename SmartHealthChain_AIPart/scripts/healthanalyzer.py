import pandas as pd
import mysql.connector
from transformers import pipeline
from sklearn.ensemble import RandomForestClassifier
from sklearn.model_selection import train_test_split
import numpy as np
from datetime import datetime
import configparser
import os
import json
import logging

# Set up logging
logging.basicConfig(level=logging.ERROR, filename='error.log')
logger = logging.getLogger(__name__)

# Load configuration
config = configparser.ConfigParser()
config_path = os.path.join(os.path.dirname(os.path.abspath(__file__)), '../config/config.ini')
if not os.path.exists(config_path):
    raise FileNotFoundError(f"Configuration file not found at {config_path}")
config.read(config_path)
if 'Database' not in config:
    raise KeyError("Database section missing in config.ini")
db_config = {
    'host': config['Database']['host'],
    'port': int(config['Database']['port']),  # Ensure port is an integer
    'user': config['Database']['user'],
    'password': config['Database']['password'],
    'database': config['Database']['database']
}

# Initialize NLP summarizer with PyTorch
summarizer = pipeline("summarization", model="facebook/bart-large-cnn", framework="pt")

def fetch_patient_data(patient_id):
    try:
        conn = mysql.connector.connect(**db_config)
        cursor = conn.cursor(dictionary=True)

        cursor.execute("""
            SELECT p.patient_id, u.first_name, u.last_name, p.blood_type, p.height, p.weight
            FROM patient_profiles p
            JOIN users u ON p.user_id = u.user_id
            WHERE p.patient_id = %s
        """, (patient_id,))
        profile = cursor.fetchone()

        cursor.execute("""
            SELECT heart_rate, blood_pressure_systolic, blood_pressure_diastolic, temperature, timestamp
            FROM vital_signs
            WHERE patient_id = %s
            ORDER BY timestamp DESC
            LIMIT 10
        """, (patient_id,))
        vital_signs = cursor.fetchall()

        cursor.execute("""
            SELECT record_type, title, description, record_date
            FROM medical_records
            WHERE patient_id = %s
            ORDER BY record_date DESC
            LIMIT 5
        """, (patient_id,))
        medical_records = cursor.fetchall()

        cursor.execute("""
            INSERT INTO audit_logs (user_id, action_type, entity_type, entity_id, new_values, created_at)
            VALUES (NULL, 'GENERATE', 'AI Report', %s, 'Fetched patient data for AI analysis', NOW())
        """, (patient_id,))
        conn.commit()

        cursor.close()
        conn.close()
        return profile, vital_signs, medical_records
    except Exception as e:
        logger.error(f"Error in fetch_patient_data for patient_id {patient_id}: {str(e)}")
        print(f"Error fetching data: {e}")
        return None, None, None

def summarize_records(records):
    text = " ".join([f"{r['title']}: {r['description']}" for r in records if r['description']])
    if not text:
        return "No detailed medical records available."
    try:
        summary = summarizer(text, max_length=150, min_length=50, do_sample=False)
        return summary[0]['summary_text']
    except Exception as e:
        logger.error(f"Error in summarize_records: {str(e)}")
        print(f"Error in summarization: {e}")
        return "Summary generation failed."

def predict_health_risk(vital_signs, conditions=None):
    if not vital_signs:
        return "Low", 0.5

    df_vitals = pd.DataFrame(vital_signs)
    features = ['heart_rate', 'blood_pressure_systolic', 'blood_pressure_diastolic', 'temperature']
    X = df_vitals[features].fillna(df_vitals[features].mean())

    y = ['Low' if i % 2 == 0 else 'Medium' for i in range(len(X))]
    X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

    model = RandomForestClassifier(n_estimators=100, random_state=42)
    model.fit(X_train, y_train)

    latest_vitals = X.iloc[-1].values.reshape(1, -1)
    risk = model.predict(latest_vitals)[0]
    confidence = model.predict_proba(latest_vitals).max()
    return risk, confidence

def generate_recommendations():
    recs = ["Maintain regular check-ups and update vital signs via wearable devices."]
    return recs

def generate_report(patient_id):
    profile, vital_signs, medical_records = fetch_patient_data(patient_id)
    if not profile:
        logger.error(f"No profile found for patient_id {patient_id}")
        return None

    summary = summarize_records(medical_records)
    risk, confidence = predict_health_risk(vital_signs)
    recommendations = generate_recommendations()

    report = {
        'patient_name': f"{profile['first_name']} {profile['last_name']}",
        'patient_id': profile['patient_id'],
        'date': datetime.now().strftime("%Y-%m-%d"),
        'summary': summary,
        'vital_signs': [
            f"HR: {v['heart_rate']} bpm, BP: {v['blood_pressure_systolic']}/{v['blood_pressure_diastolic']} mmHg, Temp: {v['temperature']} °F at {v['timestamp']}"
            for v in vital_signs
        ],
        'conditions': [],
        'risk_level': risk,
        'confidence': f"{confidence:.2%}",
        'recommendations': recommendations,
        'qr_code_url': f"http://yourapp.com/emergency/{patient_id}"
    }
    return report

if __name__ == "__main__":
    import sys
    if len(sys.argv) != 2:
        print("Usage: python healthanalyzer.py <patient_id>")
        sys.exit(1)
    patient_id = int(sys.argv[1])
    report = generate_report(patient_id)
    if report:
        print(json.dumps(report))
    else:
        print("Failed to generate report.")