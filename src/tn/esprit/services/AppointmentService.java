package tn.esprit.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 import java.util.Date;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tn.esprit.entities.Appointment;
import tn.esprit.tools.DBConnexion;

public class AppointmentService {

    public void deleteAppointment(int appointmentId) {
        try {
            String query = "DELETE FROM appointment WHERE appointmentId = ?";
            PreparedStatement statement = DBConnexion.getInstance().getCnx().prepareStatement(query);
            statement.setInt(1, appointmentId);
            statement.executeUpdate();
            System.out.println("Appointment deleted successfully!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ObservableList<Appointment> getAppointments(int id) {
        ObservableList<Appointment> list = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM appointment WHERE medecinId=" + id;
            Statement statement = DBConnexion.getInstance().getCnx().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Appointment appointment = new Appointment();
                appointment.setAppointmentId(resultSet.getInt("appointmentId"));
                appointment.setCalendarId(resultSet.getInt("CalendarID"));
                appointment.setMedecinId(resultSet.getInt("medecinId"));
                appointment.setPatientId(resultSet.getInt("patientId"));
                appointment.setDescription(resultSet.getString("description"));
                appointment.setStatus(resultSet.getBoolean("status"));
                appointment.setAppointmentDateTime(resultSet.getTimestamp("appointmentDateTime"));
 
                list.add(appointment);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    public ObservableList<Appointment> getAllAppointments() {
        ObservableList<Appointment> list = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM appointment";
            Statement statement = DBConnexion.getInstance().getCnx().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Appointment appointment = new Appointment();
                appointment.setAppointmentId(resultSet.getInt("appointmentId"));
                appointment.setCalendarId(resultSet.getInt("CalendarID"));
                appointment.setMedecinId(resultSet.getInt("medecinId"));
                appointment.setPatientId(resultSet.getInt("patientId"));
                appointment.setDescription(resultSet.getString("description"));
                appointment.setStatus(resultSet.getBoolean("status"));
                appointment.setAppointmentDateTime(resultSet.getTimestamp("appointmentDateTime"));
 
                list.add(appointment);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    public void addAppointment(Appointment appointment) {
        try {
            String query = "INSERT INTO appointment(CalendarID, medecinId, patientId, description, status, appointmentDateTime) VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = DBConnexion.getInstance().getCnx().prepareStatement(query);
            statement.setInt(1, appointment.getCalendarId());
            statement.setInt(2, appointment.getMedecinId());
            statement.setInt(3, appointment.getPatientId());
            statement.setString(4, appointment.getDescription());
            statement.setBoolean(5, appointment.getStatus());
            statement.setTimestamp(6, new java.sql.Timestamp(appointment.getAppointmentDateTime().getTime()));
 

            statement.executeUpdate();
            System.out.println("Appointment added successfully!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
}
    public void updateAppointment(Appointment appointment) {
    try {
        String query = "UPDATE appointment SET CalendarID = ?, medecinId = ?, patientId = ?, description = ?, status = ?, appointmentDateTime = ? WHERE appointmentId = ?";
        PreparedStatement statement = DBConnexion.getInstance().getCnx().prepareStatement(query);
        statement.setInt(1, appointment.getCalendarId());
        statement.setInt(2, appointment.getMedecinId());
        statement.setInt(3, appointment.getPatientId());
        statement.setString(4, appointment.getDescription());
        statement.setBoolean(5, appointment.getStatus());
        
        if (appointment.getAppointmentDateTime() != null) {
            statement.setTimestamp(6, new java.sql.Timestamp(appointment.getAppointmentDateTime().getTime()));
        } else {
            statement.setNull(6, java.sql.Types.TIMESTAMP);
        }
        
        statement.setInt(7, appointment.getAppointmentId());
        statement.executeUpdate();
        System.out.println("Appointment updated successfully!");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

}
