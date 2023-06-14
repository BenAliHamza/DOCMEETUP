package tn.esprit.services;

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
    public void ajouter(Evenement E) {
        String sql = "INSERT INTO event (organizer_id, event_name, event_date, event_time) VALUES (?, ?, ?, ?)";
        try (PreparedStatement st = cnx.prepareStatement(sql)) {
            st.setInt(1, E.getOrganizer().getUser_id());
            st.setString(2, E.getEvent_name());
            st.setDate(3, Date.valueOf(E.getEvent_date()));
            st.setTime(4, java.sql.Time.valueOf(E.getEvent_time()));

            st.executeUpdate();
            System.out.println("Evenement ajouté !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
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
            String sql = "SELECT e.event_id, e.event_name, e.event_date, e.event_time, u.username " +
                         "FROM event e INNER JOIN user u ON e.organizer_id = u.user_id";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                User u = new User(rs.getString("username"));
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
    String sql = "SELECT e.event_id, e.event_name, e.event_date, e.event_time, u.username " +
                 "FROM event e INNER JOIN user u ON e.organizer_id = u.user_id " +
                 "WHERE e.event_id = ?";
    try (PreparedStatement st = cnx.prepareStatement(sql)) {
        st.setInt(1, eventId);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            User organizer = new User(rs.getString("username"));
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
}
