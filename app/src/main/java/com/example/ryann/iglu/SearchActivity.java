package com.example.ryann.iglu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

public class SearchActivity extends AppCompatActivity
{
    AutoCompleteTextView autoCompleteTextView;


    int selectedCountryIndex;
    int selectedCityIndex;
    String[] Countries = new String[10];
    String[] Cities = new String[50];
    String[] Capital = new String[10];
    String[] Population = new String[10];
    String[] Currency = new String[10];
    String[] Languages = new String[10];
    String[] PopulationCity = new String[50];
    String[] NearestAP = new String[50];
    int[] FavouriteCities = new int[50];

    ArrayAdapter<String> adapter;

    boolean countrySearch = true;
    String userSelection;
    String citySelected;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getInfo();
        displayList();
        countryButton();
        cityButton();
    }

    private void getInfo()
    {
        Bundle extras = getIntent().getExtras();
        String[] CountriesI = extras.getStringArray("country");
        String[] CitiesI = extras.getStringArray("city");
        String[] CapitalI = extras.getStringArray("capital");
        String[] PopulationI = extras.getStringArray("population");
        String[] CurrencyI = extras.getStringArray("currency");
        String[] LanguagesI = extras.getStringArray("language");
        String[] PopulationCityI = extras.getStringArray("populationcity");
        String[] NearestAPI = extras.getStringArray("cityairport");
        int[] FavouriteCitiesI = extras.getIntArray("FavouriteCity");

         Countries = CountriesI;
         Cities = CitiesI;
         Capital = CapitalI;
         Population = PopulationI;
         Currency = CurrencyI;
         Languages = LanguagesI;
         PopulationCity = PopulationCityI;
         NearestAP = NearestAPI;
         FavouriteCities = FavouriteCitiesI;
    }

    private void displayList()
    {
        autoCompleteTextView = findViewById(R.id.AutoSearch);

        if (countrySearch)
        {
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, Countries);
            autoCompleteTextView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        } else if (!countrySearch)
        {
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, Cities);
            autoCompleteTextView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }

                autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                userSelection = parent.getItemAtPosition(position).toString();
                Toast toast = Toast.makeText(getApplicationContext(), userSelection + " is Selected", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();

                if (!countrySearch)
                {
                    for (int i = 0; i < Cities.length; i++)
                    {
                        if (userSelection == Cities[i])
                        {
                            selectedCountryIndex = i;
                            selectedCityIndex = i;
                            citySelected = userSelection;
                            userSelection = Countries[i / 5];
                            selectedCountryIndex = selectedCountryIndex/5;
                        }
                    }
                }

                if(countrySearch)
                {
                    for (int i = 0; i < Countries.length; i++)
                    {
                        if (userSelection == Countries[i])
                        {
                            selectedCountryIndex = i;
                        }
                    }
                }

                Intent selectedLoc = new Intent(SearchActivity.this, LocationViewActivity.class);
                selectedLoc.putExtra("country", Countries[selectedCountryIndex]);
                selectedLoc.putExtra("population", Population[selectedCountryIndex]);
                selectedLoc.putExtra("language", Languages[selectedCountryIndex]);
                selectedLoc.putExtra("currency", Currency[selectedCountryIndex]);
                selectedLoc.putExtra("capital", Capital[selectedCountryIndex]);
                if(!countrySearch)
                {
                    selectedLoc.putExtra("index", selectedCityIndex);
                    selectedLoc.putExtra("city", Cities[selectedCityIndex]);
                    selectedLoc.putExtra("cityairport", NearestAP[selectedCityIndex]);
                    selectedLoc.putExtra("populationcity", PopulationCity[selectedCityIndex]);
                    selectedLoc.putExtra("FavouriteCity", FavouriteCities[selectedCityIndex]);
                }
                startActivity(selectedLoc);
            }
        });
    }

    private void countryButton()
    {
        Button countryButton = findViewById(R.id.countryButton);
        countryButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                countrySearch = true;
                displayList();
            }
        });
    }

    private void cityButton()
    {
        Button cityButton = findViewById(R.id.cityButton);
        cityButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                countrySearch = false;
                displayList();
            }
        });

    }
}

