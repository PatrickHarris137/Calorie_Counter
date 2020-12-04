package com.example.caloriecounter.WeeklyProgression;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.caloriecounter.DailyConsumption.DailyConsumptionActivity;
import com.example.caloriecounter.DailyConsumption.DailyConsumptionFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.caloriecounter.DailyMacroCounter.DailyMacroCounterActivity;
import com.example.caloriecounter.FoodDisplay.FoodDisplayActivity;
import com.example.caloriecounter.R;

public class WeeklyProgressionActivity extends AppCompatActivity {
    private WeeklyProgressionFragment weeklyProgressionFragment;
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
                // get the current activity
                Activity activity =(Activity) weeklyProgressionFragment.getActivity();
                // create intent that redirects to dailyconsumptionactivity
                Intent intent = new Intent(activity, DailyConsumptionActivity.class);

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