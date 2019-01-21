package com.github.joffryferrater.patientservice.service;

import static com.github.joffryferrater.patientservice.service.TranslateUtil.toPatient;
import static com.github.joffryferrater.patientservice.service.TranslateUtil.toPatientEntity;

import com.github.joffryferrater.patientservice.repository.PatientEntity;
import com.github.joffryferrater.patientservice.repository.PatientRepository;
import com.github.joffryferrater.resource.models.Patient;
import java.util.Optional;
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

    public Optional<Patient> findBySocialSecurityNumber(String socialSecurityNumber) {
        final Optional<PatientEntity> patientEntity = patientRepository.findBySocialSecurityNumber(socialSecurityNumber);
        return patientEntity.map(TranslateUtil::toPatient);
    }

    public Optional<Patient> findPatientById(Long id) {
        final Optional<PatientEntity> patientEntityOptional = patientRepository.findById(id);
        return patientEntityOptional.map(TranslateUtil::toPatient);
    }
}
