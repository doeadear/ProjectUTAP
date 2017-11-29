package uta.utap;

import android.location.Location;

/**
 * Created by emiko on 11/20/2017.
 */

public abstract class User {
    private String m_Name;
    private Location m_Destination;
    private UtappSettings m_Settings;

    // Constructor
    public User(String name)
    {
        m_Name = name;
        m_Settings = new UtappSettings();
        m_Destination = new Location("");
    }

    public void setActiveUser(User user)
    {
        this.m_Name = user.m_Name;
        this.m_Settings = user.m_Settings;
        m_Destination.reset();
    }

    // Accessor methods
    public String getName()
    {
        return m_Name;
    }

    public Location getDestination()
    {
        return m_Destination;
    }

    public UtappSettings getUserSettings()
    {
        return m_Settings;
    }

    public void setName(String name)
    {
        m_Name = name;
    }

    public void setDestination(Location destination)
    {
        m_Destination = destination;
    }

    public abstract boolean isRegisteredUser();
}
