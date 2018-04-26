package com.example.ryann.iglu;


import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ryann.iglu.db.DatabaseOpenHelper;

public class NotesActivity extends AppCompatActivity
{

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        listView = findViewById(R.id.list_view);
    }

    public void addEntry(View view)
    {
        startActivity(new Intent(this, NotesAddEntryActivity.class));
    }

    protected void onResume()
    {
        super.onResume();

        // we first need a database open helper to even touch the DB...
        DatabaseOpenHelper doh = new DatabaseOpenHelper(this);
        // we then get a readable handler to the DB...
        SQLiteDatabase db = doh.getReadableDatabase();
        // and then we run a raw SQL query which returns a cursor pointing to the results
        Cursor cursor = db.rawQuery("SELECT * FROM entries", null);
        // number of rows in the result set
        int numOfRows = cursor.getCount();
        final String [] titles = new String[numOfRows]; // the titles of the blog entries
        final String [] bodies = new String[numOfRows]; // the bodies of the blog entries
        cursor.moveToFirst();
        int columnTitleIndex = cursor.getColumnIndex("title");
        int columnBodyIndex = cursor.getColumnIndex("body");
        for(int i = 0; i < numOfRows; i++)
        {
            titles[i] = cursor.getString(columnTitleIndex);
            bodies[i] = cursor.getString(columnBodyIndex);
            cursor.moveToNext();
        }
        cursor.close();

        final ArrayAdapter arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_expandable_list_item_1, titles);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                deleteEntry(titles[position]);
                return true;
            }
        });
    }

    private void deleteEntry(String title)
    {
        DatabaseOpenHelper doh = new DatabaseOpenHelper(this);
        SQLiteDatabase db = doh.getWritableDatabase();
        db.delete("entries", "title=" + DatabaseUtils.sqlEscapeString(title) + "", null);
    }
}

