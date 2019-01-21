package com.github.joffryferrater.patientservice.service;

import static com.github.joffryferrater.patientservice.service.TranslateUtil.toPatient;
import static com.github.joffryferrater.patientservice.service.TranslateUtil.toPatientEntity;

import com.github.joffryferrater.patientservice.repository.PatientEntity;
import com.github.joffryferrater.patientservice.repository.PatientRepository;
import com.github.joffryferrater.resource.models.Patient;
import org.springframework.stereotype.Service;

/**
 * Created by joffer on 1/21/2019
 */
@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient createPatient(Patient patient) {
        final PatientEntity patientEntity = patientRepository.save(toPatientEntity(patient));
        return toPatient(patientEntity);
    }

    public Patient findBySocialSecurityNumber(String socialSecurityNumber) {
        final PatientEntity patientEntity = patientRepository.findBySocialSecurityNumber(socialSecurityNumber);
        return toPatient(patientEntity);
    }
}
