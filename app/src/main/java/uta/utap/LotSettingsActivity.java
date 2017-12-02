package uta.utap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by emiko on 11/26/2017.
 */

public class LotSettingsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
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

        int pos;
        switch((int)AccountController.getInstance().getUser().getUserSettings().getLotSettings().getMaxLotDistance())
        {
            case 50:
                pos = 0;
                break;
            case 100:
                pos = 1;
                break;
            case 200:
                pos = 2;
                break;
            case 500:
                pos = 3;
                break;
            case 1000:
                pos = 4;
                break;
            default:
                pos = 0;
                break;

        }
        spinner4.setSelection(pos);

        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch(i)
                {
                    case 0:
                        AccountController.getInstance().getUser().getUserSettings().getLotSettings().setMaxLotDistance(50.0f);
                        break;
                    case 1:
                        AccountController.getInstance().getUser().getUserSettings().getLotSettings().setMaxLotDistance(100.0f);
                        break;
                    case 2:
                        AccountController.getInstance().getUser().getUserSettings().getLotSettings().setMaxLotDistance(200.0f);
                        break;
                    case 3:
                        AccountController.getInstance().getUser().getUserSettings().getLotSettings().setMaxLotDistance(500.0f);
                        break;
                    case 4:
                        AccountController.getInstance().getUser().getUserSettings().getLotSettings().setMaxLotDistance(1000.0f);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch(i)
                {
                    case 0:

                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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

    public void saveSettings(View view)
    {
        Intent intent = new Intent(getApplicationContext(), Settings.class);
        startActivity(intent);
    }

}
