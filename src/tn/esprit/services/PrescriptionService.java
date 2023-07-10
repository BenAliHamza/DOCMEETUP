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
import tn.esprit.tools.MaConnexion;
import tn.esprit.entities.Prescription;
/**
 *
 * @author HP
 */
public class PrescriptionService implements IService<Prescription>{
   Connection cnx;
    
    public PrescriptionService(){
        cnx=MaConnexion.getInstance().getCnx();
    } 

    public void Create(Prescription o) {
        String sql="INSERT INTO prescription (doctor_id, patient_id, prescription_date, medication_id, medication_dose, analysis_id)\n" +
        "VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement st = cnx.prepareStatement(sql);
            st.setInt(1, o.getDoctor().getUser_id());
            st.setInt(2, o.getPatient().getUser_id() );
            st.setDate(3, new java.sql.Date(o.getPrescription_date().getTime()));
            st.setInt(4, o.getMedication().getMedication_id());
            st.setString(5, o.getMedication_dose());
            st.setInt(6, o.getAnalysis().getAnalysis_id());
            System.out.println(st);
            st.executeUpdate();
            System.out.println("prescription Add");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
    }
        
    

    public void Update(Prescription o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Prescription> Read() {
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void Delete(Prescription o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Prescription SearchbyId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int ajouter(Prescription t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Prescription> afficher() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(Prescription t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
