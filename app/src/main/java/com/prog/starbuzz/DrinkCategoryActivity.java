package com.prog.starbuzz;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.View;
import android.content.Intent;
import android.widget.AdapterView;

//Imports needed to support the new code

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class DrinkCategoryActivity<itemClickListener> extends Activity {


//These are used to close the db and cursor with the onDestroy() method

    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_category);


//The following populates the list view with the data from the drinks array
//"this" refers to the current activity. The Activity class is a subset of Context
//simple_list_item_1 is a built in layout resource that tells the array adapter to display each array item in a single text view
//Drink.drinks is the actual array

//        ArrayAdapter<Drink> listAdapter = new ArrayAdapter<>(     No longer using the array adapter so delete these
//                this,
//                android.R.layout.simple_list_item_1,
//                Drink.drinks);


//Create the listener and attach it to the array using the ListView setAdapter() method

        ListView listDrinks = (ListView) findViewById(R.id.list_drinks);


//Get a reference to the database

        SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(this);
        try {
            db = starbuzzDatabaseHelper.getReadableDatabase();


//Create the Cursor

            cursor = db.query("DRINK",
                    new String[]{"_id", "NAME"},
                    null, null, null, null, null);

//Create the Cursor Adapter

            SimpleCursorAdapter listAdapter = new SimpleCursorAdapter(this,
                    android.R.layout.simple_list_item_1,
                    cursor,


//Map the contents of the NAME column to the text in the ListView

                    new String[]{"NAME"},
                    new int[]{android.R.id.text1},
                    0);


//Set the Adapater to the ListView

            listDrinks.setAdapter(listAdapter);

        } catch(SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }


//None of the listener code needs to be changed
//Create another OnItemClickListener

        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {

//Implement the onItemclick method, this time using the adapter

            public void onItemClick(AdapterView<?> listDrinks,
                                    View itemView,
                                    int position,
                                    long id) {

//Pass the drink the user clicks on to DrinkActivity
//When a user clicks a drink its ID is passed to DrinkActivity
//We add DrinkActivity next so ignore the does not exist message

                Intent intent = new Intent(DrinkCategoryActivity.this,
                        DrinkActivity.class);
                intent.putExtra(DrinkActivity.EXTRA_DRINKID, (int) id);
                startActivity(intent);
            }
        };

//Assign the listener to the list view

        listDrinks.setOnItemClickListener(itemClickListener);
    }

//End preserved listener code


//Close the database and cursor in the activity's onDestroy() method
//The cursor remains open until the cursor adapter no longer needs it

    @Override
    public void onDestroy(){
        super.onDestroy();
        cursor.close();
        db.close();
    }
}