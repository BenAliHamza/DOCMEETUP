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
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tn.esprit.entities.InsuranceProfile;
import tn.esprit.entities.User;
import tn.esprit.services.UserService;

public class View_InsuranceProfileController implements Initializable {

    @FXML
    private Button listpurchases;
    @FXML
    private Button viewinsurance;
    @FXML
    private Button listrefunds;

    @FXML
    private Text patientName;
    @FXML
    private Text email;
    @FXML
    private Text telephone;

    // Additional field
    private InsuranceProfile insuranceProfile;
    private String userEmail;
    @FXML
    private Text insurancetype;
    @FXML
    private Text expdate;
    @FXML
    private Text insurancestatus;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeData(userEmail); // Call the initializeData method with the userEmail
    }

    public void setInsuranceProfile(InsuranceProfile insuranceProfile) {
        this.insuranceProfile = insuranceProfile;
    }

    public void initializeData(String userEmail) {
        this.userEmail = userEmail; // Set the userEmail field

        // Retrieve the user's insurance profile from the database
        UserService userService = new UserService();
        User user = userService.getUserByEmail(userEmail);

        // Set the insurance profile to the controller's field
        InsuranceProfile insuranceProfile = null;
        if (user != null) {
            insuranceProfile = userService.getInsuranceProfileByUserId(user.getUser_id());
        }
        setInsuranceProfile(insuranceProfile);

        // Populate the text fields with the corresponding data
        if (user != null && insuranceProfile != null) {
            patientName.setText(user.getUsername());
            email.setText(user.getEmail());
            telephone.setText(String.valueOf(user.getPhone()));
            insurancetype.setText(insuranceProfile.getInsuranceType().name());
            expdate.setText(insuranceProfile.getInsuranceExpDate().toString());
            insurancestatus.setText(insuranceProfile.getInsuranceStatus().name());

            // Set other text fields with the corresponding data
        }
    }

    @FXML
    private void handleListPurchases(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("List_Purchases.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

            // Close or hide the current stage
            Stage currentStage = (Stage) listpurchases.getScene().getWindow();
            currentStage.close(); // or currentStage.hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleListRefunds(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("List_Refunds.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

            // Close or hide the current stage
            Stage currentStage = (Stage) listrefunds.getScene().getWindow();
            currentStage.close(); // or currentStage.hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
