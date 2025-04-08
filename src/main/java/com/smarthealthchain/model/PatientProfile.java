package com.smarthealthchain.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PatientProfile {
    private int patientId;
    private int userId;
    private String bloodType; // ENUM mapped as String
    private Double height;    // in cm
    private Double weight;    // in kg
    private String allergies;
    private String chronicConditions;
    private String currentMedications;
    private String familyMedicalHistory;
    private String lifestyleNotes;
    private String emergencyQrCode;
    private String emergencyNfcId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}