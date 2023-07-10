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
import java.util.ArrayList;
import java.util.List;
import tn.esprit.entities.User;
import tn.esprit.tools.MaConnexion;

/**
 * Cette classe représente le service de jointure entre Medication et User.
 */

public class MedicationUserService implements IService<User>{
    Connection cnx;
    


    public MedicationUserService() {
        cnx = MaConnexion.getInstance().getCnx();
    }

    public List<String> getPharmacyNamesWithMedication() {
        List<String> pharmacyNames = new ArrayList<>();

        try {
            String sql = "SELECT u.pharmacy_name,m.price, m.medication_name FROM Medication m INNER JOIN User u ON m.pharmacy_id = u.user_id";
            PreparedStatement statement = cnx.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String pharmacyName = resultSet.getString("pharmacy_name");
                Float price = resultSet.getFloat("price");
                String medication_name = resultSet.getString("medication_name");
                
                pharmacyNames.add(pharmacyName);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return pharmacyNames;
    }

    public void Create(User t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<User> Read() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void Delete(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void Update(User t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    

   