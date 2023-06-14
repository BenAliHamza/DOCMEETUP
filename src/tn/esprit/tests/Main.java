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
import java.util.SimpleTimeZone;
import tn.esprit.entities.FollowUp;
import tn.esprit.tools.Role;
import tn.esprit.entities.User;
import tn.esprit.services.FollowUpService;
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

        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date birthdate = dateFormat.parse("1993/01/04 16:49");

        User user = new User(2,"salimaroua1993@gmail.com", "mypassword", "SalimAroua",
               Role.patient, "Salim", "Aroua", birthdate,"52", "rue de russie"
                , "Bizerte", 7000, 53235426, "test123");
        
        //System.out.println(user);

        //userService.Create(user);
        //userService.Update(user);
        //userService.Delete(user);
        //System.out.println(userService.Read());
        FollowUp f= new FollowUp(5,user, birthdate, "blood_pressure123", 0, 0, 0);
        //System.out.println(userService.SearchbyId(1));
        
        
        FollowUpService followService = new FollowUpService();
        //FollowUp F=new FollowUp( user, birthdate, "blood_pressure", 0, 0, 0);
        //followService.Delete(f);
        //System.out.println(followService.SearchbyId(2));
    }
       }
    

