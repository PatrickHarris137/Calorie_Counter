 package com.example.caloriecounter.DailyMacroCounter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.caloriecounter.DailyConsumption.DailyConsumptionActivity;
import com.example.caloriecounter.FoodApplication;
import com.example.caloriecounter.R;
import com.example.caloriecounter.login.LoginDialogFragment;
import com.example.caloriecounter.login.LoginManager;
import com.example.caloriecounter.login.LoginManagerStub;
import com.example.caloriecounter.login.OnLoginListener;
import com.example.caloriecounter.model.DatabaseHandler;
import com.example.caloriecounter.model.MacroNutrient;
import com.example.caloriecounter.model.User_Daily_Consumption;
import com.example.caloriecounter.model.User_Food_Item;
import com.example.caloriecounter.model.food_Item;
import com.example.caloriecounter.model.user;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class DailyMacroCounterFragment extends Fragment {

    private DatabaseHandler dbh;
    private DailyMacroCounterActivity dailyMacroCounterActivity;
    private String date;
    private User_Food_Item userFoodItem;
    private MacroNutrient macroNutrient;
    private TextView caloriesText;
    private TextView proteinText;
    private TextView fiberText;
    private TextView sugarText;
    private TextView unsaturatedFatText;
    private TextView saturatedFatText;
    private TextView transFatText;
    private TextView cholesterolText;
    private TextView sodiumText;

    private int totalCalories;
    private double totalProtein;
    private double totalFiber;
    private double totalSugar;
    private double totalUnsaturatedFat;
    private double totalSaturatedFat;
    private double totalTransFat;
    private double totalCholesterol;
    private double totalSodium;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState

    ) {
        View root = inflater.inflate(R.layout.fragment_daily_macro_counter, container, false);
        // Inflate the layout for this fragment
        //FoodApplication application =  (FoodApplication) getActivity().getApplication();
        final LoginDialogFragment dialogFragment = new LoginDialogFragment();
        LoginManager loginManager=new LoginManagerStub(getContext());
        loginManager.setOnLoginListener(new OnLoginListener() {
            @Override
            public void onLogin(String uuid) {
                dialogFragment.dismiss();
            }
            @Override
            public void onLogout() {

            }
            @Override
            public void onRegister(String uuid) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Account Created")
                        .setMessage("Please login with your new credentials")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .create()
                        .show();
            }

            @Override
            public void onError(String message) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Problem with login.")
                        .setMessage(message)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .create()
                        .show();
            }
        });



        if(!loginManager.isLoggedIn())
            dialogFragment.show(getChildFragmentManager(),"login-fragment");

        dbh = new DatabaseHandler(getContext());

        dailyMacroCounterActivity = (DailyMacroCounterActivity) getActivity();

        caloriesText = root.findViewById(R.id.calories_text);
        proteinText = root.findViewById(R.id.protein_text);
        fiberText = root.findViewById(R.id.fiber_text);
        sugarText = root.findViewById(R.id.sugar_text);
        unsaturatedFatText = root.findViewById(R.id.unsaturated_fat_text);
        saturatedFatText = root.findViewById(R.id.saturated_fat_text);
        transFatText = root.findViewById(R.id.trans_fat_text);
        cholesterolText = root.findViewById(R.id.cholesterol_text);
        sodiumText = root.findViewById(R.id.sodium_text);

        return root;


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void getDailyMacroNutrients(){

        date = dailyMacroCounterActivity.getLocalDate();

        long userId = getUserId();
        User_Daily_Consumption userDailyConsumption = dbh.get_User_Daily_Consumption(date, userId);
        List<User_Food_Item> userFoodItems = dbh.get_User_Food_Items_By_DailyId(userDailyConsumption.getId());
        totalCalories = 0;
        totalProtein = 0;
        totalFiber = 0;
        totalSugar = 0;
        totalUnsaturatedFat = 0;
        totalSaturatedFat = 0;
        totalTransFat = 0;
        totalCholesterol = 0;
        totalSodium = 0;
        if (userFoodItems != null) {
            for (User_Food_Item userFoodItem : userFoodItems) {
                food_Item foodItem = dbh.get_FoodItem_By_FoodItemId(userFoodItem.getFood_Id());
                totalCalories += foodItem.getCalories() * userFoodItem.getNum_Of_Serving();
                MacroNutrient macroNutrient = dbh.get_MacroNutrient_By_MacroNutrientId(foodItem.getMacro_Id());
                totalProtein += macroNutrient.getProtein()  * userFoodItem.getNum_Of_Serving();
                totalFiber += macroNutrient.getFiber()  * userFoodItem.getNum_Of_Serving();
                totalSugar += macroNutrient.getSugar()  * userFoodItem.getNum_Of_Serving();
                totalUnsaturatedFat += macroNutrient.getUnsaturatedFat()  * userFoodItem.getNum_Of_Serving();
                totalSaturatedFat += macroNutrient.getSaturatedFat()  * userFoodItem.getNum_Of_Serving();
                totalTransFat += macroNutrient.getTrans_fat()  * userFoodItem.getNum_Of_Serving();
                totalCholesterol += macroNutrient.getCholesterol()  * userFoodItem.getNum_Of_Serving();
                totalSodium += macroNutrient.getSodium()  * userFoodItem.getNum_Of_Serving();
            }
        }
    }

    public long getUserId(){
        return 1;
    }

    public void setMacroNutrientText(){
        caloriesText.setText("Calories: " + Integer.toString(totalCalories));
        proteinText.setText("Protein: " + Double.toString(totalProtein) + "g");
        fiberText.setText("Fiber: " + Double.toString(totalFiber) + "g");
        sugarText.setText("Sugar: " + Double.toString(totalSugar) + "g");
        unsaturatedFatText.setText("Unsaturated Fat: " + Double.toString(totalUnsaturatedFat) + "g");
        saturatedFatText.setText("Saturated Fat: " + Double.toString(totalSaturatedFat) + "g");
        transFatText.setText("Trans Fat: " + Double.toString(totalTransFat) + "g");
        cholesterolText.setText( "Cholesterol: " + Double.toString(totalCholesterol) + "mg");
        sodiumText.setText("Sodium: " + Double.toString(totalSodium) + "mg");
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}