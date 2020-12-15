package com.example.caloriecounter.DailyConsumption;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.caloriecounter.FoodDisplay.FoodDisplayActivity;
import com.example.caloriecounter.model.DatabaseHandler;
import com.example.caloriecounter.WeeklyProgression.WeeklyProgressionActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import com.example.caloriecounter.R;

import java.time.LocalDate;
import java.util.Date;

public class DailyConsumptionActivity extends AppCompatActivity {
    public String date;
    public String day;
    private DailyConsumptionFragment dailyConsumptionFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_consumption);
        Toolbar toolbar = findViewById(R.id.toolbar);
        dailyConsumptionFragment = (DailyConsumptionFragment) getSupportFragmentManager().findFragmentById(R.id.fragment2);
        setSupportActionBar(toolbar);
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
                    intent.putExtra("date",date.toString());
                finish();

            }
        });
        weekly_FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Activity activity =(Activity) view.getContext();
                Intent intent = new Intent(activity, WeeklyProgressionActivity.class);
                activity.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String returnType=data.getStringExtra("returnType");
        if(returnType!=null){
                dailyConsumptionFragment.setDayOfWeek(data.getStringExtra("day"));
                date =data.getStringExtra("date");

        }
    }
}