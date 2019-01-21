package com.github.joffryferrater.patientservice.controller;

import com.github.joffryferrater.patientservice.service.PatientService;
import com.github.joffryferrater.resource.models.Patient;
import java.net.URI;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

    @GetMapping("patients/{id}")
    public Patient getPatientBySocialSecurityNumber(@PathVariable("id") Long id) {
        final Optional<Patient> patientOptional = patientService.findPatientById(id);
        if (patientOptional.isPresent()) {
            return patientOptional.get();
        }
        throw new PatientNotFoundException(
            "The patient with social security number " + id + " does not exist");
    }

    @PostMapping("patients")
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        final Patient result = patientService.createPatient(patient);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(result.getId())
            .toUri();
        return ResponseEntity.created(location).body(result);
    }
}
