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
public class Pharmacymedication {
    private Pharmacy parmachy;
    private Medication medication;
    private int quantite;
    private float price;

    public Pharmacymedication() {
    }

    public Pharmacymedication(Pharmacy parmachy, Medication medication, int quantite, float price) {
        this.parmachy = parmachy;
        this.medication = medication;
        this.quantite = quantite;
        this.price = price;
        
        
    }

    public Pharmacy getParmachy() {
        return parmachy;
    }

    public void setParmachy(Pharmacy parmachy) {
        this.parmachy = parmachy;
    }

    public Medication getMedication() {
        return medication;
    }

    public void setMedication(Medication medication) {
        this.medication = medication;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Pharmacymedication{" + "parmachy=" + parmachy + ", medication=" + medication + ", quantite=" + quantite + ", price=" + price + '}';
    }
    
    
}
