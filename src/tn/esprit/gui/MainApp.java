/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author DELL
 */


public class MainApp extends Application {

    @Override
    public void start(Stage stage) {
   
        try {
            Parent root = FXMLLoader.load(getClass().getResource("CreateAccount.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Create Account");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}