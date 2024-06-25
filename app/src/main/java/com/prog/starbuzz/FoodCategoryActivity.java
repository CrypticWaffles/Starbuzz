package com.prog.starbuzz;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.View;
import android.content.Intent;
import android.widget.AdapterView;

public class FoodCategoryActivity<itemClickListener> extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_category);


//The following populates the list view with the data from the drinks array
//"this" refers to the current activity. The Activity class is a subset of Context
//simple_list_item_1 is a built in layout resource that tells the array adapter to display each array item in a single text view
//Drink.drinks is the actual array

        ArrayAdapter<Food> listAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                Food.foods);

//Create the listener and attach it to the array using the ListView setAdapter() method

        ListView listFoods = (ListView) findViewById(R.id.list_foods);
        listFoods.setAdapter(listAdapter);


//Create another OnItemClickListener

        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {

//Implement the onItemclick method, this time using the adapter

            public void onItemClick(AdapterView<?> listFoods,
                                    View itemView,
                                    int position,
                                    long id) {

//Pass the drink the user clicks on to DrinkActivity
//When a user clicks a drink its ID is passed to DrinkActivity
//We add DrinkActivity next so ignore the does not exist message

                Intent intent = new Intent(FoodCategoryActivity.this,
                        FoodActivity.class);
                intent.putExtra(FoodActivity.EXTRA_FOODID, (int) id);
                startActivity(intent);
            }
        };

//Assign the listener to the list view

        listFoods.setOnItemClickListener(itemClickListener);
    }
}