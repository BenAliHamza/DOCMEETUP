/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.Gui;

import com.browniebytes.javafx.control.DateTimePicker;
import java.net.URL;
import javafx.fxml.FXML;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import tn.esprit.entities.Appointment;
import tn.esprit.entities.Calendar;
import tn.esprit.entities.User;
import tn.esprit.services.AppointmentService;
import tn.esprit.services.CalendarService;

/**
 * FXML Controller class
 *
 * @author chiha
 */
public class AppointmentusingcalController implements Initializable {

        @FXML
    private TableView<Calendar> doctv;
    @FXML
    private TableColumn<Calendar, String> endtv;
    @FXML
    private TableColumn<Calendar, String> starttv;
    private TextField calendarIdTextField;
    private TextField medecinIdTextField;
    private TextField patientIdTextField;
    @FXML
    private TextField descriptionTextField;
    private CheckBox statusCheckBox;
    @FXML
    private Button addButton;
    @FXML
    private DateTimePicker dateTimeTextField;
    @FXML
    private TableColumn<Calendar, String>idmed;
    @FXML
    private Button myap;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showAppointments ();
    }    
 
    @FXML
    private void addAppointment(ActionEvent event) {
        
        Calendar calander = new Calendar();
        User doc = new User();
                AppointmentService AppointmentService = new AppointmentService ();

                 Calendar cal  =doctv.getSelectionModel().getSelectedItem();
                 doc = cal.getMedecin();
                 int medecinId = doc.getUser_id();
         int selectedID = doctv.getSelectionModel().getSelectedIndex();
         
        System.out.println(medecinId);
        int calendarId = cal.getCalendarID();
        //User medecinId = cal.getMedecin();
       // int patientId = Integer.parseInt(patientIdTextField.getText());
                int patientId = 1;
 
 
        String description = descriptionTextField.getText();
         Date dateTime = dateTimeTextField.getTime();
         // Create a new Appointment object
        //Appointment appointment = new Appointment(calendarId,status, medecinId, patientId, description, dateTime);
        Appointment appointment = new Appointment(calendarId, medecinId, patientId, description, Boolean.FALSE, dateTime);
        
        // Add the appointment
        AppointmentService.addAppointment(appointment);
        
        // Refresh the table view
        showAppointments();
                clear();
 
        
         
    }
 

    
    
    private void clear (){
         dateTimeTextField.setTime(LocalDateTime.now());
     descriptionTextField.clear();
     }
    
    
    
      public void showAppointments (){
     CalendarService cal = new CalendarService();
     // int x =user.getsessonId();
          ObservableList <Calendar> list = cal.getAllCalendars();
          endtv.setCellValueFactory(new PropertyValueFactory<Calendar,String>("heure_debut"));
        starttv.setCellValueFactory(new PropertyValueFactory<Calendar,String>("heure_fin"));
        idmed.setCellValueFactory(new PropertyValueFactory<Calendar,String>("medecin"));  

        //System.out.print("test");
        doctv.setItems(list);
    }
 
    @FXML
    private void myap(ActionEvent event) {
                          JavaFXApplication1.setScene("AppointmentUI");

    }

}
