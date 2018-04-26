package com.example.ryann.iglu;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.ryann.iglu.db.DatabaseOpenHelperCities;
import com.example.ryann.iglu.db.DatabaseOpenHelperCountrys;


public class MainActivity extends AppCompatActivity {
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buildCityinfo();
        buildCountryInfo();

        updateCity();
        searchButton();
        notesButton();
        favouriteButton();
        DistanceCalculatorButton();
        accountButton();
    }



    protected void updateCity()
    {
        DatabaseOpenHelperCities doh = new DatabaseOpenHelperCities(this);
        SQLiteDatabase dbCities = doh.getWritableDatabase();

        Intent intent = getIntent();
        Cursor queryCursor;
        int[] citiesfromdbupdate = new int[50];
        String cityfavourited = intent.getStringExtra("selectedCity");

        if(cityfavourited != null)
        {
            String[] filter = { cityfavourited };

            String[] columns = { "City", "Country", "PopulationCity", "NearestAirport", "FavouriteCity"};
            queryCursor = dbCities.query("Cities", columns, "City=?", filter, null, null, null);
            int numOfRows = queryCursor.getCount();
            queryCursor.moveToFirst();
            int i = 0;
            while(i < numOfRows)
            {
                queryCursor.moveToNext();
                citiesfromdbupdate[i] = queryCursor.getInt(i);
                FavouriteCities[i] = citiesfromdbupdate[i];
                i++;
            }
            queryCursor.close();
        }
    }

    private void searchButton() {
        ImageButton searchButton = findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
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

    private void notesButton() {
        ImageButton notesButton = findViewById(R.id.notesButton);
        notesButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NotesActivity.class));
            }
        });
    }

    private void favouriteButton() {

        ImageButton favouriteButton = findViewById(R.id.favouriteButton);
        favouriteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
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

    private void DistanceCalculatorButton() {
        ImageButton currencyButton = findViewById(R.id.DistanceCalculatorButton);
        currencyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent DistanceCalculatorActivity = new Intent(MainActivity.this, DistanceCalculatorActivity.class);
                DistanceCalculatorActivity.putExtra("city", Cities);
                startActivity(DistanceCalculatorActivity);
            }
        });
    }

    private void accountButton() {
        Button accountButton = findViewById(R.id.accountButton);
        accountButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });
    }


    protected void buildCountryInfo() {
        DatabaseOpenHelperCountrys doh = new DatabaseOpenHelperCountrys(this);
        SQLiteDatabase dbCountry = doh.getWritableDatabase();
        Cursor cursor = dbCountry.rawQuery("SELECT * FROM Country", null);

        if (cursor.getCount() == 0) {
            String sql;
            dbCountry = doh.getWritableDatabase(); // note we get a ‘writable’ DB
            sql = "INSERT or replace INTO Country (Country, Capital, Population, Languages, Currency) VALUES ('Austria','Vienna','8.7m','German','Euro');";
            dbCountry.execSQL(sql);
            sql = "INSERT or replace INTO Country (Country, Capital, Population, Languages, Currency) VALUES ('Belgium','Brussels','11.3m','Dutch, French, German','Euro');";
            dbCountry.execSQL(sql);
            sql = "INSERT or replace INTO Country (Country, Capital, Population, Languages, Currency) VALUES ('France','Paris','67.2m','French','Euro');";
            dbCountry.execSQL(sql);
            sql = "INSERT or replace INTO Country (Country, Capital, Population, Languages, Currency) VALUES ('Germany','Berlin','82.8','German','Euro');";
            dbCountry.execSQL(sql);
            sql = "INSERT or replace INTO Country (Country, Capital, Population, Languages, Currency) VALUES ('Poland','Warsaw','38.4m','Polish','Polish Zloty')";
            dbCountry.execSQL(sql);
            sql = "INSERT or replace INTO Country (Country, Capital, Population, Languages, Currency) VALUES ('Russia','Moscow','144.5m','Russia','Russian Ruble')";
            dbCountry.execSQL(sql);
            sql = "INSERT or replace INTO Country (Country, Capital, Population, Languages, Currency) VALUES ('Switzerland','Bern','8.4m','German, French, Italian, Romansh','Swiss Franc');";
            dbCountry.execSQL(sql);
            sql = "INSERT or replace INTO Country (Country, Capital, Population, Languages, Currency) VALUES ('Sweden','Stockholm','10.1m','Swedish','Swedish Krona');";
            dbCountry.execSQL(sql);
            sql = "INSERT or replace INTO Country (Country, Capital, Population, Languages, Currency) VALUES ('United Kingdom','London','65.6m','English','Pound Sterling');";
            dbCountry.execSQL(sql);
            sql = "INSERT or replace INTO Country (Country, Capital, Population, Languages, Currency) VALUES ('United States of America','Washington DC','325.7m','English','United States Dollar');";
            dbCountry.execSQL(sql);
            dbCountry = doh.getReadableDatabase();
        }

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


        for (int i = 0; i < numOfRows; i++) {
            CountryfromDB[i] = cursor.getString(columnCountryIndex);
            CapitalfromDB[i] = cursor.getString(columnCapitalIndex);
            PopulationfromDB[i] = cursor.getString(columnPopulationIndex);
            LanguagesfromDB[i] = cursor.getString(columnLanguageIndex);
            CurrencyfromDB[i] = cursor.getString(columnCurrencyIndex);
            cursor.moveToNext();
        }
        cursor.close();

        for (int i = 0; i < 10; i++) {
            Countries[i] = CountryfromDB[i];
            Capital[i] = CapitalfromDB[i];
            Population[i] = PopulationfromDB[i];
            Languages[i] = LanguagesfromDB[i];
            Currency[i] = CurrencyfromDB[i];
        }
    }

    protected void buildCityinfo() {
        DatabaseOpenHelperCities doh = new DatabaseOpenHelperCities(this);
        SQLiteDatabase dbCities = doh.getWritableDatabase();
        Cursor cursor = dbCities.rawQuery("SELECT * FROM Cities", null);

        if (cursor.getCount() == 0) {
            String sql;
            dbCities = doh.getWritableDatabase(); // note we get a ‘writable’ DB
            sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Vienna','Austria','1.8m','Vienna International Airport', 0 );";
            dbCities.execSQL(sql);
            sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Graz','Austria','269k','Graz Airport', 0 );";
            dbCities.execSQL(sql);
            sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Linz','Austria','193k','Linz Airport', 0 );";
            dbCities.execSQL(sql);
            sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Innsbruck','Austria','124k','Innsbruck Airport', 0 );";
            dbCities.execSQL(sql);
            sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Salzburg','Austria','146k','Salzburg Airport', 0 );";
            dbCities.execSQL(sql);
            sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Antwerp','Belgium','498k','Antwerp International Airport', 0 );";
            dbCities.execSQL(sql);
            sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Brussels','Belgium','1.1m','Brussels Airport', 0 );";
            dbCities.execSQL(sql);
            sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Ghent','Belgium','248k','Ghent Airport', 0 );";
            dbCities.execSQL(sql);
            sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Liege','Belgium','195k','Liege Airport', 0 );";
            dbCities.execSQL(sql);
            sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Ypres','Belgium','35k','Brussels Airport', 0 );";
            dbCities.execSQL(sql);
            sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Paris','France','2.2m','Charles de Gaulle Airport', 0 );";
            dbCities.execSQL(sql);
            sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Nice','France','343k','Nice Cote d''Azur International Airport', 0 );";
            dbCities.execSQL(sql);
            sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Chalons en Champagne','France','45k','Chalons Vatry Airport', 0 );";
            dbCities.execSQL(sql);
            sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Marseille','France','850k','Marseille Provence Airport', 0 );";
            dbCities.execSQL(sql);
            sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Lyon','France','484k','Lyon Saint Exupery Airport', 0 );";
            dbCities.execSQL(sql);
            sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Berlin','Germany','3.4m','Berlin Schonfeld Airport', 0 );";
            dbCities.execSQL(sql);
            sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Dortmund','Germany','580k','Dortmund Airport', 0 );";
            dbCities.execSQL(sql);
            sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Stuttgart','Germany','612k','Stuttgart Airport', 0 );";
            dbCities.execSQL(sql);
            sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Leipzig','Germany','544k','Leipzig/Halle Airport', 0 );";
            dbCities.execSQL(sql);
            sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Munich','Germany','1.4m','Munich Airport', 0 );";
            dbCities.execSQL(sql);
            sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Warsaw','Poland','1.7m','Warsaw Chopin Airport', 0 );";
            dbCities.execSQL(sql);
            sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Krakow','Poland','754k','John Paul II International Airport Krakow-Balice', 0 );";
            dbCities.execSQL(sql);
            sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Lodz','Poland','701k','Lodz Wladyslaw Airport', 0 );";
            dbCities.execSQL(sql);
            sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Wroclaw','Poland','628k','Copernicus Airport Wroclaw', 0 );";
            dbCities.execSQL(sql);
            sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Poznan','Poland','542k','Poznan-Lawica Airport', 0 );";
            dbCities.execSQL(sql);
            sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Moscow','Russia','11.9m','Moscow Domodedovo Airport', 0 );";
            dbCities.execSQL(sql);
            sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Saint Petersburg','Russia','4.9m','Pulkovo International Airport', 0 );";
            dbCities.execSQL(sql);
            sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Novosibirsk','Russia','1.5m','Tolmachevo Airport', 0 );";
            dbCities.execSQL(sql);
            sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Yekaterinburg','Russia','1.3m','Koltsovo Airport', 0 );";
            dbCities.execSQL(sql);
            sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Kazan','Russia','1.1m','Kazan International Airport', 0 );";
            dbCities.execSQL(sql);
            sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Zurich','Switzerland','391k','Zurich Airport', 0 );";
            dbCities.execSQL(sql);
            sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Geneva','Switzerland','194k','Geneva Airport', 0 );";
            dbCities.execSQL(sql);
            sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Lausanne','Switzerland','133k','Lausanne Airport', 0 );";
            dbCities.execSQL(sql);
            sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Basel','Switzerland','168k','EuroAirport Basel Mulhouse Freiburg', 0 );";
            dbCities.execSQL(sql);
            sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Bern','Switzerland','130k','Bern Airport', 0 );";
            dbCities.execSQL(sql);
            sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Stockholm','Sweden','942k','Stockholm Arlanda Airport', 0 );";
            dbCities.execSQL(sql);
            sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Gothenburg','Sweden','572k','Goteborg Landvetter Airport', 0 );";
            dbCities.execSQL(sql);
            sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Malmo','Sweden','341k','Malmo Airport', 0 );";
            dbCities.execSQL(sql);
            sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Uppsala','Sweden','149k','Arna Airport', 0 );";
            dbCities.execSQL(sql);
            sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Vasteras','Sweden','151k','Stockholm Arlanda Airport', 0 );";
            dbCities.execSQL(sql);
            sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('London','United Kingdom','8.7m','Heathrow Airport', 0 );";
            dbCities.execSQL(sql);
            sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Manchester','United Kingdom','541k','Manchester Airport', 0 );";
            dbCities.execSQL(sql);
            sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Newcastle','United Kingdom','546k','Newcastle International Airport', 0 );";
            dbCities.execSQL(sql);
            sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Bradford','United Kingdom','528k','Leeds Bradford Airport', 0 );";
            dbCities.execSQL(sql);
            sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Brighton','United Kingdom','155k','Shoreham Airport', 0 );";
            dbCities.execSQL(sql);
            sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('New York City','United States of America','8.5m','John F. Kennedy International Airport', 0 );";
            dbCities.execSQL(sql);
            sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Los Angeles','United States of America','3.9m','Los Angeles International Airport', 0 );";
            dbCities.execSQL(sql);
            sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Phoenix','United States of America','1.6m','Phoenix Sky Harbor International Airport', 0 );";
            dbCities.execSQL(sql);
            sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Houston','United States of America','2.3m','George Bush Intercontinental Airport', 0 );";
            dbCities.execSQL(sql);
            sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Washington DC','United States of America','7.2m','Ronald Reagan Washington National Airport', 0 );";
            dbCities.execSQL(sql);
            dbCities = doh.getReadableDatabase();
        }


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


        for (int i = 0; i < numOfRows; i++) {
            CountryCityfromDB[i] = cursor.getString(columnCountryCitiesIndex);
            CitiesfromDB[i] = cursor.getString(columnCitiesIndex);
            PopulationCitiesfromDB[i] = cursor.getString(columnPopulationCitiesIndex);
            APCitiesfromDB[i] = cursor.getString(columnAPCitiesIndex);
            FavCitiesfromDB[i] = cursor.getInt(columnFavCitiesIndex);
            cursor.moveToNext();
        }
        cursor.close();

        for (int i = 0; i < 50; i++) {
            CountryCities[i] = CountryCityfromDB[i];
            Cities[i] = CitiesfromDB[i];
            PopulationCity[i] = PopulationCitiesfromDB[i];
            NearestAP[i] = APCitiesfromDB[i];
            FavouriteCities[i] = FavCitiesfromDB[i];
        }
    }


}