package tn.esprit.services;

import tn.esprit.entities.User;
import tn.esprit.tools.MaConnexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import tn.esprit.entities.InsuranceProfile;

public class UserService {
    private Connection connection;

    public UserService() {
        connection = MaConnexion.getInstance().getCnx();
    }

    public User getUserByEmail(String userEmail) {
        try {
            String query = "SELECT * FROM user WHERE email = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, userEmail);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String username = resultSet.getString("username");
                String roleString = resultSet.getString("role");
                

                User.Role role = User.Role.fromDatabaseValue(roleString); // Convert the role string to User.Role enum

                // Retrieve other user attributes as needed

                // Create and return the User object
                return new User(userId, email, password, username, role);
            }
        } catch (SQLException ex) {
            System.out.println("Error retrieving user by email: " + ex.getMessage());
        }

        return null; // User not found
    }
    public User getUserById(int userId) {
    try {
        String query = "SELECT * FROM user WHERE user_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, userId);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            String username = resultSet.getString("username");
            String roleString = resultSet.getString("role");
            User.Role role = null;
            try {
                role = User.Role.valueOf(roleString);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid role value: " + roleString);
            }
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            // Retrieve other user attributes as needed

            return new User(String.valueOf(userId), email, password, username, role, firstName, lastName, null, null, null, null, 0, null, 0, null);
        }
    } catch (SQLException ex) {
        System.out.println("Error retrieving user by ID: " + ex.getMessage());
    }

    return null; // User not found
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

}
