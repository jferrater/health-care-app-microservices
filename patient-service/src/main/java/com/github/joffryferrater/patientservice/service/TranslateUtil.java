package com.github.joffryferrater.patientservice.service;

import com.github.joffryferrater.patientservice.repository.PatientEntity;
import com.github.joffryferrater.resource.models.Patient;

/**
 * Created by joffer on 1/21/2019
 */
public class TranslateUtil {

    static Patient toPatient(PatientEntity patientEntity) {
        Patient patient = new Patient();
        patient.setId(patientEntity.getId());
        patient.setContactNumber(patientEntity.getContactNumber());
        patient.setFirstName(patientEntity.getFirstName());
        patient.setLastName(patientEntity.getLastName());
        patient.setSocialSecurityNumber(patientEntity.getSocialSecurityNumber());
        return patient;
    }

    static PatientEntity toPatientEntity(Patient patient) {
        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setSocialSecurityNumber(patient.getSocialSecurityNumber());
        patientEntity.setLastName(patient.getLastName());
        patientEntity.setFirstName(patient.getFirstName());
        patientEntity.setContactNumber(patient.getContactNumber());
        patientEntity.setId(patient.getId());
        return patientEntity;
    }
}
