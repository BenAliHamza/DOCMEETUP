    /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */
    package tn.esprit.gui.consultations.controllers;

    import java.io.IOException;
    import java.net.URL;
    import java.sql.Date;
    import java.sql.Time;
    import java.util.ResourceBundle;
    import javafx.event.ActionEvent;
    import javafx.fxml.FXML;
    import javafx.fxml.FXMLLoader;
    import javafx.fxml.Initializable;
    import javafx.scene.Parent;
    import javafx.scene.control.Button;
    import javafx.scene.control.TextField;
    import javafx.stage.Stage;
    import tn.esprit.entities.Consulation;
    import tn.esprit.gui.consultations.Consultations;
    import tn.esprit.services.ConsulationService;

    /**
     * FXML Controller class
     *
     * @author Hamza
     */
    public class AjouterConsultationController implements Initializable {

        @FXML
        private TextField docId;
        @FXML
        private TextField patientId;
        @FXML
        private TextField price;
        @FXML
        private Button btnCreate;
        private int doctorId ;
        private int patienttId;

        /**
         * Initializes the controller class.
         */
        @Override
        public void initialize(URL url, ResourceBundle rb) {
            // TODO
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
            int docId = Integer.parseInt(this.docId.getText());
            int patientId = Integer.parseInt(this.patientId.getText());
            Double price = Double.parseDouble(this.price.getText());
            Consulation consulation = new Consulation(docId, patientId, price, new Date(System.currentTimeMillis()), new Time(System.currentTimeMillis()));
            ConsulationService cs = new ConsulationService();
            cs.ajouter(consulation);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/afficherConsultation.fxml"));
                Parent root = loader.load();
                AfficherConsultationController ac = loader.getController();
                ac.setDate(consulation.getDate());
                ac.setDocId(consulation.getDoctor_id());
                ac.setIsPayed(consulation.getIsPayed());
                ac.setPrice(consulation.getPrice());
                ac.setPatientId(consulation.getPatient_id());
                ac.setTime(consulation.getTime());
                ac.setId(consulation.getConsultation_id());
                ((Stage) this.docId.getScene().getWindow()).setTitle("afficher");

                this.docId.getScene().setRoot(root);

                Consultations.setTitle("Detail de la consultation");
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        }

    }
