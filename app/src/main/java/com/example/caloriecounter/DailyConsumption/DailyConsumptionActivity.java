package com.example.caloriecounter.DailyConsumption;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.caloriecounter.FoodDisplay.FoodDisplayActivity;
import com.example.caloriecounter.model.DatabaseHandler;
import com.example.caloriecounter.WeeklyProgression.WeeklyProgressionActivity;
import com.example.caloriecounter.model.SampleData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.example.caloriecounter.model.food_Item;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import com.example.caloriecounter.R;

import java.time.LocalDate;
import java.util.Date;

public class DailyConsumptionActivity extends AppCompatActivity {
    //Fields
    //Date to be used for a user daily consumption
    public String date;
    //User id thats needed to query local db
    public long userId;
    public String day;
    private DailyConsumptionFragment dailyConsumptionFragment;

    //Getters
    //Gets user id from daily macronutrient
    public long getUserId() {
        Intent intent = getIntent();
        userId=intent.getLongExtra("userId",1);
        return userId;
    }
    //Gets date from daily macronutrient
    public String getDate(){
        Intent intent = getIntent();
        date=intent.getStringExtra("date");
        return date;
    }
    public String getToday(){
        Intent intent = getIntent();
        day=intent.getStringExtra("day");
        return day;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_consumption);
        dailyConsumptionFragment = (DailyConsumptionFragment) getSupportFragmentManager().findFragmentById(R.id.fragment2);
        FloatingActionButton addFood_FAB = findViewById(R.id.addFood_FAB);
        FloatingActionButton dailyFoodReturn=findViewById(R.id.dailyFoodReturn_FAB);
        FloatingActionButton weekly_FAB=findViewById(R.id.WeeklyFAB);

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

                Intent intent = getIntent();
                if(date!=null)
                    intent.putExtra("date",date);
                intent.putExtra("day",day);
                setResult(RESULT_OK, intent);
                finish();

            }
        });
        weekly_FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Activity activity =(Activity) view.getContext();
                Intent intent = new Intent(activity, WeeklyProgressionActivity.class);
                intent.putExtra("userId",dailyConsumptionFragment.getUserId());
                intent.putExtra("day",day);
                activity.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        food_Item food;
        if(resultCode==Activity.RESULT_OK) {
            String returnType = data.getStringExtra("returnType");
            if (returnType != null) {
                if (returnType.equals("FoodDisplay")) {
                    food = data.getParcelableExtra("foodToAdd");
                    dailyConsumptionFragment.addFoodToMeal(food);

                }
                else if (returnType.equals("WeeklyProgression")) {
                    day=data.getStringExtra("day");
                    dailyConsumptionFragment.setDayOfWeek(data.getStringExtra("day"));
                    date=data.getStringExtra("date");
                    dailyConsumptionFragment.updateDay(data.getStringExtra("date"));
                }
            }
        }
    }
}