package uta.utap;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Created by emiko on 11/23/2017.
 */

public class PasswordUtils
{
    private static SecureRandom randomGenerator = new SecureRandom();

    public static String generatePasswordHash(String password, byte[] salt)
    {
        String passwordHash = "";
        try
        {
            // Use SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // Add a little salt
            md.update(salt);

            // Get hash bytes
            byte[] hashBytes = md.digest(password.getBytes());

            // Convert to hex
            StringBuilder sb = new StringBuilder();

            for(int i = 0; i < hashBytes.length; i++)
            {
                sb.append(Integer.toString((hashBytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            // Hashed password in hex
            passwordHash = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return passwordHash;
    }

    // Generate salt used to generate hash
    public static byte[] generateSalt()
    {
        byte[] salt = new byte[32];
        randomGenerator.nextBytes(salt);

        return salt;
    }
}
