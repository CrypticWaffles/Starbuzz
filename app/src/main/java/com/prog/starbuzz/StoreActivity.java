package com.prog.starbuzz;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
//SQLite imports
import android.widget.Toast;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class StoreActivity extends Activity {
    public static final String EXTRA_STOREID = "storeId";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        
        //Get the drink from the intent
        int storeId = (Integer)getIntent().getExtras().get(EXTRA_STOREID);    //EXTRA_DRINKID is the ID of the drink the user selected

        SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(this);
        try {
            SQLiteDatabase db = starbuzzDatabaseHelper.getReadableDatabase();
            //Create a cursor to get name, desc, & image
            Cursor cursor = db.query ("STORE",
                    new String[] {"NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID"},
                    "_id = ?",
                    new String[] {Integer.toString(storeId)},
                    //The nulls for filtering
                    null, null, null);
            //Move Cursor
            if (cursor.moveToFirst()) {
                //Get details
                String nameText = cursor.getString(0);
                String descText = cursor.getString(1);
                int photoId = cursor.getInt(2);

                //Populate name
                TextView name = (TextView)findViewById(R.id.name);
                name.setText(nameText);
                
                //Populate the drink description
                TextView description = (TextView)findViewById(R.id.description);
                description.setText(descText);  //Use the description from the database

                //Populate image
                ImageView photo = (ImageView)findViewById(R.id.photo);
                photo.setImageResource(photoId);
                photo.setContentDescription(nameText);
            cursor.close();
            db.close();
            }
        }
        catch(SQLiteException e) {
            Toast toast = Toast.makeText(this,
                    "Database unavailable",
                    Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}