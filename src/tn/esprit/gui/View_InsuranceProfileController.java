package tn.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import tn.esprit.entities.InsuranceProfile;
import tn.esprit.entities.User;
import tn.esprit.services.InsuranceProfileService;
import tn.esprit.services.UserService;

public class View_InsuranceProfileController implements Initializable {
    @FXML
    private Button listrefunds;
    @FXML
    private Button deleteProfileButton;

    @FXML
    private Text patientName;
    @FXML
    private Text email;
    @FXML
    private Text telephone;
    @FXML
    private Text insurancetype;
    @FXML
    private Text expdate;
    @FXML
    private Text insurancestatus;

    private InsuranceProfile insuranceProfile;
    private InsuranceProfileService insuranceProfileService;
    private UserService userService;

    private int userId; // Add the userId variable

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        insuranceProfileService = new InsuranceProfileService();
        userService = new UserService();
    }

    public void setInsuranceProfile(InsuranceProfile insuranceProfile) {
        this.insuranceProfile = insuranceProfile;
    }

    private String userEmail;

    public void initializeData(String userEmail) {
        insuranceProfileService = new InsuranceProfileService();
        userService = new UserService();
        User user = userService.getUserByEmail(userEmail);
        this.userEmail = userEmail;
        InsuranceProfile insuranceProfile = null;

        if (user != null) {
            insuranceProfile = insuranceProfileService.getInsuranceProfileByUserId(user.getUser_id());
            setUserId(user.getUser_id()); // Set the userId
        }

        setInsuranceProfile(insuranceProfile);

        if (user != null && insuranceProfile != null) {
            patientName.setText(user.getUsername());
            email.setText(user.getEmail());
            telephone.setText(String.valueOf(user.getPhone()));
            insurancetype.setText(insuranceProfile.getInsuranceType().name());
            expdate.setText(insuranceProfile.getInsuranceExpDate().toString());
            insurancestatus.setText(insuranceProfile.getInsuranceStatus().name());
        }
    }

    public void setUserId(int userId) {
        this.userId = userId;
        System.out.println("User ID in View_InsuranceProfileController: " + userId);
    }

    @FXML
    private void handleListRefunds(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("List_Refunds.fxml"));
            Parent root = loader.load();

            // Pass the user ID to the List_RefundsController
            List_RefundsController refundsController = loader.getController();
            refundsController.setUserId(userId);

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("Error loading List_Refunds.fxml: " + e.getMessage());
        }
    }

    @FXML
    private void handleDeleteProfile(ActionEvent event) {
        Alert confirmationDialog = new Alert(AlertType.CONFIRMATION);
        confirmationDialog.setTitle("Delete Profile Confirmation");
        confirmationDialog.setHeaderText("Confirm Deletion");
        confirmationDialog.setContentText("Are you sure you want to delete your profile?");

        DialogPane dialogPane = confirmationDialog.getDialogPane();
        confirmationDialog.showAndWait().ifPresent(new Consumer<ButtonType>() {
            @Override
            public void accept(ButtonType result) {
                if (result == ButtonType.OK) {
                    String userEmail1 = email.getText(); // Retrieve the user's email from the view
                    User user = userService.getUserByEmail(userEmail1);

                    if (user != null) {
                        Integer userId = user.getUser_id();
                        if (userId != null) {
                            boolean deleted = insuranceProfileService.deleteInsuranceProfileByUserId(userId);

                            if (deleted) {
                                showDeleteSuccessDialog();
                                // Navigate to the login screen or any other appropriate screen after profile deletion
                            } else {
                                showDeleteErrorDialog();
                            }
                        } else {
                            showDeleteErrorDialog();
                        }
                    } else {
                        showDeleteErrorDialog();
                    }
                }
            }
        });
    }

    public void showDeleteSuccessDialog() {
        Alert successDialog = new Alert(AlertType.INFORMATION);
        successDialog.setTitle("Delete Profile");
        successDialog.setHeaderText(null);
        successDialog.setContentText("Profile deleted successfully!");

        successDialog.showAndWait();

        // Redirect to login_InsuranceProfile.fxml
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login_InsuranceProfile.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

            // Close or hide the current stage
            Stage currentStage = (Stage) deleteProfileButton.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showDeleteErrorDialog() {
        Alert errorDialog = new Alert(AlertType.ERROR);
        errorDialog.setTitle("Delete Profile");
        errorDialog.setHeaderText(null);
        errorDialog.setContentText("An error occurred while deleting the profile.");

        DialogPane dialogPane = errorDialog.getDialogPane();

        errorDialog.showAndWait();
    }

    public String getUserEmail() {
        return userEmail;
    }
    

}
