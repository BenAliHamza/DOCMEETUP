



package tn.esprit.services;

 import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tn.esprit.entities.Calendar;
import tn.esprit.entities.User;
import tn.esprit.tools.DBConnexion;

public class CalendarService {


    public void deleteCalendar(int calendarId) {
        try {
            String query = "DELETE FROM calendar WHERE calendarID = ?";
            PreparedStatement statement = DBConnexion.getInstance().getCnx().prepareStatement(query);
            statement.setInt(1, calendarId);
            statement.executeUpdate();
            System.out.println("Calendar deleted successfully!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ObservableList <Calendar> getCalendar(int id) {
        ObservableList <Calendar> list =  FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM calendar where medecinId="+id;
                        Statement statement = DBConnexion.getInstance().getCnx().createStatement();
             ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
            Calendar calendar = new Calendar();
            User medecin = new User(resultSet.getInt("medecinId"));
            calendar.setHeure_debut(resultSet.getTimestamp("heure_debut"));
            calendar.setHeure_fin(resultSet.getTimestamp("heure_fin"));
                        calendar.setCalendarID(resultSet.getInt("CalendarID"));
                        calendar.setMedecin(medecin);        
                                   list.add(calendar);
            }
        }
        
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    public ObservableList <Calendar> getAllCalendars() {
        ObservableList <Calendar> list =  FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM calendar";
                        Statement statement = DBConnexion.getInstance().getCnx().createStatement();
             ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
            Calendar calendar = new Calendar();
            User medecin = new User(resultSet.getInt("medecinId"));
            calendar.setHeure_debut(resultSet.getTimestamp("heure_debut"));
            calendar.setHeure_fin(resultSet.getTimestamp("heure_fin"));
                        calendar.setCalendarID(resultSet.getInt("CalendarID"));
                        calendar.setMedecin(medecin);        
                                   list.add(calendar);
            }
        }
        
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
    


    public void addCalendar(Calendar calendar) {
        try {
            String query = "INSERT INTO calendar(heure_debut, heure_fin, medecinId) VALUES(?, ?, ?)";
            PreparedStatement statement = DBConnexion.getInstance().getCnx().prepareStatement(query);
            statement.setTimestamp(1,(new java.sql.Timestamp (calendar.getHeure_debut().getTime())));
            statement.setTimestamp(2, new java.sql.Timestamp(calendar.getHeure_fin().getTime()));
             statement.setInt(3, calendar.getMedecin().getUser_id());
            statement.executeUpdate();
            System.out.println("Calendar added successfully!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void updateCalendar(Calendar calendar) {
        try {
            String query = "UPDATE calendar SET heure_debut = ?, heure_fin = ?WHERE calendarID = ?";
            PreparedStatement statement = DBConnexion.getInstance().getCnx().prepareStatement(query);
            statement.setTimestamp(1,(new java.sql.Timestamp (calendar.getHeure_debut().getTime())));
            statement.setTimestamp(2, new java.sql.Timestamp (calendar.getHeure_fin().getTime()));
             statement.setInt(3, calendar.getCalendarID());
            statement.executeUpdate();
            System.out.println("Calendar updated successfully!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    

}
