package tn.esprit.entities;


import java.sql.Date;
import tn.esprit.entities.Enum.City;
import tn.esprit.entities.Enum.Role;


/**
 *
 * @author DELL
 */
public class User {
    protected int user_id;
    protected String email;
    protected String password;
    protected String username;
    protected String first_name, last_name;
    protected Date birthdate;
    protected String address_line1,address_line2;
    protected City city;
    protected int phone;
    protected String Profile_picture_url;
    protected Role role;

    public User() {
    }

    public User(String email, String password, String username, String first_name, String last_name, Date birthdate, String address_line1, String address_line2, City city, int phone, String Profile_picture_url, Role role) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.first_name = first_name;
        this.last_name = last_name;
        this.birthdate = birthdate;
        this.address_line1 = address_line1;
        this.address_line2 = address_line2;
        this.city = city;
        this.phone = phone;
        this.Profile_picture_url = Profile_picture_url;
        this.role = role;
    }

    public User(int user_id, String email, String password, String username, String first_name, String last_name, Date birthdate, String address_line1, String address_line2, City city, int phone, String Profile_picture_url, Role role) {
        this.user_id = user_id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.first_name = first_name;
        this.last_name = last_name;
        this.birthdate = birthdate;
        this.address_line1 = address_line1;
        this.address_line2 = address_line2;
        this.city = city;
        this.phone = phone;
        this.Profile_picture_url = Profile_picture_url;
        this.role = role;
    }

    public int getUser_id() {
        return user_id;
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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getProfile_picture_url() {
        return Profile_picture_url;
    }

    public void setProfile_picture_url(String Profile_picture_url) {
        this.Profile_picture_url = Profile_picture_url;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" + "user_id=" + user_id + ", email=" + email + ", password=" + password + ", username=" + username + ", first_name=" + first_name + ", last_name=" + last_name + ", birthdate=" + birthdate + ", address_line1=" + address_line1 + ", address_line2=" + address_line2 + ", city=" + city + ", phone=" + phone + ", Profile_picture_url=" + Profile_picture_url + ", role=" + role + '}';
    }
    

}
