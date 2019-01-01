package com.github.joffryferrater.medicalrecordsservices.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import com.github.joffryferrater.medicalrecordsservices.domain.MedicalRecord;
import com.github.joffryferrater.medicalrecordsservices.repository.MedicalRecordEntity;
import com.github.joffryferrater.medicalrecordsservices.repository.MedicalRecordRepository;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by joffer on 1/1/2019
 */
@RunWith(SpringRunner.class)
public class MedicalRecordServiceTest {

    @MockBean
    private MedicalRecordRepository medicalRecordRepository;

    private MedicalRecordService target;

    @Before
    public void setUp() {
        target = new MedicalRecordService(medicalRecordRepository);
        mockData();
    }

    private void mockData() {
        MedicalRecordEntity medicalRecordEntity = new MedicalRecordEntity();
        medicalRecordEntity.setId(1L);
        medicalRecordEntity.setPatientName("Alice");
        medicalRecordEntity.setAdmissionNotes("admission notes");
        medicalRecordEntity.setDate(LocalDate.now());
        Mockito.when(medicalRecordRepository.getMedicalRecordsByPatientName("Alice")).thenReturn(
            Collections.singletonList(medicalRecordEntity));
    }

    @Test
    public void shouldReturnMedicalRecordOfAlice() {
        final List<MedicalRecord> result = target.getPatientMedicalRecords("Alice");

        assertThat(result.size(), is(1));
        assertThat(result.get(0).getAdmissionNotes(), is("admission notes"));
    }
}