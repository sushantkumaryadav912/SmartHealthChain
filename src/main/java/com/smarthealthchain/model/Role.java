package com.smarthealthchain.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Role {
    private int roleId;
    private String roleName;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}