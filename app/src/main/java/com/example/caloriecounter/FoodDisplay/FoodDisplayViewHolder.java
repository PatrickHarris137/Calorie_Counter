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
    private TextView foodCaloriesSizeTextView;


    private food_Item food;

    public FoodDisplayViewHolder(@NonNull View root) {
        super(root);

        this.root = root;

        foodNameTextView=root.findViewById(R.id.foodName_TextView);



    }


    public void set(food_Item food){

        this.food=food;

        foodNameTextView.setText(food.getName());

    }

}
