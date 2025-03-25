package com.example.SmartHealthChain.controller;

import com.example.SmartHealthChain.models.MedicalRecord;
import com.example.SmartHealthChain.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medical-records")
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService medicalRecordService;

    @PostMapping
    public MedicalRecord addRecord(@RequestBody MedicalRecord record) {
        return medicalRecordService.saveMedicalRecord(record);
    }

    @GetMapping("/patient/{patientId}")
    public List<MedicalRecord> getRecordsByPatientId(@PathVariable Long patientId) {
        return medicalRecordService.getRecordsByPatientId(patientId);
    }

    @GetMapping("/doctor/{doctorId}")
    public List<MedicalRecord> getRecordsByDoctorId(@PathVariable Long doctorId) {
        return medicalRecordService.getRecordsByDoctorId(doctorId);
    }

    @GetMapping
    public List<MedicalRecord> getAllRecords() {
        return medicalRecordService.getAllMedicalRecords();
    }
}
