/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui.consultations.controllers;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import tn.esprit.entities.Medication;
import tn.esprit.entities.Pharmacy;
import tn.esprit.services.MedicationService;
import tn.esprit.services.PharmacyService;

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
    @FXML
    private Button btndeletemedic;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     showmedic();
        // TODO
    }    
    @FXML
//    public void addmedic(ActionEvent event){
//        MedicationService medic = new MedicationService();
//        
//        String medicname = txmedicname.getText();
//        String description = txdescription.getText();
//        String addinfo = txinfo.getText();
//        float price = Float.parseFloat(txprice.getText());
//        int stock = Integer.parseInt(txstock.getText());
// 
//        Medication medica = new Medication(medicname,description,addinfo,price,stock);
//        medic.Create(medica);
// 
//        showmedic();
//        clear();
//
//}
    

    public void addmedic(ActionEvent event) {
    MedicationService medic = new MedicationService();

    String medicname = txmedicname.getText();
    String description = txdescription.getText();
    String addinfo = txinfo.getText();
    float price = 0.0f;
    int stock = 0;

    // Vérifier si les champs sont vides
    if (medicname.isEmpty() || description.isEmpty() || addinfo.isEmpty() || txprice.getText().isEmpty() || txstock.getText().isEmpty()) {
        // Afficher un message d'erreur indiquant que tous les champs doivent être remplis
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs");
        alert.showAndWait();
        return;
    }

    try {
        // Vérifier si les champs de prix et de stock contiennent des valeurs numériques valides
        price = Float.parseFloat(txprice.getText());
        stock = Integer.parseInt(txstock.getText());
    } catch (NumberFormatException e) {
        // Afficher un message d'erreur indiquant que les champs de prix et de stock doivent être des nombres valides
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Le prix et la quantité doivent être des nombres valides");
        alert.showAndWait();
        return;
    }

    // Vérifier les contraintes pour les champs de prix et de stock
    if (price <= 0.0f || stock <= 0) {
        // Afficher un message d'erreur indiquant que les champs de prix et de stock doivent être supérieurs à zéro
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Le prix et la quantité doivent être supérieurs à zéro");
        alert.showAndWait();
        return;
    }
        PharmacyService ps=new PharmacyService();
        Pharmacy pharma = new Pharmacy();
        pharma=ps.toPharmacy(HomePageController.getUser());
        
    // Si toutes les validations sont passées, créer l'objet Medication et l'ajouter
  //  Medication medica = new Medication(medicname, description, addinfo, price, stock);
    Medication medica = new Medication(pharma,medicname, description, addinfo, price, stock);
    medic.Create(medica);

    showmedic();
    clear();
}

    

    
    public void showmedic(){
        
         MedicationService medic = new MedicationService();
        ObservableList <Medication> list = medic.ReadbyUser(HomePageController.getUser());
        tvmedicname.setCellValueFactory(new PropertyValueFactory<Medication,String>("medication_name"));
        tvdescription.setCellValueFactory(new PropertyValueFactory<Medication,String>("description"));
        tvinfo.setCellValueFactory(new PropertyValueFactory<Medication,String>("additional_information"));
        tvprice.setCellValueFactory(new PropertyValueFactory<Medication,Float>("price"));
         tvstock.setCellValueFactory(new PropertyValueFactory<Medication,Integer>("stock"));
 

        //System.out.print("test");
        tvmedic.setItems(list);
          
        
}

//        MedicationService medic = new MedicationService();
//        
//        Medication medi  =tvmedic.getSelectionModel().getSelectedItem();
//                int selectedID = tvmedic.getSelectionModel().getSelectedIndex();
//                System.out.println(medi.getMedication_id());
//        medic.Delete(medi.getMedication_id());
//        tvmedic.getItems().remove(medi);
//        showmedic();
//      clear();
//        medic.smsreporting();
//
//    
//}
   
    @FXML
    public void deletemedic(ActionEvent event) {
    MedicationService medic = new MedicationService();

    Medication medi = tvmedic.getSelectionModel().getSelectedItem();
    int selectedID = tvmedic.getSelectionModel().getSelectedIndex();
    System.out.println(medi.getMedication_id());

    // Afficher une boîte de dialogue de confirmation avant de supprimer
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation de suppression");
    alert.setHeaderText(null);
    alert.setContentText("Êtes-vous sûr de vouloir supprimer cette médication ?");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {
        medic.Delete(medi.getMedication_id());
        tvmedic.getItems().remove(medi);
        showmedic();
        clear();
        //medic.smsreporting();
    }
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
//    private void updateM(ActionEvent event) {
//        MedicationService medic = new MedicationService();
//        Medication z  =tvmedic.getSelectionModel().getSelectedItem();
//        int selectedID = tvmedic.getSelectionModel().getSelectedIndex();
//        
//        int id = z.getMedication_id();
//        String medicname = txmedicname.getText();
//        String description = txdescription.getText();
//        String addinfo = txinfo.getText();
//        float price = Float.parseFloat(txprice.getText());
//        int stock = Integer.parseInt(txstock.getText());
//        int k = z.getMedication_id();
//
//            Medication medica = new Medication(k, medicname, description, addinfo, price, stock);
//            medic.Update(medica);
//            showmedic();
//
//
//    }
 
    

    private void updateM(ActionEvent event) {
    MedicationService medic = new MedicationService();
    Medication z = tvmedic.getSelectionModel().getSelectedItem();
    int selectedID = tvmedic.getSelectionModel().getSelectedIndex();
    
    int id = z.getMedication_id();
    String medicname = txmedicname.getText();
    String description = txdescription.getText();
    String addinfo = txinfo.getText();
    float price = 0.0f;
    int stock = 0;

    if (medicname.isEmpty() || description.isEmpty() || addinfo.isEmpty() || txprice.getText().isEmpty() || txstock.getText().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs.");
        alert.showAndWait();
        return;
    }

    try {
        price = Float.parseFloat(txprice.getText());
        stock = Integer.parseInt(txstock.getText());

        if (price <= 0.0f || stock <= 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Le prix et la quantité doivent être supérieurs à zéro.");
            alert.showAndWait();
            return;
        }
    } catch (NumberFormatException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText(null);
        alert.setContentText("Le prix et la quantité doivent être des nombres valides.");
        alert.showAndWait();
        return;
    }

    Medication medica = new Medication(id, medicname, description, addinfo, price, stock);
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
