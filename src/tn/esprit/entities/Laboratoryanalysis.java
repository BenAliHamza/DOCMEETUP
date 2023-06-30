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
public class Laboratoryanalysis {
    private Analysis analysis;
    private Laboratory laboratory;
    private float price;

    public Laboratoryanalysis() {
    }

    public Laboratoryanalysis(Analysis analysis, Laboratory laboratory, float price) {
        this.analysis = analysis;
        this.laboratory = laboratory;
        this.price = price;
    }

    public Analysis getAnalysis() {
        return analysis;
    }

    public void setAnalysis(Analysis analysis) {
        this.analysis = analysis;
    }

    public Laboratory getLaboratory() {
        return laboratory;
    }

    public void setLaboratory(Laboratory laboratory) {
        this.laboratory = laboratory;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Laboratoryanalysis{" + "analysis=" + analysis + ", laboratory=" + laboratory + ", price=" + price + '}';
    }
    
    
    
    
}
