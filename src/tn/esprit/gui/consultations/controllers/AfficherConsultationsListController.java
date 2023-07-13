package tn.esprit.gui.consultations.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import tn.esprit.entities.Consulation;
import tn.esprit.services.ConsulationService;
import javafx.collections.FXCollections;
import javafx.scene.control.ListCell;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import tn.esprit.entities.User;
import tn.esprit.gui.consultations.Consultations;
import tn.esprit.gui.consultations.Main;
import tn.esprit.services.UserService;

public class AfficherConsultationsListController implements Initializable {
    @FXML
    AnchorPane consultationDetails ;
    AnchorPane consultationDetail ;
    @FXML
    private ListView<Consulation> listView;
    @FXML 
    Button newConsBtn ; 
    private int  consultationId ; 
    private User user ; 

    private final ConsulationService consultationService = new ConsulationService();
    @FXML
    private TextField search;
            private List<Consulation> originalConsultations;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Main.setTitle("La liste des consultations");
            search.clear(); // Clear the search TextField
        populateConsultationsList();
    }
   public void populateConsultationsList() {
    originalConsultations = consultationService.afficher();
    listView.setItems(FXCollections.observableArrayList(originalConsultations));

    // Set a cell factory to customize each item in the ListView
    listView.setCellFactory(param -> new ListCell<Consulation>() {
        @Override
        protected void updateItem(Consulation item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setText(null);
                setGraphic(null);
                setStyle(null);
            } else {
                setText(item.getInformationToDisplay());

                // Add padding
                setStyle("-fx-padding: 5px;");

                // Set hover effect
                setOnMouseEntered(event -> setStyle("-fx-background-color: lightgray;"));
                setOnMouseExited(event -> setStyle(null));
                
                // Set an action when an item is clicked
                setOnMouseClicked(event -> {
                   loadConsultationInformation(item.getConsultation_id());
                });
            }
        }
    });
}
    private void setNode(Node node ) {
        consultationDetails.getChildren().clear(); 
        consultationDetails.getChildren().add( (Node)node); 
        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.5);
        ft.setToValue(1); 
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }

    public int getConsultationId() {
        return consultationId;
    }

    public void setConsultationId(int consultationId) {
        this.consultationId = consultationId;
        loadConsultationInformation(consultationId); 
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        userUpdateInterface(); 
    }
       public void loadConsultationInformation(int id ) {
        try {
            ConsulationService cs = new ConsulationService();
            UserService us = new UserService() ;
            Consulation consulation = cs.getConsultationById(id);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/afficherConsultation.fxml"));
            Parent root = loader.load();
            AfficherConsultationController ac = loader.getController();
            User doc = us.SearchById(consulation.getDoctor_id());
            User pat = us.SearchById(consulation.getPatient_id());


            ac.setDate(consulation.getDate());
            ac.setDocId("Dr : " + doc.getLast_name() + " " + doc.getFirst_name());
            ac.setIsPayed(consulation.getIsPayed());
            ac.setPrice(consulation.getPrice());
            ac.setPatientId(pat.getLast_name() + " " + pat.getFirst_name());
            ac.setTime(consulation.getTime());
            ac.setId(consulation.getConsultation_id());
            ac.setRapport(consulation.getRapport());
            Consultations.setTitle("Detail de la consultation"); 
            consultationDetail = (AnchorPane) root ; 
            setNode(consultationDetail);
        }
        catch( Exception e ) {
            System.out.println(e);
        }
    }
    @FXML
    public void createNewConsultation(){
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/HomePage.fxml"));
        Parent root = loader.load();
        HomePageController ac = loader.getController();
        Stage stage = (Stage) consultationDetails.getScene().getWindow(); // Replace `button` with your actual button object
        Scene scene = new Scene(root);
        stage.setScene(scene);
        ac.CreateNewConsultation(13);
        stage.show();

        } catch (IOException ex) {
            System.out.println(ex);
            Logger.getLogger(AfficherConsultationsListController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }   

    @FXML
    private void search(KeyEvent event) {
        String keyword = search.getText().toLowerCase().trim(); // Get the search keyword from the TextField

        if (keyword.isEmpty()) {
            // If the search keyword is empty, show all consultations
            listView.setItems(FXCollections.observableArrayList(originalConsultations));
            return;
        }

        // Filter the consultations based on the search keyword
        List<Consulation> filteredList = originalConsultations.stream()
                .filter(consultation -> consultation.getInformationToDisplay().toLowerCase().contains(keyword))
                .collect(Collectors.toList());

        listView.setItems(FXCollections.observableArrayList(filteredList));
}

    private void userUpdateInterface() {
       switch(user.getRole()){
           case patient:
               search.setPromptText("Search by doctor name");
               this.newConsBtn.setVisible(false);
               break ; 
           case doctor:
                search.setPromptText("Search by patient name");
                break;
           default:
             search.setPromptText("No Search is available ");
                            this.newConsBtn.setVisible(false);

             break ; 
               
               

               
       }
    }

    
}
