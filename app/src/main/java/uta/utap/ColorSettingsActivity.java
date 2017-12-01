package uta.utap;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by emiko on 11/26/2017.
 */

public class ColorSettingsActivity extends AppCompatActivity
{

    Spinner spinner1,spinner2,spinner3;
    ArrayAdapter<CharSequence> adapter;
    TextView t;

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

        SettingsController sc = SettingsController.getInstance();
        int unavailableColor = sc.getSettings().getColorSettings().getUnavailableColor();
        int busyColor = sc.getSettings().getColorSettings().getBusyColor();
        int availableColor = sc.getSettings().getColorSettings().getAvailableColor();

        spinner1.setSelection(colorToPosition(unavailableColor));
        spinner2.setSelection(colorToPosition(busyColor));
        spinner3.setSelection(colorToPosition(availableColor));

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i){
                    case 0:
//                      Insert method here
                        AccountController.getInstance().getUser().getUserSettings().getColorSettings()
                                .setUnavailableColor(Color.RED);
                        String message="Color Red selected for full lot";
                        Toast.makeText(ColorSettingsActivity.this,message, Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
//                      Insert method here
                        AccountController.getInstance().getUser().getUserSettings().getColorSettings()
                                .setUnavailableColor(Color.YELLOW);
                        String message2="Color Yellow selected for full lot";
                        Toast.makeText(ColorSettingsActivity.this,message2, Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
//                      Insert method here
                        AccountController.getInstance().getUser().getUserSettings().getColorSettings()
                                .setUnavailableColor(Color.GREEN);
                        String message3="Color Green selected for full lot";
                        Toast.makeText(ColorSettingsActivity.this,message3, Toast.LENGTH_SHORT).show();
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

                switch (i){
                    case 0:
//                      Insert method here
                        AccountController.getInstance().getUser().getUserSettings().getColorSettings()
                                .setBusyColor(Color.RED);
                        String message="Color Red selected for Busy lot";
                        Toast.makeText(ColorSettingsActivity.this,message, Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
//                      Insert method here
                        AccountController.getInstance().getUser().getUserSettings().getColorSettings()
                                .setBusyColor(Color.YELLOW);
                        String message2="Color Yellow selected for Busy lot";
                        Toast.makeText(ColorSettingsActivity.this,message2, Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
//                      Insert method here
                        AccountController.getInstance().getUser().getUserSettings().getColorSettings()
                                .setBusyColor(Color.GREEN);
                        String message3="Color Green selected for Busy lot";
                        Toast.makeText(ColorSettingsActivity.this,message3, Toast.LENGTH_SHORT).show();
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

                switch (i){
                    case 0:
//                      Insert method here
                        AccountController.getInstance().getUser().getUserSettings().getColorSettings()
                                .setAvailableColor(Color.RED);
                        String message="Color Red selected for empty lot";
                        Toast.makeText(ColorSettingsActivity.this,message, Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
//                      Insert method here
                        AccountController.getInstance().getUser().getUserSettings().getColorSettings()
                                .setAvailableColor(Color.YELLOW);
                        String message2="Color Yellow selected for empty lot";
                        Toast.makeText(ColorSettingsActivity.this,message2, Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
//                      Insert method here
                        AccountController.getInstance().getUser().getUserSettings().getColorSettings()
                                .setAvailableColor(Color.GREEN);
                        String message3="Color Green selected for empty lot";
                        Toast.makeText(ColorSettingsActivity.this,message3, Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
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

    // Helper methods
    private int colorToPosition(int color)
    {
        int position = 0;
        switch(color)
        {
            case Color.RED:
                position = 0;
                break;
            case Color.YELLOW:
                position = 1;
                break;
            case Color.GREEN:
                position = 2;
                break;
            default:
                break;
        }

        return position;
    }


}
