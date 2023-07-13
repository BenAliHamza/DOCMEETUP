/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entities;
 
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
 

public class Calendar {

    public Calendar(Date heure_debut, Date heure_fin, User user) {
        this.heure_debut = heure_debut;
        this.heure_fin = heure_fin;
        this.user = user;
    }

    public Calendar(int CalendarID, Date heure_debut, Date heure_fin) {
        this.CalendarID = CalendarID;
        this.heure_debut = heure_debut;
        this.heure_fin = heure_fin;
    }

    public Calendar(Date heure_debut, Date heure_fin) {
        this.heure_debut = heure_debut;
        this.heure_fin = heure_fin;
    }
 
private int CalendarID;
    private Date heure_debut;
    private Date heure_fin;
    private User user;

    public Calendar(int CalendarID, Date heure_debut, Date heure_fin, User user) {
        this.CalendarID = CalendarID;
        this.heure_debut = heure_debut;
        this.heure_fin = heure_fin;
        this.user = user;
    }

    public int getCalendarID() {
        return CalendarID;
    }

    public Date getHeure_debut() {
        return heure_debut;
    }

    @Override
    public String toString() {
        return "Calendar{" + "CalendarID=" + CalendarID + ", heure_debut=" + heure_debut + ", heure_fin=" + heure_fin + ", user=" + user + '}';
    }

    public Date getHeure_fin() {
        return heure_fin;
    }

    public User getMedecin() {
        return user;
    }

    public void setCalendarID(int CalendarID) {
        this.CalendarID = CalendarID;
    }

    public void setHeure_debut(Date heure_debut) {
        this.heure_debut = heure_debut;
    }

    public void setHeure_fin(Date heure_fin) {
        this.heure_fin = heure_fin;
    }

    public void setMedecin(User medecin) {
        this.user = medecin;
    }
     

    public Calendar() {
    }
     
}