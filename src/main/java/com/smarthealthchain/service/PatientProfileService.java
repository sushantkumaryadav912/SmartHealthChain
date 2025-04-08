package com.smarthealthchain.service;

import com.smarthealthchain.model.PatientProfile;
import com.smarthealthchain.repository.PatientProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientProfileService {

    private final PatientProfileRepository patientProfileRepository;

    public PatientProfileService(PatientProfileRepository patientProfileRepository) {
        this.patientProfileRepository = patientProfileRepository;
    }

    public List<PatientProfile> getAllPatientProfiles() {
        return patientProfileRepository.findAll();
    }

    public PatientProfile getPatientProfileById(int id) {
        return patientProfileRepository.findById(id);
    }
}