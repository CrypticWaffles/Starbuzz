package com.prog.starbuzz;
import android.content.ContentValues;
import android.content.Context;

import android.database.sqlite.SQLiteDatabase; //This is the full path to the SQLiteOpenHelper class
import android.database.sqlite.SQLiteOpenHelper;


//SQLite helpers for our app's database extend the SQLiteHelper class

class StarbuzzDatabaseHelper extends SQLiteOpenHelper {


    private static final String DB_NAME = "starbuzz"; // the name of our database.

// With no name, databases are held in memory only and are not Persistent.


    private static final int DB_VERSION = 1; // The version of the database

// Version control is important.  We typically start at 1.


    StarbuzzDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);  //call to the SQLiteHelper superclass and passing it the DB Name and Version
    }

// null is an advanced feature that is related to Cursors



//onCreate and onUpdate methods are mandatory to use.

    @Override
    public void onCreate(SQLiteDatabase db){
        updateMyDatabase(db, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDatabase(db, oldVersion, newVersion);
    }


//We need to insert multiple records, so we create a method and pass the values to be inserted to the method as parameters

    private static void insertDrink(SQLiteDatabase db, String name,
                                    String description, int resourceId) {

// Construct a ContentValues object with the values are to be inserted into the database

        ContentValues drinkValues = new ContentValues();

//NAME, DESCRIPTION, and IMAGE_RESOURCE_ID are the columns to be used

        drinkValues.put("NAME", name);
        drinkValues.put("DESCRIPTION", description);
        drinkValues.put("IMAGE_RESOURCE_ID", resourceId);

//Now insert the values

        db.insert("DRINK", null, drinkValues);
    }

    //Addition methods for Stores & Foods added
    private static void insertFood(SQLiteDatabase db, String name, String description, int resourceId) {
        // Construct a ContentValues object with the values are to be inserted into the database
        ContentValues foodValues = new ContentValues();

        //NAME, DESCRIPTION, and IMAGE_RESOURCE_ID are the columns to be used
        foodValues.put("NAME", name);
        foodValues.put("DESCRIPTION", description);
        foodValues.put("IMAGE_RESOURCE_ID", resourceId);

        //Now insert the values
        db.insert("FOOD", null, foodValues);
    }

    private static void insertStore(SQLiteDatabase db, String name, String description, int resourceId) {
        // Construct a ContentValues object with the values are to be inserted into the database
        ContentValues storeValues = new ContentValues();

        //NAME, DESCRIPTION, and IMAGE_RESOURCE_ID are the columns to be used
        storeValues.put("NAME", name);
        storeValues.put("DESCRIPTION", description);
        storeValues.put("IMAGE_RESOURCE_ID", resourceId);

        //Now insert the values
        db.insert("STORE", null, storeValues);
    }


//If the database does not exist, create it

    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 1) {


//All applications that communicate with SQLite must use SQL language

            db.execSQL("CREATE TABLE DRINK (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT, "
                    + "DESCRIPTION TEXT, "
                    + "IMAGE_RESOURCE_ID INTEGER);");

            //Added Food & Store tables to database
            db.execSQL("CREATE TABLE FOOD (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT, "
                    + "DESCRIPTION TEXT, "
                    + "IMAGE_RESOURCE_ID INTEGER);");

            db.execSQL("CREATE TABLE STORE (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT, "
                    + "DESCRIPTION TEXT, "
                    + "IMAGE_RESOURCE_ID INTEGER);");


//Insert the actual values we will extract, display, and update into the database table created above
//To insert three rows, call the method three times
//This is where modifications are made to change what is displayed on the screen

            insertDrink(db, "Latte", "Espresso and steamed milk, I don't really like it", R.drawable.latte);
            insertDrink(db, "Cappuccino", "Espresso, hot milk and steamed-milk foam, I also don't really like it", R.drawable.cappuccino);
            insertDrink(db, "Filter", "Our best drip coffee, I just don't really like coffee in general", R.drawable.filter);

            insertFood(db, "Banana Bread", "A slice of nut filled banana bread", R.drawable.latte);
            insertFood(db, "Cookie", "A freshly Baked Chocolate chip cookie", R.drawable.cappuccino);
            insertFood(db, "muffin", "A tasty blue berry filled muffin", R.drawable.filter);

            insertStore(db, "123 street store", "5.0 star store with excellent reviews", R.drawable.latte);
            insertStore(db, "789 Place Street store", "4.5 star store with mostly positive reviews", R.drawable.cappuccino);
            insertStore(db, "456 That war Dr NW", "3.0 mid ranked store with mixed reviews", R.drawable.filter);
        }

//If this version of the app uses new features that require an update to the database, make the following changes

        if (oldVersion < 2) {
            db.execSQL("ALTER TABLE DRINK ADD COLUMN FAVORITE NUMERIC;");
        }
    }
}