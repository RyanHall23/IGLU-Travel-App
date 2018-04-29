package com.example.ryann.iglu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class FavouritesAcivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);
        getFavourites();
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
        getFavourites();
    }

    public void getFavourites()
    {
        String[] Cities = new String[50];
        int[] FavouriteCities = new int[50];
        String[] FavouriteCity = new String[50];

        Bundle extras = getIntent().getExtras();
        String[] CitiesI = extras.getStringArray("city");
        int[] FavouriteCitiesI = extras.getIntArray("FavouriteCity");

        Cities = CitiesI;
        FavouriteCities = FavouriteCitiesI;

        final List<String> fav_cities = new ArrayList<String>();
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, fav_cities);
        ListView listView = findViewById(R.id.favouriteList);
        listView.setAdapter(itemsAdapter);


        for (int i = 0; i < 50; i++)
        {
            if (FavouriteCities[i] == 1)
            {
                FavouriteCity[i] = Cities[i];
                fav_cities.add(Cities[i]);
                itemsAdapter.notifyDataSetChanged();
            }
        }
    }
}
