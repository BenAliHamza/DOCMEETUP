/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entities;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;



/**
 *
 * @author DRIDI Oussama
 */
public class Evenement {
    private int event_id;
        private User organizer_id;
    private String event_name;
     LocalDate event_date;
     LocalTime event_time;
   
   

    public Evenement(int event_id, User organizer_id, String event_name, LocalDate event_date, LocalTime event_time) {
        this.event_id = event_id;
        this.organizer_id = organizer_id;
        this.event_name = event_name;
        this.event_date = event_date;
        this.event_time = event_time;
    }



    public Evenement(int event_id) {
        this.event_id = event_id;
    }

    public Evenement() {
    }

    public Evenement(int organizer_id, String Event_name, String Event_date, String Event_time) {
      
    }

    public Evenement(String event_name, LocalDate event_date, LocalTime event_time) {
        this.event_name = event_name;
        this.event_date = event_date;
        this.event_time = event_time;
    }

    public Evenement(User Organizer, String Event_name, LocalDate Event_date, LocalTime Event_time) {
         this.organizer_id = Organizer;
        this.event_name = Event_name;
        this.event_date = Event_date;
        this.event_time= Event_time;   }

    public Evenement(int i, User u, String qwerty, Date d, Time t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Evenement(String oussama, String workshop, Date d, Time t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Evenement(String workshop, Date d, Time t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public User getOrganizer() {
        return organizer_id;
    }

    public void setOrganizer(User organizer_id) {
        this.organizer_id = organizer_id;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public LocalDate getEvent_date() {
        return event_date;
    }

    public void setEvent_date(LocalDate event_date) {
        this.event_date = event_date;
    }

    public LocalTime getEvent_time() {
        return event_time;
    }

    public void setEvent_time(LocalTime event_time) {
        this.event_time = event_time;
    }

    @Override
    public String toString() {
        return "Evenement{" + "event_id=" + event_id + ", organizer_id=" + organizer_id.getUser_id()+ ", event_name=" + event_name + ", event_date=" + event_date + ", event_time=" + event_time + '}';
    }

    public void setUser_id(User ca) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setEvent_date(LocalTime eventDate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
    
    
}
