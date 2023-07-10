    /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */
    package tn.esprit.gui.consultations.controllers;

    import java.net.URL;
    import java.sql.Date;
    import java.sql.Time;
    import java.util.ResourceBundle;
    import javafx.event.ActionEvent;
    import javafx.fxml.FXML;
    import javafx.fxml.FXMLLoader;
    import javafx.fxml.Initializable;
    import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
    import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
    import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
    import javafx.stage.Stage;
    import tn.esprit.entities.Consulation;
    import tn.esprit.services.ConsulationService;

    /**
     * FXML Controller class
     *
     * @author Hamza
     */
    public class AjouterConsultationController implements Initializable {
        @FXML AnchorPane wrapper ;
        @FXML
        private TextField docId;
        @FXML
        private TextField patientId;
        @FXML
        private TextField price;
        @FXML
        private Button btnCreate;
        @FXML
        private Label titre ; 
        @FXML
        private TextArea rapport;
        @FXML
        private ToggleButton toggleButton;
        private int doctorId ;
        private int patienttId;
        private Consulation consultation ; 
        private Boolean updateMode =false;

        /**
         * Initializes the controller class.
         */
        @Override
        public void initialize(URL url, ResourceBundle rb) {
            // TODO
             toggleButton.setStyle("-fx-background-color: red; -fx-cursor: pointer;");
             toggleButton.setText("Not payed");
               // Event handler for mouse entered
             toggleButton.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
            if (!toggleButton.isSelected()) {
                toggleButton.setStyle("-fx-background-color: #ff4d4d;"); // Red color when hovered
            }else{
             toggleButton.setStyle("-fx-background-color: #0DCC31;"); // Red color when hovered

            }
        });

            // Event handler for mouse exited
            toggleButton.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
                if (!toggleButton.isSelected()) {
                    toggleButton.setStyle("-fx-background-color: red;"); // Reset to red color when not hovered
                }else{
                 toggleButton.setStyle("-fx-background-color: green;"); // Red color when hovered

                }
            });
             
        }

    public TextField getDocId() {
        return docId;
    }

    public void setDocId(int docId) {
        // this is suppose to get the user and set the user name 
        this.docId.setText(""+docId);
    }

    public TextField getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
                // this is suppose to get the user and set the user name 
        this.patientId.setText(""+patientId) ;
    }

    public Consulation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consulation consultation) {
        this.consultation = consultation;
        this.updateMode =true; 
        OnUpdateMode(); 
        
    }

    public TextField getPrice() {
        return price;
    }

    public void setPrice(TextField price) {
        this.price = price;
    }

    public Button getBtnCreate() {
        return btnCreate;
    }

    public void setBtnCreate(Button btnCreate) {
        this.btnCreate = btnCreate;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getPatienttId() {
        return patienttId;
    }

    public void setPatienttId(int patienttId) {
        this.patienttId = patienttId;
    }





        @FXML
        private void onAjouteConsultation(ActionEvent event) {
            if(updateMode) {
                onUpdateConsultation();
               
                return; 
            }else {
            boolean toggleState = toggleButton.isSelected();
            int docId = Integer.parseInt(this.docId.getText());
            int patientId = Integer.parseInt(this.patientId.getText());
            Double pricee ; 
            try {
            pricee = Double.parseDouble(this.price.getText());

            } catch (NumberFormatException e) {
            // Handle the exception if parsing the IDs fails
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid ID value. Please enter a valid numeric ID.");
            alert.showAndWait();
            return ; 
            }
            Consulation consulation = new Consulation(docId, patientId, pricee, new Date(System.currentTimeMillis()), new Time(System.currentTimeMillis()));
            consulation.setIsPayed(toggleButton.isSelected());
            consulation.setRapport(rapport.getText());
            ConsulationService cs = new ConsulationService();
            int id = cs.ajouter(consulation);
            returnToList(id);

            }
          

        }
        @FXML
        private void onToggleButtonClicked(ActionEvent event) {
            ToggleButton toggleButton = (ToggleButton) event.getSource();
            boolean isSelected = toggleButton.isSelected();

            if (isSelected) {
                toggleButton.setStyle("-fx-background-color: green;");
                toggleButton.setText("Payed");

            } else {
                toggleButton.setStyle("-fx-background-color: red;");
                toggleButton.setText("Not payed");

            }
        }
        private void OnUpdateMode() {
            titre.setText("Formulaire de modification consultation");
            docId.setText(""+consultation.getDoctor_id());
            patientId.setText(""+ consultation.getPatient_id());
            rapport.setText(consultation.getRapport());
            price.setText(""+consultation.getPrice());
            Boolean state = consultation.getIsPayed();
            btnCreate.setText("Mettre à jour la consultation");
            toggleButton.setSelected(state);
            if(state) {
                     toggleButton.setStyle("-fx-background-color: green; -fx-cursor: pointer;");
                     toggleButton.setText("Payed");
            }
            
            
        }
        public void onUpdateConsultation(){
            consultation.setIsPayed(toggleButton.isSelected());
            Double pricee ; 
            try {
            pricee = Double.parseDouble(this.price.getText());
            consultation.setPrice(pricee);

            } catch (NumberFormatException e) {
            // Handle the exception if parsing the IDs fails
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid ID value. Please enter a valid numeric ID.");
            alert.showAndWait();
            return ; 
            }
            String rap = rapport.getText();
            consultation.setRapport(rap); 
            ConsulationService cs = new ConsulationService();
            cs.updateConsultation(consultation);            
        }
        
        private void returnToList(int id ) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/HomePage.fxml"));
                Parent root = loader.load();
                HomePageController ac = loader.getController();
                ac.loadConsultationList(id);
                Stage stage = (Stage) wrapper.getScene().getWindow(); // Replace `button` with your actual button object
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                
            } catch (Exception  ex) {
                System.out.println(ex);
                System.out.println(ex.getStackTrace());

            }
        }

    }
