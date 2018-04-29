package com.example.ryann.iglu.db;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ryann.iglu.LocationViewActivity;
import com.example.ryann.iglu.MainActivity;

public class DatabaseOpenHelperCities extends SQLiteOpenHelper {

    // if you change the database schema, you must increment the db version.
    public static final int DATABASE_VERSION = 1;
    // this is used to name the underlying file storing the actual data
    public static final String DATABASE_NAME = "cities.db";

    public DatabaseOpenHelperCities(MainActivity context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DatabaseOpenHelperCities(LocationViewActivity context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_CITY_DATA);
        String sql;
        sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Vienna','Austria','1.8m','Vienna International Airport', 0 );";
        db.execSQL(sql);
        sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Graz','Austria','269k','Graz Airport', 0 );";
        db.execSQL(sql);
        sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Linz','Austria','193k','Linz Airport', 0 );";
        db.execSQL(sql);
        sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Innsbruck','Austria','124k','Innsbruck Airport', 0 );";
        db.execSQL(sql);
        sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Salzburg','Austria','146k','Salzburg Airport', 0 );";
        db.execSQL(sql);
        sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Antwerp','Belgium','498k','Antwerp International Airport', 0 );";
        db.execSQL(sql);
        sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Brussels','Belgium','1.1m','Brussels Airport', 0 );";
        db.execSQL(sql);
        sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Ghent','Belgium','248k','Ghent Airport', 0 );";
        db.execSQL(sql);
        sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Liege','Belgium','195k','Liege Airport', 0 );";
        db.execSQL(sql);
        sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Ypres','Belgium','35k','Brussels Airport', 0 );";
        db.execSQL(sql);
        sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Paris','France','2.2m','Charles de Gaulle Airport', 0 );";
        db.execSQL(sql);
        sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Nice','France','343k','Nice Cote d''Azur International Airport', 0 );";
        db.execSQL(sql);
        sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Chalons en Champagne','France','45k','Chalons Vatry Airport', 0 );";
        db.execSQL(sql);
        sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Marseille','France','850k','Marseille Provence Airport', 0 );";
        db.execSQL(sql);
        sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Lyon','France','484k','Lyon Saint Exupery Airport', 0 );";
        db.execSQL(sql);
        sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Berlin','Germany','3.4m','Berlin Schonfeld Airport', 0 );";
        db.execSQL(sql);
        sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Dortmund','Germany','580k','Dortmund Airport', 0 );";
        db.execSQL(sql);
        sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Stuttgart','Germany','612k','Stuttgart Airport', 0 );";
        db.execSQL(sql);
        sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Leipzig','Germany','544k','Leipzig/Halle Airport', 0 );";
        db.execSQL(sql);
        sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Munich','Germany','1.4m','Munich Airport', 0 );";
        db.execSQL(sql);
        sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Warsaw','Poland','1.7m','Warsaw Chopin Airport', 0 );";
        db.execSQL(sql);
        sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Krakow','Poland','754k','John Paul II International Airport Krakow-Balice', 0 );";
        db.execSQL(sql);
        sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Lodz','Poland','701k','Lodz Wladyslaw Airport', 0 );";
        db.execSQL(sql);
        sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Wroclaw','Poland','628k','Copernicus Airport Wroclaw', 0 );";
        db.execSQL(sql);
        sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Poznan','Poland','542k','Poznan-Lawica Airport', 0 );";
        db.execSQL(sql);
        sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Moscow','Russia','11.9m','Moscow Domodedovo Airport', 0 );";
        db.execSQL(sql);
        sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Saint Petersburg','Russia','4.9m','Pulkovo International Airport', 0 );";
        db.execSQL(sql);
        sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Novosibirsk','Russia','1.5m','Tolmachevo Airport', 0 );";
        db.execSQL(sql);
        sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Yekaterinburg','Russia','1.3m','Koltsovo Airport', 0 );";
        db.execSQL(sql);
        sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Kazan','Russia','1.1m','Kazan International Airport', 0 );";
        db.execSQL(sql);
        sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Zurich','Switzerland','391k','Zurich Airport', 0 );";
        db.execSQL(sql);
        sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Geneva','Switzerland','194k','Geneva Airport', 0 );";
        db.execSQL(sql);
        sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Lausanne','Switzerland','133k','Lausanne Airport', 0 );";
        db.execSQL(sql);
        sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Basel','Switzerland','168k','EuroAirport Basel Mulhouse Freiburg', 0 );";
        db.execSQL(sql);
        sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Bern','Switzerland','130k','Bern Airport', 0 );";
        db.execSQL(sql);
        sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Stockholm','Sweden','942k','Stockholm Arlanda Airport', 0 );";
        db.execSQL(sql);
        sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Gothenburg','Sweden','572k','Goteborg Landvetter Airport', 0 );";
        db.execSQL(sql);
        sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Malmo','Sweden','341k','Malmo Airport', 0 );";
        db.execSQL(sql);
        sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Uppsala','Sweden','149k','Arna Airport', 0 );";
        db.execSQL(sql);
        sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Vasteras','Sweden','151k','Stockholm Arlanda Airport', 0 );";
        db.execSQL(sql);
        sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('London','United Kingdom','8.7m','Heathrow Airport', 0 );";
        db.execSQL(sql);
        sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Manchester','United Kingdom','541k','Manchester Airport', 0 );";
        db.execSQL(sql);
        sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Newcastle','United Kingdom','546k','Newcastle International Airport', 0 );";
        db.execSQL(sql);
        sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Bradford','United Kingdom','528k','Leeds Bradford Airport', 0 );";
        db.execSQL(sql);
        sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Brighton','United Kingdom','155k','Shoreham Airport', 0 );";
        db.execSQL(sql);
        sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('New York City','United States of America','8.5m','John F. Kennedy International Airport', 0 );";
        db.execSQL(sql);
        sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Los Angeles','United States of America','3.9m','Los Angeles International Airport', 0 );";
        db.execSQL(sql);
        sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Phoenix','United States of America','1.6m','Phoenix Sky Harbor International Airport', 0 );";
        db.execSQL(sql);
        sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Houston','United States of America','2.3m','George Bush Intercontinental Airport', 0 );";
        db.execSQL(sql);
        sql = "INSERT or replace INTO Cities (City, Country, PopulationCity, NearestAirport, FavouriteCity) VALUES ('Washington DC','United States of America','7.2m','Ronald Reagan Washington National Airport', 0 );";
        db.execSQL(sql);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // in our case, we simply delete all data and recreate the DB
        db.execSQL(SQL_DELETE_CITY_DATA);
        onCreate(db);
    }

    private static final String SQL_CREATE_CITY_DATA =
            "CREATE TABLE Cities(" +
                    "City TEXT PRIMARY KEY NOT NULL, " +
                    "Country TEXT NOT NULL," +
                    "PopulationCity TEXT NOT NULL, " +
                    "NearestAirport TEXT NOT NULL," +
                    "FavouriteCity INTEGER NOT NULL)";

    private static final String SQL_DELETE_CITY_DATA =
            "DROP TABLE IF EXISTS entries";
}