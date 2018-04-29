package com.example.ryann.iglu.db;


import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ryann.iglu.MainActivity;

public class DatabaseOpenHelperCountrys extends SQLiteOpenHelper
{

    // if you change the database schema, you must increment the db version.
    public static final int DATABASE_VERSION = 1;
    // this is used to name the underlying file storing the actual data
    public static final String DATABASE_NAME = "countrys.db";

    public DatabaseOpenHelperCountrys(MainActivity context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(SQL_CREATE_COUNTRY_DATA);
        String sql;
        sql = "INSERT or replace INTO Country (Country, Capital, Population, Languages, Currency) VALUES ('Austria','Vienna','8.7m','German','Euro');";
        db.execSQL(sql);
        sql = "INSERT or replace INTO Country (Country, Capital, Population, Languages, Currency) VALUES ('Belgium','Brussels','11.3m','Dutch, French, German','Euro');";
        db.execSQL(sql);
        sql = "INSERT or replace INTO Country (Country, Capital, Population, Languages, Currency) VALUES ('France','Paris','67.2m','French','Euro');";
        db.execSQL(sql);
        sql = "INSERT or replace INTO Country (Country, Capital, Population, Languages, Currency) VALUES ('Germany','Berlin','82.8','German','Euro');";
        db.execSQL(sql);
        sql = "INSERT or replace INTO Country (Country, Capital, Population, Languages, Currency) VALUES ('Poland','Warsaw','38.4m','Polish','Polish Zloty')";
        db.execSQL(sql);
        sql = "INSERT or replace INTO Country (Country, Capital, Population, Languages, Currency) VALUES ('Russia','Moscow','144.5m','Russia','Russian Ruble')";
        db.execSQL(sql);
        sql = "INSERT or replace INTO Country (Country, Capital, Population, Languages, Currency) VALUES ('Switzerland','Bern','8.4m','German, French, Italian, Romansh','Swiss Franc');";
        db.execSQL(sql);
        sql = "INSERT or replace INTO Country (Country, Capital, Population, Languages, Currency) VALUES ('Sweden','Stockholm','10.1m','Swedish','Swedish Krona');";
        db.execSQL(sql);
        sql = "INSERT or replace INTO Country (Country, Capital, Population, Languages, Currency) VALUES ('United Kingdom','London','65.6m','English','Pound Sterling');";
        db.execSQL(sql);
        sql = "INSERT or replace INTO Country (Country, Capital, Population, Languages, Currency) VALUES ('United States of America','Washington DC','325.7m','English','United States Dollar');";
        db.execSQL(sql);
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