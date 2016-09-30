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

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity
    implements LoaderManager.LoaderCallbacks<List<Earthquake>>, android.app.LoaderManager.LoaderCallbacks<Object> {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    private static final String USGS_URL =
            "http://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);


        // new EarthquakeAsyncTask().execute();
        getLoaderManager().initLoader(0,null, this);


    }

    @Override
    public Loader<List<Earthquake>> onCreateLoader(int i, Bundle bundle){


        // TODO: Create a new loader for the given URL
        return new EarthquakeLoader(this,USGS_URL);

    }

    @Override
    public void onLoadFinished(Loader<List<Earthquake>> loader, List<Earthquake> earthquakes) {
        // TODO: Update the UI with the result
        updateUI((ArrayList<Earthquake>)earthquakes);

    }

    @Override
    public void onLoaderReset(Loader<List<Earthquake>> loader) {
        // TODO: Loader reset, so we can clear out our existing data.
        updateUI(new ArrayList<Earthquake>());

    }

    private void updateUI(ArrayList<Earthquake> earthquakes){

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new adapter
        EarthquakeAdapter adapter = new EarthquakeAdapter(getApplicationContext(),earthquakes);


        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);

    }

    private class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {

/*        protected ArrayList<Earthquake> doInBackground(String... urls) {

            //String json = QueryUtils.SAMPLE_JSON_RESPONSE;

            return Utils.fetchEarthquakeData(USGS_URL);

            // return QueryUtils.extractEarthquakes(json);
        }*/


        public EarthquakeLoader(Context context, String url) {

            super(context);
            String mUrl = url;

        }

        @Override
        protected void onStartLoading() {

            forceLoad();

        }

        @Override
        public List<Earthquake> loadInBackground(){

            return Utils.fetchEarthquakeData(USGS_URL);

        }



/*        protected void onPostExecute(ArrayList<Earthquake> earthquakes) {

            updateUI(earthquakes);

        }*/

    }
}
