/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entities;

import java.sql.Time;
import java.sql.Date;

/**
 *
 * @author HP
 */
public class FollowUp {
    private int followup_id;
    private User user;
    private Date date;
    private Time time;
    private String blood_pressure;
    private String heart_rate;
    private float temperature;
    private float weight;
    private String symptoms;
    private String additional_notes;
    private String diagnosis;
    private String prescribed_medication;
    private String recommended_tests;
    private Date next_appointment_date;

    public FollowUp(int followup_id, User user, Date date, Time time, String blood_pressure, String heart_rate, float temperature, float weight, String symptoms, String additional_notes, String diagnosis, String prescribed_medication, String recommended_tests, Date next_appointment_date) {
        this.followup_id = followup_id;
        this.user = user;
        this.date = date;
        this.time = time;
        this.blood_pressure = blood_pressure;
        this.heart_rate = heart_rate;
        this.temperature = temperature;
        this.weight = weight;
        this.symptoms = symptoms;
        this.additional_notes = additional_notes;
        this.diagnosis = diagnosis;
        this.prescribed_medication = prescribed_medication;
        this.recommended_tests = recommended_tests;
        this.next_appointment_date = next_appointment_date;
    }

    public FollowUp(User user, Date date, Time time, String blood_pressure, String heart_rate, float temperature, float weight, String symptoms, String additional_notes, String diagnosis, String prescribed_medication, String recommended_tests, Date next_appointment_date) {
        this.user = user;
        this.date = date;
        this.time = time;
        this.blood_pressure = blood_pressure;
        this.heart_rate = heart_rate;
        this.temperature = temperature;
        this.weight = weight;
        this.symptoms = symptoms;
        this.additional_notes = additional_notes;
        this.diagnosis = diagnosis;
        this.prescribed_medication = prescribed_medication;
        this.recommended_tests = recommended_tests;
        this.next_appointment_date = next_appointment_date;
    }

    public int getFollowup_id() {
        return followup_id;
    }

    public void setFollowup_id(int followup_id) {
        this.followup_id = followup_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getBlood_pressure() {
        return blood_pressure;
    }

    public void setBlood_pressure(String blood_pressure) {
        this.blood_pressure = blood_pressure;
    }

    public String getHeart_rate() {
        return heart_rate;
    }

    public void setHeart_rate(String heart_rate) {
        this.heart_rate = heart_rate;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getAdditional_notes() {
        return additional_notes;
    }

    public void setAdditional_notes(String additional_notes) {
        this.additional_notes = additional_notes;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getPrescribed_medication() {
        return prescribed_medication;
    }

    public void setPrescribed_medication(String prescribed_medication) {
        this.prescribed_medication = prescribed_medication;
    }

    public String getRecommended_tests() {
        return recommended_tests;
    }

    public void setRecommended_tests(String recommended_tests) {
        this.recommended_tests = recommended_tests;
    }

    public Date getNext_appointment_date() {
        return next_appointment_date;
    }

    public void setNext_appointment_date(Date next_appointment_date) {
        this.next_appointment_date = next_appointment_date;
    }

    @Override
    public String toString() {
        return "FollowUp{" + "followup_id=" + followup_id + ", user=" + user + ", date=" + date + ", time=" + time + ", blood_pressure=" + blood_pressure + ", heart_rate=" + heart_rate + ", temperature=" + temperature + ", weight=" + weight + ", symptoms=" + symptoms + ", additional_notes=" + additional_notes + ", diagnosis=" + diagnosis + ", prescribed_medication=" + prescribed_medication + ", recommended_tests=" + recommended_tests + ", next_appointment_date=" + next_appointment_date + '}';
    }

    
    
    
}
