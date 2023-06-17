package tn.esprit.entities;

import java.sql.Date;

/**
 *
 * @author DELL
 */
public class Insurance extends User{
    protected String insurance_name;
    protected String tax_registration_number;

    
    public Insurance() {
    }

    public Insurance(String insurance_name, String tax_registration_number, String email, String password, String username, String last_name, String first_name, Date birthdate, String address_line1, String address_line2, tn.esprit.entities.Enum.City city, int phone, String Profile_picture_url, tn.esprit.entities.Enum.Role role) {
        super(email, password, username, last_name, first_name, birthdate, address_line1, address_line2, city, phone, Profile_picture_url, role);
        this.insurance_name = insurance_name;
        this.tax_registration_number = tax_registration_number;
    }

    public Insurance(String insurance_name, String tax_registration_number, int user_id, String email, String password, String username, String last_name, String first_name, Date birthdate, String address_line1, String address_line2, tn.esprit.entities.Enum.City city, int phone, String Profile_picture_url, tn.esprit.entities.Enum.Role role) {
        super(user_id, email, password, username, last_name, first_name, birthdate, address_line1, address_line2, city, phone, Profile_picture_url, role);
        this.insurance_name = insurance_name;
        this.tax_registration_number = tax_registration_number;
    }

 
}
