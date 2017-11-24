package uta.utap;

import org.junit.Test;

import java.io.InputStream;
import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test_PasswordUtils() throws Exception
    {
        String password = "testPassword";
        byte[] salt = PasswordUtils.generateSalt();
        String hash = PasswordUtils.generatePasswordHash(password, salt);

        System.out.println("Password: " + password);
        System.out.print("Salt: ");
        for(int i = 0; i < salt.length; i++)
        {
            System.out.print(salt[i]);
        }
        System.out.println();
        System.out.println("Hash: " + hash);

        System.out.println("Verifying hash..");
        String testPassword = "testPassword";
        String testHash = PasswordUtils.generatePasswordHash(testPassword, salt);
        assertEquals(hash, testHash);

        System.out.println("Verifying failure..");
        String failPassword = "wrongPassword";
        String failHash = PasswordUtils.generatePasswordHash(failPassword, salt);
        assertNotEquals(hash, failHash);
    }
}