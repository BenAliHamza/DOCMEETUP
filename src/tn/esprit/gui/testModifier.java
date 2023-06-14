package tn.esprit.gui;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class testModifier extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierEvenement.fxml"));
            AnchorPane root = loader.load();

            ModifierEvenementController controller = loader.getController();
           
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Modifier Evenement");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
