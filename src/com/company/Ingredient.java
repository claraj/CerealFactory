package com.company;

import java.util.ArrayList;

public class Ingredient {


    //Represents one ingredient and deals with its price structure
    //WORKING IN DOLLARS AND KILOGRAMS

    String name;   //Name of ingredient, e.g. rice, corn.

    //Constructor
    Ingredient(String name){
        this.name = name;
    }

    //Price structure - tricky! TODO improve this.

    //For example, up to 100kg of rice costs 50c per kilo
    //Up to 500kg of rice costs 43c per kilo
    //500kg or more costs 30c per kilo

    ArrayList<PriceLevel> priceLevels = new ArrayList<>();
    double defaultPriceLevel;

    public void addPriceLevel(double upToKilos, double pricePerKilo ) {
        priceLevels.add(new PriceLevel(upToKilos, pricePerKilo));
    }

    public double getPriceForQuantity(double kilograms) {

        //Look at all the price levels. If this amount of kilos is less than
        //the current price level, then multiply and return
        //TODO I don't think this works. Why, and can you fix it?
        for (PriceLevel priceLevel : priceLevels) {
            if (kilograms < priceLevel.upToKilos) {
                return (kilograms * priceLevel.pricePerKilo);
            }
        }

        //If not found, return pricing for default price level
        return (kilograms * defaultPriceLevel);
    }

    public void addDefaultPriceLevel(double defaultLevel) {
        defaultPriceLevel = defaultLevel;

    }


    //Another class!
    class PriceLevel {
        PriceLevel(double upToKilos, double pricePerKilo) {
            this.upToKilos = upToKilos;
            this.pricePerKilo = pricePerKilo;
        }

        double upToKilos;
        double pricePerKilo;

    }



}
