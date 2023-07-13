

package tn.esprit.gui.consultations.controllers;

import com.browniebytes.javafx.control.DateTimePicker;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tn.esprit.entities.Appointment;
import tn.esprit.services.AppointmentService;


public class AppointmentUIController {
    
    @FXML
    private TableView<Appointment> appointmentTableView;
    
    
    
    @FXML
    private TextField calendarIdTextField;
    
    @FXML
    private TextField medecinIdTextField;
    
    @FXML
    private TextField patientIdTextField;
    
    @FXML
    private TextField descriptionTextField;
    
    @FXML
    private CheckBox statusCheckBox;
    
    @FXML
    private DateTimePicker dateTimeTextField;
    
    
    @FXML
    private Button updateButton;
    
    @FXML
    private Button deleteButton;
    
    private AppointmentService appointmentService;
    @FXML
    private TableColumn<Appointment, String> descriptiontv;
    @FXML
    private TableColumn<Appointment, Boolean> statustv;
    @FXML
    private TableColumn<Appointment, String> datetimetv;
    @FXML
    private Button addButton;
    @FXML
    private AnchorPane anchor;
    
    public void initialize() {
        appointmentService = new AppointmentService();
        showAppointments();
     }
    
    private void showAppointments() {
        int medecinId = 1; // Assuming a static medecinId
    ObservableList<Appointment> list = appointmentService.getAllAppointments();
    appointmentTableView.setItems(list);

     descriptiontv.setCellValueFactory(new PropertyValueFactory<>("description"));
    statustv.setCellValueFactory(new PropertyValueFactory<>("status"));
    datetimetv.setCellValueFactory(new PropertyValueFactory<>("appointmentDateTime"));
}
    
    @FXML
    private void addAppointment() {
        int calendarId = Integer.parseInt(calendarIdTextField.getText());
        int medecinId = Integer.parseInt(medecinIdTextField.getText());
        int patientId = Integer.parseInt(patientIdTextField.getText());
        String description = descriptionTextField.getText();
        boolean status = statusCheckBox.isSelected();
        Date dateTime = dateTimeTextField.getTime();
        System.out.println(dateTime);
        // Create a new Appointment object
        Appointment appointment = new Appointment(calendarId, medecinId, patientId, description, status, dateTime);
        
        
        // Add the appointment
        appointmentService.addAppointment(appointment);
        
        // Refresh the table view
        showAppointments();
                clear();

    }
    
    @FXML
    private void updateAppointment() {
        Appointment selectedAppointment = appointmentTableView.getSelectionModel().getSelectedItem();
             int appointmentId = selectedAppointment.getAppointmentId();
            int calendarId = Integer.parseInt(calendarIdTextField.getText());
        int medecinId = Integer.parseInt(medecinIdTextField.getText());
        int patientId = Integer.parseInt(patientIdTextField.getText());
        String description = descriptionTextField.getText();
        boolean status = statusCheckBox.isSelected();
        Date dateTime = dateTimeTextField.getTime();
        System.out.println(dateTime);
        // Create a new Appointment object
        Appointment appointment = new Appointment(appointmentId,calendarId, medecinId, patientId, description, status, dateTime);
        
        
        // Add the appointment
        appointmentService.updateAppointment(appointment);
        
        // Refresh the table view
        showAppointments();
        clear();
         
    }
    
    @FXML
    private void deleteAppointment() {
        Appointment selectedAppointment = appointmentTableView.getSelectionModel().getSelectedItem();
        if (selectedAppointment != null) {
            int appointmentId = selectedAppointment.getAppointmentId();
            
            // Delete the selected appointment
            appointmentService.deleteAppointment(appointmentId);
            
            // Refresh the table view
            showAppointments();
        }
    }

    @FXML
    private void row(MouseEvent event) {
          Appointment ap  =appointmentTableView.getSelectionModel().getSelectedItem();
        System.out.println(ap.getAppointmentId());
        
         dateTimeTextField.setTime(ap.getAppointmentDateTime());
        calendarIdTextField.setText(String.valueOf(ap.getCalendarId()));
        descriptionTextField.setText(ap.getDescription());
        medecinIdTextField.setText(String.valueOf(ap.getMedecinId()));
        patientIdTextField.setText(String.valueOf(ap.getPatientId()));
        statusCheckBox.setSelected(ap.getStatus());
     
    }
    private void clear (){
         dateTimeTextField.setTime(LocalDateTime.now());
    calendarIdTextField.clear();
    descriptionTextField.clear();
    medecinIdTextField.clear();
    patientIdTextField.clear();
    statusCheckBox.setSelected(false);
    }
    
    
    @FXML
    private void myap(ActionEvent event) {
                       try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/HomePage.fxml"));
        Parent root = loader.load();
        HomePageController ac = loader.getController();
        Stage stage = (Stage) anchor.getScene().getWindow(); // Replace `button` with your actual button object
        Scene scene = new Scene(root );
        stage.setScene(scene);
        ac.toAppointment();
        stage.show();

        } catch (IOException ex) {
            System.out.println(ex);
            Logger.getLogger(AfficherConsultationsListController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
}
