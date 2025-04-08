package com.smarthealthchain.model;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class User {
    private int userId;
    private String username;
    private String email;
    private String passwordHash;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String gender; // ENUM mapped as String
    private String address;
    private String profilePicture;
    private int roleId;
    private String emergencyContactName;
    private String emergencyContactPhone;
    private boolean isActive;
    private boolean mfaEnabled;
    private String mfaSecret;
    private LocalDateTime lastLoginAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}