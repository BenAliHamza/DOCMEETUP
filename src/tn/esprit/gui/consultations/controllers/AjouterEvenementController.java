package tn.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.entities.Evenement;
import tn.esprit.entities.User;
import tn.esprit.services.EvenementService;

public class AjouterEvenementController implements Initializable {

    @FXML
    private TextField TextOrganizer;
    @FXML
    private TextField TextName;
    @FXML
    private DatePicker  TextDate;
    @FXML
    private TextField TextTime;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO: Initialization code
    }

   @FXML
private void AddEvenement(ActionEvent event) {
    String organizerText = TextOrganizer.getText();
    String eventName = TextName.getText();
    LocalDate eventDate = TextDate.getValue();
    String eventTimeText = TextTime.getText();

    if (organizerText.isEmpty() || eventName.isEmpty() || eventDate == null || eventTimeText.isEmpty()) {
    // Afficher un message d'erreur à l'utilisateur indiquant que tous les champs doivent être remplis
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur de saisie");
    alert.setHeaderText(null);
    alert.setContentText("Tous les champs sont obligatoires.");
    alert.showAndWait();
    return;
}

// Convertir eventTimeText en LocalTime
LocalTime eventTime;
try {
    eventTime = LocalTime.parse(eventTimeText);
} catch (DateTimeParseException e) {
    // Afficher un message d'erreur à l'utilisateur indiquant que l'heure de l'événement est dans un format invalide
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur de saisie");
    alert.setHeaderText(null);
    alert.setContentText("Heure de l'événement est dans un format invalide.");
    alert.showAndWait();
    return;
}

User organizer = new User();
organizer.setUsername(organizerText); // Utiliser le nom de l'organisateur au lieu de l'ID
Evenement events = new Evenement(organizer, eventName, eventDate, eventTime);
EvenementService eventService = new EvenementService();
eventService.ajouter(events);

// Fermer la fenêtre courante
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/afficher.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) TextOrganizer.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
}

    @FXML
    private void Annuler(ActionEvent event) {
        // Close the current window
        TextOrganizer.getScene().getWindow().hide();
    }

}
