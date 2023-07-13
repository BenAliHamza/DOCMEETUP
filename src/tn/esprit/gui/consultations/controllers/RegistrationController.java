package tn.esprit.gui.consultations.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tn.esprit.entities.User;
import tn.esprit.gui.consultations.Main;
import tn.esprit.services.UserService;
import tn.esprit.tools.Role;





    
    
public class RegistrationController  implements Initializable {
    @FXML
    private MenuButton  menu ; 
    @FXML
    private Button submit;
    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private Button submit2;
    @FXML
    private AnchorPane anchor;
   @Override
    public void initialize(URL url, ResourceBundle rb) {
        Main.setTitle("Create account");

        for (Role role : Role.values()) {
                 MenuItem menuItem = new MenuItem(role.name());

                  menuItem.setStyle("-fx-text-fill: " + getTextColorForRole(role) + "; -fx-font-weight: bold;");
                     menu.getItems().add(menuItem);
                        menuItem.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            menu.setText(menuItem.getText());
                        }
                    });
             }
         }
    private String getTextColorForRole(Role role) {
       // Assign dark colors based on the role
       switch (role) {
           case patient:
               return "#FF3333"; // Dark Red
           case doctor:
               return "#33CC33"; // Dark Green
           case pharmacy:
               return "#3333FF"; // Dark Blue
           case laboratory:
               return "#993399"; // Dark Purple
           case insurance:
               return "#CCCC00"; // Dark Yellow
           default:
               return "#000000"; // Black (default color)
       }
   }

   @FXML
        private void submit(ActionEvent event) {
            UserService cs= new UserService();
            // Retrieve form inputs
            String firstnameValue = firstname.getText();
            String lastnameValue = lastname.getText();
            String emailValue = email.getText();
            String passwordValue = password.getText();
            String selectedRole = menu.getText(); // Retrieves the selected role from the menu button
            Role role = convertToRole(selectedRole);
            Boolean isEmail= cs.isEmail(emailValue);
            if(isEmail) {
             showAlert(AlertType.ERROR, "Error", "Invalid Email", "Email already exist .");
             return ;

            }
            if (role == null) {
                // Role conversion succeeded, you can use the 'role' variable here
                showAlert(AlertType.ERROR, "Error", "Invalid Role", "Invalid selected role.");
                return;
                
            }
            // Perform validations
            if (firstnameValue.isEmpty() || lastnameValue.isEmpty() || emailValue.isEmpty() || passwordValue.isEmpty()) {
                showAlert(AlertType.ERROR, "Error", "Missing Fields", "Please fill in all fields.");
                return;
            }

            if (passwordValue.length() < 6) {
                showAlert(AlertType.ERROR, "Error", "Invalid Password", "Password should be at least 6 characters.");
                return;
            }

            // Email validation using regular expression
                String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
                if (!emailValue.matches(emailRegex)) {
                    showAlert(AlertType.ERROR, "Error", "Invalid Email", "Invalid email format.");
                    return;
                }
            

            // All validations passed, proceed with user creation or further processing
            User user = new User();
            user.setFirst_name(firstnameValue);
            user.setLast_name(lastnameValue);
            user.setEmail(emailValue);
            user.setPassword(passwordValue);
            user.setRole(role);
            // ... perform further actions with the user object
            cs.Create(user);
            login();
        }

        private void showAlert(AlertType alertType, String title, String headerText, String contentText) {
            Alert alert = new Alert(alertType);
            alert.setTitle(title);
            alert.setHeaderText(headerText);
            alert.setContentText(contentText);
            alert.showAndWait();
        }
        private Role convertToRole(String selectedRole) {
            System.out.println(selectedRole);
            for (Role role : Role.values()) {
                if (role.name().equalsIgnoreCase(selectedRole)) {
                    return role;
                }
            }
            return null; // Return null or handle the case when the selected role is not found
        }

    @FXML
    private void login(ActionEvent event) {
         try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/login.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) anchor.getScene().getWindow(); // Replace `button` with your actual button object
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            } 
    }
      private void login() {
         try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/login.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) anchor.getScene().getWindow(); // Replace `button` with your actual button object
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            } 
    }

}
