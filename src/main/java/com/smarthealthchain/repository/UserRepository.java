package com.smarthealthchain.repository;

import com.smarthealthchain.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<User> userRowMapper = (rs, rowNum) -> {
        User user = new User();
        user.setUserId(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
        user.setEmail(rs.getString("email"));
        user.setPasswordHash(rs.getString("password_hash"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setPhoneNumber(rs.getString("phone_number"));
        user.setDateOfBirth(rs.getDate("date_of_birth") != null ? rs.getDate("date_of_birth").toLocalDate() : null);
        user.setGender(rs.getString("gender"));
        user.setAddress(rs.getString("address"));
        user.setProfilePicture(rs.getString("profile_picture"));
        user.setRoleId(rs.getInt("role_id"));
        user.setEmergencyContactName(rs.getString("emergency_contact_name"));
        user.setEmergencyContactPhone(rs.getString("emergency_contact_phone"));
        user.setActive(rs.getBoolean("is_active"));
        user.setMfaEnabled(rs.getBoolean("mfa_enabled"));
        user.setMfaSecret(rs.getString("mfa_secret"));
        user.setLastLoginAt(rs.getTimestamp("last_login_at") != null ? rs.getTimestamp("last_login_at").toLocalDateTime() : null);
        user.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        user.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
        return user;
    };

    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM users", userRowMapper);
    }

    public User findById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE user_id = ?", userRowMapper, id);
    }

    public User save(User user) {
        String sql = "INSERT INTO users (username, email, password_hash, first_name, last_name, phone_number, date_of_birth, gender, address, profile_picture, role_id, emergency_contact_name, emergency_contact_phone, is_active, mfa_enabled, mfa_secret) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPasswordHash());
            ps.setString(4, user.getFirstName());
            ps.setString(5, user.getLastName());
            ps.setString(6, user.getPhoneNumber());
            ps.setObject(7, user.getDateOfBirth());
            ps.setString(8, user.getGender());
            ps.setString(9, user.getAddress());
            ps.setString(10, user.getProfilePicture());
            ps.setInt(11, user.getRoleId());
            ps.setString(12, user.getEmergencyContactName());
            ps.setString(13, user.getEmergencyContactPhone());
            ps.setBoolean(14, user.isActive());
            ps.setBoolean(15, user.isMfaEnabled());
            ps.setString(16, user.getMfaSecret());
            return ps;
        }, keyHolder);

        int generatedId = keyHolder.getKey().intValue();
        return findById(generatedId);
    }
}