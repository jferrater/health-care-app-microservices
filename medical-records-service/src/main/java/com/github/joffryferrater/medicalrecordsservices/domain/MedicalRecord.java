package com.github.joffryferrater.medicalrecordsservices.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.joffryferrater.medicalrecordsservices.repository.MedicalRecordEntity;
import java.time.LocalDate;

/**
 * Created by joffer on 1/1/2019
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MedicalRecord {

    @JsonProperty("id")
    private long id;
    @JsonProperty("patient")
    private String patient;
    @JsonProperty("date")
    private LocalDate date;
    @JsonProperty("admissionNotes")
    private String admissionNotes;
    @JsonProperty("onServiceNotes")
    private String onServiceNotes;
    @JsonProperty("progressNotes")
    private String progressNotes;
    @JsonProperty("preOperativeNotes")
    private String preOperativeNotes;
    @JsonProperty("operativeNotes")
    private String operativeNotes;
    @JsonProperty("postOperativeNotes")
    private String postOperativeNotes;
    @JsonProperty("procedureNotes")
    private String procedureNotes;
    @JsonProperty("deliveryNotes")
    private String deliveryNotes;
    @JsonProperty("postpartumNotes")
    private String postpartumNotes;
    @JsonProperty("dischargeNotes")
    private String dischargeNotes;

    public MedicalRecord() {
        //Empty constructor for Jackson
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
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

    public MedicalRecordEntity translateToMedicalRecordEntity() {
        MedicalRecordEntity medicalRecordEntity = new MedicalRecordEntity();
        medicalRecordEntity.setPatientName(this.patient);
        medicalRecordEntity.setOnServiceNotes(this.onServiceNotes);
        medicalRecordEntity.setDate(this.date);
        medicalRecordEntity.setDischargeNotes(this.dischargeNotes);
        medicalRecordEntity.setAdmissionNotes(this.admissionNotes);
        medicalRecordEntity.setDeliveryNotes(this.deliveryNotes);
        medicalRecordEntity.setOperativeNotes(this.operativeNotes);
        medicalRecordEntity.setPostOperativeNotes(this.postOperativeNotes);
        medicalRecordEntity.setPostpartumNotes(this.postpartumNotes);
        medicalRecordEntity.setPreOperativeNotes(this.preOperativeNotes);
        medicalRecordEntity.setProcedureNotes(this.procedureNotes);
        medicalRecordEntity.setProgressNotes(this.progressNotes);
        return medicalRecordEntity;
    }

    public static MedicalRecord translateToMedicalRecord(MedicalRecordEntity record) {
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setId(record.getId());
        medicalRecord.setPatient(record.getPatientName());
        medicalRecord.setAdmissionNotes(record.getAdmissionNotes());
        medicalRecord.setDate(record.getDate());
        medicalRecord.setDeliveryNotes(record.getDeliveryNotes());
        medicalRecord.setDischargeNotes(record.getDischargeNotes());
        medicalRecord.setOnServiceNotes(record.getOnServiceNotes());
        medicalRecord.setOperativeNotes(record.getOperativeNotes());
        medicalRecord.setPostOperativeNotes(record.getPostOperativeNotes());
        medicalRecord.setPostpartumNotes(record.getPostpartumNotes());
        medicalRecord.setPreOperativeNotes(record.getPreOperativeNotes());
        medicalRecord.setProcedureNotes(record.getProcedureNotes());
        medicalRecord.setProgressNotes(record.getProgressNotes());
        return medicalRecord;
    }
}
