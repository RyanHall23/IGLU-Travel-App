package com.example.ryann.iglu.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOpenHelper extends SQLiteOpenHelper {

    // if you change the database schema, you must increment the db version.
    public static final int DATABASE_VERSION = 1;
    // this is used to name the underlying file storing the actual data
    public static final String DATABASE_NAME = "blog_entries.db";

    public DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_BLOG_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // in our case, we simply delete all data and recreate the DB
        db.execSQL(SQL_DELETE_BLOG_ENTRIES);
        onCreate(db);
    }

    private static final String SQL_CREATE_BLOG_ENTRIES =
            "CREATE TABLE entries (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "title TEXT NOT NULL, "+
                    "body TEXT NOT NULL "+
                    ")";

    private static final String SQL_DELETE_BLOG_ENTRIES =
            "DROP TABLE IF EXISTS entries";
}