package tn.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tn.esprit.entities.Refund;
import tn.esprit.services.RefundService;

public class List_RefundsController implements Initializable {

    @FXML
    private Button insuranceprofile;
    @FXML
    private Button listpurchases;

    private int userId;

    @FXML
    private TableView<Refund> refundTable;
    @FXML
    private TableColumn<Refund, Integer> refundIdColumn;
    @FXML
    private TableColumn<Refund, Integer> userIdColumn;
    @FXML
    private TableColumn<Refund, Integer> insuranceProfileIdColumn;
    @FXML
    private TableColumn<Refund, Double> refundAmountColumn;
    @FXML
    private TableColumn<Refund, Refund.RefundStatus> refundStatusColumn;
    @FXML
    private TableColumn<Refund, Refund.RefundMethod> refundMethodColumn;
    @FXML
    private TableColumn<Refund, String> refundCommentsColumn;
    @FXML
    private TableColumn<Refund, Integer> purchaseIdColumn;
    @FXML
    private Button Refresh;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeTableColumns();
    }

    public void setUserId(int userId) {
        this.userId = userId;
        populateRefunds();
    }

    public void initializeTableColumns() {
        refundIdColumn.setCellValueFactory(new PropertyValueFactory<>("refundId"));
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        insuranceProfileIdColumn.setCellValueFactory(new PropertyValueFactory<>("insuranceProfileId"));
        refundAmountColumn.setCellValueFactory(new PropertyValueFactory<>("refundAmount"));
        refundStatusColumn.setCellValueFactory(new PropertyValueFactory<>("refundStatus"));
        refundMethodColumn.setCellValueFactory(new PropertyValueFactory<>("refundMethod"));
        refundCommentsColumn.setCellValueFactory(new PropertyValueFactory<>("refundComments"));
        purchaseIdColumn.setCellValueFactory(new PropertyValueFactory<>("purchaseId"));
    }

    public void populateRefunds() {
        RefundService refundService = new RefundService();
        List<Refund> refunds = refundService.getRefundsByUserId(userId);
        refundTable.getItems().setAll(refunds);
    }

    @FXML
private void handleInsuranceProfile2(ActionEvent event) {
    // Close the current stage
    Stage currentStage = (Stage) insuranceprofile.getScene().getWindow();
    currentStage.close();
}





    @FXML
    public void handleListPurchases(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("List_Purchases.fxml"));
            Parent root = loader.load();

            // Pass the user ID to the List_PurchasesController
            List_PurchasesController purchasesController = loader.getController();
            purchasesController.setUserId(userId);

            // Populate the purchases
            purchasesController.populatePurchases();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("Error loading List_Purchases.fxml: " + e.getMessage());
        }
    }

    @FXML
private void Refresh(ActionEvent event) {
    populateRefunds();
}

    
}