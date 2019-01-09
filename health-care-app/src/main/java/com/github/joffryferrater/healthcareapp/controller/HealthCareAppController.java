package com.github.joffryferrater.healthcareapp.controller;

import com.github.joffryferrater.resource.models.MedicalRecord;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by joffer on 1/8/2019
 */
@RestController
public class HealthCareAppController {

    private JmsTemplate jmsTemplate;

    public HealthCareAppController(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @PostMapping("/medical_records")
    public void send(@RequestBody MedicalRecord medicalRecord) {
        jmsTemplate.convertAndSend("medical-record-create-queue", medicalRecord);
    }
}
