package tn.esprit.services;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import tn.esprit.entities.Evenement;
import tn.esprit.entities.User;
import tn.esprit.tools.MaConnexion;

public class EvenementService implements IService<Evenement> {
    Connection cnx;

    public EvenementService() {
        cnx = MaConnexion.getInstance().getCnx();
    }
    @Override
    public int ajouter(Evenement E) {
        System.out.println(E.toString());
        String sql = "INSERT INTO event (organizer_id, event_name, event_date, event_time) VALUES (?, ?, ?, ?)";
        try (PreparedStatement st = cnx.prepareStatement(sql)) {
            st.setInt(1, E.getOrganizer().getUser_id());
            st.setString(2, E.getEvent_name());
            st.setDate(3, Date.valueOf(E.getEvent_date()));
            st.setTime(4, java.sql.Time.valueOf(E.getEvent_time()));
                
            st.executeUpdate();
            System.out.println("Evenement ajouté !");
            return -1; 
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return -1; 
        }
    }

public void Update(Evenement E) {
    String sql = "UPDATE event SET event_name = ?, event_date = ?, event_time = ? WHERE event_id = ?";
    try (PreparedStatement st = cnx.prepareStatement(sql)) {
        st.setString(1, E.getEvent_name());
        st.setDate(2, Date.valueOf(E.getEvent_date()));

        LocalTime localTime = LocalTime.parse(E.getEvent_time().format(DateTimeFormatter.ISO_LOCAL_TIME));
        st.setTime(3, java.sql.Time.valueOf(localTime));

        st.setInt(4, E.getEvent_id());

        st.executeUpdate();
        System.out.println("Evenement mis à jour !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}





    @Override
    public List<Evenement> afficher() {
        List<Evenement> evenements = new ArrayList<>();
        try {
            String sql = "SELECT e.event_id, e.event_name, e.event_date, e.event_time, u.username ,e.organizer_id  " +
                         "FROM event e INNER JOIN user u ON e.organizer_id = u.user_id";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);
            UserService us = new  UserService();
            while (rs.next()) {
                int s = rs.getInt("organizer_id");
                User u = us.SearchById(s);
                Evenement e = new Evenement(
                        rs.getInt("event_id"),
                        u,
                        rs.getString("event_name"),
                        rs.getDate("event_date").toLocalDate(),
                        rs.getTime("event_time").toLocalTime()
                );
                evenements.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return evenements;
    }

    public void delete(Evenement E) {
        String sql = "DELETE FROM event WHERE event_id = ?";
        try (PreparedStatement st = cnx.prepareStatement(sql)) {
            st.setInt(1, E.getEvent_id());
            st.executeUpdate();
            System.out.println("Evenement supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Evenement E) {
        String sql = "DELETE FROM event WHERE event_id = ?";
        try (PreparedStatement st = cnx.prepareStatement(sql)) {
            st.setInt(1, E.getEvent_id());
            st.executeUpdate();
            System.out.println("Evenement supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
public Evenement getById(int eventId) {
    Evenement evenement = null;
    String sql = "SELECT e.event_id, e.event_name, e.event_date, e.event_time, u.username , e.organizer_id " +
                 "FROM event e INNER JOIN user u ON e.organizer_id = u.user_id " +
                 "WHERE e.event_id = ?";
    try (PreparedStatement st = cnx.prepareStatement(sql)) {
        st.setInt(1, eventId);
        ResultSet rs = st.executeQuery();
        UserService us = new UserService(); 
        if (rs.next()) {
              int s = rs.getInt("organizer_id");
                User organizer = us.SearchById(s);
            Date eventDate = rs.getDate("event_date");
            Time eventTime = rs.getTime("event_time");
            LocalDate localDate = eventDate.toLocalDate();
            LocalTime localTime = eventTime.toLocalTime();

            evenement = new Evenement(
                    rs.getInt("event_id"),
                    organizer,
                    rs.getString("event_name"),
                    localDate,
                    localTime
            );
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return evenement;
}
public List<User> getParticipants(int eventId) {
    List<User> participants = new ArrayList<>();
    String sql = "SELECT u.user_id, u.username " +
                 "FROM user u INNER JOIN eventattendance ea ON u.user_id = ea.attendee_id " +
                 "WHERE ea.event_id = ?";
    try (PreparedStatement st = cnx.prepareStatement(sql)) {
        st.setInt(1, eventId);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            User participant = new User();
            participant.setUser_id(rs.getInt("user_id"));
            participant.setUsername(rs.getString("username"));
            participants.add(participant);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return participants;
}

public void pdf(String blood, String heart ,String weight) throws FileNotFoundException, SQLException, DocumentException {

           Document my_pdf_report = new Document();
           PdfWriter.getInstance(my_pdf_report, new FileOutputStream("C://test//test.pdf"));
    my_pdf_report.open();
    //we have four columns in our table
        Phrase Phrase1 = new Phrase("Please found bellow the Selected Follow Up.");
        my_pdf_report.add(Phrase1);
           PdfPTable my_report_table = new PdfPTable(2);
    //create a cell object
    PdfPCell table_cell;
    
     table_cell = new PdfPCell(new Phrase("blood pressure"));
    my_report_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase(blood));
    my_report_table.addCell(table_cell);

    table_cell = new PdfPCell(new Phrase("heart rate "));
    my_report_table.addCell(table_cell);
        
            table_cell = new PdfPCell(new Phrase(heart));
    my_report_table.addCell(table_cell);

    table_cell = new PdfPCell(new Phrase("weight"));
    my_report_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase(weight));
    my_report_table.addCell(table_cell);

    /*table_cell = new PdfPCell(new Phrase("temperature "));
    my_report_table.addCell(table_cell); 
            table_cell = new PdfPCell(new Phrase(emp));
    my_report_table.addCell(table_cell);*/


    /* Attach report table to PDF */
    my_pdf_report.add(my_report_table);
        
        Phrase Phrase2 = new Phrase("This file is generated by DocMeetUp.");
        my_pdf_report.add(Phrase2);
    my_pdf_report.close();

    /* Close all DB related objects */
    }

   /* public void pdf(String event_name, String event_date, String event_time) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
}
