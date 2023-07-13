/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.sql.SQLException;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tn.esprit.entities.FollowUp;
import tn.esprit.tools.MaConnexion;




/**
 *
 * @author HP
 */
public class FollowUpService implements IService<FollowUp>{
    Connection cnx;
    public FollowUpService() {
        cnx= MaConnexion.getInstance().getCnx();
    }
    public void Create(FollowUp o) {
String sql="insert into followup(followup_id,user_id,date,blood_pressure,heart_rate,temperature,weight)"
        + " values(?,?,?,?,?,?,?)";
        int med = 1;
        try {
            PreparedStatement st = cnx.prepareStatement(sql);
            st.setInt(1, o.getFollowup_id());
            //st.setInt(2, o.getUser().getUser_id());
            st.setInt(2, med);
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

    public void Update(FollowUp o) {
        int med = 1;
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
           // st.setInt(1,o.getUser().getUser_id());
             st.setInt(1, med);
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

    public List<FollowUp> Read() {
        List<FollowUp> Followups = new ArrayList<>();
        try {
           
            String sql="select * from followup";
            Statement st = cnx.createStatement();
            UserService us= new UserService();
            
            ResultSet rs= st.executeQuery(sql);
            while(rs.next()){
                FollowUp f = new FollowUp(rs.getInt("followup_id" ),
                        us.SearchById(rs.getInt("user_id")),
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
    
   public   ObservableList <FollowUp> listfo(){
        
      ObservableList <FollowUp> list =  FXCollections.observableArrayList();
        try{
           String sql="select * from followup";
            Statement st = cnx.createStatement();
            UserService us= new UserService();
            
            ResultSet rs= st.executeQuery(sql);
       
            while (rs.next()){
                
          FollowUp f = new FollowUp(rs.getInt("followup_id" ),
                        us.SearchById(rs.getInt("user_id")),
                        rs.getDate("date"), 
                        rs.getString("blood_pressure"),
                        rs.getInt("heart_rate"),
                        rs.getFloat("temperature"),
                        rs.getFloat("weight"));
                      list.add(f);

            }
         
        }

        catch(SQLException k){
System.out.println(k.getMessage());         }
        return list;
        
    } 



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
    

    public FollowUp SearchbyId(int id) {
        FollowUp f= new FollowUp();
        try {
           
            String sql="select * from followup where followup_id= "+id;
            Statement st = cnx.createStatement();
            ResultSet rs= st.executeQuery(sql);
             while(rs.next()){
           
            UserService us=new UserService();
                f = new FollowUp(rs.getInt("followup_id"),
                        us.SearchById(rs.getInt("user_id")),
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
    
    
    
    
    
    
    
    
    public void pdf(String blood, String heart, String weight, String emp) throws FileNotFoundException, SQLException, DocumentException {

	       Document my_pdf_report = new Document();
	       PdfWriter.getInstance(my_pdf_report, new FileOutputStream("C://test//test.pdf"));
	my_pdf_report.open();
	//we have four columns in our table
        Phrase Phrase1 = new Phrase("Please found bellow the Selected Follow Up.");
        my_pdf_report.add(Phrase1);
	       PdfPTable my_report_table = new PdfPTable(2);
	//create a cell object
	PdfPCell table_cell;
    
 	table_cell = new PdfPCell(new Phrase("blood pressure"));
	my_report_table.addCell(table_cell);
        	table_cell = new PdfPCell(new Phrase(blood));
	my_report_table.addCell(table_cell);

	table_cell = new PdfPCell(new Phrase("heart rate "));
	my_report_table.addCell(table_cell);
        
        	table_cell = new PdfPCell(new Phrase(heart));
	my_report_table.addCell(table_cell);

	table_cell = new PdfPCell(new Phrase("weight"));
	my_report_table.addCell(table_cell);
        	table_cell = new PdfPCell(new Phrase(weight));
	my_report_table.addCell(table_cell);

	table_cell = new PdfPCell(new Phrase("temperature "));
	my_report_table.addCell(table_cell); 
        	table_cell = new PdfPCell(new Phrase(emp));
	my_report_table.addCell(table_cell);


	/* Attach report table to PDF */
	my_pdf_report.add(my_report_table);
        
        Phrase Phrase2 = new Phrase("This file is generated by DocMeetUp.");
        my_pdf_report.add(Phrase2);
	my_pdf_report.close();

	/* Close all DB related objects */
    }
    public ObservableList<FollowUp> listfollowupid(int id) {

        ObservableList<FollowUp> list = FXCollections.observableArrayList();
        try {
            String sql = "select * from followup where followup_id=" + id;
            Statement st = cnx.createStatement();
            UserService us = new UserService();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                FollowUp f = new FollowUp(rs.getInt("followup_id"),
                        us.SearchById(rs.getInt("user_id")),
                        rs.getDate("date"),
                        rs.getString("blood_pressure"),
                        rs.getInt("heart_rate"),
                        rs.getFloat("temperature"),
                        rs.getFloat("weight"));
                list.add(f);
            }
        } catch (SQLException k) {
            System.out.println(k.getMessage());
        }
        return list;

    }

    @Override
    public int ajouter(FollowUp t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<FollowUp> afficher() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(FollowUp t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
