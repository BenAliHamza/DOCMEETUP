/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void SignUp(ActionEvent event) throws IOException {
    
    String L = Login.getText();
    String P = Password.getText();
   
     // Vérifier si les identifiants sont valides et créer le compte
    if (isValidCredentials(L, P)) {
            createAccount(L, P);
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
            // Vous pouvez passer des données au contrôleur de la page InscriptionMenuController si nécessaire
            // inscriptionMenuController.setUserData(userData);
            // Code pour afficher la scène InscriptionMenu.fxml dans une fenêtre séparée

        } catch (IOException e) {
            e.printStackTrace();
        }
            
    }

private void createAccount(String login, String password) {
    String salt = PasswordHashing.generateSalt();
    String hashedPassword = PasswordHashing.hashPassword(password, salt);

    // Vous pouvez utiliser le login, le hashedPassword et le salt pour enregistrer le compte dans votre système

    System.out.println("Login : " + login);
    System.out.println("Mot de passe haché : " + hashedPassword);
}

    private boolean isValidCredentials(String L, String P) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void redirectToCreateAccount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
