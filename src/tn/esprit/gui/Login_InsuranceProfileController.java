package tn.esprit.gui;

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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.entities.InsuranceProfile;
import tn.esprit.entities.User;
import tn.esprit.services.InsuranceProfileService;
import tn.esprit.services.UserService;

public class Login_InsuranceProfileController implements Initializable {

    @FXML
    private TextField email;

    @FXML
    private Button view_insurance;

    @FXML
    private Button create_insurance;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
    }
    private String userEmail;


    @FXML
public void handleViewInsurance(ActionEvent event) {
    String userEmail = email.getText();
    try {
        UserService userService = new UserService();
        User user = userService.getUserByEmail(userEmail);
        if (user != null && user.getRole() == User.Role.PATIENT) {
            InsuranceProfileService insuranceProfileService = new InsuranceProfileService();
            InsuranceProfile insuranceProfile = insuranceProfileService.getInsuranceProfileByUserId(user.getUser_id());
            if (insuranceProfile != null) {
                // User has an insurance profile
                FXMLLoader loader = new FXMLLoader(getClass().getResource("View_InsuranceProfile.fxml"));
                Parent root = loader.load();
                View_InsuranceProfileController controller = loader.getController();
                controller.initializeData(userEmail); // Pass the userEmail to the initializeData method

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            } else {
                // User doesn't have an insurance profile
                showAlert(Alert.AlertType.ERROR, "No Insurance Profile", "You don't have an insurance profile yet.");
            }
        } else {
            // User is not a patient
            showAlert(Alert.AlertType.ERROR, "Invalid User", "You are not authorized to view the insurance profile.");
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}


    @FXML
    private void handleCreateInsurance(ActionEvent event) {
        String userEmail = email.getText();
        try {
            UserService userService = new UserService();
            User user = userService.getUserByEmail(userEmail);
            if (user != null && user.getRole() == User.Role.PATIENT) {
                InsuranceProfileService insuranceProfileService = new InsuranceProfileService();
                InsuranceProfile insuranceProfile = insuranceProfileService.getInsuranceProfileByUserId(user.getUser_id());
                if (insuranceProfile == null) {
                    // User doesn't have an insurance profile
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Create_InsuranceProfile.fxml"));
                    Parent root = loader.load();
                    Create_InsuranceProfileController controller = loader.getController();
                    controller.setUser(user);

                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                } else {
                    // User already has an insurance profile
                    showAlert(Alert.AlertType.ERROR, "Insurance Profile Exists", "You already have an insurance profile.");
                }
            } else {
                // User is not a patient
                showAlert(Alert.AlertType.ERROR, "Invalid User", "You are not authorized to create an insurance profile.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}