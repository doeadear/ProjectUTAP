package uta.utap;

import android.graphics.Color;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by emiko on 11/20/2017.
 */

public class ColorSettings extends AppCompatActivity
{
    private int m_UnavailableColor;
    private int m_BusyColor;
    private int m_AvailableColor;

    // Constructor
    public ColorSettings()
    {
        setDefaultColorSettings();
    }

    // Method to set and reset color settings to default values
    public void setDefaultColorSettings()
    {
        m_UnavailableColor = Color.RED;
        m_BusyColor = Color.YELLOW;
        m_AvailableColor = Color.GREEN;
    }

    // Accessor methods
    public void setUnavailableColor(int unavailableColor)
    {
        m_UnavailableColor = unavailableColor;
    }

    public void setBusyColor(int busyColor)
    {
        m_BusyColor = busyColor;
    }

    public void setAvailableColor(int availableColor)
    {
        m_AvailableColor = availableColor;
    }

    public int getUnavailableColor()
    {
        return m_UnavailableColor;
    }

    public int getBusyColor()
    {
        return m_BusyColor;
    }

    public int getAvailableColor()
    {
        return m_AvailableColor;
    }

}
