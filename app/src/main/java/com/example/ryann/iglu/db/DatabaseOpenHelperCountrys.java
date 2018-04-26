package com.example.ryann.iglu.db;


import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ryann.iglu.MainActivity;

public class DatabaseOpenHelperCountrys extends SQLiteOpenHelper {

    // if you change the database schema, you must increment the db version.
    public static final int DATABASE_VERSION = 1;
    // this is used to name the underlying file storing the actual data
    public static final String DATABASE_NAME = "countrys.db";

    public DatabaseOpenHelperCountrys(MainActivity context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_COUNTRY_DATA);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // in our case, we simply delete all data and recreate the DB
        db.execSQL(SQL_DELETE_COUNTRY_DATA);
        onCreate(db);
    }

    private static final String SQL_CREATE_COUNTRY_DATA =
            "CREATE TABLE Country(" +
                    "Country TEXT PRIMARY KEY NOT NULL, " +
                    "Capital TEXT NOT NULL," +
                    "Population TEXT NOT NULL," +
                    "Languages TEXT NOT NULL," +
                    "Currency TEXT NOT NULL)";

    private static final String SQL_DELETE_COUNTRY_DATA =
            "DROP TABLE IF EXISTS entries";
}