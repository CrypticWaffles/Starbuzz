package com.prog.starbuzz;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

//These are new imports that are needed

import android.widget.Toast;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;


public class DrinkActivity extends Activity {
    public static final String EXTRA_DRINKID = "drinkId";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);


//Get the drink from the intent

        int drinkId = (Integer)getIntent().getExtras().get(EXTRA_DRINKID);    //EXTRA_DRINKID is the ID of the drink the user selected

        //       Drink drink = Drink.drinks[drinkId];   We're no longer getting our data from the drinks array, so this can be deleted.




        SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(this);
        try {
            SQLiteDatabase db = starbuzzDatabaseHelper.getReadableDatabase();


            //Create a cursor that gets the NAME, DESCRIPTION, and IMAGE_RESOURCE_ID from the DRINK table where the _id of the user selection matches the drinkId in the database table.

            Cursor cursor = db.query ("DRINK",
                    new String[] {"NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID"},
                    "_id = ?",
                    new String[] {Integer.toString(drinkId)},

//The nulls are for filtering and ordering for more complex sql queries

                    null, null, null);

            //Move to the first record in the Cursor

            if (cursor.moveToFirst()) {


//Get the drink details from the cursor
//The order here Name, Desc, image is because we told the cursor to use this order

                String nameText = cursor.getString(0);
                String descriptionText = cursor.getString(1);
                int photoId = cursor.getInt(2);

//Populate the drink name

                TextView name = (TextView)findViewById(R.id.name);

//        name.setText(drink.getName()); We're no longer getting our data from the drinks array, so this can be deleted.

                name.setText(nameText);  //Use the drink name from the database


//Populate the drink description

                TextView description = (TextView)findViewById(R.id.description);

//       description.setText(drink.getDescription());  We're no longer getting our data from the drinks array, so this can be deleted.

                description.setText(descriptionText);  //Use the description from the database



//Populate the drink image

                ImageView photo = (ImageView)findViewById(R.id.photo);
//        photo.setImageResource(drink.getImageResourceId());   We're no longer getting our data from the drinks array, so this can be deleted.
//        photo.setContentDescription(drink.getName());   We're no longer getting our data from the drinks array, so this can be deleted.
                photo.setImageResource(photoId);  //Use the image info from the database
                photo.setContentDescription(nameText);  //Use the image description from the database
            }

            cursor.close();    //Close the Cursor
            db.close();        //Close the database


// If an SQL exception is thrown, there is a problem with the database.
//Display a toast message to the user.

        } catch(SQLiteException e) {
            Toast toast = Toast.makeText(this,
                    "Database unavailable",
                    Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}