package com.example.caloriecounter.DailyConsumption;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.caloriecounter.R;
import com.example.caloriecounter.model.DatabaseHandler;
import com.example.caloriecounter.model.SampleData;
import com.example.caloriecounter.model.User_Daily_Consumption;
import com.example.caloriecounter.model.User_Food_Item;
import com.example.caloriecounter.model.food_Item;
import com.example.caloriecounter.model.meal;
import com.example.caloriecounter.sqlite.DatabaseException;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.Button;
import android.widget.TextView;

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
    private DailyConsumptionActivity dailyConsumptionActivity;
    private int maximumDailyCalories;
    private TextView dailyCaloriePercent;
    private ArrayList<food_Item> foodList;
    public meal Meal;
    private List<food_Item> food_items_list;

    public DatabaseHandler getDbh() {
        return dbh;
    }

    private DatabaseHandler dbh;

    public long getUserId() {
        return userId;
    }

    private long userId;
    public User_Daily_Consumption getDaily_consumption() {
        return daily_consumption;
    }

    public void setDaily_consumption(User_Daily_Consumption daily_consumption) {
        this.daily_consumption = daily_consumption;
    }

    public TextView getDailyCalorieIntake() {
        return dailyCalorieIntake;
    }

    private User_Daily_Consumption daily_consumption;

    public TextView getDayOfWeek() {
        return dayOfWeek;
    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        /*   CHANGE LATER     */
        userId=1;
        root = inflater.inflate(R.layout.fragment_daily_consumption, container, false);
        dailyConsumptionFragment=this;
        dayOfWeek=root.findViewById(R.id.day_TextView);
        dayOfWeek.setText(getToday());
        dailyCaloriePercent = root.findViewById(R.id.dailyIntakePercent_TextView);
        dailyConsumptionActivity= (DailyConsumptionActivity) getActivity();
        dailyCalorieIntake=root.findViewById(R.id.dailyCalories_TextView);

        breakfast_Button=root.findViewById(R.id.breakfast_Button);
        lunch_Button=root.findViewById(R.id.lunch_Button);
        dinner_Button=root.findViewById(R.id.dinner_Button);
        snacks_Button=root.findViewById(R.id.snacks_Button);
        breakfast_Button.setBackgroundColor(getResources().getColor(R.color.green));
        lunch_Button.setBackgroundColor(getResources().getColor(R.color.purple_500));
        dinner_Button.setBackgroundColor(getResources().getColor(R.color.purple_500));
        snacks_Button.setBackgroundColor(getResources().getColor(R.color.purple_500));

        Meal=meal.breakfast;
        dbh = new DatabaseHandler(getContext());

        List<User_Food_Item> ufi;

        try {
            foodList = (ArrayList<food_Item>) dbh.get_Food_Item_Table().readAll();
            daily_consumption = dbh.get_User_Daily_Consumption(getCurrentDay(),userId);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        TextView calorie_Total=root.findViewById(R.id.dailyCalories_TextView);

        maximumDailyCalories=2200;

        //Gets food for breakfast of current day
        food_items_list = dbh.get_FoodItem_By_UserFoodItem(daily_consumption.getId(),Meal);

        adapter = new DailyConsumptionAdapter(food_items_list,dailyConsumptionFragment);
        foodRecyclerView = root.findViewById(R.id.dailyConsumption_RecyclerView);
        foodRecyclerView.setAdapter(adapter);
        foodRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        calculateTotalCalories(food_items_list);

        //OnclickListeners for Meal types
        breakfast_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                breakfast_Button.setBackgroundColor(getResources().getColor(R.color.green));
                lunch_Button.setBackgroundColor(getResources().getColor(R.color.purple_500));
                dinner_Button.setBackgroundColor(getResources().getColor(R.color.purple_500));
                snacks_Button.setBackgroundColor(getResources().getColor(R.color.purple_500));
                Meal=meal.breakfast;
                food_items_list = dbh.get_FoodItem_By_UserFoodItem(daily_consumption.getId(),Meal);
                adapter = new DailyConsumptionAdapter(food_items_list,dailyConsumptionFragment);
                foodRecyclerView.setAdapter(adapter);
                calculateTotalCalories(food_items_list);
            }
        });
        lunch_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                breakfast_Button.setBackgroundColor(getResources().getColor(R.color.purple_500));
                lunch_Button.setBackgroundColor(getResources().getColor(R.color.green));
                dinner_Button.setBackgroundColor(getResources().getColor(R.color.purple_500));
                snacks_Button.setBackgroundColor(getResources().getColor(R.color.purple_500));
                Meal=meal.lunch;
                food_items_list = dbh.get_FoodItem_By_UserFoodItem(daily_consumption.getId(),Meal);
                adapter = new DailyConsumptionAdapter(food_items_list,dailyConsumptionFragment);
                foodRecyclerView.setAdapter(adapter);
                calculateTotalCalories(food_items_list);
            }
        });
        dinner_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                breakfast_Button.setBackgroundColor(getResources().getColor(R.color.purple_500));
                lunch_Button.setBackgroundColor(getResources().getColor(R.color.purple_500));
                dinner_Button.setBackgroundColor(getResources().getColor(R.color.green));
                snacks_Button.setBackgroundColor(getResources().getColor(R.color.purple_500));
                Meal=meal.dinner;
                food_items_list = dbh.get_FoodItem_By_UserFoodItem(daily_consumption.getId(),Meal);
                adapter = new DailyConsumptionAdapter(food_items_list,dailyConsumptionFragment);
                foodRecyclerView.setAdapter(adapter);
                calculateTotalCalories(food_items_list);
            }
        });
        snacks_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                breakfast_Button.setBackgroundColor(getResources().getColor(R.color.purple_500));
                lunch_Button.setBackgroundColor(getResources().getColor(R.color.purple_500));
                dinner_Button.setBackgroundColor(getResources().getColor(R.color.purple_500));
                snacks_Button.setBackgroundColor(getResources().getColor(R.color.green));
                Meal=meal.snacks;
                food_items_list = dbh.get_FoodItem_By_UserFoodItem(daily_consumption.getId(),Meal);
                adapter = new DailyConsumptionAdapter(food_items_list,dailyConsumptionFragment);
                foodRecyclerView.setAdapter(adapter);
                calculateTotalCalories(food_items_list);
            }
        });
    //*************************************************************************************
        return root;
    }

    public String getCurrentDay(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String today = sdf.format(new Date());
        return today;
    }
    public void addFoodToMeal(food_Item food){
        adapter.addFoodItem(food);


    }
    public meal getMeal(){
        return this.Meal;
    }
    public void setDayOfWeek(String day){

        dayOfWeek.setText(day);

    }
    public String getToday(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
        String today = simpleDateFormat.format(new Date());
        return today;
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    public void updateDay(String date){
        try{
            daily_consumption = dbh.get_User_Daily_Consumption(date,userId);
            if(daily_consumption.getDate()==null){
                daily_consumption = new User_Daily_Consumption(userId,date);
                dbh.getUser_Daily_Consumption_Table().create(daily_consumption);
            }
            food_items_list = dbh.get_FoodItem_By_UserFoodItem(daily_consumption.getId(),Meal);
            adapter = new DailyConsumptionAdapter(food_items_list,dailyConsumptionFragment);
            foodRecyclerView.setAdapter(adapter);
        }catch (Exception e){

        }
    }
    public void calculateTotalCalories(List<food_Item> mealsOfDayList){
        int total_Calories=0;
        double caloriePercentage;
        long daily_Id=daily_consumption.getId();
        for (food_Item food : mealsOfDayList){
            User_Food_Item userFoodItem=dbh.getSpecificUserFoodItem(daily_Id,food.getId(),Meal);
            total_Calories=food.getCalories()*userFoodItem.getNum_Of_Serving()+total_Calories;
        }

        dailyCalorieIntake.setText(Integer.toString(total_Calories));
        dailyCaloriePercent.setText(Double.toString(Math.round(((double) total_Calories/(double)maximumDailyCalories)*100))+"%");
    }
}