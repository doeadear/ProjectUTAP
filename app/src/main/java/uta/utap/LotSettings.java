package uta.utap;

import java.util.ArrayList;

/**
 * Created by emiko on 11/20/2017.
 */

public class LotSettings
{
    private float m_MaxLotDistance; // lot distance in meters TODO miles instead?
    private int m_MaxWaitTime; // wait time in minutes
    private ArrayList<Lot> m_PreferredLots = new ArrayList<>();

    private static final float DEFAULT_MAX_LOT_DIST = 500.0f;
    private static final int DEFAULT_MAX_WAIT_TIME = 10;

    // Constructor
    public LotSettings()
    {
        setDefaultLotSettings();
    }

    // Set member variables to defaults
    public void setDefaultLotSettings()
    {
        m_MaxLotDistance = DEFAULT_MAX_LOT_DIST;
        m_MaxWaitTime = DEFAULT_MAX_WAIT_TIME;
        m_PreferredLots.clear();
    }

    // Accessor methods
    public float getMaxLotDistance()
    {
        return m_MaxLotDistance;
    }

    public int getMaxWaitTime()
    {
        return m_MaxWaitTime;
    }

    public ArrayList<Lot> getPreferredLots()
    {
        return m_PreferredLots;
    }

    public void setMaxLotDistance(float maxLotDistance)
    {
        m_MaxLotDistance = maxLotDistance;
    }

    public void setMaxWaitTime(int maxWaitTime)
    {
        m_MaxWaitTime = maxWaitTime;
    }

    public void addPreferredLot(Lot preferredLot)
    {
        m_PreferredLots.add(preferredLot);
    }

    public void removePreferredLot(Lot preferredLot)
    {
        m_PreferredLots.remove(preferredLot);
    }
}
