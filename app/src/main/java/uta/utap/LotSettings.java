package uta.utap;

import java.util.ArrayList;

/**
 * Created by emiko on 11/20/2017.
 */

public class LotSettings
{
    private float m_MaxLotDistance; // lot distance in meters TODO miles instead?
    private int m_MaxWaitTime; // wait time in minutes
    private ArrayList<Lot> m_PreferredLots = new ArrayList<Lot>();

    private static final float DEFAULT_MAX_LOT_DIST = 500.0f;
    private static final int DEFAULT_MAX_WAIT_TIME = 10;
    public LotSettings()
    {
    }

    public void setDefaultLotSettings()
    {
        m_MaxLotDistance = DEFAULT_MAX_LOT_DIST;
        m_MaxWaitTime = DEFAULT_MAX_WAIT_TIME;
    }
}
