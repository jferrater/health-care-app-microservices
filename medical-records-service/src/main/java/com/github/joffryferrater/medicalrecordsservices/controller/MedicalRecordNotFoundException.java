package com.github.joffryferrater.medicalrecordsservices.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by joffer on 1/2/2019
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class MedicalRecordNotFoundException extends RuntimeException {

    public MedicalRecordNotFoundException(String message) {
        super(message);
    }
}
