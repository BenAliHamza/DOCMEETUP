package tn.esprit.gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.esprit.entities.Evenement;
import tn.esprit.services.EvenementService;

public class AfficherController implements Initializable {

    @FXML
    private TableView<Evenement> tableviewEquipement;

    @FXML
    private TableColumn<Evenement, String> nomColumn;

    @FXML
    private TableColumn<Evenement, String> catColumn;

    @FXML
    private TableColumn<Evenement, String> datColumn;

    @FXML
    private TableColumn<Evenement, String> quantColumn;

    @FXML
    private Button buttonDelete;

    private EvenementService evenementService;


@Override
public void initialize(URL url, ResourceBundle rb) {
    evenementService = new EvenementService();
    initializeTableView();
}

private void initializeTableView() {
    nomColumn.setCellValueFactory(new PropertyValueFactory<>("event_name"));
    catColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getOrganizer().getUsername()));
    datColumn.setCellValueFactory(new PropertyValueFactory<>("event_date"));
    quantColumn.setCellValueFactory(new PropertyValueFactory<>("event_time"));

    List<Evenement> evenements = evenementService.afficher();
    tableviewEquipement.setItems(FXCollections.observableArrayList(evenements));
}


    @FXML
    private void Delete() {
        Evenement selectedEvent = tableviewEquipement.getSelectionModel().getSelectedItem();
        if (selectedEvent != null) {
            evenementService.supprimer(selectedEvent);
tableviewEquipement.setItems(FXCollections.observableArrayList(evenementService.afficher()));
        }
    }
}
