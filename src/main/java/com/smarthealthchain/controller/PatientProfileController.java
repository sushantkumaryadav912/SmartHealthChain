package com.smarthealthchain.controller;

import com.smarthealthchain.model.PatientProfile;
import com.smarthealthchain.service.PatientProfileService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/patient-profiles")
public class PatientProfileController {

    private final PatientProfileService patientProfileService;

    public PatientProfileController(PatientProfileService patientProfileService) {
        this.patientProfileService = patientProfileService;
    }

    @GetMapping
    public List<PatientProfile> getAllPatientProfiles() {
        return patientProfileService.getAllPatientProfiles();
    }

    @GetMapping("/{id}")
    public PatientProfile getPatientProfileById(@PathVariable int id) {
        return patientProfileService.getPatientProfileById(id);
    }
}