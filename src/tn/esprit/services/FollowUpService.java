/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tn.esprit.entities.FollowUp;
import tn.esprit.tools.DBConnexion;

/**
 *
 * @author HP
 */
public class FollowUpService implements IService<FollowUp>{
    Connection cnx;
    public FollowUpService() {
        cnx= DBConnexion.getInstance().getCnx();
    }
    @Override
    public void Create(FollowUp o) {
String sql="insert into followup(followup_id,user_id,date,blood_pressure,heart_rate,temperature,weight)"
        + " values(?,?,?,?,?,?,?)";
        try {
            PreparedStatement st = cnx.prepareStatement(sql);
            st.setInt(1, o.getFollowup_id());
            st.setInt(2, o.getUser().getUser_id());
            st.setDate(3, new java.sql.Date(o.getDate().getTime()));
            st.setString(4, o.getBlood_pressure());
            st.setInt(5, o.getHeart_rate());
            st.setDouble(6, o.getTemperature());
            st.setDouble(7, o.getWeight());

            System.out.println(st);
            
            st.executeUpdate();
            System.out.println("FollowUp Add");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }

    @Override
    public void Update(FollowUp o) {
        String sql= "update followup "
                + "set "
                +"user_id=?,"
                + "date = ?,"
                + "blood_pressure = ?,"
                + "heart_rate = ?,"
                + "temperature = ?,"
                + "weight = ?"
                + " where followup_id = ?;";
        try {
            PreparedStatement st = cnx.prepareStatement(sql);
            st.setInt(1,o.getUser().getUser_id());
            st.setDate(2, new java.sql.Date(o.getDate().getTime()));
            st.setString(3,o.getBlood_pressure());
            st.setInt(4,o.getHeart_rate());
            st.setFloat(5,o.getTemperature());
            st.setFloat(6,o.getWeight());
            st.setInt(7,o.getFollowup_id());
            
            System.out.println(st);
            st.executeUpdate();
            System.out.println("FollowUp updated");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    }

    @Override
    public List<FollowUp> Read() {
        List<FollowUp> Followups = new ArrayList<>();
        try {
           
            String sql="select * from followup";
            Statement st = cnx.createStatement();
            UserService us= new UserService();
            
            ResultSet rs= st.executeQuery(sql);
            while(rs.next()){
                FollowUp f = new FollowUp(rs.getInt("followup_id" ),
                        us.SearchbyId(rs.getInt("user_id")),
                        rs.getDate("date"), 
                        rs.getString("blood_pressure"),
                        rs.getInt("heart_rate"),
                        rs.getFloat("temperature"),
                        rs.getFloat("weight"));
                Followups.add(f);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Followups;
    }


    @Override
    public void Delete(FollowUp o) {
      String sql="delete from followup where followup_id ="
                 + " ? ;";
        try {
            PreparedStatement st= cnx.prepareStatement(sql);
            st.setInt(1, o.getFollowup_id());
            st.executeUpdate();
            System.out.println("Followup deleted");
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    

    @Override
    public FollowUp SearchbyId(int id) {
        FollowUp f= new FollowUp();
        try {
           
            String sql="select * from followup where followup_id= "+id;
            Statement st = cnx.createStatement();
            ResultSet rs= st.executeQuery(sql);
             while(rs.next()){
           
            UserService us=new UserService();
                f = new FollowUp(rs.getInt("followup_id"),
                        us.SearchbyId(rs.getInt("user_id")),
                        rs.getDate("date"),
                        rs.getString("blood_pressure"), 
                        rs.getInt("heart_rate"), 
                        rs.getFloat("temperature"), 
                        rs.getFloat("weight"));
       
             }
             } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return f;    }
    
}
