package uta.utap;

/**
 * Created by emiko on 11/23/2017.
 */

public class RegisteredUser extends User
{
    private byte[] m_Salt;
    private String m_Password;

    // Constructor
    public RegisteredUser(String name, String password)
    {
        super(name);

        // Save salt to regenerate hash for password verification
        m_Salt = PasswordUtils.generateSalt();
        setPassword(password);
    }

    // Accessor methods
    public byte[] getSalt()
    {
        return m_Salt;
    }

    public String getPassword()
    {
        return m_Password;
    }

    public void setPassword(String password)
    {
        // Only save hashed password
        m_Password = PasswordUtils.generatePasswordHash(password, m_Salt);
    }

    // Utility methods
    public boolean verifyPassword(String password)
    {
        String hashPassword = PasswordUtils.generatePasswordHash(password, m_Salt);

        return hashPassword.equals(m_Password);
    }

    // Abstract methods
    public boolean isRegisteredUser()
    {
        return true;
    }
}
