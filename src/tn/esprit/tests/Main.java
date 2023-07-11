package tn.esprit.tests;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import tn.esprit.entities.DocMeetupPurchase;
import tn.esprit.entities.DocMeetupPurchase.ProductType;
import tn.esprit.entities.InsuranceProfile;
import tn.esprit.entities.InsuranceProfile.InsuranceStatus;
import tn.esprit.entities.InsuranceProfile.InsuranceType;
import tn.esprit.entities.Refund;
import tn.esprit.entities.Refund.RefundMethod;
import tn.esprit.entities.Refund.RefundStatus;
import tn.esprit.services.DocMeetupPurchaseService;
import tn.esprit.services.InsuranceProfileService;
import tn.esprit.services.RefundService;

public class Main {
    public static void main(String[] args) {
        RefundService refundService = new RefundService();

        // Create refund
        Refund refundToCreate = new Refund();
        refundToCreate.setUserId(2); // Replace with the appropriate user ID
        refundToCreate.setInsuranceProfileId(19); // Replace with the appropriate insurance profile ID
        refundToCreate.setRefundAmount(100.0); // Replace with the refund amount
        refundToCreate.setRefundStatus(RefundStatus.ACCEPTED); // Replace with the refund status
        refundToCreate.setRefundMethod(RefundMethod.CASH); // Replace with the refund method
        refundToCreate.setRefundComments("Refund comments"); // Replace with the refund comments
        refundToCreate.setPurchaseId(1); // Replace with the purchase ID

        refundService.createRefund(refundToCreate);

        // Update refund
        Refund refundToUpdate = new Refund();
        refundToUpdate.setUserId(123); // Replace with the appropriate user ID
        refundToUpdate.setInsuranceProfileId(9); // Replace with the appropriate insurance profile ID
        refundToUpdate.setRefundAmount(300.0); // Replace with the updated refund amount
        refundToUpdate.setRefundStatus(RefundStatus.REJECTED); // Replace with the updated refund status
        refundToUpdate.setRefundMethod(RefundMethod.CHEQUE); // Replace with the updated refund method
        refundToUpdate.setRefundComments("Updated refund comments"); // Replace with the updated refund comments
        refundToUpdate.setPurchaseId(3); // Replace with the updated purchase ID
        refundToUpdate.setRefundId(8); // Replace with the appropriate refund ID

        refundService.updateRefund(refundToUpdate);

        // Delete refund
        int refundIdToDelete = 10; // Replace with the refund ID to delete

        refundService.deleteRefund(refundIdToDelete);
        
 
   
    }
}
