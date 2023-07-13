package tn.esprit.services;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import tn.esprit.entities.InsuranceProfile;
import tn.esprit.tools.MaConnexion;

public class InsuranceProfileService {
    private Connection connection;

    public InsuranceProfileService() {
        connection = MaConnexion.getInstance().getCnx();
    }

    public boolean createInsuranceProfile(InsuranceProfile insuranceProfile) {
        try {
            String query = "INSERT INTO insuranceprofile (user_id, insurance_type, insurance_cost, insurance_exp_date, insurance_tel, insurance_company, insurance_status) VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, insuranceProfile.getUserId());
            statement.setString(2, insuranceProfile.getInsuranceType().name());
            statement.setBigDecimal(3, insuranceProfile.getInsuranceCost());
            statement.setDate(4, insuranceProfile.getInsuranceExpDate());
            statement.setString(5, insuranceProfile.getInsuranceTel());
            statement.setString(6, insuranceProfile.getInsuranceCompany());
            statement.setString(7, insuranceProfile.getInsuranceStatus().name());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException ex) {
            System.out.println("Error creating insurance profile: " + ex.getMessage());
        }

        return false;
    }

    public void updateInsuranceProfile(InsuranceProfile insuranceProfile) {
        try {
            String query = "UPDATE insuranceprofile SET user_id = ?, insurance_type = ?, insurance_cost = ?, insurance_exp_date = ?, insurance_tel = ?, insurance_company = ?, insurance_status = ? WHERE insuranceprofile_id = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, insuranceProfile.getUserId());
            statement.setString(2, insuranceProfile.getInsuranceType().name());
            statement.setBigDecimal(3, insuranceProfile.getInsuranceCost());
            statement.setDate(4, insuranceProfile.getInsuranceExpDate());
            statement.setString(5, insuranceProfile.getInsuranceTel());
            statement.setString(6, insuranceProfile.getInsuranceCompany());
            statement.setString(7, insuranceProfile.getInsuranceStatus().name());
            statement.setInt(8, insuranceProfile.getProfileInsuranceId());

            statement.executeUpdate();
            System.out.println("Insurance profile updated successfully.");

        } catch (SQLException ex) {
            System.out.println("Error updating insurance profile: " + ex.getMessage());
        }
    }

public boolean deleteInsuranceProfileByEmail(String userEmail) {
    try {
        // Retrieve the userId from the user table using the userEmail
        String userQuery = "SELECT user_id FROM user WHERE email = ?";
        PreparedStatement userStatement = connection.prepareStatement(userQuery);
        userStatement.setString(1, userEmail);
        ResultSet userResultSet = userStatement.executeQuery();

        if (userResultSet.next()) {
            int userId = userResultSet.getInt("user_id");

            // Delete the insurance profile from the database based on the userId
            String deleteQuery = "DELETE FROM insuranceprofile WHERE user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, userId);
            int rowsAffected = preparedStatement.executeUpdate();

            // Check if the deletion was successful
            if (rowsAffected > 0) {
                return true; // Deletion successful
            } else {
                return false; // Deletion failed
            }
        }
    } catch (SQLException ex) {
        System.out.println("Error deleting insurance profile: " + ex.getMessage());
    }

    return false; // Deletion failed
}




    public boolean doesInsuranceProfileExist(int userId) {
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

    public InsuranceProfile getInsuranceProfileByUserId(int userId) {
        try {
            String query = "SELECT * FROM insuranceprofile WHERE user_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                InsuranceProfile insuranceProfile = new InsuranceProfile();
                insuranceProfile.setProfileInsuranceId(resultSet.getInt("insuranceprofile_id"));
                insuranceProfile.setUserId(resultSet.getInt("user_id"));
                insuranceProfile.setInsuranceType(InsuranceProfile.InsuranceType.valueOf(resultSet.getString("insurance_type")));
                insuranceProfile.setInsuranceCost(resultSet.getBigDecimal("insurance_cost"));
                insuranceProfile.setInsuranceExpDate(resultSet.getDate("insurance_exp_date"));
                insuranceProfile.setInsuranceTel(resultSet.getString("insurance_tel"));
                insuranceProfile.setInsuranceCompany(resultSet.getString("insurance_company"));
                insuranceProfile.setInsuranceStatus(InsuranceProfile.InsuranceStatus.valueOf(resultSet.getString("insurance_status")));
                return insuranceProfile;
            }
        } catch (SQLException ex) {
            System.out.println("Error getting insurance profile: " + ex.getMessage());
        }

        return null;
    }
    
    public boolean deleteInsuranceProfileByUserId(int userId) {
    try {
        String query = "DELETE FROM insuranceprofile WHERE user_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, userId);

        int rowsAffected = statement.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException ex) {
        System.out.println("Error deleting insurance profile: " + ex.getMessage());
    }

    return false;
}


    public InsuranceProfile getInsuranceProfileByEmail(String userEmail) {
    try {
        // Retrieve the userId from the user table using the userEmail
        String userQuery = "SELECT user_id FROM user WHERE email = ?";
        PreparedStatement userStatement = connection.prepareStatement(userQuery);
        userStatement.setString(1, userEmail);
        ResultSet userResultSet = userStatement.executeQuery();

        if (userResultSet.next()) {
            int userId = userResultSet.getInt("user_id");

            // Retrieve the insurance profile using the userId from the insuranceprofile table
            String insuranceQuery = "SELECT * FROM insuranceprofile WHERE user_id = ?";
            PreparedStatement insuranceStatement = connection.prepareStatement(insuranceQuery);
            insuranceStatement.setInt(1, userId);
            ResultSet insuranceResultSet = insuranceStatement.executeQuery();

            if (insuranceResultSet.next()) {
                int profileInsuranceId = insuranceResultSet.getInt("insuranceprofile_id");
                InsuranceProfile.InsuranceType insuranceType = InsuranceProfile.InsuranceType.valueOf(insuranceResultSet.getString("insurance_type"));
                BigDecimal insuranceCost = insuranceResultSet.getBigDecimal("insurance_cost");
                Date insuranceExpDate = insuranceResultSet.getDate("insurance_exp_date");
                String insuranceTel = insuranceResultSet.getString("insurance_tel");
                String insuranceCompany = insuranceResultSet.getString("insurance_company");
                InsuranceProfile.InsuranceStatus insuranceStatus = InsuranceProfile.InsuranceStatus.valueOf(insuranceResultSet.getString("insurance_status"));

                return new InsuranceProfile(profileInsuranceId, userId, insuranceType, insuranceCost, insuranceExpDate, insuranceTel, insuranceCompany, insuranceStatus);
            }
        }
    } catch (SQLException ex) {
        System.out.println("Error retrieving insurance profile by email: " + ex.getMessage());
    }

    return null;
}

    // Other methods in the InsuranceProfileService class...
}
