/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.tests;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import tn.esprit.entities.Role;
import tn.esprit.entities.User;
import tn.esprit.services.UserService;


/**
 *
 * @author Fayechi
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        UserService userService = new UserService();

        // Create a User object
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date birthdate = dateFormat.parse("1993/01/04");

        User user = new User(1,"salimaroua1993@gmail.com", "mypassword", "SalimAroua",
                Role.patient, "Salim", "Aroua", birthdate,"52", "rue de russie"
                ,"Bizerte north", "Bizerte", 7000, "Tunisia", 53235426, "test123");
        //System.out.println(user);

        // Add the user to the database
        //userService.Create(user);
        //userService.Update(user);
        userService.Delete(user);
        //System.out.println(userService.Read());
       }}
    

