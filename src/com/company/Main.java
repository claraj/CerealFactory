package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    static Ingredient rice, salt, sugar, corn;


    public static void main(String[] args) {

        ArrayList<Cereal> allCereals = new ArrayList<>();
        ArrayList<Ingredient> allIngredients = new ArrayList<>();

        //Create a Cereal object for each Cereal, and add to an ArrayList
        addTestIngredients(allIngredients);
        //Create an Ingredient object for each Ingredient, and add to an ArrayList
        addTestCereals(allCereals);

        Scanner scanner = new Scanner(System.in);

        //Hashmap for Cereals and number of boxes of that cereal ordered
        HashMap<Cereal, Integer>boxesOfEachCerealOrdered = new HashMap<>();

        //For each cereal, ask user for number of boxes ordered
        for (Cereal c : allCereals) {
            System.out.println("How many boxes of " + c.getName() + " to order?");
            int boxes = Integer.parseInt(scanner.nextLine()); //TODO validation
            boxesOfEachCerealOrdered.put(c, boxes);
        }

        //For every ingredient, figure out how many kilos needed.
        //For every Ingredient, look at the list of Cereals. How much of that Ingredient is
        //needed to make that many boxes of that cereal?
        //e.g. For Rice, you'll need 400g for every box of Rice Crunchies ordered and 200g for every box of Rice Crunchies
        HashMap<Ingredient, Double> kilogramsOfEachIngredient = new HashMap<>();

        System.out.println("\n* Calculating amount of each ingredient needed *\n");

        for (Ingredient ingredient : allIngredients) {

            double weightOfIngredient = 0;

            for (Cereal cereal : allCereals) {
                //How much of this ingredient in ONE BOX of this cereal?
                double kilosForOneBoxOfThisCereal = cereal.getQuantityOfIngredient(ingredient);
                //We need to make how many boxes of this cereal?
                int boxes = boxesOfEachCerealOrdered.get(cereal);

                double totalWeightForAllBoxesOfThisCereal = kilosForOneBoxOfThisCereal * boxes;

                weightOfIngredient += totalWeightForAllBoxesOfThisCereal;

            }

            kilogramsOfEachIngredient.put(ingredient, weightOfIngredient);

            System.out.println("You will need to buy " + weightOfIngredient + " kilos of " + ingredient.name + " to make all the cereals in this order");

        }

        //Now we know the weight of each ingredient needed.
        //Add up the total cost of all of the ingredients needed.
        //This is where the price levels are needed.

        System.out.println("\n* Calculating price for each ingredient *\n");

        double totalCostOfAllIngredients = 0;

        for (Ingredient i : kilogramsOfEachIngredient.keySet()) {

            double kilogramsOfThisIngredient = kilogramsOfEachIngredient.get(i);
            double price = i.getPriceForQuantity(kilogramsOfThisIngredient);
            totalCostOfAllIngredients += price;

            System.out.println(kilogramsOfThisIngredient  + " kilograms of " + i.name + " will cost $" + price);

        }


        System.out.println("\n* Grand total *\n");

        System.out.println("All of the ingredients will cost $" + totalCostOfAllIngredients);

    }

    private static void addTestIngredients(ArrayList<Ingredient> allIngredients) {

        rice = new Ingredient("rice");
        rice.addPriceLevel(100, 0.50);
        rice.addPriceLevel(500, 0.43);
        rice.addDefaultPriceLevel(0.30);
        allIngredients.add(rice);

        corn = new Ingredient("corn");
        corn.addPriceLevel(80, 0.50);
        corn.addDefaultPriceLevel(0.41);
        allIngredients.add(corn);


        sugar = new Ingredient("sugar");
        sugar.addPriceLevel(200, 1.15);
        sugar.addDefaultPriceLevel(0.95);
        allIngredients.add(sugar);

        salt = new Ingredient("salt");
        salt.addDefaultPriceLevel(0.70);
        allIngredients.add(salt);

    }

    private static void addTestCereals(ArrayList<Cereal> allCereals) {
        //Add TEST data
        //TODO real program - replace with method to read in from file or database or ask user


        HashMap<Ingredient, Double> triangleIngredients = new HashMap<>();
        triangleIngredients.put(corn, 0.200);
        triangleIngredients.put(rice, 0.200);
        triangleIngredients.put(sugar, 0.0350);
        triangleIngredients.put(salt, 0.0005);  //Work in KILOS
        Cereal trianglz = new Cereal("Trianglz", triangleIngredients);
        allCereals.add(trianglz);   //Pass by reference means that main's cereal picks up this change
        //Repeat for other cereals

        HashMap<Ingredient, Double> riceCrunchyIngredients = new HashMap<>();
        riceCrunchyIngredients.put(rice, 0.400);
        riceCrunchyIngredients.put(sugar, 0.020);
        riceCrunchyIngredients.put(salt, 0.001);  //Work in KILOS
        Cereal riceCrunchy = new Cereal("Rice Crunchies", riceCrunchyIngredients);
        allCereals.add(riceCrunchy);   //Pass by reference means that main's cereal picks up this change

        HashMap<Ingredient, Double> morningFlakesIngredients = new HashMap<>();
        morningFlakesIngredients.put(corn, 0.350);
        morningFlakesIngredients.put(sugar, 0.040);
        morningFlakesIngredients.put(salt, 0.001);  //Work in KILOS
        Cereal morningFlakes = new Cereal("Morning Flakes", morningFlakesIngredients);
        allCereals.add(morningFlakes);   //Pass by reference means that main's cereal picks up this change



    }
}
