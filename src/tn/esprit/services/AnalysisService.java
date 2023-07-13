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
import tn.esprit.entities.Analysis;
import tn.esprit.tools.MaConnexion;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tn.esprit.entities.User;

/**
 *
 * @author ASUS
 */
public class AnalysisService implements IService<Analysis> {

    Connection cnx;

    public AnalysisService() {
        cnx = MaConnexion.getInstance().getCnx();

    }

    public void Create(Analysis a) {
        String sql = "insert into analysis(analysis_name,user_id,description, result_type, price) values"
                + "(?,?,?,?,?)";
        try {
            PreparedStatement st = cnx.prepareStatement(sql);

            st.setString(1, a.getAnalysis_name());
            st.setInt(2, a.getLaboratory().getUser_id());
            st.setString(3, a.getDescription());
            st.setString(4, a.getResult_type());
            st.setFloat(5, a.getPrice());

            st.executeUpdate();
            System.out.println("Analysis add");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ObservableList<Analysis> Readbyid(User user) {
        ObservableList<Analysis> list = FXCollections.observableArrayList();
        String sql = "select * from Analysis where user_id=?";
        try {
            PreparedStatement st = cnx.prepareStatement(sql);
            st.setInt(1, user.getUser_id());
            try {
                System.out.println(st);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    Analysis a = new Analysis(rs.getInt("analysis_id"),
                            rs.getString("Analysis_name"), 
                            rs.getString("Description"),
                            rs.getString("result_type"), 
                            rs.getFloat("price"));
                    System.out.println(a);
                    list.add(a);
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnalysisService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public ObservableList<Analysis> Read() {
        ObservableList<Analysis> list = FXCollections.observableArrayList();
        try {

            String sql = "select * from Analysis where user_id=2";//laboratory id statique en attendant la preparation de la méthode session
            Statement st = cnx.createStatement();

            ResultSet rs = st.executeQuery(sql);
            UserService ms = new UserService();
            while (rs.next()) {
                Analysis a = new Analysis(rs.getInt("analysis_id"), rs.getString("Analysis_name"), rs.getString("Description"),
                        rs.getString("result_type"), rs.getFloat("price"));
                list.add(a);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    public ObservableList<Analysis> Readallanalyse() {
        ObservableList<Analysis> list = FXCollections.observableArrayList();
        try {

            String sql = "select * from Analysis ";//laboratory id statique en attendant la preparation de la méthode session
            Statement st = cnx.createStatement();

            ResultSet rs = st.executeQuery(sql);
            UserService ms = new UserService();
            while (rs.next()) {
                Analysis a = new Analysis(rs.getInt("analysis_id"), rs.getString("Analysis_name"), rs.getString("Description"),
                        rs.getString("result_type"), rs.getFloat("price"));
                list.add(a);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    public void Delete(int id) {
        String sql = "delete from Analysis where analysis_id =?";
        try {
            PreparedStatement st = cnx.prepareStatement(sql);
            st.setInt(1, id);
            try {
                st.executeUpdate();
                System.out.println("Analysis deleted");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void Update(Analysis a) {
        String sql = "UPDATE Analysis SET analysis_name=?, description=?, result_type=?, price=? WHERE analysis_id=?";
        try {
            PreparedStatement st = cnx.prepareStatement(sql);
            st.setString(1, a.getAnalysis_name());
            st.setString(2, a.getDescription());
            //System.out.println(a.getDescription());
            st.setString(3, a.getResult_type());
            st.setFloat(4, a.getPrice());
            st.setInt(5, a.getAnalysis_id());
            st.executeUpdate();
            System.out.println("Analysis updated successfully.");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void smsreporting() {

        Twilio.init("AC727b58b86ea4b6329528676b91b8bc49", "b1af49ba31b27c76c6b29d045456abfd");
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+21652986595"),
                new com.twilio.type.PhoneNumber("+12176248786"),
                "votre commande est préte")
                .create();

        System.out.println(message.getSid());
    }

    public ObservableList<Analysis> searchAnalysis(String searchTerm) {
        ObservableList<Analysis> searchResults = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM analysis WHERE analysis_name LIKE ?";
            PreparedStatement st = cnx.prepareStatement(sql);
            st.setString(1, "%" + searchTerm + "%");
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Analysis analysis = new Analysis(
                        rs.getInt("analysis_id"),
                        rs.getString("analysis_name"),
                        rs.getString("description"),
                        rs.getString("result_type"),
                        rs.getFloat("price")
                );
                searchResults.add(analysis);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return searchResults;
    }

    public ObservableList<Analysis> ReadAlll() {
        ObservableList<Analysis> list = FXCollections.observableArrayList();
        try {

            String sql = "select * from Analysis";
            Statement st = cnx.createStatement();

            ResultSet rs = st.executeQuery(sql);
            UserService ms = new UserService();
            while (rs.next()) {
                Analysis a = new Analysis(rs.getInt("analysis_id"), rs.getString("Analysis_name"), rs.getString("Description"),
                        rs.getString("result_type"), rs.getFloat("price"));
                list.add(a);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    @Override
    public int ajouter(Analysis t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Analysis> afficher() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(Analysis t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
