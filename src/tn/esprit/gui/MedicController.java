/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import tn.esprit.entities.Medication;
import tn.esprit.services.MedicationService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class MedicController implements Initializable {

    @FXML
    private TextField txmedicname;
    @FXML
    private TextField txdescription;
    @FXML
    private TextField txinfo;
    @FXML
    private TextField txprice;
    @FXML
    private TextField txstock;
    @FXML
    private Button btnaddmedic;
    @FXML
    private Button btndeletemedic;
    @FXML
    private TextField filterField;
    @FXML
    private TableView<Medication> tvmedic;
    @FXML
    private TableColumn<Medication, String> tvmedicname;
    @FXML
    private TableColumn<Medication, String> tvdescription;
    @FXML
    private TableColumn<Medication, String> tvinfo;
    @FXML
    private TableColumn<Medication, Float> tvprice;
    @FXML
    private TableColumn<Medication, Integer> tvstock;
    @FXML
    private Button up;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     showmedic();
        // TODO
    }    
    @FXML
    public void addmedic(ActionEvent event){
        MedicationService medic = new MedicationService();
        
        String medicname = txmedicname.getText();
        String description = txdescription.getText();
        String addinfo = txinfo.getText();
        float price = Float.parseFloat(txprice.getText());
        int stock = Integer.parseInt(txstock.getText());
        Medication medica = new Medication(medicname,description,addinfo,price,stock);
        medic.Create(medica);
 
        showmedic();
        clear();

}
    

    
    public void showmedic(){
        
         MedicationService medic = new MedicationService();
     
        ObservableList <Medication> list = medic.Read();
        tvmedicname.setCellValueFactory(new PropertyValueFactory<Medication,String>("medication_name"));
        tvdescription.setCellValueFactory(new PropertyValueFactory<Medication,String>("description"));
        tvinfo.setCellValueFactory(new PropertyValueFactory<Medication,String>("additional_information"));
        tvprice.setCellValueFactory(new PropertyValueFactory<Medication,Float>("price"));
         tvstock.setCellValueFactory(new PropertyValueFactory<Medication,Integer>("stock"));
 

        //System.out.print("test");
        tvmedic.setItems(list);
          
        
}

    @FXML
    public void deletemedic(ActionEvent event){
        MedicationService medic = new MedicationService();
        
        Medication medi  =tvmedic.getSelectionModel().getSelectedItem();
                int selectedID = tvmedic.getSelectionModel().getSelectedIndex();
                System.out.println(medi.getMedication_id());
        medic.Delete(medi.getMedication_id());
        tvmedic.getItems().remove(medi);
        showmedic();
      clear();
        medic.smsreporting();

    
}

      public void clear (){
      
  txmedicname.clear();
             txdescription.clear();
             txinfo.clear();
             txprice.clear();
             txstock.clear();
      
      
      
      }
     @FXML
    private void selectrow(MouseEvent event) {
        Medication medic  =tvmedic.getSelectionModel().getSelectedItem();
        int selectedID = tvmedic.getSelectionModel().getSelectedIndex();
             txmedicname.setText(medic.getMedication_name());
             txdescription.setText(medic.getDescription());
             txinfo.setText(medic.getAdditional_information());
             txprice.setText(String.valueOf(medic.getPrice()));
             txstock.setText(String.valueOf(medic.getStock()));
              System.out.println(medic.getMedication_id());
              


     }

    @FXML
    private void updateM(ActionEvent event) {
        MedicationService medic = new MedicationService();
        Medication z  =tvmedic.getSelectionModel().getSelectedItem();
        int selectedID = tvmedic.getSelectionModel().getSelectedIndex();
        
        int id = z.getMedication_id();
        String medicname = txmedicname.getText();
        String description = txdescription.getText();
        String addinfo = txinfo.getText();
        float price = Float.parseFloat(txprice.getText());
        int stock = Integer.parseInt(txstock.getText());
        int k = z.getMedication_id();

            Medication medica = new Medication(k, medicname, description, addinfo, price, stock);
            medic.Update(medica);
            showmedic();


    }
 
    @FXML
    private void search(KeyEvent event) {
        
             MedicationService medic = new MedicationService();
        ObservableList <Medication> list = medic.Read();
        tvmedicname.setCellValueFactory(new PropertyValueFactory<Medication,String>("medication_name"));
        tvdescription.setCellValueFactory(new PropertyValueFactory<Medication,String>("description"));
        tvinfo.setCellValueFactory(new PropertyValueFactory<Medication,String>("additional_information"));
        tvprice.setCellValueFactory(new PropertyValueFactory<Medication,Float>("price"));
         tvstock.setCellValueFactory(new PropertyValueFactory<Medication,Integer>("stock"));
 

        //System.out.print("test");
        tvmedic.setItems(list);
        
        FilteredList<Medication> filteredData = new FilteredList<>(list, b -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Medication -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (Medication.getMedication_name().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches username
                } else if (String.valueOf(Medication.getPrice()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                else if (String.valueOf(Medication.getStock()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<Medication> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tvmedic.comparatorProperty());
        tvmedic.setItems(sortedData);
    }

}
