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

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseObject;


public class CreateInfoActivity extends ActionBarActivity {

    protected EditText mItemName;
    protected EditText mItemNum;
    protected Button mSaveButn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_info);

        Parse.initialize(this, "Y8GDS3B8JaZU2imH3rF84qxFqHZYRjx0kZ95iSCV", "pus4xNS2XU4KOBn1krmSPrrGu581dasgUidOhXyH");
        ParseInstallation.getCurrentInstallation().saveInBackground();
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();

        mSaveButn = (Button) findViewById(R.id.buttonsave);

        mItemName = (EditText) findViewById(R.id.editTextName);
        mItemNum = (EditText) findViewById(R.id.editTextInt);


        mSaveButn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String ItemNameEntered = mItemName.getText().toString();
                String ItemNumEntered = mItemNum.getText().toString();


                ParseObject enteredItems = new ParseObject("EnteredObjects");
                enteredItems.put("Usertext", ItemNameEntered);
                enteredItems.put("numberEntered", ItemNumEntered);

                enteredItems.saveInBackground();

                Toast.makeText(CreateInfoActivity.this, "Info Saved!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_info, menu);
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
