/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui.consultations.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import tn.esprit.entities.Consulation;
import tn.esprit.entities.User;
import tn.esprit.services.UserService;
import tn.esprit.tools.Role;

/**
 * FXML Controller class
 *
 * @author Hamza
 */

public class HomePageController implements Initializable {
    
    @FXML
    AnchorPane  holderPanel ;
    AnchorPane  homeBackground ;
     Button btnConsultation;
      @FXML
    Button followUp;
    @FXML
    Button dashboard ; 
    static private User user;
    private UserService us ; 
    @FXML
    private Button consultation;
    @FXML
    private Button out;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         us = new UserService();
        loadHomePage(); 
    }    
    private void setNode(Node node ) {
        holderPanel.getChildren().clear(); 
        holderPanel.getChildren().add( (Node)node); 
        FadeTransition ft = new FadeTransition(Duration.millis(1000));
        ft.setNode(node);
        ft.setFromValue(0.5);
        ft.setToValue(1); 
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        HomePageController.user = user;
    }


    
    @FXML
    public void loadHomePage() {
        try {
                   homeBackground =  FXMLLoader.load(getClass().getResource("../FXML/Home.fxml"));
                   setNode(homeBackground);
        }catch( Exception e ) {
            System.out.println(e);
        }
    }
      public void loadEvents() {
        try {
                   homeBackground =  FXMLLoader.load(getClass().getResource("../FXML/afficher.fxml"));
                   setNode(homeBackground);
        }catch( Exception e ) {
            System.out.println(e);
        }
    }
    @FXML
      public void loadConsultationList() {
        try {
                   FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/AfficherConsultationsList.fxml"));
                   Parent consultationPane = loader.load();
                   AfficherConsultationsListController c = loader.getController();
                   c.setUser(user);
                   c.populateConsultationsList(); 
                   setNode(consultationPane);
        }catch( Exception e ) {
            System.out.println(e);
        }
    }
      public void loadConsultationList(int id ) {
                 try {
                   FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/AfficherConsultationsList.fxml"));
                   Parent consultationPane = loader.load();
                   AfficherConsultationsListController c = loader.getController();
                   c.populateConsultationsList();
                   c.setUser(user);
                   c.setConsultationId(id);
                   setNode(consultationPane);
                }catch( Exception e ) {
                    System.out.println(e);
                }
     
      }

    public void CreateNewConsultation() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/AjouterConsultation.fxml"));
            Parent consultationPane = loader.load();
            AjouterConsultationController controller = loader.getController();
            controller.setDoctor(user);
            User patient = this.us.SearchById(13);
            controller.setPatient(patient);
            setNode(consultationPane);
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
    }
    public void CreateNewConsultation(Consulation c ) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/AjouterConsultation.fxml"));
            Parent consultationPane = loader.load();
            AjouterConsultationController controller = loader.getController();        
            controller.setConsultation(c);
            setNode(consultationPane);
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
    }

    @FXML
          public void onFollowUp() {
            Role role  = Role.pharmacy  ; 
            String url ; 
            switch(role) {
                case  patient : 
                    url = "../FXML/Search.fxml" ; 
                       try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
                                Parent consultationPane = loader.load();
                                SearchController controller = loader.getController();        
                                setNode(consultationPane);
                     }catch( Exception e ) {
                         System.out.println(e);
                         System.out.println(e.getMessage());
                     }
                     break ; 
                case pharmacy : 
                    url = "../FXML/medic.fxml" ; 
                       try {
                       FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
                       Parent consultationPane = loader.load();
                       MedicController controller = loader.getController();        
                       setNode(consultationPane);
                        }catch( Exception e ) {
                            System.out.println(e);
                            System.out.println(e.getMessage());
                        }
                    break ; 
                case laboratory : 
                    url = "../FXML/analysis.fxml" ; 
                       try {
                       FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
                       Parent consultationPane = loader.load();
                       AnalysisController controller = loader.getController();        
                       setNode(consultationPane);
                        }catch( Exception e ) {
                            System.out.println(e);
                            System.out.println(e.getMessage());
                        }
                    break ; 
                default:
                    url = "../FXML/Search.fxml" ;
                       try {
                       FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
                       Parent consultationPane = loader.load();
                       setNode(consultationPane);
                        }catch( Exception e ) {
                            System.out.println(e);
                            System.out.println(e.getMessage());
                        }
                    break ;
                                        
            }
         
        }
        public void getUserConnected(User user) {
            this.user = user ;
        }

    @FXML
    private void logout(ActionEvent event) {
            Platform.exit();
            System.exit(0);
    }



    
  
    
}
