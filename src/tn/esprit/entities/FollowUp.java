/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entities;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author HP
 */
public class FollowUp {
    private int followup_id;
    private User user;
    private Date date;
    private String blood_pressure;
    private int heart_rate;
    private float temperature;
    private float weight;
    

    public FollowUp(int followup_id, User user, Date date, String blood_pressure, int heart_rate, float temperature, float weight ) {
        this.followup_id = followup_id;
        this.user = user;
        this.date = date;
        this.blood_pressure = blood_pressure;
        this.heart_rate = heart_rate;
        this.temperature = temperature;
        this.weight = weight;
        
    }

    public FollowUp(User user, Date date, String blood_pressure, int heart_rate, float temperature, float weight) {
        this.user = user;
        this.date = date;
        this.blood_pressure = blood_pressure;
        this.heart_rate = heart_rate;
        this.temperature = temperature;
        this.weight = weight;
    }

    public FollowUp(  Date date, String blood_pressure, int heart_rate, float temperature, float weight) {
         this.date = date;
        this.blood_pressure = blood_pressure;
        this.heart_rate = heart_rate;
        this.temperature = temperature;
        this.weight = weight;
    }
    public FollowUp() {
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


    public String getBlood_pressure() {
        return blood_pressure;
    }

    public void setBlood_pressure(String blood_pressure) {
        this.blood_pressure = blood_pressure;
    }

    public int getHeart_rate() {
        return heart_rate;
    }

    public void setHeart_rate(int heart_rate) {
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

    @Override
    public String toString() {
        return "FollowUp{" + "followup_id=" + followup_id + ", user=" + user + ", date=" + date + ", blood_pressure=" + blood_pressure + ", heart_rate=" + heart_rate + ", temperature=" + temperature + ", weight=" + weight + '}';
    }

    

    
    
    
}
