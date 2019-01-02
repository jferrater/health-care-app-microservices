package com.github.joffryferrater.medicalrecordsservices.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by joffer on 1/1/2019
 */
public interface MedicalRecordRepository extends CrudRepository<MedicalRecordEntity, Long> {

    List<MedicalRecordEntity> getMedicalRecordsByPatientName(String patientName);
}
