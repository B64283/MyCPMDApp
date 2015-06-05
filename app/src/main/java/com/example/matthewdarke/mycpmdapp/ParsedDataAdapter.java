package com.example.matthewdarke.mycpmdapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.parse.ParseObject;

import java.util.List;

/**
 * Created by matthewdarke on 6/4/15.
 */
public class ParsedDataAdapter extends ArrayAdapter<ParseObject> {

//use similar adapter used in java2 for connected application
    ViewHolder vH;

    public static Context mContext;
    protected List<ParseObject>mUserText;

        public ParsedDataAdapter(Context context,  List<ParseObject> objects) {
        super(context, R.layout.customlistlayout, objects);
            mContext = context;
            mUserText = objects;



    }


    public static class ViewHolder {

        TextView userText;
        TextView userNumber;
    }

//initialize to inflate the customListview
   @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


       LayoutInflater inflater =
               (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
       View view = inflater.inflate(R.layout.customlistlayout, null);


       ParseObject object = mUserText.get(position);

       TextView userText = (TextView) view.findViewById(R.id.EnteredText);
       TextView userNumber = (TextView) view.findViewById(R.id.NumberText);
       String uText = object.getString("Usertext");
       userText.setText(uText);

       String uNum = object.getString("numberEntered");
       userNumber.setText(uNum);


       return view;


   }





}
