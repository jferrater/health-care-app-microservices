package com.github.joffryferrater.medicalrecordsservices.jms;

import com.github.joffryferrater.medicalrecordsservices.service.MedicalRecordService;
import com.github.joffryferrater.resource.models.MedicalRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * Created by joffer on 1/3/2019
 */
@Component
public class MedicalRecordReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(MedicalRecordReceiver.class);

    private final MedicalRecordService medicalRecordService;

    public MedicalRecordReceiver(
        MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }

    @JmsListener(destination = "medical-record-create-queue")
    public void receiveMedicalRecordMessage(@Payload MedicalRecord medicalRecord) {
        LOGGER.info("Received message {}", medicalRecord);
        medicalRecordService.createMedicalRecord(medicalRecord);
    }


}

