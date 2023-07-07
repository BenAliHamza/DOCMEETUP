/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author ASUS
 */public class main extends Application {
    @Override
    public void start(Stage stage) {
  try {
      Parent root = FXMLLoader.load(getClass().getResource("medic.fxml"));
   //Parent root = FXMLLoader.load(getClass().getResource("gestionemplacement.fxml"));
   Scene scene = new Scene(root);
   stage.setScene(scene);
   stage.show();
   
   
  } catch(Exception e) {
   e.printStackTrace();
  }
 } 

 public static void main(String[] args) {
  launch(args);
 }
}