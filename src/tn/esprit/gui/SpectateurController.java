package tn.esprit.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import tn.esprit.entities.User;
import tn.esprit.services.EvenementService;

public class SpectateurController {
    @FXML
    private TextField TextEvent;

    @FXML
    private ChoiceBox<String> catColumn;

    @FXML
    private Label participantsLabel;

    private EvenementService evenementService;

    public SpectateurController() {
        evenementService = new EvenementService();
    }

@FXML
void initialize() {
    String eventIdText = TextEvent.getText();
    if (!eventIdText.isEmpty()) {
        int eventId = Integer.parseInt(eventIdText);
        ObservableList<String> participantsList = getParticipants(eventId);
        catColumn.setItems(participantsList);
    }
}


@FXML
void AddEvenement(ActionEvent event) {
    int eventId = Integer.parseInt(TextEvent.getText());
    ObservableList<String> participantsList = getParticipants(eventId);
    catColumn.setItems(participantsList);
    participantsLabel.setText("Participants: " + participantsList.size());
}




    private ObservableList<String> getParticipants(int eventId) {
        ObservableList<String> participants = FXCollections.observableArrayList();
        for (User participant : evenementService.getParticipants(eventId)) {
            participants.add(participant.getUsername());
        }
        return participants;
    }
}
