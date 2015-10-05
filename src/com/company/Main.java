package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    static Ingredient rice, salt, sugar, corn;


    public static void main(String[] args) {

        ArrayList<Cereal> allCereals = new ArrayList<>();
        ArrayList<Ingredient> allIngredients = new ArrayList<>();

        addTestIngredients(allIngredients);
        addTestCereals(allCereals);

        Scanner scanner = new Scanner(System.in);
        double totalCost = 0;

        HashMap<Cereal, Integer>boxesOfEachCerealOrdered = new HashMap<>();

        //For each cereal, ask user for number of boxes ordered
        for (Cereal c : allCereals) {
            System.out.println("How many boxes of " + c.getName() + " to order?");
            int boxes = Integer.parseInt(scanner.nextLine()); //TODO validation
            boxesOfEachCerealOrdered.put(c, boxes);
        }

        HashMap<Ingredient, Double> kilogramsOfEachIngredient = new HashMap<>();

        for (Ingredient i : allIngredients) {
            //For each ingredient, how much is needed for each cereal?

            double weightOfIngredient = 0;

            System.out.println("Calculating quantities for " + i.name);

            for (Cereal c : allCereals) {

                //How much i in c?
                double howManyKilosForThisCereal = c.getQuantityOfIngredient(i);
                //We need to make how many boxes?
                int boxes = boxesOfEachCerealOrdered.get(c);

                double totalWeightForAllBoxesOfThisCereal = howManyKilosForThisCereal * boxes;

                weightOfIngredient += totalWeightForAllBoxesOfThisCereal;

            }

            kilogramsOfEachIngredient.put(i, weightOfIngredient);

            System.out.println("The ingredient " + i.name + " requires " + weightOfIngredient + " kilos to make all the cereals in this order");


        }

        //For ever

        double totalCostOfAllIngredients = 0;

        for (Ingredient i : kilogramsOfEachIngredient.keySet()) {


            double kilogramsOfThisIngredient = kilogramsOfEachIngredient.get(i);
            double price = i.getPriceForQuantity(kilogramsOfThisIngredient);
            totalCostOfAllIngredients += price;

            System.out.println("You will need to buy " + kilogramsOfThisIngredient  + " kilograms of " + i.name + " at a cost of $" + price);

        }

        System.out.println("The ingredients will cost " + totalCostOfAllIngredients);

    }

    private static void addTestIngredients(ArrayList<Ingredient> allIngredients) {

        rice = new Ingredient("Rice");
        rice.addPriceLevel(100, 0.50);
        rice.addPriceLevel(500, 0.43);
        rice.addDefaultPriceLevel(0.30);
        allIngredients.add(rice);

        corn = new Ingredient("Corn");
        corn.addPriceLevel(80, 0.50);
        corn.addDefaultPriceLevel(0.41);
        allIngredients.add(corn);


        sugar = new Ingredient("Sugar");
        sugar.addPriceLevel(200, 1.15);
        sugar.addDefaultPriceLevel(0.95);
        allIngredients.add(sugar);

        salt = new Ingredient("Salt");
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
