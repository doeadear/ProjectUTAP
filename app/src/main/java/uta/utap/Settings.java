package uta.utap;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;

    }

    public void GoColor(View view) {
        Intent intent=new Intent(this, ColorSettingsActivity.class);
        startActivity(intent);
    }

    public void GoNotification(View view) {
        Intent intent=new Intent(this, NotificationSettingsActivity.class);
        startActivity(intent);
    }

    public void GoLot(View view) {
        Intent intent=new Intent(this, LotSettingsActivity.class);
        startActivity(intent);
    }

}
