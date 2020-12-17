package com.example.caloriecounter.DailyMacroCounter;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.example.caloriecounter.DailyConsumption.DailyConsumptionActivity;
import com.example.caloriecounter.R;
import com.example.caloriecounter.WeeklyProgression.WeeklyProgressionActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@RequiresApi(api = Build.VERSION_CODES.O)
public class DailyMacroCounterActivity extends AppCompatActivity {

    private String localDate;

    private long userId;

    private DailyMacroCounterFragment dailyMacroCounterFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_macro_counter);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dailyMacroCounterFragment = (DailyMacroCounterFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);

        userId = dailyMacroCounterFragment.getUserId();
        localDate = LocalDate.now().toString();

        FloatingActionButton editDaily = findViewById(R.id.edit_Daily);

        dailyMacroCounterFragment.getDailyMacroNutrients();
        dailyMacroCounterFragment.setMacroNutrientText();

        editDaily.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( dailyMacroCounterFragment.getContext(), DailyConsumptionActivity.class);

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
                String today = simpleDateFormat.format(new Date());

                intent.putExtra("userId", userId);

                intent.putExtra("today", today);
                intent.putExtra("returntype", "DailyMacroCounter");
                intent.putExtra("date",localDate);


                startActivityForResult(intent, 1);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            localDate = data.getStringExtra("date");
            dailyMacroCounterFragment.getDailyMacroNutrients();
            dailyMacroCounterFragment.setMacroNutrientText();
        }
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
        if (id == R.id.Logout) {
            Activity activity =(Activity)this;
            Intent intent = new Intent(activity, DailyConsumptionActivity.class);
            activity.startActivityForResult(intent,1);
        }

        return super.onOptionsItemSelected(item);
    }

    public String getLocalDate(){
        return localDate;
    }

}