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
    // stores the current day
    private Day chosenDay;
    // stores datetime
    private String localDate = LocalDate.now().toString();

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

        editMonday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get intent from dailyconsumptionactivity
                Intent intent = getIntent();

                String day = "Monday";
                intent.putExtra("day", day);
                intent.putExtra("returnType", "WeeklyProgression");

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
                String today = simpleDateFormat.format(new Date());

                long todayNum = getNumToday(today);
                if(day != today)
                    localDate = findDate(day,todayNum).toString();
                intent.putExtra("date", localDate);

                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });



        editTuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get intent from dailyconsumptionactivity
                Intent intent = getIntent();

                String day = "Tuesday";
                intent.putExtra("day", day);
                intent.putExtra("returnType", "WeeklyProgression");

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
                String today = simpleDateFormat.format(new Date());

                long todayNum = getNumToday(today);

                if(day != today)
                    localDate = findDate(day,todayNum).toString();
                intent.putExtra("date", localDate);

                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

        editWednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get intent from dailyconsumptionactivity
                Intent intent = getIntent();

                String day = "Wednesday";
                intent.putExtra("day", day);
                intent.putExtra("returnType", "WeeklyProgression");

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
                String today = simpleDateFormat.format(new Date());

                long todayNum = getNumToday(today);

                if(day != today)
                    localDate = findDate(day,todayNum).toString();
                intent.putExtra("date", localDate);

                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

        editThursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get intent from dailyconsumptionactivity
                Intent intent = getIntent();

                String day = "Thursday";
                intent.putExtra("day", day);
                intent.putExtra("returnType", "WeeklyProgression");

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
                String today = simpleDateFormat.format(new Date());

                long todayNum = getNumToday(today);

                if(day != today)
                    localDate = findDate(day,todayNum).toString();
                intent.putExtra("date", localDate);


                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

        editFriday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get intent from dailyconsumptionactivity
                Intent intent = getIntent();

                String day = "Friday";
                intent.putExtra("day", day);
                intent.putExtra("returnType", "WeeklyProgression");

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
                String today = simpleDateFormat.format(new Date());

                long todayNum = getNumToday(today);

                if(day != today)
                    localDate = findDate(day,todayNum).toString();
                intent.putExtra("date", localDate);

                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

        editSaturday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get intent from dailyconsumptionactivity
                Intent intent = getIntent();

                String day = "Saturday";
                intent.putExtra("day", day);
                intent.putExtra("returnType", "WeeklyProgression");

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
                String today = simpleDateFormat.format(new Date());

                long todayNum = getNumToday(today);

                if(day != today)
                    localDate = findDate(day,todayNum).toString();
                intent.putExtra("date", localDate);

                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

        editSunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get intent from dailyconsumptionactivity
                Intent intent = getIntent();

                String day = "Sunday";
                intent.putExtra("day", day);
                intent.putExtra("returnType", "WeeklyProgression");

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
                String today = simpleDateFormat.format(new Date());

                long todayNum = getNumToday(today);

                if(day != today)
                    localDate = findDate(day,todayNum).toString();
                intent.putExtra("date", localDate);

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
    public String findDate(String day, long numToday){
        LocalDate tmpLocalDate = LocalDate.now();

        switch (day){
            case "Monday":
                long dayOfWeek = Day.MONDAY.ordinal();
                long dayDifference = dayOfWeek - numToday;
                localDate = tmpLocalDate.plusDays(dayDifference).toString();;
                break;
            case "Tuesday":
                dayOfWeek = Day.TUESDAY.ordinal();
                dayDifference = dayOfWeek - numToday;
                localDate = tmpLocalDate.plusDays(dayDifference ).toString();
                break;
            case "Wednesday":
                dayOfWeek = Day.WEDNESDAY.ordinal();
                dayDifference = dayOfWeek - numToday;
                localDate = tmpLocalDate.plusDays(dayDifference ).toString();
                break;
            case "Thursday":
                dayOfWeek = Day.THURSDAY.ordinal();
                dayDifference = dayOfWeek - numToday;
                localDate = tmpLocalDate.plusDays(dayDifference ).toString();
                break;
            case "Friday":
                dayOfWeek = Day.FRIDAY.ordinal();
                dayDifference = dayOfWeek - numToday;
                localDate = tmpLocalDate.plusDays(dayDifference ).toString();
                break;
            case "Saturday":
                dayOfWeek = Day.SATURDAY.ordinal();
                dayDifference = dayOfWeek - numToday;
                localDate = tmpLocalDate.plusDays(dayDifference ).toString();
                break;
            case "Sunday":
                dayOfWeek = Day.SUNDAY.ordinal();
                dayDifference = dayOfWeek - numToday;
                localDate = tmpLocalDate.plusDays(dayDifference ).toString();
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