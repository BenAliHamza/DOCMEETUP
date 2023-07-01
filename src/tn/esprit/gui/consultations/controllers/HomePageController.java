/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui.consultations.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
/**
 * FXML Controller class
 *
 * @author Hamza
 */

public class HomePageController implements Initializable {
    
    @FXML
    AnchorPane  holderPanel ;
    AnchorPane  homeBackground ;
     @FXML
    Button btnConsultation;
    @FXML
    Button dashboard ; 


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        loadHomePage(); 
    }    
    private void setNode(Node node ) {
        holderPanel.getChildren().clear(); 
        holderPanel.getChildren().add( (Node)node); 
        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.5);
        ft.setToValue(1); 
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }
    public void loadHomePage() {
        try {
                   homeBackground =  FXMLLoader.load(getClass().getResource("../FXML/Home.fxml"));
                   setNode(homeBackground);
        }catch( Exception e ) {
            System.out.println(e);
        }
    }
      public void loadConsultationList() {
        try {
                   homeBackground =  FXMLLoader.load(getClass().getResource("../FXML/AfficherConsultationsList.fxml"));
                   setNode(homeBackground);
        }catch( Exception e ) {
            System.out.println(e);
        }
    }
        public void CreateNewConsultation() {
        try {
                  homeBackground =  FXMLLoader.load(getClass().getResource("../FXML/AjouterConsultation.fxml"));
                   setNode(homeBackground);
        }catch( Exception e ) {
            System.out.println(e);
        }
    }



    
  
    
}
