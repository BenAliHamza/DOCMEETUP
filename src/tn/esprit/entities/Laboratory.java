/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entities;

import java.sql.Date;

/**
 *
 * @author ASUS
 */
public class Laboratory extends User {
    
    private String laboratory_name;
    private String tax_registration_number ;

    public Laboratory() {
    }

    public Laboratory(String laboratory_name, String tax_registration_number) {
        this.laboratory_name = laboratory_name;
        this.tax_registration_number = tax_registration_number;
    }

    public Laboratory(String laboratory_name, String tax_registration_number, String email, String password, String username, Role role, String first_name, String last_name, java.util.Date birthdate, String address_line1, String address_line2, String city, int postal_code, int phone, String profile_picture_url) {
        super(email, password, username, role, first_name, last_name, birthdate, address_line1, address_line2, city, postal_code, phone, profile_picture_url);
        this.laboratory_name = laboratory_name;
        this.tax_registration_number = tax_registration_number;
    }

    public Laboratory(String laboratory_name, String tax_registration_number, int user_id, String email, String password, String username, Role role, String first_name, String last_name, java.util.Date birthdate, String address_line1, String address_line2, String city, int postal_code, int phone, String profile_picture_url) {
        super(user_id, email, password, username, role, first_name, last_name, birthdate, address_line1, address_line2, city, postal_code, phone, profile_picture_url);
        this.laboratory_name = laboratory_name;
        this.tax_registration_number = tax_registration_number;
    }

    public String getLaboratory_name() {
        return laboratory_name;
    }

    public void setLaboratory_name(String laboratory_name) {
        this.laboratory_name = laboratory_name;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(int postal_code) {
        this.postal_code = postal_code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    @Override
    public String toString() {
        return "Laboratory{" + "laboratory_name=" + laboratory_name + ", tax_registration_number=" + tax_registration_number + '}';
    }
    
    

}