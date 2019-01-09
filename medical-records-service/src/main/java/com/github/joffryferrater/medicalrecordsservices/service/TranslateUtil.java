package com.github.joffryferrater.medicalrecordsservices.service;

import com.github.joffryferrater.medicalrecordsservices.repository.MedicalRecordEntity;
import com.github.joffryferrater.resource.models.MedicalRecord;

/**
 * Created by joffer on 1/8/2019
 */
public class TranslateUtil {

    private TranslateUtil(){
        // Hide default constructor
    }

    public static MedicalRecordEntity toMedicalRecordEntity(MedicalRecord medicalRecord) {
        MedicalRecordEntity medicalRecordEntity = new MedicalRecordEntity();
        medicalRecordEntity.setPatientName(medicalRecord.getPatient());
        medicalRecordEntity.setOnServiceNotes(medicalRecord.getOnServiceNotes());
        medicalRecordEntity.setDate(medicalRecord.getDate());
        medicalRecordEntity.setDischargeNotes(medicalRecord.getDischargeNotes());
        medicalRecordEntity.setAdmissionNotes(medicalRecord.getAdmissionNotes());
        medicalRecordEntity.setDeliveryNotes(medicalRecord.getDeliveryNotes());
        medicalRecordEntity.setOperativeNotes(medicalRecord.getOperativeNotes());
        medicalRecordEntity.setPostOperativeNotes(medicalRecord.getPostOperativeNotes());
        medicalRecordEntity.setPostpartumNotes(medicalRecord.getPostpartumNotes());
        medicalRecordEntity.setPreOperativeNotes(medicalRecord.getPreOperativeNotes());
        medicalRecordEntity.setProcedureNotes(medicalRecord.getProcedureNotes());
        medicalRecordEntity.setProgressNotes(medicalRecord.getProgressNotes());
        return medicalRecordEntity;
    }

    public static MedicalRecord toMedicalRecord(MedicalRecordEntity record) {
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setId(record.getId());
        medicalRecord.setPatient(record.getPatientName());
        medicalRecord.setAdmissionNotes(record.getAdmissionNotes());
        medicalRecord.setDate(record.getDate());
        medicalRecord.setDeliveryNotes(record.getDeliveryNotes());
        medicalRecord.setDischargeNotes(record.getDischargeNotes());
        medicalRecord.setOnServiceNotes(record.getOnServiceNotes());
        medicalRecord.setOperativeNotes(record.getOperativeNotes());
        medicalRecord.setPostOperativeNotes(record.getPostOperativeNotes());
        medicalRecord.setPostpartumNotes(record.getPostpartumNotes());
        medicalRecord.setPreOperativeNotes(record.getPreOperativeNotes());
        medicalRecord.setProcedureNotes(record.getProcedureNotes());
        medicalRecord.setProgressNotes(record.getProgressNotes());
        return medicalRecord;
    }
}
