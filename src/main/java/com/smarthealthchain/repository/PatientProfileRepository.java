package com.smarthealthchain.repository;

import com.smarthealthchain.model.PatientProfile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PatientProfileRepository {

    private final JdbcTemplate jdbcTemplate;

    public PatientProfileRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<PatientProfile> patientProfileRowMapper = (rs, rowNum) -> {
        PatientProfile profile = new PatientProfile();
        profile.setPatientId(rs.getInt("patient_id"));
        profile.setUserId(rs.getInt("user_id"));
        profile.setBloodType(rs.getString("blood_type"));
        profile.setHeight(rs.getDouble("height"));
        profile.setWeight(rs.getDouble("weight"));
        profile.setAllergies(rs.getString("allergies"));
        profile.setChronicConditions(rs.getString("chronic_conditions"));
        profile.setCurrentMedications(rs.getString("current_medications"));
        profile.setFamilyMedicalHistory(rs.getString("family_medical_history"));
        profile.setLifestyleNotes(rs.getString("lifestyle_notes"));
        profile.setEmergencyQrCode(rs.getString("emergency_qr_code"));
        profile.setEmergencyNfcId(rs.getString("emergency_nfc_id"));
        profile.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        profile.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
        return profile;
    };

    public List<PatientProfile> findAll() {
        return jdbcTemplate.query("SELECT * FROM patient_profiles", patientProfileRowMapper);
    }

    public PatientProfile findById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM patient_profiles WHERE patient_id = ?", patientProfileRowMapper, id);
    }
}