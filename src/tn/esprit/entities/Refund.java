package tn.esprit.entities;

public class Refund {
    private int refundId;
    private int userId;
    private int insuranceProfileId;
    private double refundAmount;
    private RefundStatus refundStatus;
    private RefundMethod refundMethod;
    private String refundComments;
    private int purchaseId;

    public Refund() {
    }

    public Refund(int refundId, int userId, int insuranceProfileId, double refundAmount, RefundStatus refundStatus,
                  RefundMethod refundMethod, String refundComments, int purchaseId) {
        this.refundId = refundId;
        this.userId = userId;
        this.insuranceProfileId = insuranceProfileId;
        this.refundAmount = refundAmount;
        this.refundStatus = refundStatus;
        this.refundMethod = refundMethod;
        this.refundComments = refundComments;
        this.purchaseId = purchaseId;
    }

    public int getRefundId() {
        return refundId;
    }

    public void setRefundId(int refundId) {
        this.refundId = refundId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getInsuranceProfileId() {
        return insuranceProfileId;
    }

    public void setInsuranceProfileId(int insuranceProfileId) {
        this.insuranceProfileId = insuranceProfileId;
    }

    public double getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(double refundAmount) {
        this.refundAmount = refundAmount;
    }

    public RefundStatus getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(RefundStatus refundStatus) {
        this.refundStatus = refundStatus;
    }

    public RefundMethod getRefundMethod() {
        return refundMethod;
    }

    public void setRefundMethod(RefundMethod refundMethod) {
        this.refundMethod = refundMethod;
    }

    public String getRefundComments() {
        return refundComments;
    }

    public void setRefundComments(String refundComments) {
        this.refundComments = refundComments;
    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }
    

public enum RefundStatus {
    ACCEPTED,
    REJECTED
}
public enum RefundMethod {
    CASH,
    CHEQUE,
    BANK_TRANSFER
}
@Override
    public String toString() {
        return "Refund{" +
                "refundId=" + refundId +
                ", userId=" + userId +
                ", insuranceProfileId=" + insuranceProfileId +
                ", refundAmount=" + refundAmount +
                ", refundStatus=" + refundStatus +
                ", refundMethod=" + refundMethod +
                ", refundComments='" + refundComments + '\'' +
                ", purchaseId=" + purchaseId +
                '}';
    }

}
