/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import tn.esprit.entities.FollowUp;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import tn.esprit.services.FollowUpService;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyEvent;
import tn.esprit.entities.Analysis;
import tn.esprit.entities.Medication;
import tn.esprit.entities.User;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class FollowUserIController implements Initializable {

    @FXML
    private TextField txdbp;
    @FXML
    private DatePicker txDate;
    @FXML
    private TextField txheartrate;
    @FXML
    private TextField txtemp;
    @FXML
    private TextField txweight;
    @FXML
    private TextField filterField;
    @FXML
    private TableView<FollowUp> tvfollowup;
    @FXML
    private TableColumn<FollowUp, String> tvbp;
    @FXML
    private TableColumn<FollowUp, Date> tvdate;
    @FXML
    private TableColumn<FollowUp, Integer> tvheart;
    @FXML
    private TableColumn<FollowUp, Float> tvtemp;
    @FXML
    private TableColumn<FollowUp, Float> tvweight;
    @FXML
    private Button btnadd;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btndelete;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showfollow();
            java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());

        //System.out.println(currentDate);
 

// TODO
    }

    public void showfollow() {
        FollowUpService serv = new FollowUpService();

        ObservableList<FollowUp> list = serv.listfo();
        tvbp.setCellValueFactory(new PropertyValueFactory<FollowUp, String>("blood_pressure"));
        tvdate.setCellValueFactory(new PropertyValueFactory<FollowUp, Date>("date"));
        tvheart.setCellValueFactory(new PropertyValueFactory<FollowUp, Integer>("heart_rate"));
        tvtemp.setCellValueFactory(new PropertyValueFactory<FollowUp, Float>("temperature"));
        tvweight.setCellValueFactory(new PropertyValueFactory<FollowUp, Float>("weight"));

        //System.out.print("test");
        tvfollowup.setItems((ObservableList<FollowUp>) list);
    }

    void searcb() {

    }

    /*
    @FXML
    private void selectrow(MouseEvent event) {
        activites act  =tvactivites.getSelectionModel().getSelectedItem();
        System.out.println("id"+act.getId_act());
        
        txnomact.setText(act.nom_act);
        calddebut.setValue(LocalDate.parse(act.d_debut.toString()));
        caldfinal.setValue(LocalDate.parse(act.d_fin.toString()));
        txemplacement.setText(act.emplacement);
        txdescirptionact.setText(act.description);
        Date.valueOf(calddebut.getValue());
        tfnb_personne.setText( String.valueOf(act.nb_personne));
        
    }*/
    @FXML
    private void row(javafx.scene.input.MouseEvent event) {
        FollowUpService serv = new FollowUpService();
        FollowUp follow = tvfollowup.getSelectionModel().getSelectedItem();
        System.out.println(follow.getBlood_pressure());
        txdbp.setText(follow.getBlood_pressure());
        txDate.setValue(LocalDate.parse(follow.getDate().toString()));
        txtemp.setText(String.valueOf(follow.getTemperature()));
        txweight.setText(String.valueOf(follow.getWeight()));
        txheartrate.setText(String.valueOf(follow.getHeart_rate()));
    }

    private boolean validateInput(int heartRate, Date date, float temperature, float weight,String bloodpressure) {
    // Validate input values
    java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
    float bloodPressureValue = Float.parseFloat(bloodpressure);
        System.out.println(currentDate);

    
    if (date.after(currentDate)) {
        Alert alert = new Alert (Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Please check the date");
        alert.showAndWait();
        //System.out.println(date);
        //System.out.println("Future not accepted");
        // Date is in the future
         return false;
    }

    // Validate heart rate
    if (heartRate < 0 || heartRate > 250) {
        // Heart rate is outside the logical range
        Alert alert = new Alert (Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Please check the heart Rate value");
        alert.showAndWait();
        // System.out.println("heartRate");
        return false;
    }

    // Validate temperature
    if (temperature < 35.0f || temperature > 42.0f) {
        // Temperature is outside the logical range
        Alert alert = new Alert (Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Please check the Temperature value");
        alert.showAndWait();
         //System.out.println("temperature");
        return false;
    }

    // Validate weight
    if (weight < 5.0f || weight > 500.0f) {
        // Weight is outside the logical range
        Alert alert = new Alert (Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Please check the weight");
        alert.showAndWait();
         //System.out.println("weight");
        return false;
    }

// Validate blood pressure
if (bloodPressureValue < 0.0f || bloodPressureValue > 150.0f) {
    // Blood pressure is outside the logical range
    Alert alert = new Alert (Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Please check the blood pressure value");
        alert.showAndWait();
     //System.out.println("bloodPressure");
    return false;
}

    return true;
}
    @FXML
    public void addfollow(ActionEvent event) {
    FollowUpService serv = new FollowUpService();

    String bloodPressure = txdbp.getText();
    int heartRate;
    Date date;
    float temperature;
    float weight;
    Date currentDate;

    try {
        heartRate = Integer.parseInt(txheartrate.getText());
        date = Date.valueOf(txDate.getValue());
        temperature = Float.parseFloat(txtemp.getText());
        weight = Float.parseFloat(txweight.getText());
        bloodPressure = txdbp.getText();

        if (!validateInput(heartRate, date, temperature, weight,bloodPressure)==true) {
            // Input validation failed
            return;
        }

        FollowUp f = new FollowUp();
        f.setBlood_pressure(bloodPressure);
        f.setHeart_rate(heartRate);
        f.setDate(date);
        f.setTemperature(temperature);
        f.setWeight(weight);

        serv.Create(f);
        showfollow();
    } catch (NumberFormatException e) {
        // Handle number format exceptions (e.g., when parsing integers or floats)
        // Handle the validation error
    }
}

@FXML
    private void updatefollow(ActionEvent event) {
    FollowUp follow = tvfollowup.getSelectionModel().getSelectedItem();
    FollowUpService serv = new FollowUpService();
    String bloodPressure = txdbp.getText();
    int heartRate = Integer.parseInt(txheartrate.getText());
    Date date = Date.valueOf(txDate.getValue());
    float temperature = Float.parseFloat(txtemp.getText());
    float weight = Float.parseFloat(txweight.getText());
    int a = follow.getFollowup_id();
    User test = new User(follow.getUser().getUser_id());
        bloodPressure = txdbp.getText();

    if (!validateInput(heartRate, date, temperature, weight,bloodPressure)==true) {
        // Input validation failed
        return;
    }

    FollowUp f = new FollowUp(a, test, date, bloodPressure, heartRate, temperature, weight);
    System.out.println(bloodPressure);
    serv.Update(f);
    showfollow();
}


    @FXML
    private void deletefollow(ActionEvent event) {
        FollowUpService serv = new FollowUpService();
        FollowUp follow = tvfollowup.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Please confirme your action");
        Optional<ButtonType> result = alert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {
        serv.Delete(follow);
        int selectedID = tvfollowup.getSelectionModel().getSelectedIndex();
        tvfollowup.getItems().remove(selectedID);
        showfollow();
        //System.out.println("suppression suppression avec succés ");
    }
    }

    @FXML
    private void search(KeyEvent event) {
        FollowUpService serv = new FollowUpService();

        ObservableList<FollowUp> dataList = serv.listfo();
        tvbp.setCellValueFactory(new PropertyValueFactory<FollowUp, String>("blood_pressure"));
        tvdate.setCellValueFactory(new PropertyValueFactory<FollowUp, Date>("date"));
        tvheart.setCellValueFactory(new PropertyValueFactory<FollowUp, Integer>("heart_rate"));
        tvtemp.setCellValueFactory(new PropertyValueFactory<FollowUp, Float>("temperature"));
        tvweight.setCellValueFactory(new PropertyValueFactory<FollowUp, Float>("weight"));

        //System.out.print("test");
        tvfollowup.setItems(dataList);

        FilteredList<FollowUp> filteredData = new FilteredList<>(dataList, b -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(FollowUp -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (FollowUp.getBlood_pressure().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches bloodpressure
                } else if (String.valueOf(FollowUp.getDate()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<FollowUp> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tvfollowup.comparatorProperty());
        tvfollowup.setItems(sortedData);
    }
    @FXML
    private void pdf(ActionEvent event) throws FileNotFoundException, SQLException, DocumentException {
        FollowUpService serv = new FollowUpService();

        ObservableList<FollowUp> all, Single;
        all = tvfollowup.getItems();
        Single = tvfollowup.getSelectionModel().getSelectedItems();
        FollowUp A = Single.get(0);
        System.out.println(A);
        String getBlood_pressure = String.valueOf(A.getBlood_pressure());
        String getHeart_rate = String.valueOf(A.getHeart_rate());
        String getWeight = String.valueOf(A.getWeight());
        //String strNumber = String.valueOf(A.getWeight());
        String getTemperature = String.valueOf(A.getTemperature());

        serv.pdf(/*"FollowUp" +*/ getBlood_pressure, getHeart_rate, getWeight, getTemperature);
        System.out.println("PDF OK");
    }

}
