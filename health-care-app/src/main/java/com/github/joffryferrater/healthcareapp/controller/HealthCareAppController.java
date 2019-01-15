package com.github.joffryferrater.healthcareapp.controller;

import com.github.joffryferrater.resource.models.MedicalRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by joffer on 1/8/2019
 */
@RestController
public class HealthCareAppController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HealthCareAppController.class);

    private JmsTemplate jmsTemplate;

    public HealthCareAppController(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @PostMapping("/medical_records")
    @Transactional
    public void createMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        LOGGER.info("Sending notification {}", medicalRecord);
        jmsTemplate.convertAndSend("medical-record-create-queue", medicalRecord);
    }


}
