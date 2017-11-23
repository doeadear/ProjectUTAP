package uta.utap;

/**
 * Created by emiko on 11/23/2017.
 */

public class RegisteredUser extends User
{
    private byte[] m_Salt;
    private String m_Password;

    public RegisteredUser(String name, String password)
    {
        super(name);
    }
}
