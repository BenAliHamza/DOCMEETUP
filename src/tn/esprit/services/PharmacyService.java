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
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.esprit.entities.Medication;
import tn.esprit.entities.Pharmacy;
import tn.esprit.entities.User;
import tn.esprit.tools.MaConnexion;
import tn.esprit.tools.Role;
import tn.esprit.entities.Analysis;

/**
 *
 * @author HP
 */
public class PharmacyService {
 Connection cnx;
    

    public PharmacyService() {
        cnx= MaConnexion.getInstance().getCnx();
    }
    public Pharmacy toPharmacy(User user) {
        Pharmacy lab = new Pharmacy();

        String sql = "select * from user where user_id=?";
        try {
            PreparedStatement st = cnx.prepareStatement(sql);
            st.setInt(1, user.getUser_id());

            try {
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    lab = new Pharmacy(
                            rs.getString("Pharmacy_name"),
                            rs.getString("tax_registration_number"),
                            rs.getInt("User_id"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getString("username"),
                            Role.valueOf(rs.getString("role")),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getDate("birthdate"),
                            rs.getString("address_line1"),
                            rs.getString("address_line2"),
                            rs.getString("city"),
                            rs.getInt("postal_code"),
                            rs.getInt("phone"),
                            rs.getString("profile_picture_url")
                    );

                }
            } catch (SQLException ex) {
                Logger.getLogger(LaboratoryService.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LaboratoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lab;
    }
    public Pharmacy toPharmacyid(int id) {
        Pharmacy lab = new Pharmacy();

        String sql = "select * from user where user_id=?";
        try {
            PreparedStatement st = cnx.prepareStatement(sql);
            st.setInt(1, id);

            try {
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    lab = new Pharmacy(
                            rs.getString("Pharmacy_name"),
                            rs.getString("tax_registration_number"),
                            rs.getInt("User_id"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getString("username"),
                            Role.valueOf(rs.getString("role")),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getDate("birthdate"),
                            rs.getString("address_line1"),
                            rs.getString("address_line2"),
                            rs.getString("city"),
                            rs.getInt("postal_code"),
                            rs.getInt("phone"),
                            rs.getString("profile_picture_url")
                    );

                }
            } catch (SQLException ex) {
                Logger.getLogger(LaboratoryService.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LaboratoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lab;
    }
    

}
