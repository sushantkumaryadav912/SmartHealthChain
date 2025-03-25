package com.example.SmartHealthChain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.SmartHealthChain.models.MedicalRecord;
import java.util.List;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
    List<MedicalRecord> findByPatientId(Long patientId);
    List<MedicalRecord> findByDoctorId(Long doctorId);
}
