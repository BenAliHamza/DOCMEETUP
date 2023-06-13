package tn.esprit.entities;

import java.sql.Date;

/**
 *
 * @author DELL
 */
public class Patient extends User{

    public Patient() {
    }

    public Patient(String email, String password, String username, String last_name, String first_name, Date birthdate, String address_line1, String address_line2, tn.esprit.entities.Enum.City city, int phone, String Profile_picture_url, tn.esprit.entities.Enum.Role role) {
        super(email, password, username, last_name, first_name, birthdate, address_line1, address_line2, city, phone, Profile_picture_url, role);
    }

    public Patient(int user_id, String email, String password, String username, String last_name, String first_name, Date birthdate, String address_line1, String address_line2, tn.esprit.entities.Enum.City city, int phone, String Profile_picture_url, tn.esprit.entities.Enum.Role role) {
        super(user_id, email, password, username, last_name, first_name, birthdate, address_line1, address_line2, city, phone, Profile_picture_url, role);
    }


   
    public class Role {
    // Implémentez la classe Role selon vos besoins
}

    }
    



