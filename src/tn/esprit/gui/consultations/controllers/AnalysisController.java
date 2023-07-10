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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import tn.esprit.entities.Analysis;
import tn.esprit.services.AnalysisService;


/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AnalysisController implements Initializable {

    @FXML
    private TextField filterField;
    
    @FXML
    private TextField txpriceanaly;
    @FXML
    private Button btnaddanaly;
    @FXML
    private Button btndeleteanaly;
    @FXML
    private TableColumn<Analysis,String> tvanalyname;
    @FXML
    private TableColumn<Analysis,String> tvdescriptionanaly;
    @FXML
    private TableColumn<Analysis,Float> tvpriceanaly;
    @FXML
    private Button upanaly;
    @FXML
    private TextField txanalyname;
    @FXML
    private TextField txresulttypeanaly;
    @FXML
    private TableView<Analysis> tvanaly;
    @FXML
    private TextField txdescriptionanaly;
    @FXML
    private TableColumn<Analysis,String> tvresulttypeanaly;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showanaly();
        // TODO
    }    


    @FXML
    private void search(KeyEvent event) {
      
        AnalysisService analy = new AnalysisService();
        ObservableList <Analysis> list = analy.Read();
        tvanalyname.setCellValueFactory(new PropertyValueFactory<Analysis,String>("analysis_name"));
        tvdescriptionanaly.setCellValueFactory(new PropertyValueFactory<Analysis,String>("description"));
        tvresulttypeanaly.setCellValueFactory(new PropertyValueFactory<Analysis,String>("result_type"));
        tvpriceanaly.setCellValueFactory(new PropertyValueFactory<Analysis,Float>("price"));

        tvanaly.setItems(list);
   
        
        FilteredList<Analysis> filteredData = new FilteredList<>(list, b -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Analysis -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (Analysis.getAnalysis_name().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches username
                } else if (String.valueOf(Analysis.getPrice()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                 else {
                    return false;
                }
            });
        });
        SortedList<Analysis> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tvanaly.comparatorProperty());
        tvanaly.setItems(sortedData);
    }
                

    @FXML
    private void selectrow(MouseEvent event) {
        
        Analysis analy  =tvanaly.getSelectionModel().getSelectedItem();
        int selectedID = tvanaly.getSelectionModel().getSelectedIndex();
             txanalyname.setText(analy.getAnalysis_name());
             txresulttypeanaly.setText(analy.getResult_type());
             txdescriptionanaly.setText(analy.getDescription());
             txpriceanaly.setText(String.valueOf(analy.getPrice()));

    }

    @FXML
//    private void addanaly(ActionEvent event) {
//        AnalysisService analy = new AnalysisService();
//        
//        String analyname = txanalyname.getText();
//        String description = txdescriptionanaly.getText();
//        String result_type = txresulttypeanaly.getText();
//        float price = Float.parseFloat(txpriceanaly.getText());
//       Analysis ana = new Analysis(analyname,description,result_type,price);
//       analy.Create(ana);
//       
//             showanaly();
//             clear();
//    }


private void addanaly(ActionEvent event) {
    // Récupérer les valeurs saisies dans les champs de texte
    String analyname = txanalyname.getText();
    String description = txdescriptionanaly.getText();
    String result_type = txresulttypeanaly.getText();
    float price;

    // Vérifier si les champs sont vides
    if (analyname.isEmpty() || description.isEmpty() || result_type.isEmpty() || txpriceanaly.getText().isEmpty()) {
        // Afficher une boîte de dialogue d'alerte avec le message d'erreur
        showAlert(AlertType.ERROR, "Erreur de saisie", "Veuillez remplir tous les champs.", "Veuillez s\u00e9lectionner une analyse \u00e0 supprimer.");
        return; // Arrêter l'exécution de la méthode
    }

    // Vérifier si le prix est valide
    try {
        price = Float.parseFloat(txpriceanaly.getText());
        if (price <= 0) {
            // Afficher une boîte de dialogue d'alerte avec le message d'erreur
            showAlert(AlertType.ERROR, "Erreur de saisie", "Le prix doit être supérieur à zéro.", "Veuillez s\u00e9lectionner une analyse \u00e0 supprimer.");
            return; // Arrêter l'exécution de la méthode
        }
    } catch (NumberFormatException e) {
        // Afficher une boîte de dialogue d'alerte avec le message d'erreur
        showAlert(AlertType.ERROR, "Erreur de saisie", "Le prix doit être un nombre valide.", "Veuillez s\u00e9lectionner une analyse \u00e0 supprimer.");
        return; // Arrêter l'exécution de la méthode
    }

    // Si les contrôles de saisie sont valides, créer l'objet Analysis et l'ajouter à la base de données
    AnalysisService analy = new AnalysisService();
    Analysis ana = new Analysis(analyname, description, result_type, price);
    analy.Create(ana);

    // Actualiser l'affichage des analyses
    showanaly();
    // Vider les champs de texte
    clear();
}

