package ca.qc.johnabbott.cs616.server.model;

import javax.persistence.*;

@Entity
@Table(name = "macronutrient")
public class MacroNutrient {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "protein")
    private double protein;

    @Column(name = "fiber")
    private double fiber;

    @Column(name = "sugar")
    private double sugar;

    @Column(name = "unsaturatedfat")
    private double unsaturatedFat;

    @Column(name = "saturatedfat")
    private double saturatedFat;

    @Column(name = "transfat")
    private double transFat;

    @Column(name = "cholesterol")
    private double cholesterol;

    @Column(name = "sodium")
    private double sodium;

    @OneToOne
    @JoinColumn(name = "fooditem")
    private FoodItem foodItem;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public double getTransFat() {
        return transFat;
    }

    public void setTransFat(double transFat) {
        this.transFat = transFat;
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

    public FoodItem getFoodItem() {
        return foodItem;
    }

    public void setFoodItem(FoodItem foodItem) {
        this.foodItem = foodItem;
    }
}
