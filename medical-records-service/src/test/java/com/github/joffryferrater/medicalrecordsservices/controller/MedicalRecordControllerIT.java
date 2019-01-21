package com.github.joffryferrater.medicalrecordsservices.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.joffryferrater.medicalrecordsservices.MedicalRecordsServicesApplication;
import com.github.joffryferrater.medicalrecordsservices.repository.MedicalRecordEntity;
import com.github.joffryferrater.medicalrecordsservices.repository.MedicalRecordRepository;
import com.github.joffryferrater.resource.models.MedicalRecord;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Created by joffer on 1/1/2019
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    classes = MedicalRecordsServicesApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class MedicalRecordControllerIT {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Before
    public void setUp() {
        createTestData();
    }

    private void createTestData() {
        MedicalRecordEntity medicalRecordEntity = new MedicalRecordEntity();
        medicalRecordEntity.setPatientName("Kevin");
        medicalRecordEntity.setAdmissionNotes("admission notes");
        medicalRecordEntity.setOnServiceNotes("on service notes");
        medicalRecordEntity.setDate(new Date());
        medicalRecordRepository.save(medicalRecordEntity);
    }

    @Test
    public void shouldReturnStatusOk() throws Exception {
        mockMvc.perform(get("/api/v1/medical_records?patient=Kevin")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content()
                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[0].patient", is("Kevin")));
    }

    @Test
    public void shouldReturnStatusCreated() throws Exception {
        final MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setPatient("Peter");
        medicalRecord.setPostOperativeNotes("postOperativeNotes");
        mockMvc.perform(post("/api/v1/medical_records")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsBytes(medicalRecord)))
            .andExpect(status().isCreated())
            .andExpect(content()
                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.postOperativeNotes", is("postOperativeNotes")))
            .andExpect(jsonPath("$.patient", is("Peter")));
    }

    @Test
    public void shouldReturnStatusOkForMedicalRecordIdPath() throws Exception {
        mockMvc.perform(get("/api/v1/medical_records/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content()
                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.patient", is("Tony")));
    }

}