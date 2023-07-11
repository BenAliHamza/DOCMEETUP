/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui.consultations.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tn.esprit.entities.User;
import tn.esprit.services.UserService;

/**
 * FXML Controller class
 *
 * @author Hamza
 */
public class LoginController implements Initializable {

    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private Button btnSub;
    @FXML
    private Button btnSub2;
    @FXML
    private AnchorPane anchor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onSubmit(ActionEvent event) {
            UserService cs= new UserService();
            String emailValue = email.getText();
            String passwordValue = password.getText();
            String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
            if (!emailValue.matches(emailRegex)) {
                showAlert(Alert.AlertType.ERROR, "Error", "Invalid Email", "Invalid email format.");
                return;
            }
            Boolean isEmail= cs.isEmail(emailValue);
            if(!isEmail) {
             showAlert(Alert.AlertType.ERROR, "Error", "Invalid Email", "There is no account related to this email  .");
             return ;
            }
            if(passwordValue.length() == 0){
                showAlert(Alert.AlertType.ERROR, "Error", "Invalid Email", "There is no password   .");
                return; 
            }
            int id = cs.login(emailValue, passwordValue);
            if (id != -1) {
                    System.out.println("Login successful! User ID: " + id);
                    toHome(id);
                }
    }
    private void showAlert(Alert.AlertType alertType, String title, String headerText, String contentText) {
               Alert alert = new Alert(alertType);
               alert.setTitle(title);
               alert.setHeaderText(headerText);
               alert.setContentText(contentText);
               alert.showAndWait();
           }
    @FXML
    private void register(ActionEvent event) {
           try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/registration.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) anchor.getScene().getWindow(); // Replace `button` with your actual button object
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            } 
    }
    private void toHome(int id ){
                UserService cs= new UserService();
                try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/HomePage.fxml"));
                Parent root = loader.load();
                HomePageController h = loader.getController();
                User user = cs.SearchById(id); 
                HomePageController.setUser(user);
                Stage stage = (Stage) anchor.getScene().getWindow(); // Replace `button` with your actual button object
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
    }

}
