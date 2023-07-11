package tn.esprit.entities;

import java.util.Date;

public class DocMeetupPurchase {
    private int purchaseId;
    private int userId;
    private ProductType productType;
    private int productCost;
    private boolean refundedTest;
    private Date purchaseDate;
    private int numUnits; // New attribute

    public DocMeetupPurchase() {
    }

    public DocMeetupPurchase(int userId, ProductType productType, int productCost) {
        this.userId = userId;
        this.productType = productType;
        this.productCost = productCost;
    }

    public DocMeetupPurchase(int purchaseId, int userId, ProductType productType, int productCost) {
        this.purchaseId = purchaseId;
        this.userId = userId;
        this.productType = productType;
        this.productCost = productCost;
    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public int getProductCost() {
        return productCost;
    }

    public void setProductCost(int productCost) {
        this.productCost = productCost;
    }

    public boolean isRefundedTest() {
        return refundedTest;
    }

    public void setRefundedTest(boolean refundedTest) {
        this.refundedTest = refundedTest;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public int getNumUnits() {
        return numUnits;
    }

    public void setNumUnits(int numUnits) {
        this.numUnits = numUnits;
    }

    public enum ProductType {
        MEDICAMENT,
        ANALYSES,
        CONSULTATION,
    }
}
