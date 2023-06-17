/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entities;

import java.sql.Date;
import tn.esprit.entities.Enum.City;
import tn.esprit.entities.Enum.Role;

/**
 *
 * @author DELL
 */
public class Admin extends User{

    public Admin() {
        super();
    }

    public Admin(String email, String password, String username, String first_name, String last_name, Date birthdate, String address_line1, String address_line2, City city, int phone, String Profile_picture_url, tn.esprit.entities.Enum.Role role) {
        super(email, password, username, first_name, last_name, birthdate, address_line1, address_line2, city, phone, Profile_picture_url, role);
    }

    public Admin(int user_id, String email, String password, String username, String first_name, String last_name, Date birthdate, String address_line1, String address_line2, City city, int phone, String Profile_picture_url, tn.esprit.entities.Enum.Role role) {
        super(user_id, email, password, username, first_name, last_name, birthdate, address_line1, address_line2, city, phone, Profile_picture_url, role);
    }
    
}