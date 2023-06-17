package tn.esprit.tests;

import java.sql.Date;
import java.util.List;
import tn.esprit.entities.User;
import tn.esprit.services.UserService;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import tn.esprit.entities.Enum.City;
import tn.esprit.entities.Enum.Role;

public class Main {

    /*public static void main(String[] args) {
        String dateString = "2023-06-06"; // La date en format String

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            java.util.Date parsedDate = dateFormat.parse(dateString);
            Date sqlDate = new Date(parsedDate.getTime());

            System.out.println("Date: " + sqlDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Date d = new Date(2023, 06, 23);*/
        UserService ps = new UserService();
        User t;

    public Main() {
        this.t = new User(123456, "tt@esprit.tn", "password", "username", "First_name", "Last_name", "14/06/2023", "", "", City.Ariana, 99887766, "Profile", Role.doctor);
    }

        ps.Create(t);
    }

//    public static void main(String[] args) {
//    ps.update(t);
//  ps.supprimer(p);
//    public static void main(String[] args) {
//        String password = "MyPassword123";
    //   }
}
