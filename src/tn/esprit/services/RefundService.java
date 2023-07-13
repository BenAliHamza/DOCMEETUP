package tn.esprit.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tn.esprit.entities.Refund;
import tn.esprit.entities.Refund.RefundMethod;
import tn.esprit.entities.Refund.RefundStatus;
import tn.esprit.tools.MaConnexion;

public class RefundService {
    private Connection connection;

    public RefundService() {
        connection = MaConnexion.getInstance().getCnx();
    }

    public void createRefund(Refund refund) {
        try {
            // Check if the user has an insurance profile before creating the refund
            if (!doesInsuranceProfileExist(refund.getUserId())) {
                System.out.println("Cannot create refund. User does not have an insurance profile.");
                return;
            }

            String query = "INSERT INTO refund (user_id, insuranceprofile_id, refund_amount, refund_status, " +
                 "refund_method, refund_comments, purchase_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, refund.getUserId());
            statement.setInt(2, refund.getInsuranceProfileId());
            statement.setDouble(3, refund.getRefundAmount());
            statement.setString(4, refund.getRefundStatus().name());
            statement.setString(5, refund.getRefundMethod().name());
            statement.setString(6, refund.getRefundComments());
            statement.setInt(7, refund.getPurchaseId());

            statement.executeUpdate();
            System.out.println("Refund created successfully.");

        } catch (SQLException ex) {
            System.out.println("Error creating refund: " + ex.getMessage());
        }
    }

    public void updateRefund(Refund refund) {
        try {
            // Check if the user has an insurance profile before updating the refund
            if (!doesInsuranceProfileExist(refund.getUserId())) {
                System.out.println("Cannot update refund. User does not have an insurance profile.");
                return;
            }

            String query = "UPDATE refund SET user_id = ?, insuranceprofile_id = ?, refund_amount = ?, " +
                    "refund_status = ?, refund_method = ?, refund_comments = ?, purchase_id = ? " +
                    "WHERE refund_id = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, refund.getUserId());
            statement.setInt(2, refund.getInsuranceProfileId());
            statement.setDouble(3, refund.getRefundAmount());
            statement.setString(4, refund.getRefundStatus().name());
            statement.setString(5, refund.getRefundMethod().name());
            statement.setString(6, refund.getRefundComments());
            statement.setInt(7, refund.getPurchaseId());
            statement.setInt(8, refund.getRefundId());

            statement.executeUpdate();
            System.out.println("Refund updated successfully.");

        } catch (SQLException ex) {
            System.out.println("Error updating refund: " + ex.getMessage());
        }
    }

    public void deleteRefund(int refundId) {
        try {
            String query = "DELETE FROM refund WHERE refund_id = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, refundId);

            statement.executeUpdate();
            System.out.println("Refund deleted successfully.");

        } catch (SQLException ex) {
            System.out.println("Error deleting refund: " + ex.getMessage());
        }
    }

    private boolean doesInsuranceProfileExist(int userId) {
        try {
            String query = "SELECT COUNT(*) FROM insuranceprofile WHERE user_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException ex) {
            System.out.println("Error checking insurance profile existence: " + ex.getMessage());
        }

        return false;
    }
   public List<Refund> getRefundsByUserId(int userId) {
    List<Refund> refunds = new ArrayList<>();

    try {
        String query = "SELECT * FROM refund WHERE user_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, userId);

        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int refundId = resultSet.getInt("refund_id");
                int insuranceProfileId = resultSet.getInt("insuranceprofile_id");
                double refundAmount = resultSet.getDouble("refund_amount");
                RefundStatus refundStatus = RefundStatus.valueOf(resultSet.getString("refund_status"));
                RefundMethod refundMethod = RefundMethod.valueOf(resultSet.getString("refund_method"));
                String refundComments = resultSet.getString("refund_comments");
                int purchaseId = resultSet.getInt("purchase_id");
                
                // Create a new Refund object and set its attributes
                Refund refund = new Refund();
                refund.setRefundId(refundId);
                refund.setUserId(userId);
                refund.setInsuranceProfileId(insuranceProfileId);
                refund.setRefundAmount(refundAmount);
                refund.setRefundStatus(refundStatus);
                refund.setRefundMethod(refundMethod);
                refund.setRefundComments(refundComments);
                refund.setPurchaseId(purchaseId);

                refunds.add(refund);
            }
        }
    } catch (SQLException ex) {
        System.out.println("Error retrieving refunds: " + ex.getMessage());
    }

    return refunds;
}






    // Other methods in the RefundService class...
}
