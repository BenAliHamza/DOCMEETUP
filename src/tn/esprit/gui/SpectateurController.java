/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.esprit.entities.Evenement;
import tn.esprit.entities.User;
import tn.esprit.services.EvenementService;
/**
 * FXML Controller class
 *
 * @author USER
 */
public class SpectateurController implements Initializable {

    @FXML
     
private TableColumn<Evenement, User> catColumn;
       
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         catColumn.setCellValueFactory(new PropertyValueFactory<>("catv_id"));
          EvenementService ec = new EvenementService();
     //   tableviewEquipement.setItems(FXCollections.observableArrayList(ec.afficher()));
    }    
    
}
