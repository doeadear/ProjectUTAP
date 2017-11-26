package uta.utap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import uta.utap.R;

public class Input_Schedule extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input__schedule);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Spinner spinner = (Spinner) findViewById(R.id.schedule_spinner1);
        Spinner spinner2 = (Spinner) findViewById(R.id.schedule_spinner2);
        Spinner spinner3 = (Spinner) findViewById(R.id.schedule_spinner3);
        Spinner spinner4 = (Spinner) findViewById(R.id.schedule_spinner4);
        Spinner spinner5 = (Spinner) findViewById(R.id.schedule_spinner5);
        Spinner spinner6 = (Spinner) findViewById(R.id.schedule_spinner6);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.Days, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.Schedule_Time, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Define time array, format not clear
        //Set Adapter for time array
        spinner.setAdapter(adapter1);
        spinner2.setAdapter(adapter2);
        spinner3.setAdapter(adapter1);
        spinner4.setAdapter(adapter2);
        spinner5.setAdapter(adapter1);
        spinner6.setAdapter(adapter2);

        //Spinner 1 Listener, use the same for other spinners
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), Profile.class);
        startActivityForResult(myIntent, 0);
        return true;

    }


}
