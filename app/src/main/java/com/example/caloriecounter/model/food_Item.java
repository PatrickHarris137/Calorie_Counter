package com.example.caloriecounter.model;

import java.util.UUID;

public class food_Item {
    private int id;
    private String UUID;
    private String name;
    private Category category;
    private int serving_Size;
    private int calories;
    private MacroNutrient macroNutrient;

    //Constructor
    public food_Item(String name,Category category, int serving_Size, int calories,MacroNutrient macroNutrient) {
        this.UUID= java.util.UUID.randomUUID().toString();
        this.name = name;
        this.category=category;
        this.serving_Size = serving_Size;
        this.calories = calories;
        this.macroNutrient=macroNutrient;
    }

    //Setters and Getters
    public int getId() {return id; }

    public void setId(int id) {this.id = id; }

    public String getUUID() {return UUID; }

    public void setUUID(String UUID) {this.UUID = UUID; }

    public String getName() {return name;}

    public void setName(String name) { this.name = name; }

    public Category getCategory() { return category;  }

    public void setCategory(Category category) {this.category = category;}

    public int getServing_Size() {return serving_Size;}

    public void setServing_Size(int serving_Size) {this.serving_Size = serving_Size;}

    public int getCalories() {return calories;}

    public void setCalories(int calories) {this.calories = calories;}

    public MacroNutrient getMacroNutrient() {return macroNutrient;}

    public void setMacroNutrient(MacroNutrient macroNutrient) {this.macroNutrient = macroNutrient;}
}
