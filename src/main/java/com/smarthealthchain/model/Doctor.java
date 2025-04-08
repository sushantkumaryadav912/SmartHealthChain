package com.smarthealthchain.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Doctor {
    private int doctorId;
    private int userId;
    private String licenseNumber;
    private Integer practiceYears;
    private String biography;
    private Double consultationFee;
    private boolean isAcceptingPatients;
    private Double averageRating;
    private Integer totalReviews;
    private String verificationStatus; // ENUM mapped as String
    private LocalDateTime verifiedAt;
    private Integer verifiedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}