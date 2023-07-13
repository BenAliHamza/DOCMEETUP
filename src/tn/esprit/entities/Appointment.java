package tn.esprit.entities;
 import java.util.Date;

 
public class Appointment {
    private int appointmentId;
    private int calendarId;
    private int medecinId;
    private int patientId;
    private String description;
    private Boolean status;
    private Date appointmentDateTime;

    public Appointment(int calendarId, int medecinId, int patientId, String description, Date appointmentDateTime) {
        this.calendarId = calendarId;
        this.medecinId = medecinId;
        this.patientId = patientId;
        this.description = description;
        this.appointmentDateTime = appointmentDateTime;
    }

    public Appointment() {
    }

    public Appointment(int appointmentId, int calendarId, int medecinId, int patientId, String description, Boolean status) {
        this.appointmentId = appointmentId;
        this.calendarId = calendarId;
        this.medecinId = medecinId;
        this.patientId = patientId;
        this.description = description;
        this.status = status;
    }
 

    public Appointment(int calendarId, int medecinId, int patientId, String description, Boolean status, Date appointmentDateTime) {
        this.calendarId = calendarId;
        this.medecinId = medecinId;
        this.patientId = patientId;
        this.description = description;
        this.status = status;
        this.appointmentDateTime = appointmentDateTime;
    }

    public Appointment(int calendarId, int medecinId, int patientId, String description, boolean status, Date dateTime, int appointmentId) {
   this.calendarId = calendarId;
        this.medecinId = medecinId;
        this.patientId = patientId;
        this.description = description;
        this.status = status;
        this.appointmentDateTime = appointmentDateTime; 
    this.appointmentId =    appointmentId;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public int getCalendarId() {
        return calendarId;
    }

    public int getMedecinId() {
        return medecinId;
    }

    public int getPatientId() {
        return patientId;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getStatus() {
        return status;
    }

    public Date getAppointmentDateTime() {
        return appointmentDateTime;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public void setCalendarId(int calendarId) {
        this.calendarId = calendarId;
    }

    public void setMedecinId(int medecinId) {
        this.medecinId = medecinId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setAppointmentDateTime(Date appointmentDateTime) {
        this.appointmentDateTime = appointmentDateTime;
    }

    @Override
    public String toString() {
        return "Appointment{" + "appointmentId=" + appointmentId + ", calendarId=" + calendarId + ", medecinId=" + medecinId + ", patientId=" + patientId + ", description=" + description + ", status=" + status + ", appointmentDateTime=" + appointmentDateTime + '}';
    }

    public Appointment(int appointmentId, int calendarId, int medecinId, int patientId, String description, Boolean status, Date appointmentDateTime) {
        this.appointmentId = appointmentId;
        this.calendarId = calendarId;
        this.medecinId = medecinId;
        this.patientId = patientId;
        this.description = description;
        this.status = status;
        this.appointmentDateTime = appointmentDateTime;
    }
 
    
}
