/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package tn.esprit.entities;

import java.util.Date;
import tn.esprit.tools.Role;

/**
 *
 * @author Fayechi
 */
public class Pharmacy extends User{
    
    private String pharmacy_name;
    private String tax_registration_number ;
   

    public Pharmacy() {
    }

    public Pharmacy(String pharmacy_name, String tax_registration_number, String email, String password, String username, Role role, String first_name, String last_name, Date birthdate, String address_line1, String address_line2, String city, String state, int postal_code, String country, int phone, String profile_picture_url) {
        super(email, password, username, role, first_name, last_name, birthdate, address_line1, address_line2, city, postal_code,  phone, profile_picture_url);
        this.pharmacy_name = pharmacy_name;
        this.tax_registration_number = tax_registration_number;
    }

    public Pharmacy(String pharmacy_name, String tax_registration_number, int user_id, String email, String password, String username, Role role, String first_name, String last_name, Date birthdate, String address_line1, String address_line2, String city, String state, int postal_code, String country, int phone, String profile_picture_url) {
        super(user_id, email, password, username, role, first_name, last_name, birthdate, address_line1, address_line2, city,  postal_code, phone, profile_picture_url);
        this.pharmacy_name = pharmacy_name;
        this.tax_registration_number = tax_registration_number;
    }

   
    

    

    public String getPharmacy_name() {
        return pharmacy_name;
    }

    public void setPharmacy_name(String pharmacy_name) {
        this.pharmacy_name = pharmacy_name;
    }

    public String getTax_registration_number() {
        return tax_registration_number;
    }

    public void setTax_registration_number(String tax_registration_number) {
        this.tax_registration_number = tax_registration_number;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public java.util.Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(java.util.Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getAddress_line1() {
        return address_line1;
    }

    public void setAddress_line1(String address_line1) {
        this.address_line1 = address_line1;
    }

    public String getAddress_line2() {
        return address_line2;
    }

    public void setAddress_line2(String address_line2) {
        this.address_line2 = address_line2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

   

    public int getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(int postal_code) {
        this.postal_code = postal_code;
    }

   

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getProfile_picture_url() {
        return profile_picture_url;
    }

    public void setProfile_picture_url(String profile_picture_url) {
        this.profile_picture_url = profile_picture_url;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    

    @Override
    public String toString() {
        return "Pharmacy{" + "pharmacy_name=" + pharmacy_name + ", tax_registration_number=" + tax_registration_number + '}';
    }
    
    

}