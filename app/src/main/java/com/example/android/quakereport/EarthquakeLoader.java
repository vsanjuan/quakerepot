package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by Salvador on 01/10/2016.
 */

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {

    /*        protected ArrayList<Earthquake> doInBackground(String... urls) {

                //String json = QueryUtils.SAMPLE_JSON_RESPONSE;

                return Utils.fetchEarthquakeData(USGS_URL);

                // return QueryUtils.extractEarthquakes(json);
            }*/
    private String mUrl;


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

        return Utils.fetchEarthquakeData(getmUrl());

    }



/*        protected void onPostExecute(ArrayList<Earthquake> earthquakes) {

            updateUI(earthquakes);

        }*/

}
