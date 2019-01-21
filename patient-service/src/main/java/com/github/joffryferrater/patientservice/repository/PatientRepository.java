package com.github.joffryferrater.patientservice.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by joffer on 1/21/2019
 */
public interface PatientRepository extends CrudRepository<PatientEntity, Long> {

    Optional<PatientEntity> findBySocialSecurityNumber(String socialSecurityNumber);
}
