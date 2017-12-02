package uta.utap;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

import java.util.Vector;

/**
 * Created by emiko on 11/20/2017.
 */

public class Lot
{
    public enum Status { UNAVAILABLE, BUSY, AVAILABLE }

    private Location m_Location; // Center of the lot
    private boolean m_FacultyOnly; // Faculty only lot
    private Status m_Status;  // Lot status
    private Vector<LatLng> m_PolyPoints;
    private String m_Name;

    // Constructor
    public Lot(Location location, Vector<LatLng> points, boolean facultyOnly, Status status)
    {
        m_Location = location;
        m_FacultyOnly = facultyOnly;
        m_Status = status;
        m_PolyPoints = points;
        m_Name = "default";
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

    public Vector<LatLng> getPolyPoints()
    {
        return m_PolyPoints;
    }

    public String getName()
    {
        return m_Name;
    }

    public void setName(String name)
    {
        m_Name = name;
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
