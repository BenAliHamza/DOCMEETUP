/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.Gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import tn.esprit.entities.Appointment;
import tn.esprit.services.AppointmentService;

/**
 * FXML Controller class
 *
 * @author chiha
 */
public class DocTimeController implements Initializable {


    @FXML
    private TableView<Appointment> appointmentTableView;
      @FXML
    private TableColumn<Appointment, String> descriptiontv;
    @FXML
    private TableColumn<Appointment, Boolean> statustv;
    @FXML
    private TableColumn<Appointment, String> datetimetv;
    @FXML
    private Button ValidS;
    @FXML
    private Button back;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showAppointments();
        
        
        
         }    
    
    
     
    
    
     
    
    private void showAppointments() {
        AppointmentService appointmentService = new AppointmentService();
        int medecinId = 1; // Assuming a static medecinId
    ObservableList<Appointment> list = appointmentService.getAppointments(medecinId);
    appointmentTableView.setItems(list);

     descriptiontv.setCellValueFactory(new PropertyValueFactory<>("description"));
    statustv.setCellValueFactory(new PropertyValueFactory<>("status"));
    datetimetv.setCellValueFactory(new PropertyValueFactory<>("appointmentDateTime"));
}

    @FXML
 private void validS(ActionEvent event) {
            AppointmentService appointmentService = new AppointmentService();

    // Get the selected appointment from the table
    Appointment selectedAppointment = appointmentTableView.getSelectionModel().getSelectedItem();
    
    if (selectedAppointment != null) {
        // Get the current status of the appointment
        boolean currentStatus = selectedAppointment.getStatus();
        
        // Toggle the status
        boolean newStatus = !currentStatus;
        
        // Change the status of the selected appointment
        selectedAppointment.setStatus(newStatus);
        
        // Update the appointment in the database using the AppointmentService
        appointmentService.updateAppointment(selectedAppointment);
        
        // Refresh the table view
        showAppointments();
        
        System.out.println("Appointment status changed successfully.");
    } else {
        System.out.println("Please select an appointment from the table.");
    }
}

 
    @FXML
    private void myap(ActionEvent event) {
        JavaFXApplication1.setScene("calendarUI");

    }

    @FXML
    private void row(MouseEvent event) {
    }
    
}
