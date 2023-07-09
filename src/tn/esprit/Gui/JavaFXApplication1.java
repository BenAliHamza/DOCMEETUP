/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.Gui;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author chiha
 */
public class JavaFXApplication1 extends Application {
     public static Stage _stage;
    public static JavaFXApplication1 _self;
    @Override 
     public void start(Stage primaryStage) {
          _stage = primaryStage;
          _self = this;
          setScene("appointmentusingcal");
          //setScene("AppointmentUI");
          //setScene("DocTime");
         //setScene("calendarUI");
    }
    public static void setScene(String sceneName)
    {
        try {
            Parent root = FXMLLoader.load(_self.getClass().getResource(sceneName + ".fxml"));
            //Parent root = FXMLLoader.load(getClass().getResource("Appointmentusingcal.fxml"));

            Scene scene = new Scene(root);
            _stage.setScene(scene);
            _stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
      public static void main(String[] args) {
        launch(args);
    }
  
}

 