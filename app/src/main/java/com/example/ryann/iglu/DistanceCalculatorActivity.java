package com.example.ryann.iglu;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class DistanceCalculatorActivity extends AppCompatActivity {

    public static final String TAG = "networkingandjson";

    private TextView textViewJSON;
    String[] Cities = new String[50];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distance);

        onStart();
        getInfo();
        //buildCityinfo();
        //buildCountryInfo();

        // bind to the origins spinner, and initialize with a few cities
        final Spinner spinnerOrigin = (Spinner) findViewById(R.id.spinnerOrigin);
        ArrayAdapter originsAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Cities);
        spinnerOrigin.setAdapter(originsAdapter);

        // bind to the destinations spinner, and initialize with a few other cities
        final Spinner spinnerDestination= (Spinner) findViewById(R.id.spinnerDestination);
        ArrayAdapter destinationsAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Cities);
        spinnerDestination.setAdapter(destinationsAdapter);

        // this is the text view where the answer will be displayed
        final TextView textViewDistanceAndTravelTime = (TextView) findViewById(R.id.textViewDistanceAndTravelTime);
        textViewDistanceAndTravelTime.setText("");

        // this is the text view where the JSON code will be displayed - for now this is fixed
        textViewJSON = (TextView) findViewById(R.id.textViewJSON);
        textViewJSON.setText(MADRID_TO_ROME_JSON);

        // this is the button that starts the processing of the JSON code
        /*Button button = (Button) findViewById(R.id.buttonComputeDistanceAndTravelTime);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String json = textViewJSON.getText().toString();
                String distanceAndTravelTime = processData(json);
                textViewDistanceAndTravelTime.setText(distanceAndTravelTime);
            }
        });*/

        Button buttonConnect = (Button) findViewById(R.id.updateSelectedLocationsButton);
        buttonConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String json = textViewJSON.getText().toString();
                String distanceAndTravelTime = processData(json);
                textViewDistanceAndTravelTime.setText(distanceAndTravelTime);
                // get the selected origin...
                String origin = Cities[spinnerOrigin.getSelectedItemPosition()];
                // ... and destination
                String destination = Cities[spinnerDestination.getSelectedItemPosition()];
                // then call a method to form the appropriate URL address
                String urlAddress = createUrl(origin, destination);
                Log.d(TAG, "urlAddress: " + urlAddress);
                new DownloadWebpageTask().execute(urlAddress);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart()");
    }

    private void getInfo()
    {
        Bundle extras = getIntent().getExtras();
        String[] CitiesI = extras.getStringArray("city");
        Cities = CitiesI;
    }

    private String processData(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray rows = jsonObject.getJSONArray("rows");
            JSONObject row = rows.getJSONObject(0); // index 0 is first element
            JSONArray elements = row.getJSONArray("elements");
            JSONObject element = elements.getJSONObject(0);
            JSONObject distance = element.getJSONObject("distance");
            JSONObject duration = element.getJSONObject("duration");
            return distance.getString("text") + " (" + duration.getString("text") + ")";
        } catch (JSONException jsone) {
            throw new RuntimeException(jsone);
        }
    }

    private String createUrl(final String origin, final String destination)
    {
        try
        {
            return "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" +
                    URLEncoder.encode(origin, "UTF-8") +
                    "&destinations=" +
                    URLEncoder.encode(destination, "UTF-8");
        }
        catch (UnsupportedEncodingException uee)
        {
            Log.e(TAG, uee.getMessage());
            return null;
        }
    }

    private String downloadUrl(final String urlAddress)
            throws IOException
    {
        InputStream inputStream = null;

        try
        {
            URL url = new URL(urlAddress);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET"); // this is the default anyway
            conn.setDoInput(true); // connections can be used for input or output
            conn.connect(); // connects and starts the query
            int response = conn.getResponseCode(); // should be 200 if all is OK
            Log.d(TAG, "The response is: " + response);
            inputStream = conn.getInputStream();

            // handle response
            final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"), 8);
            // the string builder is used to collect all the read bytes into a single string
            final StringBuilder stringBuilder = new StringBuilder();
            String line; // used as a temporary buffer
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            return stringBuilder.toString();
        }
        finally
        {
            // Makes sure that the InputStream is closed after the app is finished using it.
            if (inputStream != null)
            {
                inputStream.close();
            }
        }
    }

    private class DownloadWebpageTask extends AsyncTask<String, Void, String> {
        @Override protected String doInBackground(String... urls) {
            // execute in background, in separate thread – cannot edit the UI
            try
            {
                // ... then call the method that connects and fetches the data ...
                return downloadUrl(urls[0]);
            }
            catch (IOException ioe) {
                return "Error: " + ioe;
            }
        }
        @Override protected void onPostExecute(String result)
        {
            // execute after the thread finishes – can edit the UI
            // ... and finally, update the TextView accordingly
            textViewJSON.setText(result);
        }
    }

    public static final String MADRID_TO_ROME_JSON =
            "{\n" +
                    "   \"destination_addresses\" : [ \"Rome, Italy\" ],\n" +
                    "   \"origin_addresses\" : [ \"Madrid, Madrid, Spain\" ],\n" +
                    "   \"rows\" : [\n" +
                    "      {\n" +
                    "         \"elements\" : [\n" +
                    "            {\n" +
                    "               \"distance\" : {\n" +
                    "                  \"text\" : \"1,953 km\",\n" +
                    "                  \"value\" : 1952607\n" +
                    "               },\n" +
                    "               \"duration\" : {\n" +
                    "                  \"text\" : \"18 hours 22 mins\",\n" +
                    "                  \"value\" : 66129\n" +
                    "               },\n" +
                    "               \"status\" : \"OK\"\n" +
                    "            }\n" +
                    "         ]\n" +
                    "      }\n" +
                    "   ],\n" +
                    "   \"status\" : \"OK\"\n" +
                    "}\n";

}



