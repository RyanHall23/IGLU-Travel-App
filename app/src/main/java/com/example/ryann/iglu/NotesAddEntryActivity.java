package com.example.ryann.iglu;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ryann.iglu.db.DatabaseOpenHelper;

public class NotesAddEntryActivity extends AppCompatActivity
{

    private EditText titleEditText;
    private EditText bodyEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_add_entry);

        titleEditText = findViewById(R.id.editTextTitle);
        bodyEditText = findViewById(R.id.editTextBody);
    }

    public void add(View view)
    {
        String title = titleEditText.getText().toString().trim();
        String body = bodyEditText.getText().toString().trim();

        if(title.isEmpty() || body.isEmpty())
        {
            Toast.makeText(this, "Both 'title' and 'Description' sections must be filled", Toast.LENGTH_SHORT).show();
        }
        else
        {
            // store in DB
            DatabaseOpenHelper doh = new DatabaseOpenHelper(this);
            SQLiteDatabase db = doh.getWritableDatabase(); // note we get a ‘writable’ DB
            ContentValues contentValues = new ContentValues(); // a map data structure
            contentValues.put("title", title);
            contentValues.put("body", body);
            db.insert("entries", null, contentValues);

            finish(); // causes this activity to terminate, and return to the parent one
        }
    }
}
