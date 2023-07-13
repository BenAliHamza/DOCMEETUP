/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui.consultations.controllers;

import com.itextpdf.text.Document;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PageOrientation;
import javafx.print.PrinterJob;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
import org.apache.pdfbox.printing.Scaling;
import tn.esprit.entities.Consulation;
import tn.esprit.gui.consultations.Main;
import tn.esprit.services.ConsulationService;

/**
 * FXML Controller class
 *
 * @author Hamza
 */
public class AfficherConsultationController implements Initializable {
    private int id ; 
    private Consulation  consultation ; 



    @FXML
    private TextField docId;
    @FXML
    private TextField patientId;
    @FXML
    private TextField price;
    @FXML
    private TextField date;
    @FXML
    private TextField isPayed;
    @FXML
    private TextField time;
    @FXML
    private TextArea rapport ; 
    
    @FXML 
    private Button update;
    @FXML 
    private Button delete;
    @FXML 
    private Button download;
    @FXML
    private AnchorPane consultationDetail;
    

    /**
     * Initializes the controller class.
     *
     */
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                
     Main.setTitle("Details de la consultation");

    }    
    public int getId() {
           return id;
    }

    public void setId(int id) {
            setConsultation(id);
            this.id = id;
        }
    public void setDocId(String docId) {
        this.docId.setText(docId);
    }

    public void setPatientId( String patientId) {
        this.patientId.setText(patientId); 
    }

    public Consulation getConsultation() {
        return consultation;
    }

    public void setConsultation(int id) {
            ConsulationService  cs = new ConsulationService();
            Consulation c = cs.getConsultationById(id);
            this.consultation = c;
            
    }

    public void setPrice(Double price) {
        this.price.setText(""+ price + " dinars ");
    }

   public void setDate(Date date) {
    this.date.setText(date.toString());
}

    public TextArea getRapport() {
        return rapport;
    }

    public void setRapport(String rapport) {
        this.rapport.setText(rapport);
    }

    public void setIsPayed(boolean  isPayed) {
        if(isPayed) {
            this.isPayed.setText("La consulation est payée"); 
        }else {
            this.isPayed.setText("La consulation n'est payée"); 

        }
        
    }

    public void setTime(Time time) {
        this.time.setText(time.toString());
    }

  @FXML
    private void onDelete(ActionEvent event) {
        // Create a confirmation dialog
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirm Delete");
        confirmation.setHeaderText(null);
        confirmation.setContentText("veulliez confirmer la supprission de la consultation ");

        Optional<ButtonType> result = confirmation.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // User confirmed the delete action
            ConsulationService cs = new ConsulationService();
            System.out.println(consultation);
            cs.supprimer(consultation);

            // Show success message
            Alert success = new Alert(Alert.AlertType.INFORMATION);
            success.setTitle("Delete Consultation");
            success.setHeaderText(null);
            success.setContentText("Consultation deleted successfully!");
            success.showAndWait();
            returnToList();
        }
    }
    @FXML 
    public void onUpdate( ActionEvent event ) {
               try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/HomePage.fxml"));
        Parent root = loader.load();
        HomePageController ac = loader.getController();
        Stage stage = (Stage) docId.getScene().getWindow(); // Replace `button` with your actual button object
        Scene scene = new Scene(root);
        stage.setScene(scene);
        ac.CreateNewConsultation(consultation);
        stage.show();

        } catch (IOException ex) {
            System.out.println(ex);
            Logger.getLogger(AfficherConsultationsListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void returnToList(){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/HomePage.fxml"));
                Parent root = loader.load();
                HomePageController ac = loader.getController();
                Stage stage = (Stage) rapport.getScene().getWindow(); // Replace `button` with your actual button object
                ac.loadConsultationList();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                
            } catch (Exception  ex) {
                System.out.println(ex);
                System.out.println(ex.getStackTrace());

            }
    }
     public void download(Consulation c) throws FileNotFoundException, SQLException, com.itextpdf.text.DocumentException {

	
	/* Close all DB related objects */
    }

    @FXML
    private void download(ActionEvent event) {
    }

    
    
}
