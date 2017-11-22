package uta.utap;

import android.location.Location;

/**
 * Created by emiko on 11/20/2017.
 */

public class User {
    private String m_Name;
    private Location m_Destination;
    private UtappSettings m_Settings;

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
}
