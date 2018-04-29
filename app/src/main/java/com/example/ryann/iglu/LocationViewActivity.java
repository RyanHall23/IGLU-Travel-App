package com.example.ryann.iglu;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ryann.iglu.db.DatabaseOpenHelperCities;

public class LocationViewActivity extends AppCompatActivity
{

    int SelectedCityFav;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_view);
        Bundle extras = getIntent().getExtras();
        final String SelectedLocation;
        final String SelectedCity;
        String SelectedCapital;
        String SelectedPopulation;
        String SelectedCurrency;
        String SelectedLanguage;
        String SelectedCityPop;
        final String SelectedCityAP;
        final int currentCity;

        final DatabaseOpenHelperCities doh = new DatabaseOpenHelperCities(LocationViewActivity.this);
        final SQLiteDatabase dbCities = doh.getWritableDatabase();

        Intent i = getIntent();
        SelectedLocation = extras.getString("country");
        SelectedCity = extras.getString("city");
        SelectedCapital = extras.getString("capital");
        SelectedPopulation = extras.getString("population");
        SelectedCurrency = extras.getString("currency");
        SelectedLanguage = extras.getString("language");
        SelectedCityPop = extras.getString("populationcity");
        SelectedCityAP = extras.getString("cityairport");
        currentCity = extras.getInt("index");
        SelectedCityFav = extras.getInt("FavouriteCity");

        TextView selectedCountry = findViewById(R.id.selectedCountry);
        selectedCountry.setText(SelectedLocation);

        TextView selectedCapital = findViewById(R.id.selectedCapital);
        selectedCapital.setText(SelectedCapital);

        TextView selectedPopulation = findViewById(R.id.selectedPopulation);
        selectedPopulation.setText(SelectedPopulation);

        TextView selectedCurrency = findViewById(R.id.selectedCurrency);
        selectedCurrency.setText(SelectedCurrency);

        TextView selectedLanguages = findViewById(R.id.selectedLanguages);
        selectedLanguages.setText(SelectedLanguage);

        Button airportWikiButton = findViewById(R.id.airportWikiButton);
        Button cityWikiButton = findViewById(R.id.cityWikiButton);
        ImageButton cityFavButton = findViewById(R.id.cityFavButton);
        airportWikiButton.setVisibility(View.GONE);
        cityWikiButton.setVisibility(View.GONE);
        cityFavButton.setVisibility(View.GONE);

        if (SelectedCity != null)
        {
            final TextView selectedCity = findViewById(R.id.selectedCity);
            selectedCity.setText(SelectedCity);

            TextView selectedCityAP = findViewById(R.id.selectedCityAP);
            selectedCityAP.setText(SelectedCityAP);

            TextView selectedCityPop = findViewById(R.id.selectedCityPop);
            selectedCityPop.setText(SelectedCityPop);

            airportWikiButton.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    Intent openAirportWiki = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.co.uk/maps/place/" + SelectedCityAP));
                    startActivity(openAirportWiki);
                    //Toast toast = Toast.makeText(getApplicationContext(), "https://www.google.co.uk/maps/place/" + SelectedCityAP, Toast.LENGTH_SHORT);
                    //toast.setGravity(Gravity.CENTER, 0, 0);
                    //toast.show();
                }
            });

            cityWikiButton.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    Intent openCityWiki = new Intent(Intent.ACTION_VIEW, Uri.parse("https://en.wikipedia.org/wiki/" + SelectedCity));
                    startActivity(openCityWiki);
                    //Toast toast = Toast.makeText(getApplicationContext(), "https://en.wikipedia.org/wiki/" + SelectedCity, Toast.LENGTH_SHORT);
                    //toast.setGravity(Gravity.CENTER, 0, 0);
                    //toast.show();
                }
            });

            cityFavButton.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    switch (SelectedCityFav)
                    {
                        case 0:
                            SelectedCityFav = 1;
                            Intent selectedLoc = new Intent(LocationViewActivity.this, MainActivity.class);
                            selectedLoc.putExtra("selectedCity", SelectedCity);
                            Toast favourite = Toast.makeText(getApplicationContext(), SelectedCity + " Is now a favourite", Toast.LENGTH_SHORT);
                            favourite.setGravity(Gravity.CENTER, 0, 0);
                            favourite.show();
                            //doh.onUpdate(dbCities,SelectedCity,SelectedCityFav);
                            String[] filter = {SelectedCity};
                            ContentValues cv = new ContentValues();
                            cv.put("FavouriteCity", 1);
                            dbCities.update("Cities", cv, "City=?", filter);

                            break;
                        case 1:
                            SelectedCityFav = 0;
                            Toast unFavourite = Toast.makeText(getApplicationContext(), SelectedCity + " Is no longer a favourite", Toast.LENGTH_SHORT);
                            unFavourite.setGravity(Gravity.CENTER, 0, 0);
                            unFavourite.show();
                            //doh.onUpdate(dbCities,SelectedCity,SelectedCityFav);

                            String[] filterR = {SelectedCity};
                            ContentValues cvR = new ContentValues();
                            cvR.put("FavouriteCity", 0);
                            dbCities.update("Cities", cvR, "City=?", filterR);
                            break;
                    }
                }
            });
            airportWikiButton.setVisibility(View.VISIBLE);
            cityWikiButton.setVisibility(View.VISIBLE);
            cityFavButton.setVisibility(View.VISIBLE);
        }
    }
}
