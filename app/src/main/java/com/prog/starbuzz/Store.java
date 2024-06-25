package com.prog.starbuzz;

public class Store {
    private String name;
    private String description;
    private int imageResourceId;


//To get us started, drinks is an array of three Drinks.  R.drawable.. are file paths to the image files

    public static final Store[] stores = {
            new Store("123 street store", "5.0 star store with excellent reviews",
                    R.drawable.latte),
            new Store("789 Place Street store", "4.5 star store",
                    R.drawable.cappuccino),
            new Store("456 That war Dr NW", "3.0 mid ranked store with mixed reviews",
                    R.drawable.filter)
    };


//This is the Drink constructor

    private Store(String name, String description, int imageResourceId) {
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
