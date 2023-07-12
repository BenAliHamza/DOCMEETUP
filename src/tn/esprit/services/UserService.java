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
import tn.esprit.tools.Role;
import tn.esprit.entities.User;
import tn.esprit.gui.consultations.Consultations;
import tn.esprit.gui.consultations.controllers.HomePageController;
import tn.esprit.tools.MaConnexion;
/**
 *
 * @author AS
 */
    public class UserService implements IService<User>{
    Connection cnx;
    User user ;
    

    public UserService() {
        cnx= MaConnexion.getInstance().getCnx();
        user = HomePageController.getUser();
       
    }
    public boolean  isEmail(String email ) {
            String sql = "SELECT * FROM user WHERE email = ?";
            ResultSet rs ; 
            try {
            PreparedStatement st = cnx.prepareStatement(sql);
            st = cnx.prepareStatement(sql);
            st.setString(1, email);
            rs = st.executeQuery();
              return rs.next();
            }catch( Exception e){
                System.out.println(e.getMessage());
                            return false; 
        }

    }
    //@Override
    public void Create(User u){
       String sql="insert into user(email,password,username,first_name,last_name,role) values"
                 + "(?,?,?,?,?,?)";
        try {
            PreparedStatement st = cnx.prepareStatement(sql);
            st.setString(1, u.getEmail());
            st.setString(2, u.getPassword());
            st.setString(3, u.getUsername());
            st.setString(4, u.getFirst_name());
            st.setString(5, u.getLast_name());
            st.setString(6, u.getRole().name());     
            
            st.executeUpdate();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Registration");
                        alert.setHeaderText(null);
                        alert.setContentText("Le compte est cré");
                        alert.showAndWait();
                        
            }
            
                 catch (SQLException ex) {
                   Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Registration Error");
                        alert.setHeaderText(null);
                        alert.setContentText(ex.getMessage());
                        alert.showAndWait();
                    System.out.println(ex.getMessage());
                }
    } 
    
    //@Override
    public List<User> Read() {
         List<User> Users = new ArrayList<>();
        try {
           
            String sql="select * from user where user_id = " + user.getUser_id();
            Statement st = cnx.createStatement();
            ResultSet rs= st.executeQuery(sql);
            while(rs.next()){
                User u = new User(rs.getInt("user_id"),rs.getString("email"),rs.getString("password"),rs.getString("username"),(rs.getString("role")),
                        rs.getString("first_name"),rs.getString("last_name"),rs.getDate("birthdate"),rs.getString("address_line1"),rs.getString("address_line2"),rs.getString("city")
                        ,rs.getInt("postal_code"),rs.getInt("phone"),rs.getString("profile_picture_url"));
                Users.add(u);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Users;
    }
   public int login(String email, String password) {
        int userId = -1;
        try  {
            String query = "SELECT user_id FROM user WHERE email = ? AND password = ?";
            PreparedStatement statement = cnx.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
              


            if (resultSet.next()) {
                userId = resultSet.getInt("user_id");
                
            }else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("login");
                        alert.setHeaderText(null);
                        alert.setContentText("Le mots de passe ou l'email est inccorecte.");
                        alert.showAndWait();
            }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("login");
                        alert.setHeaderText(null);
                        alert.setContentText(e.getMessage());
                        alert.showAndWait();
        }
        return userId;
    }
    //@Override
    public void Update(User u) {
        String sql= "update user "
                + "set email = ?,"
                + "password = ?,"
                + "username = ?,"
                + "first_name = ?,"
                + "last_name = ?,"
                + "birthdate = ?,"
                + "address_line1 = ?,"
                + "address_line2 = ?,"
                + "city = ?,"
              
                + "postal_code = ?,"
               
                + "phone = ?,"
                + "profile_picture_url= ?,"
                + "role = ?"
                + " where user_id = ?;";
        try {
            PreparedStatement st = cnx.prepareStatement(sql);
            st.setString(1, u.getEmail());
            st.setString(2, u.getPassword());
            st.setString(3, u.getUsername());
            st.setString(4, u.getFirst_name());
            st.setString(5, u.getLast_name());
            st.setDate(6,new java.sql.Date(u.getBirthdate().getTime()));
            st.setString(7, u.getAddress_line1());
            st.setString(8,u.getAddress_line2());
            st.setString(9,u.getCity());
           
            st.setInt(11,u.getPostal_code());
     
            st.setInt(13,u.getPhone());
            st.setString(14,u.getProfile_picture_url());
            st.setString(15, u.getRole().name());
            st.setInt(16,u.getUser_id());
            System.out.println(st);
            st.executeUpdate();
            System.out.println("User updated");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    }

   // @Override
    public void Delete(int id) {
         String sql="delete from user where user_id ="
                 + " ? ;";
        try {
            PreparedStatement st= cnx.prepareStatement(sql);
            st.setInt(1,id);
            st.executeUpdate();
            System.out.println("User deleted");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
        public User SearchById(int id) {
            User u = null;
            try {
                String sql ; 
                switch(user.getRole()){
                    case patient:
                         sql= "SELECT * FROM user WHERE patient_id = " + id;
                          break;

                    case doctor:
                       sql= "SELECT * FROM user WHERE doctor_id = " + id;
                        break;

                    default:
                         sql= "SELECT * FROM user WHERE doctor_id = " + -1;
                        break;
                     
                }
                Statement st = cnx.createStatement();
                ResultSet rs = st.executeQuery(sql);

                if (rs.next()) {
                    u = new User(
                        rs.getInt("user_id"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("username"),
                        Role.valueOf(rs.getString("role")), // Assuming Role is an enum
                        rs.getString("first_name"),
                        rs.getString("last_name")
                    );
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            return u;
        }


    @Override
    public int ajouter(User t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> afficher() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(User t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    }
