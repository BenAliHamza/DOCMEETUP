/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entities;

import java.sql.Date;
import java.sql.Time;


/**
 *
 * @author Hamza
 */
public class Consulation {
    private int consultation_id , doctor_id ,patient_id    ; 
    private Boolean isPayed    ,isPrescription  ; 
    private double price ; 
    private  Date  date ;
    private  Time time ; 
    private  String rapport;  
    
    
    public Consulation(int doctor_id, int patient_id, double price, Date date, Time time) {;
        this.doctor_id = doctor_id;
        this.patient_id = patient_id;
        this.price =  price;
        this.date = date;
        this.time = time;
        this.isPayed = false ; 
        this.isPrescription = false ; 
    }

    public Consulation(int consultation_id, int doctor_id, int patient_id, Boolean isPayed, Boolean isPrescription, double price, Date date, Time time , String rapport) {
        this.consultation_id = consultation_id;
        this.doctor_id = doctor_id;
        this.patient_id = patient_id;
        this.isPayed = isPayed;
        this.isPrescription = isPrescription;
        this.price = price;
        this.date = date;
        this.time = time;
        this.rapport =rapport;
    }


    

    public int getConsultation_id() {
        return consultation_id;
    }

    public void setConsultation_id(int consultation_id) {
        this.consultation_id = consultation_id;
    }

    public String getRapport() {
        return rapport;
    }

    public void setRapport(String rapport) {
        this.rapport = rapport;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public Boolean getIsPayed() {
        return isPayed;
    }

    public void setIsPayed(Boolean isPayed) {
        this.isPayed = isPayed;
    }

    public Boolean getIsPrescription() {
        return isPrescription;
    }

    public void setIsPrescription(Boolean isPrescription) {
        this.isPrescription = isPrescription;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice( double  price) {
        this.price = price;
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

    @Override
    public String toString() {
        return "Consulation{" + "consultation_id=" + consultation_id + ", doctor_id=" + doctor_id + ", patient_id=" + patient_id + ", isPayed=" + isPayed + ", isPrescription=" + isPrescription + ", price=" + price + ", date=" + date + ", time=" + time + '}';
    }
    public String getInformationToDisplay(){
        return "Consulation : Mr/Mme " + patient_id + "  le " + date.toString();
    }


   
    
    
}
