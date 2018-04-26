package com.example.ryann.iglu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ViewEntryActivity extends AppCompatActivity
{

    public static final String EXTRA_TITLE = "extra-title";
    public static final String EXTRA_BODY= "extra-body";

    private TextView textViewTitle;
    private TextView textViewBody;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_entry);

        this.textViewTitle = findViewById(R.id.textViewTitle);
        this.textViewBody = findViewById(R.id.textViewBody);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        Intent intent = getIntent();
        if(intent != null)
        {
            textViewTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            textViewBody.setText(intent.getStringExtra(EXTRA_BODY));
        }
    }
}
