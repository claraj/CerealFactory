package com.company;

import java.util.HashMap;


public class Cereal {

    String name;
    HashMap<Ingredient, Double> ingredients;

    public Cereal(String name, HashMap<Ingredient, Double> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    public double getQuantityOfIngredient(Ingredient i){
        if (ingredients.containsKey(i)) {
            return ingredients.get(i);
        } else {
            return 0;
        }
    }


    public String getName() {

        return name;
    }
}
