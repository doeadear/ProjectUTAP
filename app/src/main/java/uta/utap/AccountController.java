package uta.utap;

/**
 * Created by emiko on 11/25/2017.
 */

public class AccountController
{
    private static final AccountController accountController = new AccountController();
    private User m_User;

    // Constructor
    private AccountController()
    {
        // Create new guest user on initialization since user has not logged in
        m_User = new GuestUser("Guest");
    }

    public static AccountController getInstance()
    {
        return accountController;
    }

    // Accessor methods
    public User getUser()
    {
        return m_User;
    }

    // Create guest account
    public boolean createAccount(String uid)
    {
        boolean success = false;

        // TODO Add logic to query DBMgr for whether or not account already exists in client DB

        // TODO Add logic to create a new GuestUser

        return success;
    }

    // Create user account
    public boolean createAccount(String uid, String password)
    {
        boolean success = false;

        // TODO Add logic to query DBMgr for whether or not account already exists in server DB

        // TODO Add logic to create a new RegisteredUser

        return success;
    }
}
