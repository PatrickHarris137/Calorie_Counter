package com.example.caloriecounter.DailyConsumption;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.caloriecounter.R;
import com.example.caloriecounter.model.SampleData;
import com.example.caloriecounter.model.food_Item;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DailyConsumptionFragment extends Fragment {
    public TextView dayOfWeek;
    public TextView dailyCalorieIntake;
    private View root;
    private Button breakfast_Button;
    private Button lunch_Button;
    private Button dinner_Button;
    private Button snacks_Button;
    private List<ArrayList<food_Item>> mealsOfDayList;
    private RecyclerView foodRecyclerView;
    private DailyConsumptionAdapter adapter;
    private DailyConsumptionFragment dailyConsumptionFragment;
    private int maximumDailyCalories;
    private TextView dailyCaloriePercent;

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_daily_consumption, container, false);
        dailyConsumptionFragment=this;
        dayOfWeek=root.findViewById(R.id.day_TextView);
        dayOfWeek.setText(getToday());
        dailyCaloriePercent = root.findViewById(R.id.dailyIntakePercent_TextView);
        dailyCalorieIntake=root.findViewById(R.id.dailyCalories_TextView);
        breakfast_Button=root.findViewById(R.id.breakfast_Button);
        lunch_Button=root.findViewById(R.id.lunch_Button);
        dinner_Button=root.findViewById(R.id.dinner_Button);
        snacks_Button=root.findViewById(R.id.snacks_Button);

        TextView calorie_Total=root.findViewById(R.id.dailyCalories_TextView);
        //OnclickListeners for Meal types
        breakfast_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                adapter = new DailyConsumptionAdapter(mealsOfDayList.get(0),dailyConsumptionFragment);
                foodRecyclerView.setAdapter(adapter);
                calculateTotalCalories(mealsOfDayList.get(0));
            }
        });
        lunch_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                adapter = new DailyConsumptionAdapter(mealsOfDayList.get(1),dailyConsumptionFragment);
                foodRecyclerView.setAdapter(adapter);
                calculateTotalCalories(mealsOfDayList.get(1));
            }
        });
        dinner_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                adapter = new DailyConsumptionAdapter(mealsOfDayList.get(2),dailyConsumptionFragment);
                foodRecyclerView.setAdapter(adapter);
                calculateTotalCalories(mealsOfDayList.get(2));
            }
        });
        snacks_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                adapter = new DailyConsumptionAdapter(mealsOfDayList.get(3),dailyConsumptionFragment);
                foodRecyclerView.setAdapter(adapter);
                calculateTotalCalories(mealsOfDayList.get(3));
            }
        });
    //*************************************************************************************
        maximumDailyCalories=2200;
        mealsOfDayList = SampleData.generateFoodForDay();
        adapter = new DailyConsumptionAdapter(mealsOfDayList.get(0),dailyConsumptionFragment);
        foodRecyclerView = root.findViewById(R.id.dailyConsumption_RecyclerView);
        foodRecyclerView.setAdapter(adapter);
        foodRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        calculateTotalCalories(mealsOfDayList.get(0));
        return root;
    }
    public String getToday(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
        String today = simpleDateFormat.format(new Date());
        return today;
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    public void calculateTotalCalories(List<food_Item> mealsOfDayList){
        int total_Calories=0;
        double caloriePercentage;
        for (food_Item food : mealsOfDayList)
          total_Calories=food.getCalories()*food.getServing_Size()+total_Calories;
        dailyCalorieIntake.setText(Integer.toString(total_Calories));
        dailyCaloriePercent.setText(Double.toString(Math.round(((double) total_Calories/(double)maximumDailyCalories)*100))+"%");
    }
}