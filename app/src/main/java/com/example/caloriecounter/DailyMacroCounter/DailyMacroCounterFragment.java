 package com.example.caloriecounter.DailyMacroCounter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
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

import java.time.LocalDate;
import java.util.List;

public class DailyMacroCounterFragment extends Fragment {

    private DatabaseHandler dbh;

    public DailyMacroCounterActivity getDailyMacroCounterActivity() {
        return dailyMacroCounterActivity;
    }

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
    private LoginManager loginManager;
    private LoginDialogFragment dialogFragment;


    public LoginManager getLoginManager() {
        return loginManager;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState

    ) {
        View root = inflater.inflate(R.layout.fragment_daily_macro_counter, container, false);
        // Inflate the layout for this fragment
        //FoodApplication application =  (FoodApplication) getActivity().getApplication();
        dialogFragment = new LoginDialogFragment();
        dialogFragment.setDailyMacroCounterFragment(this);
        loginManager = new LoginManagerStub(getContext());
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
        if(loginManager.getUserId()!=0)
            dailyMacroCounterActivity.setUserId(loginManager.getUserId());
        caloriesText = root.findViewById(R.id.calories_text);
        proteinText = root.findViewById(R.id.protein_text);
        fiberText = root.findViewById(R.id.fiber_text);
        sugarText = root.findViewById(R.id.sugar_text);
        unsaturatedFatText = root.findViewById(R.id.unsaturated_fat_text);
        saturatedFatText = root.findViewById(R.id.saturated_fat_text);
        transFatText = root.findViewById(R.id.trans_fat_text);
        cholesterolText = root.findViewById(R.id.cholesterol_text);
        sodiumText = root.findViewById(R.id.sodium_text);
        getDailyMacroNutrients();
        setMacroNutrientText();
        return root;


    }
    public void logout(){
        loginManager.logout();

        dialogFragment.show(getChildFragmentManager(),"login-fragment");
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void getDailyMacroNutrients(){

        date = dailyMacroCounterActivity.getLocalDate();
        if(date==null)
            date= LocalDate.now().toString();
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    public long getUserId(){
        return dailyMacroCounterActivity.getUserId();
    }

    public void setMacroNutrientText(){
        MacroNutrient macroNutrient = new MacroNutrient();


        caloriesText.setText("Calories: " + Integer.toString(totalCalories));
        proteinText.setText("Protein: " + String.format("%.1f",totalProtein) + "g");
        fiberText.setText("Fiber: " + String.format("%.1f" ,totalFiber) + "g");
        sugarText.setText("Sugar: " + String.format("%.1f" ,totalSugar) + "g");
        unsaturatedFatText.setText("Unsaturated Fat: " + String.format("%.1f" ,totalUnsaturatedFat) + "g");
        saturatedFatText.setText("Saturated Fat: " + String.format("%.1f" ,totalSaturatedFat) + "g");
        transFatText.setText("Trans Fat: " + String.format("%.1f" ,totalTransFat) + "g");
        cholesterolText.setText( "Cholesterol: " + String.format("%.1f" ,totalCholesterol) + "mg");
        sodiumText.setText("Sodium: " + String.format("%.1f" , totalSodium) + "mg");

        if(totalCalories >= 2000 && totalCalories <= 2500)
            caloriesText.setTextColor(Color.parseColor("#00FF0A"));
        else
            caloriesText.setTextColor(Color.parseColor("#FF0000"));

        if(macroNutrient.sufficientProtein(totalProtein))
            proteinText.setTextColor(Color.parseColor("#00FF0A"));
        else
            proteinText.setTextColor(Color.parseColor("#FF0000"));

        if(macroNutrient.sufficientFiber(totalFiber))
            fiberText.setTextColor(Color.parseColor("#00FF0A"));
        else
            fiberText.setTextColor(Color.parseColor("#FF0000"));

        if(macroNutrient.sufficientSugar(totalSugar))
            sugarText.setTextColor(Color.parseColor("#00FF0A"));
        else
            sugarText.setTextColor(Color.parseColor("#FF0000"));

        if(macroNutrient.sufficientUnsaturatedFat(totalUnsaturatedFat))
            unsaturatedFatText.setTextColor(Color.parseColor("#00FF0A"));
        else
            unsaturatedFatText.setTextColor(Color.parseColor("#FF0000"));

        if(macroNutrient.sufficientSaturatedFat(totalSaturatedFat))
            saturatedFatText.setTextColor(Color.parseColor("#00FF0A"));
        else
            saturatedFatText.setTextColor(Color.parseColor("#FF0000"));

        if(macroNutrient.sufficientTrans_fat(totalTransFat))
            transFatText.setTextColor(Color.parseColor("#00FF0A"));
        else
            transFatText.setTextColor(Color.parseColor("#FF0000"));

        if(macroNutrient.sufficientCholesterol(totalCholesterol))
            cholesterolText.setTextColor(Color.parseColor("#00FF0A"));
        else
            cholesterolText.setTextColor(Color.parseColor("#FF0000"));

        if(macroNutrient.sufficientSodium(totalSodium))
            sodiumText.setTextColor(Color.parseColor("#00FF0A"));
        else
            sodiumText.setTextColor(Color.parseColor("#FF0000"));
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}