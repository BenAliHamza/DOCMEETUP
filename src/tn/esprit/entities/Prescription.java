/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entities;

import java.util.Date;

/**
 *
 * @author HP
 */
public class Prescription {
    private int prescription_id;
    private User doctor;
    private User patient;
    private Date prescription_date;
    private Medication medication;
    private String medication_dose;
    private Analysis analysis;

    public Prescription(int prescription_id, User doctor, User patient, Date prescription_date, Medication medication, String medication_dose, Analysis analysis) {
        this.prescription_id = prescription_id;
        this.doctor = doctor;
        this.patient = patient;
        this.prescription_date = prescription_date;
        this.medication = medication;
        this.medication_dose = medication_dose;
        this.analysis = analysis;
    }

    public Prescription(int prescription_id, User doctor, User patient, Date prescription_date, Analysis analysis) {
        this.prescription_id = prescription_id;
        this.doctor = doctor;
        this.patient = patient;
        this.prescription_date = prescription_date;
        this.analysis = analysis;
    }

    public Prescription(int prescription_id, User doctor, User patient, Date prescription_date, Medication medication, String medication_dose) {
        this.prescription_id = prescription_id;
        this.doctor = doctor;
        this.patient = patient;
        this.prescription_date = prescription_date;
        this.medication = medication;
        this.medication_dose = medication_dose;
    }

    public Prescription(User doctor, User patient, Date prescription_date, Medication medication, String medication_dose, Analysis analysis) {
        this.doctor = doctor;
        this.patient = patient;
        this.prescription_date = prescription_date;
        this.medication = medication;
        this.medication_dose = medication_dose;
        this.analysis = analysis;
    }

    public Prescription(User doctor, User patient, Date prescription_date, Analysis analysis) {
        this.doctor = doctor;
        this.patient = patient;
        this.prescription_date = prescription_date;
        this.analysis = analysis;
    }

    public Prescription(User doctor, User patient, Date prescription_date, Medication medication, String medication_dose) {
        this.doctor = doctor;
        this.patient = patient;
        this.prescription_date = prescription_date;
        this.medication = medication;
        this.medication_dose = medication_dose;
    }

    public int getPrescription_id() {
        return prescription_id;
    }

    public User getDoctor() {
        return doctor;
    }

    public User getPatient() {
        return patient;
    }

    public Date getPrescription_date() {
        return prescription_date;
    }

    public Medication getMedication() {
        return medication;
    }

    public String getMedication_dose() {
        return medication_dose;
    }

    public Analysis getAnalysis() {
        return analysis;
    }

    public void setPrescription_id(int prescription_id) {
        this.prescription_id = prescription_id;
    }

    public void setDoctor(User doctor) {
        this.doctor = doctor;
    }

    public void setPatient(User patient) {
        this.patient = patient;
    }

    public void setPrescription_date(Date prescription_date) {
        this.prescription_date = prescription_date;
    }

    public void setMedication(Medication medication) {
        this.medication = medication;
    }

    public void setMedication_dose(String medication_dose) {
        this.medication_dose = medication_dose;
    }

    public void setAnalysis(Analysis analysis) {
        this.analysis = analysis;
    }

    @Override
    public String toString() {
        return "Prescription{" + "prescription_id=" + prescription_id + ", doctor=" + doctor + ", patient=" + patient + ", prescription_date=" + prescription_date + ", medication=" + medication + ", medication_dose=" + medication_dose + ", analysis=" + analysis + '}';
    }
    
}
