/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<FollowUp> Read() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Delete(FollowUp o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public FollowUp SearchbyId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
