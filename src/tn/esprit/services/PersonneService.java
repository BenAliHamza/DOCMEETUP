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
import tn.esprit.entities.Personne;
import tn.esprit.tools.MaConnexion;

/**
 *
 * @author Fayechi
 */
public class PersonneService implements IService<Personne>{
    Connection cnx;
    

    public PersonneService() {
        cnx= MaConnexion.getInstance().getCnx();
    }
    
    
    @Override
    public void ajouter(Personne t) {
//        try {
//            String sql="insert into personne(nom,prenom,age) "
//                    + "values ('"+t.getNom()+"','"+t.getPrenom()+
//                    "','"+t.getAge()+"')";
//            Statement st = cnx.createStatement();
//            st.executeUpdate(sql);
//            System.out.println("personne ajoutée ");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }

         String sql="insert into personne(nom,prenom,age) values"
                 + "(?,?,?)";
        try {
            PreparedStatement st = cnx.prepareStatement(sql);
            st.setString(1, t.getNom());
            st.setString(2, t.getPrenom());
            st.setInt(3, t.getAge());
            st.executeUpdate();
            System.out.println("personne ajoutéé !!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Personne> afficher() {
         List<Personne> personnes = new ArrayList<>();
        try {
           
            String sql="select * from personne";
            Statement st = cnx.createStatement();
            
            ResultSet rs= st.executeQuery(sql);
            while(rs.next()){
                Personne p = new Personne(rs.getInt("id"),
                        rs.getInt("age"), rs.getString("nom"), rs.getString(3));
                personnes.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return personnes;
    }

    @Override
    public void supprimer(Personne t) {
        try {
            String sql="delete from personne where id ="+t.getId();
            Statement ste = cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println("personne supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
