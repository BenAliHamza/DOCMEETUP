/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.entities.Enum.Role;
import tn.esprit.entities.PasswordHashing;


/**
 * FXML Controller class
 *
 * @author DELL
 */
public class CreateAccountController implements Initializable {

    @FXML
    private TextField Login;
    @FXML
    private PasswordField Password;
    @FXML
    private Button SignUp;
    @FXML
    private ComboBox<Role> RoleComboBox;
    

    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        RoleComboBox.getItems().addAll(Role.values());
    }

@FXML
    private void SignUp(ActionEvent event) throws IOException {
        String L = Login.getText();
        String P = Password.getText();
        Role selectedRole = RoleComboBox.getValue();

        if (isValidCredentials(L, P) && selectedRole != null) {
            createAccount(L, P, selectedRole);
            redirectToInscriptionMenu();
        } else {
            redirectToCreateAccount();
        }
    }

private void redirectToInscriptionMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InscriptionMenu.fxml"));
            Parent root = loader.load();
            InscriptionMenuController inscriptionMenuController = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Inscription Menu");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
            
    }

private void createAccount(String login, String password, Role role) {
        String salt = PasswordHashing.generateSalt();
        String hashedPassword = PasswordHashing.hashPassword(password, salt);
        System.out.println("Login : " + login);
        System.out.println("Mot de passe haché : " + hashedPassword);
        System.out.println("Rôle : " + role);
    }


 private boolean isValidCredentials(String L, String P) {
        return !L.isEmpty() && !P.isEmpty();
    }
 
 private void redirectToCreateAccount() {
  
 }

    private static class FXMLLoa {
        public FXMLLoa() {
        }
    }
}
