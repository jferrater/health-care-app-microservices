package com.github.joffryferrater.medicalrecordsservices.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import com.github.joffryferrater.medicalrecordsservices.repository.MedicalRecordEntity;
import com.github.joffryferrater.medicalrecordsservices.repository.MedicalRecordRepository;
import com.github.joffryferrater.resource.models.MedicalRecord;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by joffer on 1/1/2019
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class MedicalRecordServiceTest {

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    private MedicalRecordService target;

    @Before
    public void setUp() {
        target = new MedicalRecordService(medicalRecordRepository);
        testData();
    }

    private void testData() {
        MedicalRecordEntity medicalRecordEntity = new MedicalRecordEntity();
        medicalRecordEntity.setPatientName("Alice");
        medicalRecordEntity.setAdmissionNotes("admission notes");
        medicalRecordEntity.setDate(new Date());
        medicalRecordRepository.save(medicalRecordEntity);
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
        medicalRecordEntity.setPatientName("Peter");
        medicalRecordRepository.save(medicalRecordEntity);

        final Optional<MedicalRecord> result = target.getMedicalRecordById(1L);

        assertThat(result.isPresent(), is(true));
    }

    @Test
    public void shouldCreateMedicalRecord() {
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setPatient("Bob");

        final MedicalRecord result = target.createMedicalRecord(medicalRecord);

        assertThat(result, is(notNullValue()));
    }

}