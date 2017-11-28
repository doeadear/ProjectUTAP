package uta.utap;

import android.location.Location;

/**
 * Created by emiko on 11/20/2017.
 */

public class Lot
{
    public enum Status { UNAVAILABLE, BUSY, AVAILABLE }

    private Location m_Location; // Center of the lot
    private boolean m_FacultyOnly; // Faculty only lot
    private Status m_Status;  // Lot status

    // Constructor
    public Lot(Location location, boolean facultyOnly, Status status)
    {
        m_Location = location;
        m_FacultyOnly = facultyOnly;
        m_Status = status;
    }

    // Accessor methods
    public Location getLocation()
    {
        return m_Location;
    }

    public Status getStatus()
    {
        return m_Status;
    }

    public boolean isFacultyOnly()
    {
        return m_FacultyOnly;
    }

    public void setStatus(Status status)
    {
        m_Status = status;
    }

    public void setFacultyOnly(boolean facultyOnly)
    {
        m_FacultyOnly = facultyOnly;
    }
}
