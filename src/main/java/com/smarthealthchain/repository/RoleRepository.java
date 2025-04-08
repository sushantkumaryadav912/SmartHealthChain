package com.smarthealthchain.repository;

import com.smarthealthchain.model.Role;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RoleRepository {

    private final JdbcTemplate jdbcTemplate;

    public RoleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Role> roleRowMapper = (rs, rowNum) -> {
        Role role = new Role();
        role.setRoleId(rs.getInt("role_id"));
        role.setRoleName(rs.getString("role_name"));
        role.setDescription(rs.getString("description"));
        role.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        role.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
        return role;
    };

    public List<Role> findAll() {
        return jdbcTemplate.query("SELECT * FROM roles", roleRowMapper);
    }

    public Role findById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM roles WHERE role_id = ?", roleRowMapper, id);
    }
}