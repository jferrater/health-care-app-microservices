package com.github.joffryferrater.patientservice.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.github.joffryferrater.patientservice.PatientServiceApplication;
import com.github.joffryferrater.patientservice.repository.PatientEntity;
import com.github.joffryferrater.patientservice.repository.PatientRepository;
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
 * Created by joffer on 1/21/2019
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    classes = PatientServiceApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class PatientControllerIT {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PatientRepository patientRepository;

    @Before
    public void setUp() {
        initialData();
    }

    @After
    public void tearDown() {
        patientRepository.deleteAll();
    }

    private void initialData() {
        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setFirstName("Bob");
        patientEntity.setLastName("Roger");
        patientEntity.setContactNumber("1234567");
        patientEntity.setSocialSecurityNumber("19720827-0456");
        patientRepository.save(patientEntity);
    }

    @Test
    public void shouldFindPatientBySocialSecurityNumber() throws Exception {
        mockMvc.perform(get("/api/v1/patients?socialSecurityNumber=19720827-0456")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content()
                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.lastName", is("Roger")))
            .andExpect(jsonPath("$.firstName", is("Bob")))
            .andExpect(jsonPath("$.contactNumber", is("1234567")))
            .andExpect(jsonPath("$.socialSecurityNumber", is("19720827-0456")));
    }

    @Test
    public void shouldReturnNotFound() throws Exception {
        mockMvc.perform(get("/api/v1/patients?socialSecurityNumber=not-existing")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }
}