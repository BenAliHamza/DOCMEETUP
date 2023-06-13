package tn.esprit.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tn.esprit.entities.Enum.City;
import tn.esprit.entities.Enum.Role;
import tn.esprit.entities.User;
import tn.esprit.tools.MaConnexion;

/**
 *
 * @author DELL
 */
public  class UserService  implements IService<User>{

    Connection cnx;
    

    public UserService() {
        cnx= MaConnexion.getInstance().getCnx();
    }
     @Override
    public void Create(User u){
       String sql="insert into User(user_id, email, password, username, first_name, last_name, birthdate, address_line1,address_line2,city,phone,profile_picture_url,role) values"
                 + "(?,?,?,?,?,?,?,?,?,?,?,?,?)";
       
        try {
            PreparedStatement st = cnx.prepareStatement(sql);
            st.setInt   (1, u.getUser_id());
            st.setString(2, u.getEmail());
            st.setString(3, u.getPassword());
            st.setString(4, u.getUsername());
            st.setString(5, u.getFirst_name());
            st.setString(6, u.getLast_name());
            st.setDate  (7, u.getBirthdate());
            st.setString(8, u.getAddress_line1());
            st.setString(9, u.getAddress_line2());
            st.setString(10, u.getCity().name());
            st.setInt   (11, u.getPhone());
            st.setString(12, u.getProfile_picture_url());
            st.setString(13, u.getRole().name());     
            //System.out.println(st);
            st.executeUpdate();
            System.out.println("User Add");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    } 
    
     
public void Update(User u) {
    String sql = "UPDATE User SET  user_id=?, email=?, Password=?, Username=?, First_name=?, Last_name=?, Birthdate=?, Address_line1=?, Address_line2=?, City=?,"
             + " Phone=?, Profile_picture_url=?, Role=?,  WHERE user_id=?";

    try {
        PreparedStatement st = cnx.prepareStatement(sql);
            st.setInt   (1, u.getUser_id());
            st.setString(2, u.getEmail());
            st.setString(3, u.getPassword());
            st.setString(4, u.getUsername());
            st.setString(5, u.getFirst_name());
            st.setString(6, u.getLast_name());
            st.setDate  (7, u.getBirthdate());
            st.setString(8, u.getAddress_line1());
            st.setString(9, u.getAddress_line2());
            st.setString(10, u.getCity().name());
            st.setInt   (11, u.getPhone());
            st.setString(12, u.getProfile_picture_url());
            st.setString(13, u.getRole().name()); 

        st.executeUpdate();
        System.out.println("User Updated !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

    @Override
      public List<User> Read() {
         List<User> Users = new ArrayList<>();
        try {
           
            String sql="select * from user";
            Statement st = cnx.createStatement();
            
            ResultSet rs= st.executeQuery(sql);
            while(rs.next()){
                User u;
                
                u = new User(
                        rs.getInt("user_id"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("username"),
                        rs.getString("first_name"),
                        rs.getString("last_name"), 
                        rs.getDate("birthdate"),
                        rs.getString("address_line1"),
                        rs.getString("address_line2"),
                        City.valueOf(rs.getString("city")),
                        rs.getInt("phone"),
                        rs.getString("profile_picture_url"),
                        Role.valueOf(rs.getString("role")));
//                
                Users.add(u);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Users;
    }
   

    
    @Override
    public void Delete(User u) {
        try {
            String sql="Delete from User WHERE user_id = ?";
            Statement st = cnx.createStatement();
            st.executeUpdate(sql);
            System.out.println("User deleted");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

 

}
