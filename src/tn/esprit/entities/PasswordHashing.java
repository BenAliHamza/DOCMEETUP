package tn.esprit.entities;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 *
 * @author DELL
 */
public class PasswordHashing {
    private static final int SALT_LENGTH = 16;

    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    public static String hashPassword(String password, String salt) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] saltBytes = Base64.getDecoder().decode(salt);
            byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);
            byte[] saltedPassword = new byte[saltBytes.length + passwordBytes.length];

            System.arraycopy(saltBytes, 0, saltedPassword, 0, saltBytes.length);
            System.arraycopy(passwordBytes, 0, saltedPassword, saltBytes.length, passwordBytes.length);

            byte[] hashedBytes = digest.digest(saltedPassword);
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String password = "MyPassword123";
       // String salt = generateSalt();

        String hashedPassword = hashPassword(password, "16");
        System.out.println("Mot de passe haché : " + hashedPassword);
        System.out.println("Sel : " + "16");
    }
//kMwZ8I2EMN9wR2KKSsNf7TQr/1CIcOaMKBoJ41oDVTk=
    //MwZ8I2EMN9wR2KKSsNf7TQr/1CIcOaMKBoJ41oDVTk=
}