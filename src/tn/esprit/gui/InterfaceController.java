/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import tn.esprit.entities.Evenement;
import tn.esprit.entities.User;
import tn.esprit.services.EvenementService;

/**
 * FXML Controller class
 *
 * @author DRIDI Oussama
 */
public class InterfaceController implements Initializable {

    @FXML
    private TextField TextOrganizer;
    @FXML
    private TextField TextName;
    @FXML
    private TextField TextTime;
    @FXML
    private DatePicker TextDate;
    @FXML
    private TextField TextEvent;
    @FXML
    private Button buttonDelete;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AddEvenement(ActionEvent event) {
        String organizerText = TextOrganizer.getText();
    String eventName = TextName.getText();
    LocalDate eventDate = TextDate.getValue();
    String eventTimeText = TextTime.getText();

    if (organizerText.isEmpty() || eventName.isEmpty() || eventDate == null || eventTimeText.isEmpty()) {
        // Show an error message to the user indicating that all fields must be filled
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText(null);
        alert.setContentText("Tous les champs sont obligatoires.");
        alert.showAndWait();
        return;
    }

    int organizer_id;
    try {
        organizer_id = Integer.parseInt(organizerText);
    } catch (NumberFormatException e) {
        // Show an error message to the user indicating that the organizer ID must be a number
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText(null);
        alert.setContentText("ID de l'organisateur doit être un nombre entier.");
        alert.showAndWait();
        return;
    }

    // Convert eventTimeText to LocalTime
    LocalTime eventTime;
    try {
        eventTime = LocalTime.parse(eventTimeText);
    } catch (DateTimeParseException e) {
        // Show an error message to the user indicating that the event time is in an invalid format
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText(null);
        alert.setContentText("Heure de l'événement est dans un format invalide.");
        alert.showAndWait();
        return;
    }

    User organizer = new User();
    organizer.setUser_id(organizer_id);
    Evenement events = new Evenement(organizer, eventName, eventDate, eventTime);
    EvenementService eventService = new EvenementService();
    eventService.ajouter(events);

    // Close the current window
    TextOrganizer.getScene().getWindow().hide();
    }

    @FXML
    private void Annuler(ActionEvent event) {
        TextOrganizer.getScene().getWindow().hide();
    }

    @FXML
    private void UpdEvenement(ActionEvent event) {
        int eventID = Integer.parseInt(TextEvent.getText());
    String organizerName = TextOrganizer.getText();
    String eventName = TextName.getText();
    LocalDate eventDate = TextDate.getValue();

    String[] timeParts = TextTime.getText().split(":");
    int hours = Integer.parseInt(timeParts[0]);
    int minutes = Integer.parseInt(timeParts[1]);
    int seconds = Integer.parseInt(timeParts[2]);

LocalTime eventTime = LocalTime.parse(TextTime.getText());
EvenementService es= new EvenementService();
    Evenement existingEvent = es.getById(eventID);

    existingEvent.setOrganizer(new User(organizerName));
    existingEvent.setEvent_name(eventName);
    existingEvent.setEvent_date(eventDate);
    existingEvent.setEvent_time(eventTime);

    // Update the event in the database
    es.Update(existingEvent);

    // Clear the text fields
    TextEvent.clear();
    TextOrganizer.clear();
    TextName.clear();
    TextTime.clear();
}

        
    

    
    public void Delete(ActionEvent event) {

    }
}

