package uta.utap;

import java.util.ArrayList;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


/**
 * Created by emiko on 11/20/2017.
 */

public class LotSettings extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    private float m_MaxLotDistance; // lot distance in meters TODO miles instead?
    private int m_MaxWaitTime; // wait time in minutes
    private ArrayList<Lot> m_PreferredLots = new ArrayList<>();

    private static final float DEFAULT_MAX_LOT_DIST = 500.0f;
    private static final int DEFAULT_MAX_WAIT_TIME = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lot__settings);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Spinner spinner = (Spinner) findViewById(R.id.lot_spinner1);
        Spinner spinner2 = (Spinner) findViewById(R.id.lot_spinner2);
        Spinner spinner3 = (Spinner) findViewById(R.id.lot_spinner3);
        Spinner spinner4 = (Spinner) findViewById(R.id.lot_spinner4);
        Spinner spinner5 = (Spinner) findViewById(R.id.lot_spinner5);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Lot_List, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.max_dist, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.max_wait, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner

        spinner.setAdapter(adapter);
        spinner2.setAdapter(adapter);
        spinner3.setAdapter(adapter);
        spinner4.setAdapter(adapter2);
        spinner5.setAdapter(adapter3);
        spinner.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        //Insert on Selection Method

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), Settings.class);
        startActivityForResult(myIntent, 0);
        return true;

    }

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
