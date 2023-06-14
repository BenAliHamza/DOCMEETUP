package tn.esprit.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import tn.esprit.entities.Evenement;
import tn.esprit.services.EvenementService;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.scene.control.DatePicker;
import tn.esprit.entities.User;

public class ModifierEvenementController implements Initializable {
    @FXML
    private TextField TextEvent;

    @FXML
    private TextField TextOrganizer;

    @FXML
    private TextField TextName;

    @FXML
    private DatePicker  TextDate;

    @FXML
    private TextField TextTime;


    private EvenementService evenementService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        evenementService = new EvenementService();
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

    Evenement existingEvent = evenementService.getById(eventID);

    existingEvent.setOrganizer(new User(organizerName));
    existingEvent.setEvent_name(eventName);
    existingEvent.setEvent_date(eventDate);
    existingEvent.setEvent_time(eventTime);

    // Update the event in the database
    evenementService.Update(existingEvent);

    // Clear the text fields
    TextEvent.clear();
    TextOrganizer.clear();
    TextName.clear();
    TextTime.clear();
}


}
