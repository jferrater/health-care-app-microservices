package com.github.joffryferrater.medicalrecordsservices.controller;


import com.github.joffryferrater.medicalrecordsservices.service.MedicalRecordService;
import com.github.joffryferrater.resource.models.MedicalRecord;
import java.net.URI;
import java.util.List;
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

    @GetMapping("/medical_records/{id}")
    public MedicalRecord getMedicalRecordById(@PathVariable("id") Long id) {
        final Optional<MedicalRecord> medicalRecordOptional = medicalRecordService.getMedicalRecordById(id);
        if (medicalRecordOptional.isPresent()) {
            return medicalRecordOptional.get();
        }
        throw new MedicalRecordNotFoundException("Medical Record not found with id: " + id);
    }

    @PostMapping("medical_records")
    public ResponseEntity<MedicalRecord> createMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        final MedicalRecord result = medicalRecordService.createMedicalRecord(medicalRecord);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(result.getId())
            .toUri();
        return ResponseEntity.created(location).body(result);
    }
}
