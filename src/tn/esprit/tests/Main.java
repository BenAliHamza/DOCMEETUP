/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.tests;

import java.sql.Date;
import java.sql.Time;
import tn.esprit.entities.Evenement;
import tn.esprit.entities.User;
import tn.esprit.services.EvenementService;

/**
 *
 * @author DRIDI Oussama
 */
public class Main {
    
    Date d= new Date(2020,05,21);
            Time t=new Time(20,22,59);
    EvenementService es = new EvenementService();
        User u =new User();
        
        Evenement e = new Evenement(2,u , "qwerty", d, t);
        

   
}
