package com.example.matthewdarke.mycpmdapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class RegisterUserActivity extends Activity {

    protected EditText mUserNm;
    protected EditText mUserEmail;
    protected EditText mUserPasswrd;
    protected Button mRedgesterBtn;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        mRedgesterBtn = (Button)findViewById(R.id.buttonRegister);

        mUserPasswrd = (EditText)findViewById(R.id.editTextPass);
        mUserNm = (EditText)findViewById(R.id.editTextName);
        mUserEmail = (EditText)findViewById(R.id.editTextEmail);

      //create clickListener listen for click of redgest buttn

        mRedgesterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              //grab user entered text and create strings out of entered text

              String UserName = mUserNm.getText().toString().trim();
              String UserEmail = mUserEmail.getText().toString().trim();
              String UserPasswrd = mUserPasswrd.getText().toString().trim();


               //we will use this to stor in parse
                ParseUser user = new ParseUser();
                user.setUsername(UserName);
                user.setPassword(UserPasswrd);
                user.setEmail(UserEmail);



                user.signUpInBackground(new SignUpCallback() {

                    @Override
                    public void done(ParseException e) {

                        if(e == null){

                            //Succsessful Registry
                            Toast.makeText(RegisterUserActivity.this, "Thank You! You're all signed up!", Toast.LENGTH_LONG).show();

                        // Take user to Main page
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);


                        } else {

                            // error with registry\
                            Toast.makeText(RegisterUserActivity.this, "Uh-O There was an error", Toast.LENGTH_LONG).show();

                        }


                    }
                });






            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register_user, menu);
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
