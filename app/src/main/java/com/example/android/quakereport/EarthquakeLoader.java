package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * Created by Salvador on 01/10/2016.
 */

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {

    private String mUrl;

    private static final String LOG_TAG = EarthquakeLoader.class.getName();


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
        Log.v(LOG_TAG,"onStartLoading");

    }

    @Override
    public List<Earthquake> loadInBackground(){

        if (getmUrl() == null) {
            return null;
        }

        Log.v(LOG_TAG,"onLoadingBackground");
        // Perform the network request, parse the response, and extract a list of earthquakes.
        return QueryUtils.fetchEarthquakeData(getmUrl());


    }

}
