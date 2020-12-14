package com.example.caloriecounter.DailyConsumption;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.caloriecounter.FoodDisplay.FoodDisplayActivity;
import com.example.caloriecounter.model.DatabaseHandler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import com.example.caloriecounter.R;

public class DailyConsumptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_consumption);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton addFood_FAB = findViewById(R.id.addFood_FAB);
        FloatingActionButton dailyFoodReturn=findViewById(R.id.dailyFoodReturn_FAB);
        addFood_FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Activity activity =(Activity) view.getContext();
                Intent intent = new Intent(activity, FoodDisplayActivity.class);
                activity.startActivityForResult(intent,1);
            }
        });
        dailyFoodReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}