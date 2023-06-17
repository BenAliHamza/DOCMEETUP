/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class InscriptionMenuController implements Initializable {

    private TextField First_Name;
    private TextField Last_Name;
    private TextField Email;
    private TextField Birthdate;
    private TextField Address1;
    private TextField Address2;
    private TextField City;
    private TextField Phone;
    private TextField Profile_picture_url;
    @FXML
    private Button Save;

    @FXML
    private DatePicker myDatePicker;

    private void getDate(ActionEvent event) {
        LocalDate mybirthdate = myDatePicker.getValue();
        String myFormattedDate = mybirthdate.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
    } 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
    private void save(ActionEvent event) {
        
        String firstName = First_Name.getText();
        String lastName = Last_Name.getText();
        String email = Email.getText();
        Date birthdate = Date.valueOf(Birthdate.getText());
        String address1 = Address1.getText();
        String address2 = Address2.getText();
        String city = City.getText();
        int phone = Integer.parseInt(Phone.getText());
        String profile_picture_url = Profile_picture_url.getText();
        
        // Effectuer les opérations de sauvegarde ou de traitement des données
        // Afficher un message de succès ou d'échec de la sauvegarde
    }

    @FXML
    private void Save(ActionEvent event) {
    }

}
