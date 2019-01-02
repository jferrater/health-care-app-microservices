package com.github.joffryferrater.medicalrecordsservices.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import com.github.joffryferrater.medicalrecordsservices.domain.MedicalRecord;
import com.github.joffryferrater.medicalrecordsservices.repository.MedicalRecordEntity;
import com.github.joffryferrater.medicalrecordsservices.repository.MedicalRecordRepository;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by joffer on 1/1/2019
 */
@RunWith(SpringRunner.class)
public class MedicalRecordServiceTest {

    @MockBean
    private MedicalRecordRepository mockMedicalRecordRepository;
    @MockBean
    private MedicalRecord mockMedicalRecord;

    private MedicalRecordService target;

    @Before
    public void setUp() {
        target = new MedicalRecordService(mockMedicalRecordRepository);
        mockData();
    }

    private void mockData() {
        MedicalRecordEntity medicalRecordEntity = new MedicalRecordEntity();
        medicalRecordEntity.setId(1L);
        medicalRecordEntity.setPatientName("Alice");
        medicalRecordEntity.setAdmissionNotes("admission notes");
        medicalRecordEntity.setDate(LocalDate.now());
        when(mockMedicalRecordRepository.getMedicalRecordsByPatientName("Alice")).thenReturn(
            Collections.singletonList(medicalRecordEntity));
    }

    @Test
    public void shouldReturnMedicalRecordOfAlice() {
        final List<MedicalRecord> result = target.getPatientMedicalRecords("Alice");

        assertThat(result.size(), is(1));
        assertThat(result.get(0).getAdmissionNotes(), is("admission notes"));
    }

    @Test
    public void shouldReturnMedicalRecordById() {
        MedicalRecordEntity medicalRecordEntity = new MedicalRecordEntity();
        medicalRecordEntity.setId(4L);
        medicalRecordEntity.setPatientName("Peter");
        when(mockMedicalRecordRepository.findById(4L)).thenReturn(Optional.of(medicalRecordEntity));

        final Optional<MedicalRecord> result = target.getMedicalRecordById(4L);

        assertThat(result.isPresent(), is(true));
    }

    @Test
    public void shouldCreateMedicalRecord() {
        MedicalRecordEntity medicalRecordEntity = new MedicalRecordEntity();
        medicalRecordEntity.setId(2L);
        medicalRecordEntity.setPatientName("Bob");
        medicalRecordEntity.setDate(LocalDate.now());
        when(mockMedicalRecordRepository.save(medicalRecordEntity)).thenReturn(medicalRecordEntity);
        when(mockMedicalRecord.translateToMedicalRecordEntity()).thenReturn(medicalRecordEntity);

        final MedicalRecord result = target.createMedicalRecord(mockMedicalRecord);

        assertThat(result, is(notNullValue()));
    }
}