// Méthode utilitaire pour afficher une boîte de dialogue d'alerte
private void showAlert(AlertType alertType, String title, String message, String veuillez_sélectionner_une_analyse_à_suppr) {
    Alert alert = new Alert(alertType);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}
    
//     @FXML
//    private void deleteanaly(ActionEvent event) {
//        
//        AnalysisService analy = new AnalysisService();        
//        Analysis ana  =tvanaly.getSelectionModel().getSelectedItem();
//        int selectedID = tvanaly.getSelectionModel().getSelectedIndex();
//        System.out.println(ana.getAnalysis_id());
//        analy.Delete(ana.getAnalysis_id());
//        tvanaly.getItems().remove(ana);
//        showanaly();
//        clear();
//        analy.smsreporting();
//
//    }
    @FXML
    private void deleteanaly(ActionEvent event) {
    Analysis selectedAnalysis = tvanaly.getSelectionModel().getSelectedItem();
    if (selectedAnalysis == null) {
        // Aucune analyse sélectionnée, afficher un message d'erreur
        showAlert(Alert.AlertType.ERROR, "Erreur", "Aucune analyse sélectionnée",
                "Veuillez sélectionner une analyse à supprimer.");
        return;
    }
    
    // Affichage d'une boîte de dialogue de confirmation
    Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
    confirmationDialog.setTitle("Confirmation de suppression");
    confirmationDialog.setHeaderText("Êtes-vous sûr de vouloir supprimer cette analyse ?");
    confirmationDialog.setContentText("Cette action est irréversible.");
    Optional<ButtonType> result = confirmationDialog.showAndWait();
    
    if (result.isPresent() && result.get() == ButtonType.OK) {
        // Suppression de l'analyse
        AnalysisService analyService = new AnalysisService();
        analyService.Delete(selectedAnalysis.getAnalysis_id());
        
        // Affichage d'un message de succès
        showAlert(Alert.AlertType.INFORMATION, "Succès", "Analyse supprimée",
                "L'analyse a été supprimée avec succès.");
        
        // Actualisation de l'affichage
        showanaly();
        clear();
        analyService.smsreporting();
    }
}

    
    

    


    @FXML
//    private void updateanaly(ActionEvent event) {
//        
//        AnalysisService analy = new AnalysisService();
//        Analysis z  =tvanaly.getSelectionModel().getSelectedItem();
//        int selectedID = tvanaly.getSelectionModel().getSelectedIndex();
//       
//        String analyname = txanalyname.getText();
//        String description = txdescriptionanaly.getText();
//        String result_type = txresulttypeanaly.getText();
//        float price = Float.parseFloat(txpriceanaly.getText());
//        int k = z.getAnalysis_id();
//
//            Analysis ana = new Analysis(k, analyname, description, result_type, price);
//            analy.Update(ana);
//            showanaly();
//    }
//    


    private void updateanaly(ActionEvent event) {
    AnalysisService analy = new AnalysisService();
    Analysis z = tvanaly.getSelectionModel().getSelectedItem();
    int selectedID = tvanaly.getSelectionModel().getSelectedIndex();

    String analyname = txanalyname.getText();
    String description = txdescriptionanaly.getText();
    String result_type = txresulttypeanaly.getText();
    float price;
    
    if (analyname.isEmpty() || description.isEmpty() || result_type.isEmpty() || txpriceanaly.getText().isEmpty()) {
        showAlert(AlertType.ERROR, "Erreur de saisie", "Veuillez remplir tous les champs.", "Veuillez sélectionner une analyse à supprimer.");
        return;
    }

    try {
        price = Float.parseFloat(txpriceanaly.getText());
        if (price <= 0) {
            showAlert(AlertType.ERROR, "Erreur de saisie", "Le prix doit être supérieur à zéro.", "Veuillez sélectionner une analyse à supprimer.");
            return;
        }
    } catch (NumberFormatException e) {
        showAlert(AlertType.ERROR, "Erreur de saisie", "Le prix doit être un nombre valide.", "Veuillez sélectionner une analyse à supprimer.");
        return;
    }

    int k = z.getAnalysis_id();
    Analysis ana = new Analysis(k, analyname, description, result_type, price);
    analy.Update(ana);
    showanaly();
}

    public void showanaly(){
        
         AnalysisService analy = new AnalysisService();
     
        ObservableList <Analysis> list = analy.Read();
        tvanalyname.setCellValueFactory(new PropertyValueFactory<Analysis,String>("analysis_name"));
        tvdescriptionanaly.setCellValueFactory(new PropertyValueFactory<Analysis,String>("description"));
        tvresulttypeanaly.setCellValueFactory(new PropertyValueFactory<Analysis,String>("result_type"));
        tvpriceanaly.setCellValueFactory(new PropertyValueFactory<Analysis,Float>("price"));

        tvanaly.setItems(list);
    }
    
    public void clear (){
      
             txanalyname.clear();
             txdescriptionanaly.clear();
             txresulttypeanaly.clear();
             txpriceanaly.clear();
            
      
      
      
      }
    
}
