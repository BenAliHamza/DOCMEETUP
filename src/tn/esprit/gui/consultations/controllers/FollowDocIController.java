package tn.esprit.gui.consultations.controllers;

import tn.esprit.entities.Appointment;
import tn.esprit.entities.FollowUp;
import tn.esprit.entities.Medication;
import tn.esprit.entities.Analysis;
import tn.esprit.entities.User;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.esprit.services.AppointmentService;
import tn.esprit.services.UserService;
import tn.esprit.services.FollowUpService;
import tn.esprit.services.MedicationService;

public class FollowDocIController implements Initializable {

   // private DoctorService doctorService; // Service for doctor-related operations

    @FXML
    private TextField filterField;
    @FXML
    private TableView<User> tvpatient;
    @FXML
    private TableColumn<User, String> tvfirname;
    @FXML
    private TableColumn<User, String> tvlasname;
    @FXML
     private TableView<FollowUp> tvfollowup;
    @FXML
    private TableColumn<FollowUp, Date> tvdate;
    @FXML
    private TableColumn<FollowUp, String> tvbp;
    @FXML
    private TableColumn<FollowUp, Integer> tvheart;
    @FXML
    private TableColumn<FollowUp, Float> tvtemp;
    @FXML
    private TableColumn<FollowUp, Float> tvweight;
    @FXML
    private TableView<Medication> tvmedic;
    @FXML
    private TableColumn<Medication, String> tvmedicname;
    @FXML
    private TableColumn<Medication, String> tvmedicdesc;
    @FXML
    private TableView<Analysis> tvanalyse;
    @FXML
    private TableColumn<Analysis, String> tvanalysename;
    @FXML
    private TableColumn<Analysis, String> tvanalysedes;
    @FXML
    private TableColumn<Analysis, String> tvanalyseres;
 
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     //   doctorService = new DoctorServiceImpl(); // Initialize the doctor service
         loadPatients(HomePageController.getUser().getUser_id());
    }

    @FXML
    private void search(KeyEvent event) {
        // Filter the patient list based on the entered text in the filterField
        String filterText = filterField.getText();
        // Call a method to update the patient list based on the filterText
        // (e.g., doctorService.filterPatients(filterText))
        // Update the tvpatient TableView accordingly
    }

    @FXML
    private void row(MouseEvent event) {
        // Get the selected appointment
        User selectedAppointment = tvpatient.getSelectionModel().getSelectedItem();
        
        if (selectedAppointment != null) {
            int patientId = selectedAppointment.getUser_id();
             // Retrieve and display the relevant information for the selected patient
            // (e.g., follow-up, medication, analysis)
            loadFollowUp(patientId);
            loadMedication(patientId);
            loadAnalysis(patientId);
        }
    }
private void loadPatients(int doctorid) {
    UserService UserService = new UserService();
            ObservableList<User> list = UserService.getDoctorPatients(doctorid);


    tvfirname.setCellValueFactory(new PropertyValueFactory<>("first_name"));
    tvlasname.setCellValueFactory(new PropertyValueFactory<>("last_name"));
    tvpatient.setItems(list);

}


    private void loadFollowUp(int patientId) {
        // Retrieve the follow-up records for the patient with the given patientId
        // (e.g., doctorService.getFollowUpRecords(patientId))
        // Update the tvfollowup TableView with the follow-up records
            FollowUpService FollowUpService = new FollowUpService();

                ObservableList<FollowUp> followUpList = FollowUpService.listfollowupid(patientId);

    tvbp.setCellValueFactory(new PropertyValueFactory<FollowUp, String>("blood_pressure"));
        tvdate.setCellValueFactory(new PropertyValueFactory<FollowUp, Date>("date"));
        tvheart.setCellValueFactory(new PropertyValueFactory<FollowUp, Integer>("heart_rate"));
        tvtemp.setCellValueFactory(new PropertyValueFactory<FollowUp, Float>("temperature"));
        tvweight.setCellValueFactory(new PropertyValueFactory<FollowUp, Float>("weight"));
        tvfollowup.setItems(followUpList);

    }

    private void loadMedication(int patientId) {
          MedicationService MedicationService = new MedicationService();
          ObservableList<Medication> list = MedicationService.medicationperid(patientId);
          tvmedicname.setCellValueFactory(new PropertyValueFactory<Medication, String>("medication_name"));
          tvmedicdesc.setCellValueFactory(new PropertyValueFactory<Medication, String>("description"));
                tvmedic.setItems(list);

     }

     
    private void loadAnalysis(int patientId) {
        MedicationService MedicationService = new MedicationService();
          ObservableList<Analysis> list = MedicationService.medicationanalyse(patientId);
          tvanalysename.setCellValueFactory(new PropertyValueFactory<Analysis, String>("analysis_name"));
          tvanalysedes.setCellValueFactory(new PropertyValueFactory<Analysis, String>("description"));
          tvanalyseres.setCellValueFactory(new PropertyValueFactory<Analysis, String>("result_type"));
                tvanalyse.setItems(list);

    }
}
