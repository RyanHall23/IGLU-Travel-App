package com.example.ryann.iglu;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.example.ryann.iglu.db.DatabaseOpenHelperCities;
import com.example.ryann.iglu.db.DatabaseOpenHelperCountrys;


public class MainActivity extends AppCompatActivity
{

    // Country Data pulled from database, store in a local variable for searching and passing through activities
    String[] Countries = new String[10];
    String[] Population = new String[10];
    String[] Currency = new String[10];
    String[] Capital = new String[10];
    String[] Languages = new String[10];
    String[] CountryCities = new String[50];
    String[] Cities = new String[50];
    String[] PopulationCity = new String[50];
    String[] NearestAP = new String[50];
    int[] FavouriteCities = new int[50];


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buildCityinfo();
        buildCountryInfo();

        updateCity();
        searchButton();
        notesButton();
        favouriteButton();
    }


    protected void updateCity()
    {
        DatabaseOpenHelperCities doh = new DatabaseOpenHelperCities(this);
        SQLiteDatabase dbCities = doh.getWritableDatabase();

        Intent intent = getIntent();
        Cursor queryCursor;
        int[] citiesfromdbupdate = new int[50];
        String cityfavourited = intent.getStringExtra("selectedCity");

        if (cityfavourited != null)
        {
            String[] filter = {cityfavourited};

            String[] columns = {"City", "Country", "PopulationCity", "NearestAirport", "FavouriteCity"};
            queryCursor = dbCities.query("Cities", columns, "City=?", filter, null, null, null);
            int numOfRows = queryCursor.getCount();
            queryCursor.moveToFirst();
            int i = 0;
            while (i < numOfRows)
            {
                queryCursor.moveToNext();
                citiesfromdbupdate[i] = queryCursor.getInt(i);
                FavouriteCities[i] = citiesfromdbupdate[i];
                i++;
            }
            queryCursor.close();
        }
    }

    private void searchButton()
    {
        ImageButton searchButton = findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent Search = new Intent(MainActivity.this, SearchActivity.class);
                Search.putExtra("country", Countries);
                Search.putExtra("population", Population);
                Search.putExtra("language", Languages);
                Search.putExtra("currency", Currency);
                Search.putExtra("capital", Capital);
                Search.putExtra("city", Cities);
                Search.putExtra("cityairport", NearestAP);
                Search.putExtra("populationcity", PopulationCity);
                Search.putExtra("FavouriteCity", FavouriteCities);
                startActivity(Search);
            }
        });
    }

    private void notesButton()
    {
        ImageButton notesButton = findViewById(R.id.notesButton);
        notesButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, NotesActivity.class));
            }
        });
    }

    private void favouriteButton()
    {

        ImageButton favouriteButton = findViewById(R.id.favouriteButton);
        favouriteButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                updateCity();
                Intent Favourites = new Intent(MainActivity.this, FavouritesAcivity.class);
                Favourites.putExtra("country", Countries);
                Favourites.putExtra("population", Population);
                Favourites.putExtra("language", Languages);
                Favourites.putExtra("currency", Currency);
                Favourites.putExtra("capital", Capital);
                Favourites.putExtra("city", Cities);
                Favourites.putExtra("cityairport", NearestAP);
                Favourites.putExtra("populationcity", PopulationCity);
                Favourites.putExtra("FavouriteCity", FavouriteCities);
                startActivity(Favourites);
            }
        });
    }

    protected void buildCountryInfo()
    {
        DatabaseOpenHelperCountrys doh = new DatabaseOpenHelperCountrys(this);
        SQLiteDatabase dbCountry = doh.getWritableDatabase();
        Cursor cursor = dbCountry.rawQuery("SELECT * FROM Country", null);

        int numOfRows = cursor.getCount();
        final String[] CountryfromDB = new String[numOfRows];
        final String[] CapitalfromDB = new String[numOfRows];
        final String[] PopulationfromDB = new String[numOfRows];
        final String[] LanguagesfromDB = new String[numOfRows];
        final String[] CurrencyfromDB = new String[numOfRows];

        cursor.moveToFirst();
        int columnCountryIndex = cursor.getColumnIndex("Country");
        int columnCapitalIndex = cursor.getColumnIndex("Capital");
        int columnPopulationIndex = cursor.getColumnIndex("Population");
        int columnLanguageIndex = cursor.getColumnIndex("Languages");
        int columnCurrencyIndex = cursor.getColumnIndex("Currency");

        for (int i = 0; i < numOfRows; i++)
        {
            CountryfromDB[i] = cursor.getString(columnCountryIndex);
            CapitalfromDB[i] = cursor.getString(columnCapitalIndex);
            PopulationfromDB[i] = cursor.getString(columnPopulationIndex);
            LanguagesfromDB[i] = cursor.getString(columnLanguageIndex);
            CurrencyfromDB[i] = cursor.getString(columnCurrencyIndex);
            cursor.moveToNext();
        }
        cursor.close();

        for (int i = 0; i < 10; i++)
        {
            Countries[i] = CountryfromDB[i];
            Capital[i] = CapitalfromDB[i];
            Population[i] = PopulationfromDB[i];
            Languages[i] = LanguagesfromDB[i];
            Currency[i] = CurrencyfromDB[i];
        }
    }

    protected void buildCityinfo()
    {
        DatabaseOpenHelperCities doh = new DatabaseOpenHelperCities(this);
        SQLiteDatabase dbCities = doh.getWritableDatabase();
        Cursor cursor = dbCities.rawQuery("SELECT * FROM Cities", null);

        int numOfRows = cursor.getCount();
        final String[] CountryCityfromDB = new String[numOfRows];
        final String[] CitiesfromDB = new String[numOfRows];
        final String[] PopulationCitiesfromDB = new String[numOfRows];
        final String[] APCitiesfromDB = new String[numOfRows];
        final int[] FavCitiesfromDB = new int[numOfRows];

        cursor.moveToFirst();
        int columnCountryCitiesIndex = cursor.getColumnIndex("Country");
        int columnCitiesIndex = cursor.getColumnIndex("City");
        int columnPopulationCitiesIndex = cursor.getColumnIndex("PopulationCity");
        int columnAPCitiesIndex = cursor.getColumnIndex("NearestAirport");
        int columnFavCitiesIndex = cursor.getColumnIndex("FavouriteCity");


        for (int i = 0; i < numOfRows; i++)
        {
            CountryCityfromDB[i] = cursor.getString(columnCountryCitiesIndex);
            CitiesfromDB[i] = cursor.getString(columnCitiesIndex);
            PopulationCitiesfromDB[i] = cursor.getString(columnPopulationCitiesIndex);
            APCitiesfromDB[i] = cursor.getString(columnAPCitiesIndex);
            FavCitiesfromDB[i] = cursor.getInt(columnFavCitiesIndex);
            cursor.moveToNext();
        }
        cursor.close();

        for (int i = 0; i < 50; i++)
        {
            CountryCities[i] = CountryCityfromDB[i];
            Cities[i] = CitiesfromDB[i];
            PopulationCity[i] = PopulationCitiesfromDB[i];
            NearestAP[i] = APCitiesfromDB[i];
            FavouriteCities[i] = FavCitiesfromDB[i];
        }
    }
}