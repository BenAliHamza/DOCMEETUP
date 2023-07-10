/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package tn.esprit.entities;

/**
 *
 * @author ASUS
 */
public class Medication {

  
    private int medication_id;
    private Pharmacy pharmacy;
    private String medication_name;
    private String description;
    private String additional_information;
    private float price;
    private int stock;
   

   
    
    

    public Medication() {
    }

    public Medication(int medication_id, Pharmacy pharmacy, String medication_name, String description, String additional_information, float price,int stock) {
        this.medication_id = medication_id;
        this.pharmacy = pharmacy;
        this.medication_name = medication_name;
        this.description = description;
        this.additional_information = additional_information;
        this.price = price;
        this.stock = stock;
    }

    public Medication(Pharmacy pharmacy, String medication_name, String description, String additional_information, float price,int stock) {
        this.pharmacy = pharmacy;
        this.medication_name = medication_name;
        this.description = description;
        this.additional_information = additional_information;
        this.price = price;
        this.stock = stock;

    }
    
      public Medication(String medication_name, String description, String additional_information, float price,int stock) {
        this.medication_name = medication_name;
        this.description = description;
        this.additional_information = additional_information;
        this.price = price;
        this.stock = stock;

    }
    
    
     public Medication(   int medication_id,String medication_name, String description, String additional_information, float price,int stock) {
                 this.medication_id = medication_id;

        this.medication_name = medication_name;
        this.description = description;
        this.additional_information = additional_information;
        this.price = price;
        this.stock = stock;

    }
 

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public int getMedication_id() {
        return medication_id;
    }

    public void setMedication_id(int medication_id) {
        this.medication_id = medication_id;
    }

    public String getMedication_name() {
        return medication_name;
    }

    public void setMedication_name(String medication_name) {
        this.medication_name = medication_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdditional_information() {
        return additional_information;
    }

    public void setAdditional_information(String additional_information) {
        this.additional_information = additional_information;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
 public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
     @Override
    public String toString() {
        return "Medication{" + "medication_id=" + medication_id + ", pharmacy=" + pharmacy + ", medication_name=" + medication_name + ", description=" + description + ", additional_information=" + additional_information + ", price=" + price + ", stock=" + stock + '}';
    }
   


}