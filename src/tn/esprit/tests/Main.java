/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.tests;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import tn.esprit.entities.Analysis;
import tn.esprit.entities.Medication;
import tn.esprit.entities.Pharmacy;
import static tn.esprit.entities.Role.pharmacy;
import tn.esprit.services.AnalysisService;
import tn.esprit.services.MedicationService;



/**
 *
 * @author Fayechi
 */


public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        MedicationService ms = new MedicationService();
          //AnalysisService as = new AnalysisService();
        // Create a User object
       SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
       Date birthdate = dateFormat.parse("1993/01/04");
     Pharmacy p=new Pharmacy("pharmacy_name", "tax_registration_number", 1, "email", "password", "username", pharmacy, "first_name", "last_name", birthdate, "address_line1", "address_line2", "city", "tate", 0," country", 0, "profile_picture_url");
       Medication medication = new Medication(p,"Doliprane","Pour adultes","nothing to add",60.5f,105);
        // Add the medication to the database
        ms.Create(medication);
         System.out.println(ms.Read());
         //Analysis analysis = new Analysis("Blood Test", "Complete blood count","nothing to add",63.5f);
        //Add the analysis to the database
        //as.create(analysis);
        //System.out.println(as.read());
        
    }
    
    
    }
       

