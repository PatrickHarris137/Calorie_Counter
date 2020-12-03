package com.example.caloriecounter.model;

public class MacroNutrient {

    private int id;
    private double protein;
    private double fiber;
    private double sugar;
    private double unsaturatedFat;
    private double saturatedFat;
    private double trans_fat;
    private double cholesterol;
    private double sodium;

    public MacroNutrient() {

    }

    public MacroNutrient(double protein, double fiber, double sugar, double unsaturatedFat, double saturatedFat, double trans_fat, double cholesterol, double sodium) {
        this.protein = protein;
        this.fiber = fiber;
        this.sugar = sugar;
        this.unsaturatedFat = unsaturatedFat;
        this.saturatedFat = saturatedFat;
        this.trans_fat = trans_fat;
        this.cholesterol = cholesterol;
        this.sodium = sodium;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getFiber() {
        return fiber;
    }

    public void setFiber(double fiber) {
        this.fiber = fiber;
    }

    public double getSugar() {
        return sugar;
    }

    public void setSugar(double sugar) {
        this.sugar = sugar;
    }

    public double getUnsaturatedFat() {
        return unsaturatedFat;
    }

    public void setUnsaturatedFat(double unsaturatedFat) {
        this.unsaturatedFat = unsaturatedFat;
    }

    public double getSaturatedFat() {
        return saturatedFat;
    }

    public void setSaturatedFat(double saturatedFat) {
        this.saturatedFat = saturatedFat;
    }

    public double getTrans_fat() {
        return trans_fat;
    }

    public void setTrans_fat(double trans_fat) {
        this.trans_fat = trans_fat;
    }

    public double getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(double cholesterol) {
        this.cholesterol = cholesterol;
    }

    public double getSodium() {
        return sodium;
    }

    public void setSodium(double sodium) {
        this.sodium = sodium;
    }




}
