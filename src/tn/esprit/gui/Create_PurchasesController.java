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
import tn.esprit.services.DocMeetupPurchaseService;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.stage.Stage;
import tn.esprit.entities.Refund;
import tn.esprit.entities.Refund.RefundMethod;

public class Create_PurchasesController implements Initializable {

    @FXML
    private ChoiceBox<DocMeetupPurchase.ProductType> product;
    @FXML
    private ChoiceBox<Integer> units;
    @FXML
    private TextField cost;
    @FXML
    private TextField attachmentField;
    @FXML
    private Button createPurchaseButton;
    @FXML
    private Button uploadButton;

    private int userId; // Variable to store the user ID
    private DocMeetupPurchaseService docMeetupPurchaseService;
    @FXML
    private Button cancel;
    @FXML
    private ChoiceBox<RefundMethod> refundMethod;

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setDocMeetupPurchaseService(DocMeetupPurchaseService docMeetupPurchaseService) {
        this.docMeetupPurchaseService = docMeetupPurchaseService;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Populate the ChoiceBox with enum values
        ObservableList<DocMeetupPurchase.ProductType> productTypes =
                FXCollections.observableArrayList(DocMeetupPurchase.ProductType.values());
        product.setItems(productTypes);
        
        // Populate the refundMethod ChoiceBox with enum values
        ObservableList<RefundMethod> refundMethods = FXCollections.observableArrayList(RefundMethod.values());
        refundMethod.setItems(refundMethods);
    

        // Populate the units ChoiceBox with numbers from 1 to 10
        ObservableList<Integer> unitOptions = FXCollections.observableArrayList();
        for (int i = 1; i <= 10; i++) {
            unitOptions.add(i);
        }
        units.setItems(unitOptions);
    }
    
    @FXML
    public void createDocMeetupPurchase(ActionEvent event) {
        // Retrieve the selected product type
        DocMeetupPurchase.ProductType selectedProductType = product.getValue();

        // Retrieve the selected number of units
        Integer selectedUnits = units.getValue();

        // Retrieve the entered cost
        int enteredCost = Integer.parseInt(cost.getText());

        // Create a new DocMeetupPurchase object with the retrieved values
        DocMeetupPurchase docMeetupPurchase = new DocMeetupPurchase();
        docMeetupPurchase.setUserId(userId); // Set the userId
        docMeetupPurchase.setProductType(selectedProductType);
        docMeetupPurchase.setProductCost(enteredCost);
        docMeetupPurchase.setNumUnits(selectedUnits);

        // Call the service method to create the DocMeetupPurchase
        if (docMeetupPurchaseService != null) {
            docMeetupPurchaseService.createDocMeetupPurchase(docMeetupPurchase);
        }

        // Display a success message or perform any other necessary actions
        System.out.println("DocMeetup purchase created successfully.");
    }

    @FXML
    public void handleUpload(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose File to Upload");
        File file = fileChooser.showOpenDialog(uploadButton.getScene().getWindow());
        if (file != null) {
            // Process the selected file
            String filePath = file.getAbsolutePath();
            attachmentField.setText(filePath);
        }
    }

    public Button getCreatePurchaseButton() {
        return createPurchaseButton;
    }

    public Button getUploadButton() {
        return uploadButton;
    }

    @FXML
    private void cancel(ActionEvent event) {
    // Close the current stage
    Stage currentStage = (Stage) cancel.getScene().getWindow();
    currentStage.close();
}
}
