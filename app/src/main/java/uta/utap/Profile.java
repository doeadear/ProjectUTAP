package uta.utap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        AccountController ac = AccountController.getInstance();

        if(ac.getUser().isRegisteredUser())
        {
            Button registerButton = (Button) findViewById(R.id.button_reg);
            registerButton.setEnabled(false);

            Button loginButton = (Button) findViewById(R.id.button_log);
            loginButton.setEnabled(false);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;

    }

    public void Login(View view) {
        Intent intent=new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void SignUp(View view) {
        Intent intent = new Intent(this, SIgnup.class);
        startActivity(intent);
    }

    public void GoSchedule(View view) {
        Intent intent=new Intent(this, Input_Schedule.class);
        startActivity(intent);
    }
}
