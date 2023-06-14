/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import tn.esprit.services.FollowUpService;
import tn.esprit.entities.FollowUp;
import tn.esprit.entities.User;
import tn.esprit.tools.Role;


/**
 * FXML Controller class
 *
 * @author HP
 */
public class FollowUpGUIController implements Initializable {

    @FXML
    private AnchorPane AddFollowUp;
    @FXML
    private TextField tfblood_pressure;
    @FXML
    private TextField tfheart_rate;
    @FXML
    private TextField tftemperature;
    @FXML
    private TextField tfweight;
    @FXML
    private Button btadd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AddFollowup(ActionEvent event) throws ParseException {
        
        FollowUpService fus=new FollowUpService();
         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date birthdate = dateFormat.parse("1993/01/04 16:49");

        User user = new User(2,"salimaroua1993@gmail.com", "mypassword", "SalimAroua",
               Role.patient, "Salim", "Aroua", birthdate,"52", "rue de russie"
                , "Bizerte", 7000, 53235426, "test123");
        FollowUp fl= new FollowUp(user, birthdate, tfblood_pressure.getText(), Integer.parseInt(tfheart_rate.getText()), Float.parseFloat(tftemperature.getText()), Float.parseFloat(tfweight.getText()));
        fus.Create(fl);
               
    }
    
    
}
