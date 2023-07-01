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
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Hamza
 */
public class AfficherConsultationController implements Initializable {
    private int id ; 



    @FXML
    private TextField docId;
    @FXML
    private TextField patientId;
    @FXML
    private TextField price;
    @FXML
    private TextField date;
    @FXML
    private TextField isPayed;
    @FXML
    private TextField time;
    @FXML
    private TextField rapport ; 
    @FXML
    private MenuItem updateBtn;
    @FXML
    private MenuItem deleteBtn;

    /**
     * Initializes the controller class.
     *
     */
        public int getId() {
        return id;
    }

        public void setId(int id) {
            this.id = id;
        }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setDocId(int docId) {
        this.docId.setText(""+docId);
    }

    public void setPatientId(int patientId) {
        this.patientId.setText(""+patientId); 
    }

    public void setPrice(Double price) {
        this.price.setText(""+ price + " dinars ");
    }

   public void setDate(Date date) {
    this.date.setText(date.toString());
}

    public TextField getRapport() {
        return rapport;
    }

    public void setRapport(String rapport) {
        this.rapport.setText(rapport);
    }

    public void setIsPayed(boolean  isPayed) {
        if(isPayed) {
            this.isPayed.setText("La consulation est payée"); 
        }else {
            this.isPayed.setText("La consulation n'est payée"); 

        }
        
    }

    public void setTime(Time time) {
        this.time.setText(time.toString());
    }

    @FXML
    private void onUpdate(ActionEvent event) {
    }

    @FXML
    private void onDelete(ActionEvent event) {
    }
    
}
