/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui.consultations.controllers;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
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

        @FXML
        private void download(ActionEvent event) {
            Document document = new Document();
            String title = consultation.getpdfTitle();

            try {
                // Create a file chooser dialog to select the save location
                FileChooser fileChooser = new FileChooser();
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
                File file = fileChooser.showSaveDialog(new Stage());

                if (file != null) {
                    // Create a PDF writer
                    PdfWriter.getInstance(document, new FileOutputStream(file));

                    document.open();

                    // Add the title at the top
                    Font titleFont = FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLD);
                    Paragraph titleParagraph = new Paragraph(title, titleFont);
                    titleParagraph.setAlignment(Element.ALIGN_CENTER);
                    document.add(titleParagraph);

                    // Add some spacing
                    document.add(Chunk.NEWLINE);

                    // Create a table to hold the consultation details
                    PdfPTable table = new PdfPTable(2);
                    table.setWidthPercentage(100);
                    table.setSpacingBefore(10f);
                    table.setSpacingAfter(10f);

                    // Add consultation details to the table
                    Font cellFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL);
                    for (int i = 0; i < 7; i++) {
                        String label;
                        String value;
                        if (i == 0) {
                            label = "Doctor";
                            value = docId.getText();
                        } else if (i == 1) {
                            label = "Patient";
                            value = patientId.getText();
                        } else if (i == 2) {
                            label = "Price";
                            value = price.getText();
                        } else if (i == 3) {
                            label = "Date";
                            value = date.getText();
                        } else if (i == 4) {
                            label = "Facturation";
                            value = isPayed.getText();
                        } else if (i == 5) {
                            label = "Time";
                            value = time.getText();
                        } else {
                            label = "Rapport";
                            value = rapport.getText();
                        }

                        PdfPCell labelCell = new PdfPCell(new Phrase(label, cellFont));
                        PdfPCell valueCell = new PdfPCell(new Phrase(value, cellFont));

                        // Set styles for odd cells (index starts from 0)
                        if (i % 2 == 0) {
                            labelCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                            labelCell.setBorderColor(BaseColor.BLUE);
                            labelCell.setBorderWidth(1f);
                            labelCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            valueCell.setBorderColor(BaseColor.BLUE);
                        }
                        labelCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        labelCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        labelCell.setPadding(5f);
                        table.addCell(labelCell);
                        table.addCell(valueCell);
                    }

                    // Add the table to the document
                    document.add(table);

                    // Close the document
                    document.close();

                    // Show success message
                    Alert success = new Alert(Alert.AlertType.INFORMATION);
                    success.setTitle("Download Consultation");
                    success.setHeaderText(null);
                    success.setContentText("Consultation downloaded successfully!");
                    success.showAndWait();
                }
            } catch (Exception e) {
                // Handle exceptions
                e.printStackTrace();

                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Error");
                error.setHeaderText(null);
                error.setContentText("An error occurred while creating the PDF document: " + e.getMessage());
                error.showAndWait();
            }
        }


        private PdfPCell createTableCell(String content) {
            PdfPCell cell = new PdfPCell(new Phrase(content));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            return cell;
        }


    
    
}
