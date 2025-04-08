package com.smarthealthchain.repository;

import com.smarthealthchain.model.Doctor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class DoctorRepository {

    private final JdbcTemplate jdbcTemplate;

    public DoctorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Doctor> doctorRowMapper = (rs, rowNum) -> {
        Doctor doctor = new Doctor();
        doctor.setDoctorId(rs.getInt("doctor_id"));
        doctor.setUserId(rs.getInt("user_id"));
        doctor.setLicenseNumber(rs.getString("license_number"));
        doctor.setPracticeYears(rs.getObject("practice_years") != null ? rs.getInt("practice_years") : null);
        doctor.setBiography(rs.getString("biography"));
        doctor.setConsultationFee(rs.getObject("consultation_fee") != null ? rs.getDouble("consultation_fee") : null);
        doctor.setAcceptingPatients(rs.getBoolean("is_accepting_patients"));
        doctor.setAverageRating(rs.getObject("average_rating") != null ? rs.getDouble("average_rating") : null);
        doctor.setTotalReviews(rs.getObject("total_reviews") != null ? rs.getInt("total_reviews") : null);
        doctor.setVerificationStatus(rs.getString("verification_status"));
        doctor.setVerifiedAt(rs.getTimestamp("verified_at") != null ? rs.getTimestamp("verified_at").toLocalDateTime() : null);
        doctor.setVerifiedBy(rs.getObject("verified_by") != null ? rs.getInt("verified_by") : null);
        doctor.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        doctor.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
        return doctor;
    };

    public List<Doctor> findAll() {
        return jdbcTemplate.query("SELECT * FROM doctors", doctorRowMapper);
    }

    public Doctor findById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM doctors WHERE doctor_id = ?", doctorRowMapper, id);
    }
}