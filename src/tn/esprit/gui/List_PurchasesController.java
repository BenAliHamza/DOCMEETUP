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
import tn.esprit.entities.DocMeetupPurchase;
import tn.esprit.services.DocMeetupPurchaseService;

public class List_PurchasesController implements Initializable {

    @FXML
    private TableView<DocMeetupPurchase> purchasesTable;
    @FXML
    private TableColumn<DocMeetupPurchase, Integer> purchaseIdColumn;
    @FXML
    private TableColumn<DocMeetupPurchase, String> productColumn;
    @FXML
    private TableColumn<DocMeetupPurchase, Integer> quantityColumn;
    @FXML
    private TableColumn<DocMeetupPurchase, Double> priceColumn;
    @FXML
    private TableColumn<DocMeetupPurchase, String> dateColumn;
    @FXML
    private Button returnButton;

    private int userId; // Variable to store the user ID
    private DocMeetupPurchaseService purchaseService;
    @FXML
    private Button addButton;

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        purchaseService = new DocMeetupPurchaseService();
        initializeTableColumns();
        populatePurchases();
    }

    public void initializeTableColumns() {
        purchaseIdColumn.setCellValueFactory(new PropertyValueFactory<>("purchaseId"));
        productColumn.setCellValueFactory(new PropertyValueFactory<>("productType"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("numUnits"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("productCost"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("purchaseDate"));
    }

    public void populatePurchases() {
        List<DocMeetupPurchase> purchases = purchaseService.getPurchasesByUserId(userId);
        purchasesTable.getItems().setAll(purchases);
    }

    public void handleReturnToInsuranceProfile(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("View_InsuranceProfile.fxml"));
            Parent root = loader.load();

            // Pass the user ID to the View_InsuranceProfileController
            View_InsuranceProfileController profileController = loader.getController();
            profileController.setUserId(userId);

            Scene scene = new Scene(root);
            Stage stage = (Stage) returnButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("Error loading View_InsuranceProfile.fxml: " + e.getMessage());
        }
    }

    @FXML
    public void handleAddPurchase(ActionEvent event) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Create_Purchases.fxml"));
        Parent root = loader.load();

        // Pass the user ID to the CRUDPurchaseController
        CRUDPurchasesController createPurchaseController = loader.getController();
        createPurchaseController.setUserId(userId);

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    } catch (IOException e) {
        System.out.println("Error loading Create_Purchase.fxml: " + e.getMessage());
    }
}


}
