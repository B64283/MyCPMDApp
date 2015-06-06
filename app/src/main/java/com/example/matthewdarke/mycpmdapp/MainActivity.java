package com.example.matthewdarke.mycpmdapp;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;


public class MainActivity extends ListActivity {

protected List<ParseObject> mDataItems;
protected ParsedDataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ParseUser isCurrentUser = ParseUser.getCurrentUser();

        if (isCurrentUser != null) {

            ParseQuery<ParseObject> query = ParseQuery.getQuery("EnteredObjects");
            query.orderByDescending("createdAt");
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> objectId, com.parse.ParseException e) {

                    if(e == null){
        // we got it

            mDataItems = objectId;

             adapter = new ParsedDataAdapter(getListView().getContext(), mDataItems);
            setListAdapter(adapter);



        }else{
        //no.. there was a problem


        }



        }

            });


        } else {


            }






    }


@Override
public void onListItemClick(ListView l, View v, final int position, long id) {

    super.onListItemClick(l, v, position, id);

    final ParseObject enteredObject = mDataItems.get(position);
    //String objectId = enteredObject.getObjectId();

    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setMessage(R.string.delete)
    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int id) {
            // Delete item!
            enteredObject.deleteInBackground();
            enteredObject.saveInBackground();
            finish();
            startActivity(getIntent());

        }
    })
            .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User cancelled the dialog

                }
            });


    builder.show();





}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        switch (id) {

            case R.id.action_settings:
                //send user to createActivity
                Intent intent = new Intent(this, CreateInfoActivity.class);
                startActivity(intent);
                break;


            case R.id.action_LogOut:
                // log out user
                ParseUser.logOut();

                intent = new Intent(this, LogSignupActivity.class);
                startActivity(intent);

                break;



        }

        return super.onOptionsItemSelected(item);
    }


}
