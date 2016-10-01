package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by Salvador on 01/10/2016.
 */

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {

    private String mUrl;

    public static final String LOG_TAG = EarthquakeLoader.class.getName();


    public EarthquakeLoader(Context context, String url) {

        super(context);
        mUrl = url;

    }

    public String getmUrl() {

        return mUrl;

    }

    @Override
    protected void onStartLoading() {

        forceLoad();

    }

    @Override
    public List<Earthquake> loadInBackground(){

        if (getmUrl() == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of earthquakes.
        return Utils.fetchEarthquakeData(getmUrl());

    }

}
