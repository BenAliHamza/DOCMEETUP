package tn.esprit.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import tn.esprit.entities.DocMeetupPurchase;
import tn.esprit.entities.DocMeetupPurchase.ProductType;
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

    public void getDocMeetupPurchaseById(int purchaseId) {
        try {
            String query = "SELECT * FROM docmeetup_purchases WHERE purchaseId = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, purchaseId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                ProductType productType = ProductType.valueOf(resultSet.getString("product_type"));
                int productCost = resultSet.getInt("product_cost");
                boolean refundedTest = resultSet.getBoolean("refunded_test");
                Timestamp purchaseDate = resultSet.getTimestamp("purchase_date");
                int numUnits = resultSet.getInt("num_units");

                DocMeetupPurchase docMeetupPurchase = new DocMeetupPurchase(purchaseId, userId, productType, productCost);
                docMeetupPurchase.setRefundedTest(refundedTest);
                docMeetupPurchase.setPurchaseDate(purchaseDate);
                docMeetupPurchase.setNumUnits(numUnits);
                System.out.println(docMeetupPurchase);
            }

        } catch (SQLException ex) {
            System.out.println("Error retrieving DocMeetup purchase: " + ex.getMessage());
        }
    }
}
