package com.github.joffryferrater.medicalrecordsservices.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;

import com.github.joffryferrater.medicalrecordsservices.MedicalRecordsServicesApplication;
import com.github.joffryferrater.medicalrecordsservices.repository.MedicalRecordEntity;
import com.github.joffryferrater.medicalrecordsservices.repository.MedicalRecordRepository;
import java.time.LocalDate;
import org.junit.After;
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
public class MedicalRecordControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Before
    public void setUp() {
        createTestData();
    }

    @After
    public void tearDown() {
        medicalRecordRepository.deleteAll();
    }

    private void createTestData() {
        MedicalRecordEntity medicalRecordEntity = new MedicalRecordEntity();
        medicalRecordEntity.setId(3L);
        medicalRecordEntity.setPatientName("Kevin");
        medicalRecordEntity.setAdmissionNotes("admission notes");
        medicalRecordEntity.setOnServiceNotes("on service notes");
        medicalRecordEntity.setDate(LocalDate.now());
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
}