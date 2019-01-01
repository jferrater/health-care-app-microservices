package com.github.joffryferrater.medicalrecordsservices.repository;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by joffer on 1/1/2019
 */
@Entity
@Table(name = "MEDICAL_RECORDS")
public class MedicalRecordEntity {

    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "PATIENT_NAME", nullable = false, unique = true)
    private String patientName;
    @Column(name = "DATE")
    private LocalDate date;
    @Column(name = "ADMISSION_NOTES")
    private String admissionNotes;
    @Column(name = "ONSERVICE_NOTES")
    private String onServiceNotes;
    @Column(name = "PROGRESS_NOTES")
    private String progressNotes;
    @Column(name = "PREOPERATIVE_NOTES")
    private String preOperativeNotes;
    @Column(name = "OPERATIVE_NOTES")
    private String operativeNotes;
    @Column(name = "POSTOPERATIVE_NOTES")
    private String postOperativeNotes;
    @Column(name = "PROCEDURE_NOTES")
    private String procedureNotes;
    @Column(name = "DELIVERY_NOTES")
    private String deliveryNotes;
    @Column(name = "POSTPARTUM_NOTES")
    private String postpartumNotes;
    @Column(name = "DISCHARGE_NOTES")
    private String dischargeNotes;

    public MedicalRecordEntity() {
        //Empty constructor for JPA
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getAdmissionNotes() {
        return admissionNotes;
    }

    public void setAdmissionNotes(String admissionNotes) {
        this.admissionNotes = admissionNotes;
    }

    public String getOnServiceNotes() {
        return onServiceNotes;
    }

    public void setOnServiceNotes(String onServiceNotes) {
        this.onServiceNotes = onServiceNotes;
    }

    public String getProgressNotes() {
        return progressNotes;
    }

    public void setProgressNotes(String progressNotes) {
        this.progressNotes = progressNotes;
    }

    public String getPreOperativeNotes() {
        return preOperativeNotes;
    }

    public void setPreOperativeNotes(String preOperativeNotes) {
        this.preOperativeNotes = preOperativeNotes;
    }

    public String getOperativeNotes() {
        return operativeNotes;
    }

    public void setOperativeNotes(String operativeNotes) {
        this.operativeNotes = operativeNotes;
    }

    public String getPostOperativeNotes() {
        return postOperativeNotes;
    }

    public void setPostOperativeNotes(String postOperativeNotes) {
        this.postOperativeNotes = postOperativeNotes;
    }

    public String getProcedureNotes() {
        return procedureNotes;
    }

    public void setProcedureNotes(String procedureNotes) {
        this.procedureNotes = procedureNotes;
    }

    public String getDeliveryNotes() {
        return deliveryNotes;
    }

    public void setDeliveryNotes(String deliveryNotes) {
        this.deliveryNotes = deliveryNotes;
    }

    public String getPostpartumNotes() {
        return postpartumNotes;
    }

    public void setPostpartumNotes(String postpartumNotes) {
        this.postpartumNotes = postpartumNotes;
    }

    public String getDischargeNotes() {
        return dischargeNotes;
    }

    public void setDischargeNotes(String dischargeNotes) {
        this.dischargeNotes = dischargeNotes;
    }
}
