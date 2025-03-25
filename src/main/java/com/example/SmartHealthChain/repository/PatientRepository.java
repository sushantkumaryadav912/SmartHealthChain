package com.example.SmartHealthChain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.SmartHealthChain.models.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByEmail(String email);
}
