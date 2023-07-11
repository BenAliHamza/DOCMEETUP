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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author siwar
 */
public class List_RefundsController implements Initializable {

    @FXML
    private Button insuranceprofile;
    @FXML
    private Button listrefunds;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private void handleInsuranceProfile(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("View_InsuranceProfile.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

            // Close or hide the current stage
            Stage currentStage = (Stage) insuranceprofile.getScene().getWindow();
            currentStage.close(); // or currentStage.hide();
        } catch (IOException e) {
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

        // Close the current stage (List_Refunds.fxml)
        Button button = (Button) event.getSource();
        Stage currentStage = (Stage) button.getScene().getWindow();
        currentStage.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

}
