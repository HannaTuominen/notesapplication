package com.example.notesapplication;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CustomAdapter extends ArrayAdapter<String> {

    Context context;
    ArrayList<String> osName;
    ArrayList<String> osDesc;
    ArrayList<Date> time;
    static LayoutInflater inflater;
    String date;

    public CustomAdapter(Context context, ArrayList<String> osName, ArrayList<String> osDesc, ArrayList<Date> time) {
        super(context, 0, osName);

        this.context = context;
        this.osName = osName;
        this.osDesc = osDesc;
        this.time = time;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if(time.size()>0) {
            Date today = time.get(position);
            SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy kk:mm");//formating according to my need
            date = formatter.format(today);
        }

        if(row == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.list_row, parent, false);
        }
        TextView name = (TextView) row.findViewById(R.id.nameTextView);
        TextView description = (TextView) row.findViewById(R.id.descriptionTextView);
        TextView time = (TextView) row.findViewById(R.id.timeTextView);

        name.setText(osName.get(position));
        description.setText(osDesc.get(position));
        time.setText(date);
        int zero = 0;
        int one = 1;
        int two = 2;
        int three = 3;
        int four = 4;
        int five = 5;
        int six = 6;
        int seven = 7;
        int eight = 8;
        int add = 9;


        for (int i = 0; i <= position; i++) {
            if (position == zero) {
                row.setBackgroundColor(Color.rgb(241,180,167));
            }

            if(position == one){
                //zero++;
                row.setBackgroundColor(Color.rgb(245,202,186));
            }
            if(position == two){
                //one++;
                row.setBackgroundColor(Color.rgb(255,219,210));
            }
            if(position == three){
                //two++;
                row.setBackgroundColor(Color.rgb(254,227,216));
            }
            if(position == four){
                //three++;
                row.setBackgroundColor(Color.rgb(239,238,234));
            }
            if(position == five){
                //four++;
                row.setBackgroundColor(Color.rgb(221,230,227));
            }
            if(position == six){
                //five++;
                row.setBackgroundColor(Color.rgb(186,228,226));
            }
            if(position == seven){
                //six++;
                row.setBackgroundColor(Color.rgb(130,209,214));
            }
            if(position == eight){
                //seven++;
                row.setBackgroundColor(Color.rgb(105,188,192));
            }
            zero +=add;
            one +=add;
            two +=add;
            three +=add;
            four +=add;
            five +=add;
            six +=add;
            seven +=add;
            eight +=add;
        }

        return row;
    }
}
