package com.example.matthewdarke.mycpmdapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseUser;


public class LogSignupActivity extends ActionBarActivity {

    protected EditText mUserNamem;
    protected EditText mUserPassword;
    protected Button mLoginBtn;
    protected Button mCreateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_signup);

        Parse.initialize(this, "Y8GDS3B8JaZU2imH3rF84qxFqHZYRjx0kZ95iSCV", "pus4xNS2XU4KOBn1krmSPrrGu581dasgUidOhXyH");
        ParseInstallation.getCurrentInstallation().saveInBackground();
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();

        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            // do stuff with the user
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);

        } else {
            // show the signup or login screen
        }

        mCreateBtn = (Button)findViewById(R.id.buttonSignup);
        mLoginBtn = (Button) findViewById(R.id.buttonLogin);

        mUserNamem = (EditText) findViewById(R.id.editTextNameLog);
        mUserPassword = (EditText) findViewById(R.id.editText2PassLogin);


        mLoginBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String UserNameLogin = mUserNamem.getText().toString().trim();
                String UserPSWRD = mUserPassword.getText().toString().trim();


                ParseUser.logInInBackground(UserNameLogin, UserPSWRD, new LogInCallback() {



                    public void done(ParseUser parseUser, com.parse.ParseException e) {


                             if (parseUser != null) {
                                    // Hooray! The user is logged in.

                                 Toast.makeText(LogSignupActivity.this, "Login Succsessful!", Toast.LENGTH_LONG).show();
                                 Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                 startActivity(intent);

                                } else {
                                    // Signup failed. Look at the ParseException to see what happened.
                                 Toast.makeText(LogSignupActivity.this, " Incorrect username or password", Toast.LENGTH_LONG).show();


                             }
                            }
                        });


                    }
                });






    mCreateBtn.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {

        //use intent to go to RegisterUserActivity
            Intent intent = new Intent(getApplicationContext(), RegisterUserActivity.class);
            startActivity(intent);


        }
    });


    }








        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_log_signup, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
