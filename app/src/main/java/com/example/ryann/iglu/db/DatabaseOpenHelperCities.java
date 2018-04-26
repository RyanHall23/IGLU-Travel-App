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