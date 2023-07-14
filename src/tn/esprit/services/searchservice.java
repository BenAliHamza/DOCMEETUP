/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tn.esprit.entities.Medication;
import tn.esprit.entities.Pharmacy;
import tn.esprit.tools.MaConnexion;

/**
 * 

/**
 *
 * @author Fayechi
 */
public class searchservice implements IService<Medication>{
    Connection cnx;
    

    public searchservice() {
        cnx= MaConnexion.getInstance().getCnx();
    }
    
    public void Create(Medication m){
       String sql="insert into medication( pharmacy_id,medication_name,description,additional_information,price,stock) values"
                 + "(?,?,?,?,?,?)";
        int ad =1;
        try {
            PreparedStatement st = cnx.prepareStatement(sql);
            st.setInt(1, ad);
            st.setString(2, m.getMedication_name());
             st.setString(3, m.getDescription());
            st.setString(4, m.getAdditional_information());
            st.setFloat(5, m.getPrice());
            st.setInt(6,m.getStock());

           
                    
            
            st.executeUpdate();
            System.out.println("Medication Add");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }}
    
    
    
    public ObservableList<Medication> Read() {
      ObservableList <Medication> list =  FXCollections.observableArrayList();
        try {
           
            String sql="select * from Medication where pharmacy_id = 1"; //pharmachie id statique en attendant la preparation de la méthode session
            Statement st = cnx.createStatement();
            ResultSet rs= st.executeQuery(sql);
            UserService ms= new UserService();
            while(rs.next()){
                Medication m = new Medication(
                rs.getInt("medication_id"),rs.getString("Medication_name"),
                rs.getString("Description"),rs.getString("Additional_information"), rs.getFloat("price"),rs.getInt("stock"));
                list.add(m);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    
    
        public ObservableList<Medication> Readall() {
    ObservableList<Medication> list = FXCollections.observableArrayList();
    try {
        String sql = "SELECT m.medication_id, u.pharmacy_name, m.medication_name, m.description, m.additional_information, m.price, m.stock FROM Medication m INNER JOIN User u ON m.pharmacy_id = u.user_id";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            Pharmacy pharmacy = new Pharmacy();
            pharmacy.setPharmacy_name(rs.getString("pharmacy_name"));
            
            Medication medication = new Medication(
                rs.getInt("medication_id"),
                pharmacy,
                rs.getString("medication_name"),
                rs.getString("description"),
                rs.getString("additional_information"),
                rs.getFloat("price"),
                rs.getInt("stock")
            );
            list.add(medication);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return list;
}
    
    
    
    
    
    public void Delete (int id) {
try {
            String sql="delete from Medication where medication_id ="+id;
            Statement ste = cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println("Medication deleted");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }    

 
    public void Update(Medication m) {
    String sql = "UPDATE Medication SET medication_name=?, description=?, additional_information=?, price=? , stock=? where medication_id=?";
    try {
        PreparedStatement st = cnx.prepareStatement(sql);
        st.setString(1, m.getMedication_name());
        st.setString(2, m.getDescription());
        st.setString(3, m.getAdditional_information());
        st.setFloat(4, m.getPrice());
         st.setInt(5, m.getStock());
         st.setInt(6, m.getMedication_id());
      //  st.setInt(2, m.getPharmacy().getUser_id());

        st.executeUpdate();
        System.out.println("Medication updated successfully.");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
  
}
      
      public  void  smsreporting () {
 
   
          Twilio.init("AC727b58b86ea4b6329528676b91b8bc49","b1af49ba31b27c76c6b29d045456abfd");
           Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+21652986595"),
                new com.twilio.type.PhoneNumber("+12176248786"), 
                "votre commande est préte" )
            .create();

        System.out.println(message.getSid());
    }
    
    public ObservableList<Medication> searchMedication(String searchTerm) {
    ObservableList<Medication> searchResults = FXCollections.observableArrayList();

    try {
        String sql = "SELECT * FROM medication WHERE medication_name LIKE ?";
        PreparedStatement st = cnx.prepareStatement(sql);
        st.setString(1, "%" + searchTerm + "%");
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            Medication medication = new Medication(
                rs.getInt("medication_id"),
                rs.getString("medication_name"),
                rs.getString("description"),
                rs.getString("additional_information"),
                rs.getFloat("price"),
                rs.getInt("stock")
            );
            searchResults.add(medication);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    return searchResults;
}

    @Override
    public int ajouter(Medication t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Medication> afficher() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(Medication t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
    
    
    
    
    

}





