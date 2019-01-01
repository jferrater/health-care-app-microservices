package com.github.joffryferrater.medicalrecordsservices.service;

import static java.util.stream.Collectors.toList;

import com.github.joffryferrater.medicalrecordsservices.domain.MedicalRecord;
import com.github.joffryferrater.medicalrecordsservices.repository.MedicalRecordEntity;
import com.github.joffryferrater.medicalrecordsservices.repository.MedicalRecordRepository;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Created by joffer on 1/1/2019
 */
@Service
public class MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;

    public MedicalRecordService(
        MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }

    public List<MedicalRecord> getPatientMedicalRecords(String patientName) {
        final List<MedicalRecordEntity> medicalRecordEntities = medicalRecordRepository
            .getMedicalRecordsByPatientName(patientName);
        return medicalRecordEntities.stream().map(this::toMedicalRecord).collect(toList());
    }

    private MedicalRecord toMedicalRecord(MedicalRecordEntity record) {
        MedicalRecord medicalRecord = new MedicalRecord();
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
