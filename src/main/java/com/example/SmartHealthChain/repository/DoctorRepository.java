package com.example.SmartHealthChain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.SmartHealthChain.models.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Doctor findByEmail(String email);
}
