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
import tn.esprit.tools.Role;
import tn.esprit.entities.User;
import tn.esprit.tools.DBConnexion;
/**
 *
 * @author AS
 */
    public class UserService implements IService<User>{
    Connection cnx;
    

    public UserService() {
        cnx= DBConnexion.getInstance().getCnx();
    }
    @Override
    public void Create(User u){
       String sql="insert into user(user_id,email,password,username,first_name,last_name,birthdate,address_line1,address_line2,city,postal_code,"
               + "phone,profile_picture_url,role) values"
                 + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement st = cnx.prepareStatement(sql);
            st.setInt(1, u.getUser_id());
            st.setString(2, u.getEmail());
            st.setString(3, u.getPassword());
            st.setString(4, u.getUsername());
            st.setString(5, u.getFirst_name());
            st.setString(6, u.getLast_name());
            st.setDate(7,new java.sql.Date(u.getBirthdate().getTime()));
            st.setString(8, u.getAddress_line1());
            st.setString(9,u.getAddress_line2());
            st.setString(10,u.getCity());
            
            st.setInt(12,u.getPostal_code());
            
            st.setInt(14,u.getPhone());
            st.setString(15,u.getProfile_picture_url());
            st.setString(16, u.getRole().name());     
            
            st.executeUpdate();
            System.out.println("User Add");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    } 
    
    @Override
    public List<User> Read() {
         List<User> Users = new ArrayList<>();
        try {
           
            String sql="select * from user";
            Statement st = cnx.createStatement();
            
            ResultSet rs= st.executeQuery(sql);
            while(rs.next()){
                User u = new User(rs.getInt("user_id"),rs.getString("email"),rs.getString("password"),rs.getString("username"),
                        Role.valueOf(rs.getString("role")),
                        rs.getString("first_name"),rs.getString("last_name"),rs.getDate("birthdate"),rs.getString("address_line1"),
                        rs.getString("address_line2"),rs.getString("city")
                        ,rs.getInt("postal_code"),rs.getInt("phone"),rs.getString("profile_picture_url"));
                Users.add(u);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Users;
    }

    @Override
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

    @Override
    public void Delete(User u) {
         String sql="delete from user where user_id ="
                 + " ? ;";
        try {
            PreparedStatement st= cnx.prepareStatement(sql);
            st.setInt(1, u.getUser_id());
            st.executeUpdate();
            System.out.println("User deleted");
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public User SearchbyId(int id) {
        User u= new User();
        try {
           
            String sql="select * from user where user_id= "+id;
            System.out.println(sql);
            Statement st = cnx.createStatement();
            ResultSet rs= st.executeQuery(sql);
             while(rs.next()){
            System.out.println(rs);
            
                u = new User(rs.getInt("user_id"),rs.getString("email"),rs.getString("password"),rs.getString("username"),Role.valueOf(rs.getString("role")),
                        rs.getString("first_name"),rs.getString("last_name"),rs.getDate("birthdate"),rs.getString("address_line1"),rs.getString("address_line2"),rs.getString("city")
                        ,rs.getInt("postal_code"),rs.getInt("phone"),rs.getString("profile_picture_url"));
             }
             } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return u;
        }
}
