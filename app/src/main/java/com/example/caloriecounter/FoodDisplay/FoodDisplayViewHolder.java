package com.example.caloriecounter.FoodDisplay;

import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caloriecounter.model.food_Item;
import com.example.caloriecounter.R;

public class FoodDisplayViewHolder  extends RecyclerView.ViewHolder{

    private View root;
    private TextView foodNameTextView;
    private TextView foodCategoryTextView;
    private TextView foodServingSizeTextView;
    private TextView foodCaloriesTextView;

    private TextView foodProteinTextView;
    private TextView foodFiberTextView;
    private TextView foodSugarTextView;
    private TextView foodUnsaturatedTextView;
    private TextView foodSaturatedTextView;
    private TextView foodTransTextView;
    private TextView foodCholesterolTextView;
    private TextView foodSodiumTextView;


    private food_Item food;

    public FoodDisplayViewHolder(@NonNull View root) {
        super(root);

        this.root = root;

        foodNameTextView=root.findViewById(R.id.foodName_TextView);
        foodCategoryTextView=root.findViewById(R.id.foodCategory_TextView);
        foodServingSizeTextView=root.findViewById(R.id.foodServingSize_TextView);
        foodCaloriesTextView=root.findViewById(R.id.foodCalories_TextView);

        foodProteinTextView=root.findViewById(R.id.foodProtein_TextView);
        foodFiberTextView=root.findViewById(R.id.foodFiber_TextView);
        foodSugarTextView=root.findViewById(R.id.foodSugar_TextView);
        foodUnsaturatedTextView=root.findViewById(R.id.foodUnsaturated_TextView);
        foodSaturatedTextView=root.findViewById(R.id.foodSaturated_TextView);
        foodTransTextView=root.findViewById(R.id.foodTrans_TextView);
        foodCholesterolTextView=root.findViewById(R.id.foodCholesterol_TextView);
        foodSodiumTextView=root.findViewById(R.id.foodSodium_TextView);

    }


    public void set(food_Item food){

        this.food=food;

        foodNameTextView.setText(food.getName());
        foodCategoryTextView.setText(food.getCategory().name());
        foodServingSizeTextView.setText(Integer.toString(food.getServing_Size()) + " serving size");
        foodCaloriesTextView.setText(Integer.toString(food.getCalories())+" calories");


        foodProteinTextView.setText(Double.toString(food.getMacroNutrient().getProtein()));
        foodFiberTextView.setText(Double.toString(food.getMacroNutrient().getFiber()));
        foodSugarTextView.setText(Double.toString(food.getMacroNutrient().getSugar()));
        foodUnsaturatedTextView.setText(Double.toString(food.getMacroNutrient().getUnsaturatedFat()));
        foodSaturatedTextView.setText(Double.toString(food.getMacroNutrient().getSaturatedFat()));
        foodTransTextView.setText(Double.toString(food.getMacroNutrient().getTrans_fat()));
        foodCholesterolTextView.setText(Double.toString(food.getMacroNutrient().getCholesterol()));
        foodSodiumTextView.setText(Double.toString(food.getMacroNutrient().getSodium()));
    }

}
