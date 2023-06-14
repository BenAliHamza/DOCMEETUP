package tn.esprit.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class testAjouter extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterEvenement.fxml"));
            AnchorPane root = loader.load();

            AjouterEvenementController controller = loader.getController();
           
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Ajouter Evenement");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
