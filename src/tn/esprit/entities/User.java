package tn.esprit.entities;

import java.util.Date;

public class User {
    protected int user_id;
    protected String email;
    protected String password;
    protected String username;
    protected Role role;
    protected String first_name;
    protected String last_name;
    protected Date birthdate;
    protected String address_line1;
    protected String address_line2;
    protected String city;
    protected String state;
    protected int postal_code;
    protected String country;
    protected int phone;
    protected String profile_picture_url;

    public User() {
    }

    public User(int user_id, String email, String password, String username, Role role) {
        this.user_id = user_id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.role = role;
        }
    public User(int user_id, String first_name) {
        this.user_id = user_id;
        this.first_name = first_name;
    }

    public User(String email, String password, String username, Role role, String first_name, String last_name, Date birthdate, String address_line1, String address_line2, String city, String state, int postal_code, String country, int phone, String profile_picture_url) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.role = role;
        this.first_name = first_name;
        this.last_name = last_name;
        this.birthdate = birthdate;
        this.address_line1 = address_line1;
        this.address_line2 = address_line2;
        this.city = city;
        this.state = state;
        this.postal_code = postal_code;
        this.country = country;
        this.phone = phone;
        this.profile_picture_url = profile_picture_url;
    }

    public User(int user_id, String email, String password, String username, Role role, String first_name, String last_name, Date birthdate, String address_line1, String address_line2, String city, String state, int postal_code, String country, int phone, String profile_picture_url) {
        this.user_id = user_id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.role = role;
        this.first_name = first_name;
        this.last_name = last_name;
        this.birthdate = birthdate;
        this.address_line1 = address_line1;
        this.address_line2 = address_line2;
        this.city = city;
        this.state = state;
        this.postal_code = postal_code;
        this.country = country;
        this.phone = phone;
        this.profile_picture_url = profile_picture_url;
    }

    public User(String valueOf, String email, String password, String username, Role role, String firstName, String lastName, Object object, Object object0, Object object1, Object object2, int i, Object object3, int i0, Object object4) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public User(int userId, String email, String password, String username, Role role, int phone, String first_name, String last_name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
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
        return "User{" +
                "user_id=" + user_id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", role=" + role +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", birthdate=" + birthdate +
                ", address_line1='" + address_line1 + '\'' +
                ", address_line2='" + address_line2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", postal_code=" + postal_code +
                ", country='" + country + '\'' +
                ", phone=" + phone +
                ", profile_picture_url='" + profile_picture_url + '\'' +
                '}';
    }

    public void setPhone(String phone) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public enum Role {
        PATIENT, DOCTOR, PHARMACY, LABORATORY, INSURANCE;

        public static Role fromDatabaseValue(String value) {
            if (value == null) {
                return null;
            }
            switch (value.toUpperCase()) {
                case "PATIENT":
                    return PATIENT;
                case "DOCTOR":
                    return DOCTOR;
                case "PHARMACY":
                    return PHARMACY;
                case "LABORATORY":
                    return LABORATORY;
                case "INSURANCE":
                    return INSURANCE;
                default:
                    throw new IllegalArgumentException("Invalid role value: " + value);
            }
        }
    }
    
}
