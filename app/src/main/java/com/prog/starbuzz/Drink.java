package com.prog.starbuzz;

// Attributes of the class Drink.  The imageResourceId point to pictures that we'll add in the next step

public class Drink {
    private String name;
    private String description;
    private int imageResourceId;


//To get us started, drinks is an array of three Drinks.  R.drawable.. are file paths to the image files

    public static final Drink[] drinks = {
            new Drink("Latte", "A couple of espresso shots with steamed milk",
                    R.drawable.latte),
            new Drink("Cappuccino", "Espresso, hot milk, and a steamed milk foam",
                    R.drawable.cappuccino),
            new Drink("Filter", "Highest quality beans roasted and brewed fresh",
                    R.drawable.filter)
    };


//This is the Drink constructor

    private Drink(String name, String description, int imageResourceId) {
        this.name = name;
        this.description = description;
        this.imageResourceId = imageResourceId;
    }


// these get the the values for the private variables

    public String getDescription() {
        return description;
    }
    public String getName() {
        return name;
    }
    public int getImageResourceId() {
        return imageResourceId;
    }

//The string representation of each drink is its name

    public String toString() {
        return this.name;
    }
}
