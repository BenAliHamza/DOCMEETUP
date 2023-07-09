/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package docMeetUp;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.Duration;
import tn.esprit.entities.Calendar;
import tn.esprit.services.AppointmentService;
import tn.esprit.services.CalendarService;

/**
 *
 * @author Hamza
 */
public class DocMeetUp {

    public static void main(String[] args) { 
            CalendarService a = new CalendarService();
                        AppointmentService za = new AppointmentService();
        System.out.println("azedazdazdz//////////");
       
            // System.out.println(  a.getCalendar(1));
                     //     System.out.println(  a.getAllCalendars());

                      System.out.println(  za.getAllAppointments());

    }

}