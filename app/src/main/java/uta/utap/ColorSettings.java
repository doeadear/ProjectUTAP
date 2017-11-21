package uta.utap;

import android.graphics.Color;

/**
 * Created by emiko on 11/20/2017.
 */

public class ColorSettings
{
    private int m_UnavailableColor;
    private int m_BusyColor;
    private int m_AvailableColor;

    // Constructor
    public ColorSettings()
    {
    }

    public void setDefaultColors()
    {
        m_UnavailableColor = Color.RED;
        m_BusyColor = Color.YELLOW;
        m_AvailableColor = Color.GREEN;
    }


}
