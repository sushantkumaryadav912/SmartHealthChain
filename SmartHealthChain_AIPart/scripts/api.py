from flask import Flask, jsonify, request
from healthanalyzer import generate_report

app = Flask(__name__)

@app.route('/generate_report/<int:patient_id>', methods=['POST'])
def generate_report_endpoint(patient_id):
    try:
        report = generate_report(patient_id)
        if report:
            return jsonify(report)
        return jsonify({"error": "Failed to generate report"}), 500
    except Exception as e:
        return jsonify({"error": str(e)}), 500

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)