/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import tn.esprit.entities.Consulation;
import tn.esprit.tools.MaConnexion ;

/**
 *
 * @author Hamza
 */
public class ConsulationService  implements  IService<Consulation>  {

    Connection cnx;

    public ConsulationService() {
                cnx= MaConnexion.getInstance().getCnx();
    }
    
    @Override
     public int ajouter(Consulation c) {
        String sql = "INSERT INTO consultation (doctor_id, patient_id, isPayed, isPrescription, price, consultation_date, consultation_time, rapport) VALUES (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement st = cnx.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, c.getDoctor_id());
            st.setInt(2, c.getPatient_id());
            st.setBoolean(3, true);
            st.setBoolean(4, c.getIsPrescription());
            st.setDouble(5, c.getPrice());
            st.setDate(6, new java.sql.Date(c.getTime().getTime()));
            st.setTime(7, new java.sql.Time(c.getDate().getTime()));
            st.setString(8, c.getRapport());
            st.executeUpdate();
            System.out.println("Consulation added");
            int rowsAffected = st.executeUpdate();
            try (ResultSet generatedKeys = st.getGeneratedKeys()) {
                if (rowsAffected == 1) {
                    if (generatedKeys.next()) {
                        int consultationId = generatedKeys.getInt(1);
                        c.setConsultation_id(consultationId); // Assuming you have a setId() method in your Consulation class to set the ID
                        System.out.println("Consulation added with ID: " + consultationId);
                        return consultationId;
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                // Handle the exception if parsing the IDs fails
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Storage Error");
                alert.setHeaderText(null);
                alert.setContentText(ex.getMessage());
                alert.showAndWait();
                return -1;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            // Handle the exception if parsing the IDs fails
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Storage Error");
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
            return -1;
        }
        return 0;
    }


        @Override
        public List<Consulation> afficher() {
             List<Consulation> Consulations = new ArrayList<>();
            try {

                String sql="select * from consultation";
                Statement st = cnx.createStatement();

                ResultSet rs= st.executeQuery(sql);
                while(rs.next()){
                    Consulation consulation = new Consulation(rs.getInt("consultation_id"),rs.getInt("doctor_id"),rs.getInt("patient_id") , rs.getBoolean("isPayed") , rs.getBoolean("isPrescription") , rs.getDouble("price"), 
                    rs.getDate("consultation_date" ) , rs.getTime("consultation_time"),rs.getString("rapport"));
                    Consulations.add(consulation);
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            return Consulations; 
        }

        @Override
        public void supprimer(Consulation c) {
            String sql="delete from consultation where consultation_id ="
                     + " ? ;";
            try {
                PreparedStatement st= cnx.prepareStatement(sql);
                st.setInt(1, c.getConsultation_id());
                st.executeUpdate();
                System.out.println("Consulation is Deleted");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
    }
    
        public List<Consulation> getConsultationsByPatientId(int patientId) {
                List<Consulation> consultations = new ArrayList<>();
                try {
                    String sql = "SELECT * FROM consultation WHERE patient_id = ?";
                    PreparedStatement statement = cnx.prepareStatement(sql);
                    statement.setInt(1, patientId);

                    ResultSet rs = statement.executeQuery();
                    while (rs.next()) {
                        Consulation consultation = new Consulation(rs.getInt("consultation_id"), rs.getInt("doctor_id"), rs.getInt("patient_id"), rs.getBoolean("isPayed"), rs.getBoolean("isPrescription"), rs.getDouble("price"), rs.getDate("consultation_date"), rs.getTime("consultation_time"),rs.getString("rapport"));
                        consultations.add(consultation);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
                return consultations;
        }
        public List<Consulation> getConsultationsByPatientIdAndDoctorId(int patientId , int docId) {
            List<Consulation> consultations = new ArrayList<>();
            try {
                String sql = "SELECT * FROM consultation WHERE patient_id = ? && doctor_id = ?";
                PreparedStatement statement = cnx.prepareStatement(sql);
                statement.setInt(1, patientId);
                statement.setInt(2, docId);


                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    Consulation consultation = new Consulation(rs.getInt("consultation_id"), rs.getInt("doctor_id"), rs.getInt("patient_id"), rs.getBoolean("isPayed"), rs.getBoolean("isPrescription"), rs.getDouble("price"), rs.getDate("consultation_date"), rs.getTime("consultation_time"),rs.getString("rapport"));
                    consultations.add(consultation);
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            return consultations;
    }
    
        public void updateConsultation(Consulation consultation) {
        try {
            String sql = "UPDATE consultation SET doctor_id = ?, patient_id = ?, isPayed = ?, isPrescription = ?, price = ?, consultation_date = ?, consultation_time = ?, rapport  = ? WHERE consultation_id = ?";
            PreparedStatement statement = cnx.prepareStatement(sql);
            statement.setInt(1, consultation.getDoctor_id());
            statement.setInt(2, consultation.getPatient_id());
            statement.setBoolean(3, consultation.getIsPayed());
            statement.setBoolean(4, consultation.getIsPrescription());
            statement.setDouble(5, consultation.getPrice());
            statement.setDate(6, new java.sql.Date(consultation.getTime().getTime()));
            statement.setTime(7, new java.sql.Time(consultation.getDate().getTime()));
            statement.setString(8 , consultation.getRapport()); 
            statement.setInt(9, consultation.getConsultation_id());
            System.out.println(sql);
            System.out.println(statement);
            statement.executeUpdate();
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Consultation updated successfully.");
            } else {
                System.out.println("No rows updated.");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
        public Consulation getConsultationById(int consultationId) {
        Consulation consultation = null;
        try {
            String sql = "SELECT * FROM consultation WHERE consultation_id = ?";
            PreparedStatement statement = cnx.prepareStatement(sql);
            statement.setInt(1, consultationId);

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                consultation = new Consulation(
                    rs.getInt("consultation_id"),
                    rs.getInt("doctor_id"),
                    rs.getInt("patient_id"),
                    rs.getBoolean("isPayed"),
                    rs.getBoolean("isPrescription"),
                    rs.getDouble("price"),
                    rs.getDate("consultation_date"),
                    rs.getTime("consultation_time"),
                    rs.getString("rapport")
                );
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return consultation;
    }
}
