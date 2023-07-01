/*
 * Property of Okami�
 * Not destined for commercial use
 */
package tn.esprit.gui;

import java.io.IOException;
import java.util.Locale;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author
 */
public class MainWindow extends Application {
    @Override
    public void start(Stage primaryStage) {

        try {
           //Locale.setDefault(new Locale("fr"));
            //Parent root = FXMLLoader.load(getClass().getResource("/tn/esprit/gui/AjouterEvenement.fxml"));
            //Parent root = FXMLLoader.load(getClass().getResource("/tn/esprit/gui/afficher.fxml"));
            //Parent root = FXMLLoader.load(getClass().getResource("/tn/esprit/gui/ModifierEvenement.fxml"));
            //Parent root = FXMLLoader.load(getClass().getResource("/tn/esprit/gui/spectateur.fxml"));
            Parent root = FXMLLoader.load(getClass().getResource("/tn/esprit/gui/Interface.fxml"));
            

            Scene scene = new Scene(root);

            scene.getStylesheets().add(getClass().getResource("/tn/esprit/theme/nord-dark.css").toExternalForm());

            primaryStage.setMinWidth(720);
            primaryStage.setMinHeight(480);
            primaryStage.setTitle("DocMeetUp");
            primaryStage.getIcons().add(new Image("/tn/esprit/picture/images.png"));
            primaryStage.setScene(scene);
            primaryStage.show();
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
