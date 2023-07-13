package tn.esprit.gui.consultations.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tn.esprit.entities.Evenement;
import tn.esprit.entities.User;
import tn.esprit.services.EvenementService;

public class AfficherController implements Initializable {
    private int eventSelectedId ;
    @FXML
    private TableView<Evenement> tableviewEquipement;

    @FXML
    private TableColumn<Evenement, String> nomColumn;

    @FXML
    private TableColumn<Evenement, String> catColumn;

    @FXML
    private TableColumn<Evenement, String> datColumn;

    @FXML
    private TableColumn<Evenement, String> quantColumn;


    private EvenementService evenementService;
    @FXML
    private Button buttonDelete1;
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
    private TextField TextEvent1;
    @FXML
    private ChoiceBox<String> catColumn1;
    @FXML
    private Button Afficher;
    @FXML
    private TextField TextOrganizer1;
    @FXML
    private AnchorPane anchor;
    private Evenement se ; 


@Override
public void initialize(URL url, ResourceBundle rb) {
    evenementService = new EvenementService();
    initializeTableView();
}

private void initializeTableView() {
    nomColumn.setCellValueFactory(new PropertyValueFactory<>("event_name"));
    catColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getOrganizer().getLast_name()));
    datColumn.setCellValueFactory(new PropertyValueFactory<>("event_date"));
    quantColumn.setCellValueFactory(new PropertyValueFactory<>("event_time"));

    List<Evenement> evenements = evenementService.afficher();
    System.out.println(evenements.toString());
    tableviewEquipement.setItems(FXCollections.observableArrayList(evenements));
     tableviewEquipement.setOnMouseClicked(event -> {
        if (event.getClickCount() ==1) {
            Evenement selectedEvent = tableviewEquipement.getSelectionModel().getSelectedItem();
            if (selectedEvent != null) {
                // Handle the double-click event for the selected event
               int id = selectedEvent.getEvent_id();
               EvenementService es = new EvenementService();
               Evenement e = es.getById(id);
               TextEvent.setText(""+e.getEvent_id());
               TextName.setText(e.getEvent_name());
                System.out.println(e.getOrganizer().toString());
               TextOrganizer.setText(""+e.getOrganizer().getUser_id());
               se = selectedEvent ; 
               
            }
        }
    });
}






    @FXML
    private void Delete() {
        Evenement selectedEvent = tableviewEquipement.getSelectionModel().getSelectedItem();
        if (selectedEvent != null) {
            evenementService.supprimer(selectedEvent);
tableviewEquipement.setItems(FXCollections.observableArrayList(evenementService.afficher()));
        }
    }

    @FXML
    private void Annuler(ActionEvent event) {
         TextOrganizer.getScene().getWindow().hide();
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

              try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/HomePage.fxml"));
        Parent root = loader.load();
        HomePageController ac = loader.getController();
        Stage stage = (Stage) anchor.getScene().getWindow(); // Replace `button` with your actual button object
        Scene scene = new Scene(root );
        stage.setScene(scene);
        ac.loadEvents();
        stage.show();

        } catch (IOException ex) {
            System.out.println(ex);
            Logger.getLogger(AfficherConsultationsListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
   /* @FXML
    private void Annuler(ActionEvent event) {
        TextOrganizer.getScene().getWindow().hide();
    }*/

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
   /* TextEvent.clear();
    TextOrganizer.clear();
    TextName.clear();
    TextTime.clear();*/
   try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/HomePage.fxml"));
        Parent root = loader.load();
        HomePageController ac = loader.getController();
        Stage stage = (Stage) anchor.getScene().getWindow(); // Replace `button` with your actual button object
        Scene scene = new Scene(root );
        stage.setScene(scene);
        ac.loadEvents();
        stage.show();
        
        stage.show();
               
                
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
    }

    @FXML
    private void AfficherEvenement(ActionEvent event) {
        System.out.println(se.toString());
       int eventId = se.getEvent_id();
    ObservableList<String> participantsList = getParticipants(eventId);
    catColumn1.setItems(participantsList);
   // participantsLabel.setText("Participants: " + participantsList.size());
}




    private ObservableList<String> getParticipants(int eventId) {
        ObservableList<String> participants = FXCollections.observableArrayList();
        for (User participant : evenementService.getParticipants(eventId)) {
            participants.add(participant.getUsername());
        }
        return participants;
    } 

   /* @FXML
    private void PDF(ActionEvent event) throws FileNotFoundException, SQLException, DocumentException, com.itextpdf.text.DocumentException {
        Date d= new Date(2020,05,21);
            Time t=new Time(20,22,59);
        
        EvenementService eventServices = new EvenementService();

        ObservableList<Evenement> all, Single;
        all = tableviewEquipement.getItems();
        Single = tableviewEquipement.getSelectionModel().getSelectedItems();
        Evenement A = new Evenement( "Workshop", d, t);
        System.out.println(A);
        String getEvent_name = String.valueOf(A.getEvent_name());
        String getEvent_date = String.valueOf(A.getEvent_date());
        String getEvent_time = String.valueOf(A.getEvent_time());
        //String strNumber = String.valueOf(A.getWeight());
        //String getTemperature = String.valueOf(A.getTemperature());

        eventServices.pdf(getEvent_name, getEvent_date, getEvent_time);
        System.out.println("PDF OK");
        
    }
*/
   
}


 
