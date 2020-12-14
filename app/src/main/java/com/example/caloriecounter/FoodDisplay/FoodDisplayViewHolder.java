package com.example.caloriecounter.FoodDisplay;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caloriecounter.model.DatabaseHandler;
import com.example.caloriecounter.model.MacroNutrient;
import com.example.caloriecounter.model.food_Item;
import com.example.caloriecounter.R;
import com.example.caloriecounter.sqlite.DatabaseException;

import java.util.List;

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
    private DatabaseHandler dbh;
    private List<MacroNutrient> macroNutrientList;
    public FoodDisplayViewHolder(@NonNull View root) {
        super(root);

        this.root = root;
        dbh= new DatabaseHandler(root.getContext());
        try{
            macroNutrientList=dbh.get_Macro_Nutrient_Table().readAll();
        }
        catch (Exception e){

        }
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
        long id=food.getMacroNutrient_Id();
        foodNameTextView.setText(food.getName());
        foodCategoryTextView.setText(food.getCategory().name());
        foodServingSizeTextView.setText(Integer.toString(food.getServing_Size()) + " serving size");
        foodCaloriesTextView.setText(Integer.toString(food.getCalories())+" calories");


        foodProteinTextView.setText(Double.toString(macroNutrientList.get((int)id-1).getProtein()));
        foodFiberTextView.setText(Double.toString(macroNutrientList.get((int)id-1).getFiber()));
        foodSugarTextView.setText(Double.toString(macroNutrientList.get((int)id-1).getSugar()));
        foodUnsaturatedTextView.setText(Double.toString(macroNutrientList.get((int)id-1).getUnsaturatedFat()));
        foodSaturatedTextView.setText(Double.toString(macroNutrientList.get((int)id-1).getSaturatedFat()));
        foodTransTextView.setText(Double.toString(macroNutrientList.get((int)id-1).getTrans_fat()));
        foodCholesterolTextView.setText(Double.toString(macroNutrientList.get((int)id-1).getCholesterol()));
        foodSodiumTextView.setText(Double.toString(macroNutrientList.get((int)id-1).getSodium()));
    }

}
