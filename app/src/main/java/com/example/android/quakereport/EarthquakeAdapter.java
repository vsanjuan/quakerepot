package com.example.android.quakereport;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Salvador on 24/08/2016.
 */
public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public EarthquakeAdapter(Context context, ArrayList<Earthquake> earthquakes) {

        super(context,0, earthquakes);

    }

    @Override
    public Earthquake getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.earthquake_list, parent, false);
        }

        // Recover information from the current object in the list
        Earthquake currentEarthquake = getItem(position);


        TextView textViewMagnitude = (TextView) listItemView.findViewById(R.id.magnitude);
        textViewMagnitude.setText(String.valueOf(currentEarthquake.getmMagnitude()));
        textViewMagnitude.setTextColor(Color.parseColor("#437DCC"));

        TextView textViewLocation = (TextView) listItemView.findViewById(R.id.location);
        textViewLocation.setText(currentEarthquake.getmLocation());
        textViewLocation.setTextColor(Color.parseColor("#437DCC"));

        TextView textViewDate = (TextView) listItemView.findViewById(R.id.date);
        textViewDate.setText(currentEarthquake.getmDate());
        textViewDate.setTextColor(Color.parseColor("#437DCC"));


        return listItemView;
    }
}
