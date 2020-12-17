package com.example.caloriecounter.FoodDisplay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.caloriecounter.DailyConsumption.DailyConsumptionActivity;
import com.example.caloriecounter.DailyMacroCounter.DailyMacroCounterActivity;
import com.example.caloriecounter.WeeklyProgression.WeeklyProgressionActivity;
import com.example.caloriecounter.model.Category;
import com.example.caloriecounter.model.MacroNutrient;
import com.example.caloriecounter.model.food_Item;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.caloriecounter.R;

public class AddFoodItemActivity extends AppCompatActivity {

    private AddFoodItemFragment addFoodItemFragment;
    private Button addFoodSubmitButton;
    private FloatingActionButton testButton;


    private food_Item newFood;
    private MacroNutrient newMacro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food_item);
        Toolbar toolbar = findViewById(R.id.toolbar);

        addFoodItemFragment=(AddFoodItemFragment)getSupportFragmentManager().findFragmentById(R.id.addFoodItem_Fragment);

        Intent intent=getIntent();
        newFood=intent.getParcelableExtra("newFood");
        newMacro=intent.getParcelableExtra("newMacro");
        String method=intent.getStringExtra("method");

        addFoodItemFragment.setNewFood(newFood, method);
        addFoodItemFragment.setNewMacro(newMacro,method);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}