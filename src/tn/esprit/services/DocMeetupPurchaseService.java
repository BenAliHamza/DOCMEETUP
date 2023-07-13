package tn.esprit.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import tn.esprit.entities.DocMeetupPurchase;
import tn.esprit.entities.DocMeetupPurchase.ProductType;
import tn.esprit.entities.Refund;
import tn.esprit.entities.Refund.RefundStatus;
import tn.esprit.tools.MaConnexion;

public class DocMeetupPurchaseService {
    private Connection connection;

    public DocMeetupPurchaseService() {
        connection = MaConnexion.getInstance().getCnx();
    }

    public void createDocMeetupPurchase(DocMeetupPurchase docMeetupPurchase) {
        try {
            String query = "INSERT INTO docmeetup_purchases (user_id, product_type, product_cost, refunded_test, purchase_date, num_units) VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, docMeetupPurchase.getUserId());
            statement.setString(2, docMeetupPurchase.getProductType().toString());
            statement.setInt(3, docMeetupPurchase.getProductCost());
            statement.setBoolean(4, docMeetupPurchase.isRefundedTest());
            statement.setTimestamp(5, (Timestamp) docMeetupPurchase.getPurchaseDate());
            statement.setInt(6, docMeetupPurchase.getNumUnits());

            statement.executeUpdate();
            System.out.println("DocMeetup purchase created successfully.");

        } catch (SQLException ex) {
            System.out.println("Error creating DocMeetup purchase: " + ex.getMessage());
        }
    }

    public void updateDocMeetupPurchase(DocMeetupPurchase docMeetupPurchase) {
        try {
            String query = "UPDATE docmeetup_purchases SET user_id = ?, product_type = ?, product_cost = ?, refunded_test = ?, num_units = ? WHERE purchaseId = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, docMeetupPurchase.getUserId());
            statement.setString(2, docMeetupPurchase.getProductType().toString());
            statement.setInt(3, docMeetupPurchase.getProductCost());
            statement.setBoolean(4, docMeetupPurchase.isRefundedTest());
            statement.setInt(5, docMeetupPurchase.getNumUnits());
            statement.setInt(6, docMeetupPurchase.getPurchaseId());

            statement.executeUpdate();
            System.out.println("DocMeetup purchase updated successfully.");

        } catch (SQLException ex) {
            System.out.println("Error updating DocMeetup purchase: " + ex.getMessage());
        }
    }

    public void deleteDocMeetupPurchase(int purchaseId) {
        try {
            String query = "DELETE FROM docmeetup_purchases WHERE purchaseId = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, purchaseId);

            statement.executeUpdate();
            System.out.println("DocMeetup purchase deleted successfully.");

        } catch (SQLException ex) {
            System.out.println("Error deleting DocMeetup purchase: " + ex.getMessage());
        }
    }

    public List<DocMeetupPurchase> getPurchasesByUserId(int userId) {
    List<DocMeetupPurchase> purchases = new ArrayList<>();
    String query = "SELECT * FROM docmeetup_purchases WHERE user_id = ?";

    try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setInt(1, userId);

        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int purchaseId = resultSet.getInt("purchase_id");
                ProductType productType = ProductType.valueOf(resultSet.getString("product_type"));
                int productCost = resultSet.getInt("product_cost");
                boolean refundedTest = resultSet.getBoolean("refunded_test");
                Timestamp purchaseDate = resultSet.getTimestamp("purchase_date");
                int numUnits = resultSet.getInt("Num_Units");

                DocMeetupPurchase docMeetupPurchase = new DocMeetupPurchase(purchaseId, userId, productType, productCost);
                docMeetupPurchase.setRefundedTest(refundedTest);
                docMeetupPurchase.setPurchaseDate(purchaseDate);
                docMeetupPurchase.setNumUnits(numUnits);

                // Retrieve the refund status from the Refund table
                RefundStatus refundStatus = getRefundStatusByUserId(userId);

                // Create a new Refund object and set the refund status
                Refund refund = new Refund();
                refund.setRefundStatus(refundStatus);

                // Set the refund object in the DocMeetupPurchase object
                docMeetupPurchase.setRefund(refund);

                purchases.add(docMeetupPurchase);
            }
        }
    } catch (SQLException ex) {
        System.out.println("Error retrieving DocMeetup purchases: " + ex.getMessage());
    }

    return purchases;
}


    private RefundStatus getRefundStatusByUserId(int userId) {
        String query = "SELECT refund_status FROM refund WHERE user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return RefundStatus.valueOf(resultSet.getString("refund_status"));
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error retrieving refund status: " + ex.getMessage());
        }

        return null;
    }
}
