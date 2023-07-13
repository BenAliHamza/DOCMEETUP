/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui.consultations.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
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
    @FXML
    private AnchorPane anchor;
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
        try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/HomePage.fxml"));
        Parent root = loader.load();
        HomePageController ac = loader.getController();
        Stage stage = (Stage) anchor.getScene().getWindow(); // Replace `button` with your actual button object
        Scene scene = new Scene(root );
        stage.setScene(scene);
        ac.fromDocTimeCallender();
        stage.show();

        } catch (IOException ex) {
            System.out.println(ex);
            Logger.getLogger(AfficherConsultationsListController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void row(MouseEvent event) {
    }

    @FXML
    private void createConsultation(ActionEvent event) {
         Appointment selectedAppointment = appointmentTableView.getSelectionModel().getSelectedItem();
         if( selectedAppointment == null )return ; 
             try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/HomePage.fxml"));
                Parent root = loader.load();
                HomePageController ac = loader.getController();
                Stage stage = (Stage) anchor.getScene().getWindow(); // Replace `button` with your actual button object
                Scene scene = new Scene(root );
                stage.setScene(scene);
                ac.CreateNewConsultation(selectedAppointment.getPatientId());
                stage.show();

            } catch (IOException ex) {
                System.out.println(ex);
                Logger.getLogger(AfficherConsultationsListController.class.getName()).log(Level.SEVERE, null, ex);
            }
         
    }
    
}
