package com.example.caloriecounter.WeeklyProgression;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.caloriecounter.DailyConsumption.DailyConsumptionActivity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.caloriecounter.DailyMacroCounter.DailyMacroCounterActivity;
import com.example.caloriecounter.FoodDisplay.FoodDisplayActivity;
import com.example.caloriecounter.R;

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
    // stores the current day
    private Day chosenDay;
    // stores datetime
    private LocalDate localDate = LocalDate.now();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_progression);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        weeklyProgressionFragment = (WeeklyProgressionFragment) getSupportFragmentManager().findFragmentById(R.id.fragment4);

        TextView editMonday = findViewById(R.id.monday_Calories);
        TextView editTuesday = findViewById(R.id.tuesday_Calories);
        TextView editWednesday = findViewById(R.id.wednesday_Calories);
        TextView editThursday = findViewById(R.id.thursday_Calories);
        TextView editFriday = findViewById(R.id.friday_Calories);
        TextView editSaturday = findViewById(R.id.saturday_Calories);
        TextView editSunday = findViewById(R.id.sunday_Calories);

        Intent intent = getIntent();
        dayCalories = intent.getIntExtra("totalCals", 0);


        editMonday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get the current activity
                Activity activity =(Activity) weeklyProgressionFragment.getActivity();
                // create intent that redirects to dailyconsumptionactivity
                Intent intent = new Intent(activity, DailyConsumptionActivity.class);

                chosenDay = Day.MONDAY;
                String day = "Monday";
                intent.putExtra("day", day);

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
                String today = simpleDateFormat.format(new Date());

                if(day != today)
                    localDate = findDate(today);
                intent.putExtra("date", localDate);

                activity.startActivityForResult(intent,1);
            }
        });



        editTuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get the current activity
                Activity activity =(Activity) weeklyProgressionFragment.getActivity();
                // create intent that redirects to dailyconsumptionactivity
                Intent intent = new Intent(activity, DailyConsumptionActivity.class);

                activity.startActivityForResult(intent,1);
            }
        });

        editWednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get the current activity
                Activity activity =(Activity) weeklyProgressionFragment.getActivity();
                // create intent that redirects to dailyconsumptionactivity
                Intent intent = new Intent(activity, DailyConsumptionActivity.class);

                activity.startActivityForResult(intent,1);
            }
        });

        editThursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get the current activity
                Activity activity =(Activity) weeklyProgressionFragment.getActivity();
                // create intent that redirects to dailyconsumptionactivity
                Intent intent = new Intent(activity, DailyConsumptionActivity.class);

                activity.startActivityForResult(intent,1);
            }
        });

        editFriday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get the current activity
                Activity activity =(Activity) weeklyProgressionFragment.getActivity();
                // create intent that redirects to dailyconsumptionactivity
                Intent intent = new Intent(activity, DailyConsumptionActivity.class);

                activity.startActivityForResult(intent,1);
            }
        });

        editSaturday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get the current activity
                Activity activity =(Activity) weeklyProgressionFragment.getActivity();
                // create intent that redirects to dailyconsumptionactivity
                Intent intent = new Intent(activity, DailyConsumptionActivity.class);

                activity.startActivityForResult(intent,1);
            }
        });

        editSunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get the current activity
                Activity activity =(Activity) weeklyProgressionFragment.getActivity();
                // create intent that redirects to dailyconsumptionactivity
                Intent intent = new Intent(activity, DailyConsumptionActivity.class);

                activity.startActivityForResult(intent,1);
            }
        });
    }

    public LocalDate findDate(String today){
        switch (today){
            case "Monday":
                int dayOfWeek = Day.MONDAY.ordinal();
                int dayDifference = dayOfWeek - chosenDay.ordinal();
                localDate = localDate.plusDays(dayDifference);
                break;
            case "Tuesday":
                dayOfWeek = Day.TUESDAY.ordinal();
                dayDifference = dayOfWeek - chosenDay.ordinal();
                localDate = localDate.plusDays(dayDifference);
                break;
            case "Wednesday":
                dayOfWeek = Day.WEDNESDAY.ordinal();
                dayDifference = dayOfWeek - chosenDay.ordinal();
                localDate = localDate.plusDays(dayDifference);
                break;
            case "Thursday":
                dayOfWeek = Day.THURSDAY.ordinal();
                dayDifference = dayOfWeek - chosenDay.ordinal();
                localDate = localDate.plusDays(dayDifference);
                break;
            case "Friday":
                dayOfWeek = Day.FRIDAY.ordinal();
                dayDifference = dayOfWeek - chosenDay.ordinal();
                localDate = localDate.plusDays(dayDifference);
                break;
            case "Saturday":
                dayOfWeek = Day.SATURDAY.ordinal();
                dayDifference = dayOfWeek - chosenDay.ordinal();
                localDate = localDate.plusDays(dayDifference);
                break;
            case "Sunday":
                dayOfWeek = Day.SUNDAY.ordinal();
                dayDifference = dayOfWeek - chosenDay.ordinal();
                localDate = localDate.plusDays(dayDifference);
                break;
        }

        return localDate;
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.DailyConsumptionOption) {
            Activity activity =(Activity)this;
            Intent intent = new Intent(activity, DailyConsumptionActivity.class);
            activity.startActivityForResult(intent,1);
        }
        if (id == R.id.WeeklyProgressionOption) {
            Activity activity =(Activity)this;
            Intent intent = new Intent(activity, WeeklyProgressionActivity.class);
            activity.startActivityForResult(intent,1);
        }
        if (id == R.id.FoodDisplayOption) {
            Activity activity =(Activity)this;
            Intent intent = new Intent(activity, FoodDisplayActivity.class);
            activity.startActivityForResult(intent,1);
        }
        if (id == R.id.DailyMacroCounterOption) {
            Activity activity =(Activity)this;
            Intent intent = new Intent(activity, DailyMacroCounterActivity.class);
            activity.startActivityForResult(intent,1);
        }

        return super.onOptionsItemSelected(item);
    }

}