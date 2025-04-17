package org.example.doctormerosathi.Util;

import org.mindrot.jbcrypt.BCrypt;
public class PasswordHashUtil {

    // Define the log rounds for BCrypt (higher is more secure but slower)
    private static final int LOG_ROUNDS = 12;

    public static String hashPassword(String plainTextPassword) {
        if (plainTextPassword == null || plainTextPassword.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }

        // Generate a salt and hash the password
        String salt = BCrypt.gensalt(LOG_ROUNDS);
        return BCrypt.hashpw(plainTextPassword, salt);
    }


    public static boolean verifyPassword(String plainTextPassword, String hashedPassword) {
        if (plainTextPassword == null || plainTextPassword.isEmpty() ||
                hashedPassword == null || hashedPassword.isEmpty()) {
            return false;
        }

        try {
            // Check if the password matches the hash
            return BCrypt.checkpw(plainTextPassword, hashedPassword);
        } catch (IllegalArgumentException e) {
            // If the stored hash is invalid, return false
            return false;
        }
    }
}