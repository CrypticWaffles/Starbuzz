package com.prog.starbuzz;

public class Food {
    private String name;
    private String description;
    private int imageResourceId;


//To get us started, drinks is an array of three Drinks.  R.drawable.. are file paths to the image files

    public static final Food[] foods = {
            new Food("Banana Bread", "A slice of nut filled banana bread",
                    R.drawable.latte),
            new Food("Cookie", "A freshly Baked Chocolate chip cookie",
                    R.drawable.cappuccino),
            new Food("muffin", "A tasty blue berry filled muffin",
                    R.drawable.filter)
    };


//This is the Drink constructor

    private Food(String name, String description, int imageResourceId) {
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
