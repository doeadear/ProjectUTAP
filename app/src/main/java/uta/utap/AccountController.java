package uta.utap;

import java.util.ArrayList;

/**
 * Created by emiko on 11/25/2017.
 */

public class AccountController
{
    private static final AccountController accountController = new AccountController();
    private User m_User;
    private ArrayList<User> m_UserList;

    // Constructor
    private AccountController()
    {
        // Create new guest user on initialization since user has not logged in
        m_UserList = new ArrayList<>();

        m_UserList.add(new GuestUser("Guest"));
        m_User = m_UserList.get(0);

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

    public void setUser(User user)
    {
        m_User = user;
    }

    // Create guest account
    public boolean createAccount(String uid)
    {
        boolean success = false;
        boolean available = false;

        available = guestIdAvailable(uid);

        if(available)
        {
            m_UserList.add(new GuestUser(uid));
            m_User = m_UserList.get(m_UserList.size() - 1);
            success = true;
        }

        return success;
    }

    // Create user account
    public boolean createAccount(String uid, String password)
    {
        boolean success = false;
        boolean available = false;

        available = registeredIdAvailable(uid);

        if(available)
        {
            m_UserList.add(new RegisteredUser(uid, password));
            m_User = m_UserList.get(m_UserList.size() - 1);
            success = true;
        }

        return success;
    }

    public boolean guestIdAvailable(String uid)
    {
        boolean available = false;

        // TODO Add logic to query DBMgr for whether or not account already exists in client DB
        available = (getUserAccount(uid) == null) || (getUserAccount(uid).isRegisteredUser());

        return available;
    }

    public boolean registeredIdAvailable(String uid)
    {
        boolean available = false;

        // TODO Add logic to query DBMgr for whether or not account already exists in server DB

        //GET: ../api/Server/GetUser?uid=test
        //INPUT: uid: userid
        //OUTPUT: "user":{"uid":"test","email":"test","password":"test","m_AvailableColor":0,"m_BusyColor":0,"m_UnavailableColor":0}}

        available = (getUserAccount(uid) == null) || (!getUserAccount(uid).isRegisteredUser());

        return available;

    }

    public User getUserAccount(String uid)
    {
        User user = null;
        for(int i = 0; i < m_UserList.size(); i++)
        {
            if(m_UserList.get(i).getName().equals(uid))
            {
                user = m_UserList.get(i);
            }
        }

        return user;
    }
}
