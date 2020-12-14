package com.example.caloriecounter.FoodDisplay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.caloriecounter.DailyConsumption.DailyConsumptionActivity;
import com.example.caloriecounter.DailyMacroCounter.DailyMacroCounterActivity;
import com.example.caloriecounter.R;
import com.example.caloriecounter.WeeklyProgression.WeeklyProgressionActivity;
import com.example.caloriecounter.model.MacroNutrient;
import com.example.caloriecounter.model.food_Item;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.fragment.NavHostFragment;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class FoodDisplayActivity extends AppCompatActivity {

    private Activity activity;
    private FoodDisplayFragment foodDisplayFragment;
    private food_Item food;
    private MacroNutrient macro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_display);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        activity=this;

        foodDisplayFragment=(FoodDisplayFragment)getSupportFragmentManager().findFragmentById(R.id.foodDisplay_Fragment);


        FloatingActionButton addFoodListButton = findViewById(R.id.addFoodList_Button);
        addFoodListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, AddFoodItemActivity.class);
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == this.RESULT_OK){

            food=data.getParcelableExtra("newFood");
            //macro=data.getParcelableExtra("newMacro");


        }

    }


}