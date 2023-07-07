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
    @FXML
    private Button up;

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
             txresulttypeanaly.setText(analy.getDescription());
             txdescriptionanaly.setText(analy.getDescription());
             txpriceanaly.setText(String.valueOf(analy.getPrice()));
              System.out.println(analy.getAnalysis_id());
    }

    @FXML
    private void addanaly(ActionEvent event) {
        AnalysisService analy = new AnalysisService();
        
        String analyname = txanalyname.getText();
        String description = txdescriptionanaly.getText();
        String result_type = txresulttypeanaly.getText();
        float price = Float.parseFloat(txpriceanaly.getText());
       Analysis ana = new Analysis(analyname,description,result_type,price);
       analy.Create(ana);
       
             showanaly();
             clear();
    }




    @FXML
    private void deleteanaly(ActionEvent event) {
        
        AnalysisService analy = new AnalysisService();        
        Analysis ana  =tvanaly.getSelectionModel().getSelectedItem();
        int selectedID = tvanaly.getSelectionModel().getSelectedIndex();
        System.out.println(ana.getAnalysis_id());
        analy.Delete(ana.getAnalysis_id());
        tvanaly.getItems().remove(ana);
        showanaly();
        clear();
        analy.smsreporting();

    }

    @FXML
    private void updateanaly(ActionEvent event) {
        
        AnalysisService analy = new AnalysisService();
        Analysis z  =tvanaly.getSelectionModel().getSelectedItem();
        int selectedID = tvanaly.getSelectionModel().getSelectedIndex();
       
        int id = z.getAnalysis_id();
        String analyname = txanalyname.getText();
        String description = txdescriptionanaly.getText();
        String result_type = txresulttypeanaly.getText();
        float price = Float.parseFloat(txpriceanaly.getText());
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
