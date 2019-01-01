package com.github.joffryferrater.medicalrecordsservices.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import com.github.joffryferrater.medicalrecordsservices.MedicalRecordsServicesApplication;
import com.github.joffryferrater.medicalrecordsservices.domain.MedicalRecord;
import com.github.joffryferrater.medicalrecordsservices.repository.MedicalRecordEntity;
import com.github.joffryferrater.medicalrecordsservices.repository.MedicalRecordRepository;
import java.time.LocalDate;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by joffer on 1/1/2019
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MedicalRecordsServicesApplication.class)
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class MedicalRecordServiceTest {

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    private MedicalRecordService target;

    @Before
    public void setUp() {
        target = new MedicalRecordService(medicalRecordRepository);
        createTestData();
    }

    private void createTestData() {
        MedicalRecordEntity entity1 = new MedicalRecordEntity();
        entity1.setId(1L);
        entity1.setPatientName("Alice");
        entity1.setAdmissionNotes("admission notes");
        entity1.setDate(LocalDate.now());
        MedicalRecordEntity entity2 = new MedicalRecordEntity();
        entity2.setId(2L);
        entity2.setPatientName("Bob");
        entity2.setDate(LocalDate.now());
        entity2.setDischargeNotes("discharge notes");
        medicalRecordRepository.save(entity1);
        medicalRecordRepository.save(entity2);
    }

    @Test
    public void shouldReturnMedicalRecordOfAlice() {
        final List<MedicalRecord> result = target.getPatientMedicalRecords("Alice");

        assertThat(result.size(), is(1));
        assertThat(result.get(0).getAdmissionNotes(), is("admission notes"));
    }
}