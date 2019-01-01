package com.github.joffryferrater.medicalrecordsservices.controller;


import com.github.joffryferrater.medicalrecordsservices.domain.MedicalRecord;
import com.github.joffryferrater.medicalrecordsservices.service.MedicalRecordService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by joffer on 1/1/2019
 */
@RestController
@RequestMapping("/api/v1")
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    public MedicalRecordController(
        MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }

    @GetMapping("/medical_records")
    public List<MedicalRecord> getPatientMedicalRecords(@RequestParam("patient") String patientName) {
        return medicalRecordService.getPatientMedicalRecords(patientName);
    }
}
