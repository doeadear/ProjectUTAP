package uta.utap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A login screen that offers login via email/password.
 */
public class SIgnup extends AppCompatActivity
{

    // UI references.
    private AutoCompleteTextView mUsernameView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        // Set up the login form.
        mUsernameView = (AutoCompleteTextView) findViewById(R.id.username);

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.password || id == EditorInfo.IME_NULL) {
                    // Store values at the time of the login attempt.
                    String username = mUsernameView.getText().toString();
                    String password = mPasswordView.getText().toString();

                    if(attemptRegister(username, password))
                    {
                        Intent intent=new Intent(getApplicationContext(), Profile.class);
                        startActivity(intent);
                    }
                    return true;
                }
                return false;
            }
        });

        Button mRegisterButton = (Button) findViewById(R.id.register_button);
        mRegisterButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                // Store values at the time of the login attempt.
                String username = mUsernameView.getText().toString();
                String password = mPasswordView.getText().toString();

                if(attemptRegister(username, password))
                {
                    Intent intent=new Intent(getApplicationContext(), Profile.class);
                    startActivity(intent);
                }
            }
        });

//        mLoginFormView = findViewById(R.id.login_form);
//        mProgressView = findViewById(R.id.login_progress);
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private boolean attemptRegister(String username, String password)
    {
        boolean success = false;

        // Reset errors.
        mUsernameView.setError(null);
        mPasswordView.setError(null);

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid username.
        if (TextUtils.isEmpty(username)) {
            mUsernameView.setError(getString(R.string.error_field_required));
            focusView = mUsernameView;
            cancel = true;
        } else if (!isUsernameAvailable(username, password)) {
            mUsernameView.setError("Username not available");
            focusView = mUsernameView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            if(TextUtils.isEmpty(password))
            {
                success = AccountController.getInstance().createAccount(username);
            }
            else
            {
                success = AccountController.getInstance().createAccount(username, password);
            }
        }

        return success;
    }

    private boolean isUsernameAvailable(String username, String password) {
        boolean available = false;

        if(TextUtils.isEmpty(password))
        {
            available = AccountController.getInstance().guestIdAvailable(username);
        }
        else
        {
           available = AccountController.getInstance().registeredIdAvailable(username);
        }
        return available;
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

}

