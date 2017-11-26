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
    Spinner spinner1,spinner2,spinner3;
    ArrayAdapter<CharSequence> adapter;
    TextView t;
    private int m_UnavailableColor;
    private int m_BusyColor;
    private int m_AvailableColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_set);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        spinner1=(Spinner) findViewById(R.id.spinner1);
        spinner2=(Spinner) findViewById(R.id.spinner2);
        spinner3=(Spinner) findViewById(R.id.spinner3);

        adapter=ArrayAdapter.createFromResource(this, R.array.Color_List, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);
        spinner3.setAdapter(adapter);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i){
                    case 0:
//                      Insert method here

                        String message="Color Red selected for full lot";
                        Toast.makeText(ColorSettings.this,message, Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
//                      Insert method here
                        String message2="Color Yellow selected for full lot";
                        Toast.makeText(ColorSettings.this,message2, Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
//                      Insert method here
                        String message3="Color Green selected for full lot";
                        Toast.makeText(ColorSettings.this,message3, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i){
                    case 0:
//                      Insert method here
                        String message="Color Red selected for Busy lot";
                        Toast.makeText(ColorSettings.this,message, Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
//                      Insert method here
                        String message2="Color Yellow selected for Busy lot";
                        Toast.makeText(ColorSettings.this,message2, Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
//                      Insert method here
                        String message3="Color Green selected for Busy lot";
                        Toast.makeText(ColorSettings.this,message3, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i){
                    case 0:
//                      Insert method here
                        String message="Color Red selected for empty lot";
                        Toast.makeText(ColorSettings.this,message, Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
//                      Insert method here
                        String message2="Color Yellow selected for empty lot";
                        Toast.makeText(ColorSettings.this,message2, Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
//                      Insert method here
                        String message3="Color Green selected for empty lot";
                        Toast.makeText(ColorSettings.this,message3, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), Settings.class);
        startActivityForResult(myIntent, 0);
        return true;

    }



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
