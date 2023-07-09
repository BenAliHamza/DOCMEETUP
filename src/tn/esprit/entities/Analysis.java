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
public class Analysis {
    private int analysis_id ;
    private Laboratory laboratory;
    private String analysis_name;
    private String description;
    private String result_type;
    private float price;

    public Analysis() {
    }

    public Analysis(int analysis_id, Laboratory laboratory, String analysis_name, String description, String result_type, float price) {
        this.analysis_id = analysis_id;
        this.laboratory = laboratory;
        this.analysis_name = analysis_name;
        this.description = description;
        this.result_type = result_type;
        this.price = price;
    }

    public Analysis(Laboratory laboratory, String analysis_name, String description, String result_type, float price) {
        this.laboratory = laboratory;
        this.analysis_name = analysis_name;
        this.description = description;
        this.result_type = result_type;
        this.price = price;
    }
    
    

    public Analysis(String analysis_name, String description, String result_type, float price) {
        this.analysis_name = analysis_name;
        this.description = description;
        this.result_type = result_type;
        this.price = price;
    }
    
     public Analysis(   int analysis_id, String analysis_name, String description, String result_type, float price) {
         
        this.analysis_id = analysis_id;
        this.analysis_name = analysis_name;
        this.description = description;
        this.result_type = result_type;
        this.price = price;


    }

    public int getAnalysis_id() {
        return analysis_id;
    }

    public void setAnalysis_id(int analysis_id) {
        this.analysis_id = analysis_id;
    }

    public Laboratory getLaboratory() {
        return laboratory;
    }

    public void setLaboratory(Laboratory laboratory) {
        this.laboratory = laboratory;
    }

    public String getAnalysis_name() {
        return analysis_name;
    }

    public void setAnalysis_name(String analysis_name) {
        this.analysis_name = analysis_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResult_type() {
        return result_type;
    }

    public void setResult_type(String result_type) {
        this.result_type = result_type;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Analysis{" + "analysis_id=" + analysis_id + ", laboratory=" + laboratory + ", analysis_name=" + analysis_name + ", description=" + description + ", result_type=" + result_type + ", price=" + price + '}';
    }

    
    
    
}
