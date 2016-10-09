/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity
    implements LoaderManager.LoaderCallbacks<List<Earthquake>> {

    /**
     * Constant value for the earthquake loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int EARTHQUAKE_LOADER_ID = 1;

    /** TextView that is displayed when the list is empty */
    private TextView mEmptyStateTextView;

    /** TextView that is displayed when the list is not empty */
    private ListView earthquakeListView;

    /** Spinner that is shown while the earthquake data is retrieved */
    private ProgressBar mProgressBar;

    /** Connectivity status **/
    private boolean isConnected;

    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    private static final String USGS_URL =
            "http://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Check if there is internet connection
        ConnectivityManager cm =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        isConnected = activeNetwork != null &&
                               activeNetwork.isConnectedOrConnecting();


        // Find a reference to the {@link ListView} in the layout
        earthquakeListView = (ListView) findViewById(R.id.list);

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        earthquakeListView.setEmptyView(mEmptyStateTextView);

        // Save the progress bar widget
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);

        if (!isConnected) { mProgressBar.setVisibility(View.INVISIBLE);}

        Log.v(LOG_TAG,"Call initLoader");
        getLoaderManager().initLoader(EARTHQUAKE_LOADER_ID,null, this);


    }

    @Override
    public Loader<List<Earthquake>> onCreateLoader(int i, Bundle bundle){

        Log.v(LOG_TAG,"Call OnCreateLoader");

        // Create a new loader for the given URL
        return  new EarthquakeLoader(this,USGS_URL);

    }

    @Override
    public void onLoadFinished(Loader<List<Earthquake>> loader, List<Earthquake> earthquakes) {

        Log.v(LOG_TAG,"onLoadFinished");
        // Set the text in the empty state TextView after the first load
        if (isConnected) {

            mEmptyStateTextView.setText(getText(R.string.empy_message));

        } else {

            mEmptyStateTextView.setText(R.string.no_connection);

        }



        // Hid the progress bar
        mProgressBar.setVisibility(View.INVISIBLE);

        // Empty the list to check the empty list response
        //  Comment out after checking
        // earthquakes.clear();

        // Update the UI with the result
        updateUI((ArrayList<Earthquake>)earthquakes);

    }

    @Override
    public void onLoaderReset(Loader<List<Earthquake>> loader) {

        Log.v(LOG_TAG,"onLoaderReset");

        // Loader reset, so we can clear out our existing data.
        updateUI(new ArrayList<Earthquake>());

    }

    private void updateUI(ArrayList<Earthquake> earthquakes){


        // Create a new adapter
        EarthquakeAdapter adapter = new EarthquakeAdapter(getApplicationContext(),earthquakes);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);

    }




}
