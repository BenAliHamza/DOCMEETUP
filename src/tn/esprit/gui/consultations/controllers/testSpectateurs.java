package tn.esprit.gui;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class testSpectateurs extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Spectateur.fxml"));
            AnchorPane root = loader.load();

            SpectateurController controller = loader.getController();
           
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Spectateurs");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
