package com.github.joffryferrater.patientservice.controller;

import com.github.joffryferrater.patientservice.service.PatientService;
import com.github.joffryferrater.resource.models.Patient;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by joffer on 1/21/2019
 */
@RestController
@RequestMapping("/api/v1")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("patients")
    public Patient getPatientBySocialSecurityNumber(@RequestParam("socialSecurityNumber") String socialSecurityNumber) {
        final Optional<Patient> patientOptional = patientService
            .findBySocialSecurityNumber(socialSecurityNumber);
        if (patientOptional.isPresent()) {
            return patientOptional.get();
        }
        throw new PatientNotFoundException(
            "The patient with social security number " + socialSecurityNumber + " does not exist");
    }
}
