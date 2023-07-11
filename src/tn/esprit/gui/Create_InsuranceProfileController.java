package tn.esprit.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import tn.esprit.entities.User;
import tn.esprit.entities.InsuranceProfile.InsuranceType;

public class Create_InsuranceProfileController implements Initializable {

    @FXML
    private Button btcreate;
    @FXML
    private ChoiceBox<InsuranceType> cbtype; // Updated the generic type to InsuranceType
    @FXML
    private Text textcost;
    @FXML
    private TextField tmail;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Populate the ChoiceBox with insurance type values
        cbtype.getItems().addAll(InsuranceType.values());
    }

    @FXML
    private void createInsuranceProfile(ActionEvent event) {
        // Implement the logic to create the insurance profile
    }

    void setUser(User user) {
        // Set the user details in the controller
        tmail.setText(user.getEmail());
        // Set other user details as needed
    }
}
