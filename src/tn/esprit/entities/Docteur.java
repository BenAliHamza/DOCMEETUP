package tn.esprit.entities;

import java.sql.Date;
import java.util.logging.Logger;
import tn.esprit.entities.Enum.Doctor_specialty;

/**
 *
 * @author DELL
 */
public class Docteur extends User{
    protected String doctor_license_number;
    protected String doctor_clinic_name;
    protected Doctor_specialty doctor_specialty;
    protected String tax_registration_number;

    public Docteur() {
    }

    public Docteur(String doctor_license_number, String doctor_clinic_name, Doctor_specialty doctor_specialty, String tax_registration_number, String email, String password, String username, String last_name, String first_name, Date birthdate, String address_line1, String address_line2, tn.esprit.entities.Enum.City city, int phone, String Profile_picture_url, tn.esprit.entities.Enum.Role role) {
        super(email, password, username, last_name, first_name, birthdate, address_line1, address_line2, city, phone, Profile_picture_url, role);
        this.doctor_license_number = doctor_license_number;
        this.doctor_clinic_name = doctor_clinic_name;
        this.doctor_specialty = doctor_specialty;
        this.tax_registration_number = tax_registration_number;
    }

    public Docteur(String doctor_license_number, String doctor_clinic_name, Doctor_specialty doctor_specialty, String tax_registration_number, int user_id, String email, String password, String username, String last_name, String first_name, Date birthdate, String address_line1, String address_line2, tn.esprit.entities.Enum.City city, int phone, String Profile_picture_url, tn.esprit.entities.Enum.Role role) {
        super(user_id, email, password, username, last_name, first_name, birthdate, address_line1, address_line2, city, phone, Profile_picture_url, role);
        this.doctor_license_number = doctor_license_number;
        this.doctor_clinic_name = doctor_clinic_name;
        this.doctor_specialty = doctor_specialty;
        this.tax_registration_number = tax_registration_number;
    }

 
}
