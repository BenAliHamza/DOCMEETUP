package tn.esprit.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import tn.esprit.entities.DocMeetupPurchase;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class CRUDPurchasesController implements Initializable {

    @FXML
    private ChoiceBox<DocMeetupPurchase.ProductType> product;
    @FXML
    private ChoiceBox<Integer> units;
    @FXML
    private TextField cost;
    @FXML
    private Button createpurchase;
    @FXML
    private TextField attachmentField;
    @FXML
    private Button upload;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Populate the ChoiceBox with enum values
        ObservableList<DocMeetupPurchase.ProductType> productTypes = FXCollections.observableArrayList(DocMeetupPurchase.ProductType.values());
        product.setItems(productTypes);

        // Populate the units ChoiceBox with numbers from 1 to 10
        ObservableList<Integer> unitOptions = FXCollections.observableArrayList();
        for (int i = 1; i <= 10; i++) {
            unitOptions.add(i);
        }
        units.setItems(unitOptions);
    }

    @FXML
    private void createDocMeetupPurchase(ActionEvent event) {
        // Handle create purchase action
    }

    @FXML
    private void handleUpload(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose File to Upload");
        File file = fileChooser.showOpenDialog(upload.getScene().getWindow());
        if (file != null) {
            // Process the selected file
            String filePath = file.getAbsolutePath();
            attachmentField.setText(filePath);
        }
    }
}
