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
    //Views
    private View root;

    //Buttons
    private Button breakfast_Button;
    private Button lunch_Button;
    private Button dinner_Button;
    private Button snacks_Button;

    //Textviews
    public TextView dayOfWeek;
    public TextView dailyCalorieIntake;


    //Misc
    private RecyclerView foodRecyclerView;
    private DailyConsumptionAdapter adapter;
    private DailyConsumptionFragment dailyConsumptionFragment;
    private DailyConsumptionActivity dailyConsumptionActivity;
    private int maximumDailyCalories;
    private TextView dailyCaloriePercent;
    public meal Meal;
    private List<food_Item> food_items_list;
    private String date;
    private long userId;

    private DatabaseHandler dbh;
    private User_Daily_Consumption daily_consumption;

    //Getters
    public long getUserId() {return userId;}
    public meal getMeal(){ return this.Meal; }
    public User_Daily_Consumption getDaily_consumption() {  return daily_consumption;}

    //Setters
    public void setDaily_consumption(User_Daily_Consumption daily_consumption) {this.daily_consumption = daily_consumption; }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_daily_consumption, container, false);

        //Get current fragment and activity
        dailyConsumptionFragment=this;
        dailyConsumptionActivity= (DailyConsumptionActivity) getActivity();

        //Set Text views
        dayOfWeek=root.findViewById(R.id.day_TextView);
        dayOfWeek.setText(dailyConsumptionActivity.getToday());
        dailyCaloriePercent = root.findViewById(R.id.dailyIntakePercent_TextView);
        dailyCalorieIntake=root.findViewById(R.id.dailyCalories_TextView);


        //Get users id to be used for queries
        userId=dailyConsumptionActivity.getUserId();
        //On initialization, set the current option to breakfast
        Meal=meal.breakfast;

        //Create a new db handler
        dbh = new DatabaseHandler(getContext());
        //Get the date being sent to the activity
        date=dailyConsumptionActivity.getDate();
        //Find the daily consumption associated with that date
        daily_consumption = dbh.get_User_Daily_Consumption(date,userId);

        //If a daily consumption doesnt exist yet, create one for the current day
        if(daily_consumption.getDate()==null){
            try{
                daily_consumption=new User_Daily_Consumption(userId,date);
                dbh.getUser_Daily_Consumption_Table().create(daily_consumption);
            }catch (Exception e){

            }
        }


        //Gets food for breakfast of current day
        food_items_list = dbh.get_FoodItem_By_UserFoodItem(daily_consumption.getId(),Meal);

        //Set adapter and recycler view
        adapter = new DailyConsumptionAdapter(food_items_list,dailyConsumptionFragment);
        foodRecyclerView = root.findViewById(R.id.dailyConsumption_RecyclerView);
        foodRecyclerView.setAdapter(adapter);
        foodRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //Used to calculate how much of your daily calories you've had
        maximumDailyCalories=2200;
        calculateTotalCalories(food_items_list);

        //Sets buttons
        breakfast_Button=root.findViewById(R.id.breakfast_Button);
        lunch_Button=root.findViewById(R.id.lunch_Button);
        dinner_Button=root.findViewById(R.id.dinner_Button);
        snacks_Button=root.findViewById(R.id.snacks_Button);

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

    //Adds food item to adapters list
    public void addFoodToMeal(food_Item food){

        adapter.addFoodItem(food);
    }
    //Updates the day displayed, and sets the date
    public void setDayOfWeek(String day){
        date=day;
        dayOfWeek.setText(day);
    }
    //Gets the current day
    public String getToday(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
        String today = simpleDateFormat.format(new Date());
        return today;
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    //Updates the dailyconsumption, if it doesnt exist for that date, it makes a new one
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
    //Calculates the amount of your daily calories youve eaten compared to your max
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