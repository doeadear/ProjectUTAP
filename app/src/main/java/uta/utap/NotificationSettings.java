package uta.utap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

/**
 * Created by emiko on 11/20/2017.
 */

public class NotificationSettings  extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener{

    Switch s1;
    String message="Notifications Turned ON";
    String message2="Notifications Turned OFF";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        s1=(Switch) findViewById(R.id.switch1);
        s1.setOnCheckedChangeListener(this);
    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        if(s1.isChecked())
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, message2, Toast.LENGTH_LONG).show();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), Settings.class);
        startActivityForResult(myIntent, 0);
        return true;

    }
    
}
