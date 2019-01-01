package com.github.joffryferrater.medicalrecordsservices.service;

import static com.github.joffryferrater.medicalrecordsservices.domain.MedicalRecord.translateToMedicalRecord;
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

    public MedicalRecord createMedicalRecord(MedicalRecord medicalRecord) {
        final MedicalRecordEntity medicalRecordEntity = medicalRecordRepository
            .save(medicalRecord.translateToMedicalRecordEntity());
        return translateToMedicalRecord(medicalRecordEntity);
    }

    public List<MedicalRecord> getPatientMedicalRecords(String patientName) {
        final List<MedicalRecordEntity> medicalRecordEntities = medicalRecordRepository
            .getMedicalRecordsByPatientName(patientName);
        return medicalRecordEntities.stream().map(MedicalRecord::translateToMedicalRecord).collect(toList());
    }

}
