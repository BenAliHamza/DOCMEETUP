    /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */
    package tn.esprit.gui.consultations.controllers;

    import java.net.URL;
    import java.util.ResourceBundle;
    import javafx.beans.property.SimpleStringProperty;
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
    import tn.esprit.entities.Analysis;
    import tn.esprit.entities.Medication;
    import tn.esprit.services.AnalysisService;
    import tn.esprit.services.MedicationService;
    import tn.esprit.services.searchservice;

    public class SearchController implements Initializable {

        @FXML
        private TextField filterField;
        @FXML
        private TableView<Medication> tvsearch;
        @FXML
        private TableColumn<Medication, String> tvname;
        @FXML
        private TableColumn<Medication, Float> tvprice;


        @FXML
        private TableView<Analysis> tvanalysis;
        @FXML
        private TableColumn<Medication,String> pharmacie;
        @FXML
        private TableColumn<Analysis, String> nameanalyse;
        @FXML
        private TableColumn<Analysis, String> priceanalyse;
        @FXML
        private Button buttonbuy;
        @FXML
        private TextField tfsearchana;

        @Override
        public void initialize(URL url, ResourceBundle rb) {
            showmedic();
           showanalyse();
        }

          public void showanalyse() {
              AnalysisService AnalysisService = new AnalysisService();
            ObservableList<Analysis> list = AnalysisService.Readallanalyse();

        nameanalyse.setCellValueFactory(new PropertyValueFactory<>("Analysis_name"));
        priceanalyse.setCellValueFactory(new PropertyValueFactory<>("price"));

        tvanalysis.setItems(list);
    }


       public void showmedic() {
        searchservice searchservice = new searchservice();
        ObservableList<Medication> list = searchservice.Readall();

        tvname.setCellValueFactory(new PropertyValueFactory<>("medication_name"));
        tvprice.setCellValueFactory(new PropertyValueFactory<>("price"));
        pharmacie.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPharmacy().getPharmacy_name()));
         tvsearch.setItems(list);
    }


        @FXML
        private void search(KeyEvent event) {

        searchservice searchservice = new searchservice();
            ObservableList <Medication> list = searchservice.Readall();
            tvname.setCellValueFactory(new PropertyValueFactory<>("medication_name"));
            tvprice.setCellValueFactory(new PropertyValueFactory<>("price"));
            tvsearch.setItems(list);
            FilteredList<Medication> filteredData = new FilteredList<>(list, b -> true);
            filterField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(Medication -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();

                  if (Medication.getMedication_name().toLowerCase().contains(lowerCaseFilter)) {
                        return true; 
                    }
                    if (Medication.getPharmacy().getPharmacy_name().toLowerCase().contains(lowerCaseFilter)) {
                        return true; 
                    }


                    else if (String.valueOf(Medication.getPrice()).indexOf(lowerCaseFilter) != -1) {
                        return true;
                    }
                    else {
                        return false;
                    }
                });
            });
            SortedList<Medication> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(tvsearch.comparatorProperty());
            tvsearch.setItems(sortedData);
        }

        @FXML
        private void buy(MouseEvent event) {

             Medication selectedMedication = tvsearch.getSelectionModel().getSelectedItem();
        if (selectedMedication != null && selectedMedication.getStock() > 0) {
            int newStock = selectedMedication.getStock() - 1;
            selectedMedication.setStock(newStock);
            MedicationService medicationService = new MedicationService();
            medicationService.Update(selectedMedication);
            // Update the table view or any other necessary UI elements
            tvsearch.refresh();
        }


        }

        @FXML
        private void searchan(KeyEvent event) {
         AnalysisService analysisService = new AnalysisService();
    ObservableList<Analysis> list = analysisService.Readallanalyse();
    nameanalyse.setCellValueFactory(new PropertyValueFactory<>("Analysis_name"));
    priceanalyse.setCellValueFactory(new PropertyValueFactory<>("price"));
    tvanalysis.setItems(list);
        FilteredList<Analysis> filteredData = new FilteredList<>(list, b -> true);
        tfsearchana.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Analysis -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

              if (Analysis.getAnalysis_name().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches username
                }
                if (String.valueOf(Analysis.getPrice()).indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches username
                }
                
                
               
          
                else {
                    return false;
                }
            });
        });
        SortedList<Analysis> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tvanalysis.comparatorProperty());
        tvanalysis.setItems(sortedData);
    }

   
}