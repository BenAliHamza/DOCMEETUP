/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.Gui;

import com.browniebytes.javafx.control.DateTimePicker;
import java.net.URL;
 import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
 import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import tn.esprit.services.CalendarService;
import tn.esprit.entities.Calendar;
import tn.esprit.entities.User;
/**
 *
 * @author chiha
 */
public class CalendarUIController implements Initializable {

    @FXML
    private TableView<Calendar> doctv;
    @FXML
    private TableColumn<Calendar, String> endtv;
    @FXML
    private TableColumn<Calendar, String> starttv;
    @FXML
     private Button add;
    @FXML
    private Button update;
    @FXML
    private Button delete;
    @FXML
    private TextField search;
    @FXML
    private DateTimePicker start;
    @FXML
    private DateTimePicker end;
    
    ObservableList<Calendar> dataList;

    
       public void initialize(URL url, ResourceBundle rb) {
           showperuser();
           System.out.println("az");
}
    
     public void showperuser (){
     CalendarService cal = new CalendarService();
     // int x =user.getsessonId();
     int x = 1;
         ObservableList <Calendar> list = cal.getCalendar(x);
         System.out.println(cal.getCalendar(x));
         starttv.setCellValueFactory(new PropertyValueFactory<Calendar,String>("heure_debut"));
        endtv.setCellValueFactory(new PropertyValueFactory<Calendar,String>("heure_fin"));  
        //System.out.print("test");
        doctv.setItems(list);
    }

    @FXML
    private void add(MouseEvent event) {
        CalendarService a = new CalendarService();
                  Date datestart = start.getTime(); 
                  Date dateend = end.getTime();
User user = new User(1);
                  Calendar z = new Calendar( datestart, dateend, user);
                  a.addCalendar(z);
                  showperuser();
         }
        
        
        
 

    @FXML
    private void update(MouseEvent event) {
          Calendar cal  =doctv.getSelectionModel().getSelectedItem();
          
          CalendarService a = new CalendarService();
                  Date datestart = start.getTime(); 
                  Date dateend = end.getTime();
                  Calendar z = new Calendar(cal.getCalendarID(), datestart, dateend);
                  a.updateCalendar(z);
                  showperuser();
        
    }

    @FXML
    private void delete(MouseEvent event) {
        
     CalendarService cal = new CalendarService();
         Calendar calander  =doctv.getSelectionModel().getSelectedItem();
         cal.deleteCalendar(calander.getCalendarID());
       int selectedID = doctv.getSelectionModel().getSelectedIndex();
        doctv.getItems().remove(selectedID);
         showperuser ();
         
    }

    @FXML
    private void row(MouseEvent event) {
         Calendar cal  =doctv.getSelectionModel().getSelectedItem();
        System.out.println(cal.getCalendarID());
        
         start.setTime(cal.getHeure_debut());
         end.setTime(cal.getHeure_fin());
       
         
    }

    @FXML
     
private void search(KeyEvent event) {
    CalendarService cal = new CalendarService();
    int x = 1;
    ObservableList<Calendar> list = cal.getCalendar(x);
    doctv.setItems(list);
    dataList = cal.getCalendar(x);

    FilteredList<Calendar> filteredData = new FilteredList<>(dataList, b -> true);
    search.textProperty().addListener((observable, oldValue, newValue) -> {
        filteredData.setPredicate(calendar -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();
            Date startDate = calendar.getHeure_debut();
            Date endDate = calendar.getHeure_fin();

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String startDateString = formatter.format(startDate);
            String endDateString = formatter.format(endDate);

            if (startDateString.contains(lowerCaseFilter) || endDateString.contains(lowerCaseFilter)) {
                return true; // Filter matches start or end date
            } else {
                return false; // Does not match.
            }
        });
    });

    SortedList<Calendar> sortedData = new SortedList<>(filteredData);
    sortedData.comparatorProperty().bind(doctv.comparatorProperty());
    doctv.setItems(sortedData);
}
    @FXML
  private void myap(ActionEvent event) {
        JavaFXApplication1.setScene("DocTime");

    }
    
    }
 
 