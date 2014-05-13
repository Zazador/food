package com.example.food.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.parse.PushService;
import com.parse.SaveCallback;

import java.text.ParseException;

/**
 * Created by zach on 5/13/14.
 */
public class LoginActivity extends Activity {

    public EditText emailField;
    public EditText passwordField;
    public Button loginButton;
    public View.OnClickListener loginButtonListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Parse.initialize(this, "JrZucBhi9hfW4FbpxvdsgILXhr3fi3ygv5twEB9w", "s39eDV2qnAUik2GDLCLUS21RMshXzemPziMrboQM");
        PushService.setDefaultPushCallback(getApplicationContext(), MainActivity.class);
        ParseInstallation.getCurrentInstallation().saveInBackground();


        emailField = (EditText) findViewById(R.id.email_login);
        passwordField = (EditText) findViewById(R.id.password_login);
        loginButton = (Button) findViewById(R.id.login_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginButton.setEnabled(false);

                String email = ((EditText) findViewById(R.id.email_login)).getText().toString().trim();
                String password = ((EditText) findViewById(R.id.password_login)).getText().toString().trim();

                ParseUser.logInInBackground(email, password, new LogInCallback() {
                    @Override
                    public void done(ParseUser parseUser, com.parse.ParseException e) {
                        if (parseUser != null) {
                            // User is valid and is now logged in
                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(i);

                            PushService.subscribe(getApplicationContext(), "user_" + ParseUser.getCurrentUser().getObjectId(), MainActivity.class);
                        }
                        else {
                            loginButton.setEnabled(true);
                            // sign up failed
                        }
                    }
                });
            }
        });

        ((Button)findViewById(R.id.register_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });

    }

}
