/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entities;

/**
 *
 * @author DRIDI Oussama
 */
public class EvenementAttendance {
    private Evenement event_id;
    private User attendee_id;

    public EvenementAttendance() {
    }

    public EvenementAttendance(User attendee_id) {
        this.attendee_id = attendee_id;
    }

    public EvenementAttendance(Evenement event_id, User attendee_id) {
        this.event_id = event_id;
        this.attendee_id = attendee_id;
    }

    public Evenement getEvent_id() {
        return event_id;
    }

    public void setEvent_id(Evenement event_id) {
        this.event_id = event_id;
    }

    public User getAttendee_id() {
        return attendee_id;
    }

    public void setAttendee_id(User attendee_id) {
        this.attendee_id = attendee_id;
    }

    @Override
    public String toString() {
        return "EvenementAttendance{" + "event_id=" + event_id + ", attendee_id=" + attendee_id + '}';
    }

   
    
    
}
