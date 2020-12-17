package com.example.caloriecounter.WeeklyProgression;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import com.example.caloriecounter.DailyConsumption.DailyConsumptionActivity;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.caloriecounter.DailyMacroCounter.DailyMacroCounterActivity;
import com.example.caloriecounter.FoodDisplay.FoodDisplayActivity;
import com.example.caloriecounter.R;
import com.example.caloriecounter.model.user;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

enum Day {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY
}

@RequiresApi(api = Build.VERSION_CODES.O)
public class WeeklyProgressionActivity extends AppCompatActivity {
    private WeeklyProgressionFragment weeklyProgressionFragment;
    // stores total calories for associated day
    private int dayCalories;
    // stores the current day that was selected
    private String day;
    // stores datetime
    private String localDate;
    private String today;
    //Users id
    private long userId;

    private String mondayDate;
    private String tuesdayDate;
    private String wednesdayDate;
    private String thursdayDate;
    private String fridayDate;
    private String saturdayDate;
    private String sundayDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_progression);

        weeklyProgressionFragment = (WeeklyProgressionFragment) getSupportFragmentManager().findFragmentById(R.id.fragment4);

        Intent intent = getIntent();
        userId=intent.getLongExtra("userId",5);
        weeklyProgressionFragment.setUserId(userId);
        TextView editMonday = findViewById(R.id.monday_Calories);
        TextView editTuesday = findViewById(R.id.tuesday_Calories);
        TextView editWednesday = findViewById(R.id.wednesday_Calories);
        TextView editThursday = findViewById(R.id.thursday_Calories);
        TextView editFriday = findViewById(R.id.friday_Calories);
        TextView editSaturday = findViewById(R.id.saturday_Calories);
        TextView editSunday = findViewById(R.id.sunday_Calories);
        TextView totalCaloriesText = findViewById(R.id.totalWeekCalories);

        localDate  = LocalDate.now().toString();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
        today = simpleDateFormat.format(new Date());

        long todayNum = getNumToday(today);
        setDate(todayNum);

        editMonday.setText("Monday: " + Integer.toString(weeklyProgressionFragment.getWeeklyCalories(mondayDate)) + " calories");
        editTuesday.setText("Tuesday: " + Integer.toString(weeklyProgressionFragment.getWeeklyCalories(tuesdayDate))  + " calories");
        editWednesday.setText("Wednesday: " +Integer.toString(weeklyProgressionFragment.getWeeklyCalories(wednesdayDate))  + " calories");
        editThursday.setText("Thursday: " + Integer.toString(weeklyProgressionFragment.getWeeklyCalories(thursdayDate))  + " calories");
        editFriday.setText("Friday: " + Integer.toString(weeklyProgressionFragment.getWeeklyCalories(fridayDate))  + " calories");
        editSaturday.setText("Saturday: " + Integer.toString(weeklyProgressionFragment.getWeeklyCalories(saturdayDate))  + " calories");
        editSunday.setText("Sunday: " + Integer.toString(weeklyProgressionFragment.getWeeklyCalories(sundayDate))  + " calories");
        totalCaloriesText.setText("Week: " + Integer.toString(weeklyProgressionFragment.getGetTotalWeekCalories()) + " calories");

        editMonday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get intent from dailyconsumptionactivity
                Intent intent = getIntent();

                day = "Monday";
                intent.putExtra("day", day);
                intent.putExtra("returnType", "WeeklyProgression");

                intent.putExtra("date", mondayDate);

                setResult(Activity.RESULT_OK, intent);
                finish();

            }
        });



        editTuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get intent from dailyconsumptionactivity
                Intent intent = getIntent();

                day = "Tuesday";
                intent.putExtra("day", day);
                intent.putExtra("returnType", "WeeklyProgression");


                intent.putExtra("date", tuesdayDate);

                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

        editWednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get intent from dailyconsumptionactivity
                Intent intent = getIntent();

                day = "Wednesday";
                intent.putExtra("day", day);
                intent.putExtra("returnType", "WeeklyProgression");

                intent.putExtra("date", wednesdayDate);

                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

        editThursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get intent from dailyconsumptionactivity
                Intent intent = getIntent();

                day = "Thursday";
                intent.putExtra("day", day);
                intent.putExtra("returnType", "WeeklyProgression");

                intent.putExtra("date", thursdayDate);


                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

        editFriday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get intent from dailyconsumptionactivity
                Intent intent = getIntent();

                day = "Friday";
                intent.putExtra("day", day);
                intent.putExtra("returnType", "WeeklyProgression");


                intent.putExtra("date", fridayDate);

                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

        editSaturday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get intent from dailyconsumptionactivity
                Intent intent = getIntent();

                day = "Saturday";
                intent.putExtra("day", day);
                intent.putExtra("returnType", "WeeklyProgression");

                intent.putExtra("date", saturdayDate);

                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

        editSunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get intent from dailyconsumptionactivity
                Intent intent = getIntent();

                day = "Sunday";
                intent.putExtra("day", day);
                intent.putExtra("returnType", "WeeklyProgression");


                intent.putExtra("date", sundayDate);

                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });


    }

    public long getNumToday(String today){

        long numDay =0;
        switch (today){
            case "Monday":
                numDay=0;
                break;
            case "Tuesday":
                numDay=1;
                break;
            case "Wednesday":
                numDay=2;
                break;
            case "Thursday":
                numDay=3;
                break;
            case "Friday":
                numDay=4;
                break;
            case "Saturday":
                numDay=5;
                break;
            case "Sunday":
                numDay=6;
                break;
        }

        return numDay;

    }


    public void setDate( long numToday){
        LocalDate tmpLocalDate = LocalDate.now();

        // sets monday date
        long dayOfWeek = Day.MONDAY.ordinal();
        long dayDifference = dayOfWeek - numToday;
        mondayDate = tmpLocalDate.plusDays(dayDifference).toString();;

        // sets tuesday date
        dayOfWeek = Day.TUESDAY.ordinal();
        dayDifference = dayOfWeek - numToday;
        tuesdayDate = tmpLocalDate.plusDays(dayDifference ).toString();

        // sets wednesday date
        dayOfWeek = Day.WEDNESDAY.ordinal();
        dayDifference = dayOfWeek - numToday;
        wednesdayDate = tmpLocalDate.plusDays(dayDifference ).toString();

        // sets thursday date
        dayOfWeek = Day.THURSDAY.ordinal();
        dayDifference = dayOfWeek - numToday;
        thursdayDate = tmpLocalDate.plusDays(dayDifference ).toString();

        // sets friday date
        dayOfWeek = Day.FRIDAY.ordinal();
        dayDifference = dayOfWeek - numToday;
        fridayDate = tmpLocalDate.plusDays(dayDifference ).toString();

        // sets saturday date
        dayOfWeek = Day.SATURDAY.ordinal();
        dayDifference = dayOfWeek - numToday;
        saturdayDate = tmpLocalDate.plusDays(dayDifference ).toString();

        // sets sunday date
        dayOfWeek = Day.SUNDAY.ordinal();
        dayDifference = dayOfWeek - numToday;
        sundayDate = tmpLocalDate.plusDays(dayDifference ).toString();

    }

    public String getLocalDate(){
        return localDate;
    }

    public String getDay(){
        return day;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

}