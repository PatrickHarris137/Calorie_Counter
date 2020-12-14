package com.example.caloriecounter.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SampleData {

    public static List<MacroNutrient> generateMacroNutrients(){
        List<MacroNutrient> macroNutrientDisplayList = new ArrayList<>();

        MacroNutrient tofuNutrient=new MacroNutrient(14, 1.9, 0, 0, 1, 0, 0, 16);
        MacroNutrient appleNutrient=new MacroNutrient(0.3, 2.4, 10.4, 0, 0, 0, 0, 0);
        MacroNutrient steakNutrient=new MacroNutrient(23, 0, 0, 1, 1, 0, 0, 0);
        MacroNutrient chickenNutrient=new MacroNutrient(13, 0, 0, 1, 0, 0, 45, 270);
        MacroNutrient broccoliNutrient=new MacroNutrient(1, 1, 0, 0, 0, 0, 0, 15);
        MacroNutrient toastNutrient=new MacroNutrient(4, 1, 3, 1, 0, 0, 0, 135);
        MacroNutrient baconNutrient=new MacroNutrient(5, 0, 1, 4, 1, 0, 15, 320);
        MacroNutrient almondNutrient=new MacroNutrient(21, 12, 4, 43, 0, 0, 0, 1);
        MacroNutrient riceNutrient=new MacroNutrient(2, 0, 0, 0, 0, 0, 0, 1);
        MacroNutrient cheeseNutrient=new MacroNutrient(26, 0, 0, 9, 18, 0, 93, 187);

        macroNutrientDisplayList.add(tofuNutrient);
        macroNutrientDisplayList.add(appleNutrient);
        macroNutrientDisplayList.add(steakNutrient);
        macroNutrientDisplayList.add(chickenNutrient);
        macroNutrientDisplayList.add(broccoliNutrient);
        macroNutrientDisplayList.add(toastNutrient);
        macroNutrientDisplayList.add(baconNutrient);
        macroNutrientDisplayList.add(almondNutrient);
        macroNutrientDisplayList.add(riceNutrient);
        macroNutrientDisplayList.add(cheeseNutrient);
        return macroNutrientDisplayList;
    }
    public static List<food_Item> generateFoodDisplayList(){

        List<food_Item> foodDisplayList = new ArrayList<>();



        food_Item tofu= new food_Item("Tofu", Category.Meat, 1, 320, 1);


        food_Item apple= new food_Item("Apple", Category.Fruits, 1, 120, 2);


        food_Item steak= new food_Item("Steak", Category.Meat, 1, 510, 3);


        food_Item chicken= new food_Item("Chicken", Category.Meat, 1, 450, 4);


        food_Item broccoli= new food_Item("Broccoli", Category.Vegetables, 1, 80, 5);


        food_Item toast= new food_Item("Toast", Category.Grains, 1, 132, 6);


        food_Item bacon= new food_Item("Bacon", Category.Meat, 1, 205, 7);


        food_Item almond= new food_Item("Almond", Category.Meat, 1, 90, 8);


        food_Item rice= new food_Item("Rice", Category.Grains, 1, 220, 9);


        food_Item swissCheese= new food_Item("Swiss Cheese", Category.Dairy, 1, 150, 10);


        foodDisplayList.add(tofu);
        foodDisplayList.add(apple);
        foodDisplayList.add(steak);
        foodDisplayList.add(chicken);
        foodDisplayList.add(broccoli);
        foodDisplayList.add(toast);
        foodDisplayList.add(bacon);
        foodDisplayList.add(rice);
        foodDisplayList.add(almond);
        foodDisplayList.add(swissCheese);

        return foodDisplayList;
    }
    public static  ArrayList<food_Item> generateFoodForMeal(){
        int min=0;
        int max=9;
        int maxFoodNumber=4;
        int minFoodNumber=1;
        List<food_Item> displayList = generateFoodDisplayList();
        ArrayList<food_Item> mealList = new ArrayList<>();
        Random r= new Random();
        int maxFood=r.nextInt((maxFoodNumber - minFoodNumber) + 1) + minFoodNumber;
        for (int i = 0; i < maxFood; i++) {

            int randomFoodNumber=r.nextInt((max - min) + 1) + min;
            if(mealList.contains(displayList.get(randomFoodNumber)))
                i--;
            else
                mealList.add(displayList.get(randomFoodNumber));
        }
        return  mealList;
    }
    public static  List<ArrayList<food_Item>> generateFoodForDay(){

        List<ArrayList<food_Item>> mealsOfDayList = new ArrayList<>();

        Random r= new Random();
        for (int i = 0; i < 4; i++) {
            mealsOfDayList.add(generateFoodForMeal());
        }
        return  mealsOfDayList;
    }

}
