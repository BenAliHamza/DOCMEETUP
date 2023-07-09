/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Fayechi
 */
public class DBConnexion {
    //String url ="jdbc:mysql://localhost:3306/pianwer";
    String url ="jdbc:mysql://localhost:3306/test1";
    String user ="root";
    String pwd="";
     private Connection cnx;
    public static DBConnexion ct;

    private DBConnexion() {
        try {
            cnx = DriverManager.getConnection(url, user, pwd);
            System.out.println("Cnx etablie !!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static DBConnexion getInstance(){
        if(ct==null){
            ct= new DBConnexion();
        }
        return ct;
    }

    public Connection getCnx() {
        return cnx;
    }
    
}
