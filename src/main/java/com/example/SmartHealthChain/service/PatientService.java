package com.example.SmartHealthChain.service;

import com.example.SmartHealthChain.models.Patient;
import com.example.SmartHealthChain.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    // Create a new patient
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    // Get a patient by ID
    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    // Get a patient by email
    public Optional<Patient> getPatientByEmail(String email) {
        return Optional.ofNullable(patientRepository.findByEmail(email));
    }

    // Get all patients
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    // Delete a patient
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}
