package tn.esprit.gui;

import java.io.IOException;
import java.math.BigDecimal;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tn.esprit.entities.User;
import tn.esprit.entities.InsuranceProfile;
import tn.esprit.entities.InsuranceProfile.InsuranceType;
import tn.esprit.services.UserService;
import tn.esprit.services.InsuranceProfileService;

public class Create_InsuranceProfileController implements Initializable {

    @FXML
    private Button btcreate;
    @FXML
    private ChoiceBox<InsuranceType> cbtype;
    @FXML
    private Text textcost;
    @FXML
    private TextField tmail;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Populate the ChoiceBox with insurance type values
        cbtype.getItems().addAll(InsuranceType.values());
        
        // Add event listener to the ChoiceBox
        cbtype.setOnAction((event) -> {
            updateCostText();
        });
    }

   @FXML
private void createInsuranceProfile(ActionEvent event) {
    String email = tmail.getText();
    InsuranceType insuranceType = cbtype.getValue();
    BigDecimal insuranceCost = new BigDecimal(textcost.getText());
    
    // Retrieve the user by email and get the user ID
    UserService userService = new UserService();
    User user = userService.getUserByEmail(email);
    int userId = user.getUser_id();
    
    // Create an InsuranceProfile instance
    InsuranceProfile insuranceProfile = new InsuranceProfile();
    insuranceProfile.setUserId(userId);
    insuranceProfile.setInsuranceType(insuranceType);
    insuranceProfile.setInsuranceCost(insuranceCost);
    insuranceProfile.setInsuranceStatus(InsuranceProfile.InsuranceStatus.ACTIVE);
    
    // Save the insurance profile to the database or perform any other necessary action
    InsuranceProfileService insuranceProfileService = new InsuranceProfileService();
    insuranceProfileService.createInsuranceProfile(insuranceProfile);
    
    // Show success message to the user
    showAlert(Alert.AlertType.INFORMATION, "Profile Created", "Your insurance profile has been created successfully.");
    
    // Redirect to View_InsuranceProfile with the populated data
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("View_InsuranceProfile.fxml"));
        Parent root = loader.load();
        View_InsuranceProfileController controller = loader.getController();
        controller.initializeData(email); // Pass the userEmail to the initializeData method

        Scene scene = new Scene(root);
        Stage stage = (Stage) btcreate.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
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


    void setUser(User user) {
        // Set the user details in the controller
        tmail.setText(user.getEmail());
        // Set other user details as needed
    }
    
    private void updateCostText() {
        InsuranceType selectedType = cbtype.getValue();
        if (selectedType == InsuranceType.PREMIUM) {
            textcost.setText("1000");
        } else if (selectedType == InsuranceType.BASIC) {
            textcost.setText("500");
        } else {
            textcost.setText("");
        }
    }
}



