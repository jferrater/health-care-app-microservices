package com.github.joffryferrater.medicalrecordsservices.service;

import static com.github.joffryferrater.medicalrecordsservices.service.TranslateUtil.toMedicalRecord;
import static com.github.joffryferrater.medicalrecordsservices.service.TranslateUtil.toMedicalRecordEntity;
import static java.util.stream.Collectors.toList;

import com.github.joffryferrater.medicalrecordsservices.repository.MedicalRecordEntity;
import com.github.joffryferrater.medicalrecordsservices.repository.MedicalRecordRepository;
import com.github.joffryferrater.resource.models.MedicalRecord;
import java.util.List;
import java.util.Optional;
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
            .save(toMedicalRecordEntity(medicalRecord));
        return toMedicalRecord(medicalRecordEntity);
    }

    public Optional<MedicalRecord> getMedicalRecordById(Long id) {
        final Optional<MedicalRecordEntity> medicalRecordEntityOptional = medicalRecordRepository.findById(id);
        if (medicalRecordEntityOptional.isPresent()) {
            final MedicalRecordEntity entity = medicalRecordEntityOptional.get();
            return Optional.of(toMedicalRecord(entity));
        }
        return Optional.empty();
    }

    public List<MedicalRecord> getPatientMedicalRecords(String patientName) {
        final List<MedicalRecordEntity> medicalRecordEntities = medicalRecordRepository
            .getMedicalRecordsByPatientName(patientName);
        return medicalRecordEntities.stream().map(TranslateUtil::toMedicalRecord).collect(toList());
    }

}
