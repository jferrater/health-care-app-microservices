package com.github.joffryferrater.patientservice.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import com.github.joffryferrater.patientservice.repository.PatientEntity;
import com.github.joffryferrater.patientservice.repository.PatientRepository;
import com.github.joffryferrater.resource.models.Patient;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by joffer on 1/21/2019
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class PatientServiceTest {

    @Autowired
    private PatientRepository patientRepository;

    private PatientService target;

    @Before
    public void setUp() {
        target = new PatientService(patientRepository);
        initialData();
    }

    private void initialData() {
        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setFirstName("Wella");
        patientEntity.setLastName("Opalla");
        patientEntity.setSocialSecurityNumber("2345678");
        patientEntity.setContactNumber("072123456");
        patientRepository.save(patientEntity);
    }

    @Test
    public void shouldFindPatientBySocialSecurityNumber() {
        final Optional<Patient> patientOptional = target.findBySocialSecurityNumber("2345678");
        assertThat(patientOptional.isPresent(), is(true));

        final Patient result = patientOptional.get();
        assertThat(result.getContactNumber(), is("072123456"));
        assertThat(result.getFirstName(), is("Wella"));
        assertThat(result.getLastName(), is("Opalla"));
        assertThat(result.getId(), is(notNullValue()));
    }

    @Test
    public void shouldCreatePatient() {
        final Patient patient = newPatient("Joffry",
            "Ferrater",
            "1234567",
            "19860912-8765");

        final Patient result = target.createPatient(patient);

        assertThat(result.getId(), is(notNullValue()));
    }

    @Test
    public void shouldFindPatientById(){
        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setFirstName("test");
        patientEntity.setSocialSecurityNumber("3642466");
        final PatientEntity save = patientRepository.save(patientEntity);

        final Optional<Patient> result = target.findPatientById(save.getId());

        assertThat(result.isPresent(), is(true));
    }

    private Patient newPatient(String firstName, String lastName, String contactNumber,
        String socialSecurityNumber) {
        Patient patient = new Patient();
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setContactNumber(contactNumber);
        patient.setSocialSecurityNumber(socialSecurityNumber);
        return patient;
    }
}