package tn.esprit.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Fayechi
 */
public class MaConnexion {
    String url ="jdbc:mysql://localhost:3306/docmeetupdb";
    String user ="root";
    String pwd="";
     private Connection cnx;
    public static MaConnexion ct;

    private MaConnexion() {
        try {
            cnx = DriverManager.getConnection(url, user, pwd);
            System.out.println("Cnx etablie !!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static MaConnexion getInstance(){
        if(ct==null){
            ct= new MaConnexion();
        }
        return ct;
    }

    public Connection getCnx() {
        return cnx;
    }
    
}
