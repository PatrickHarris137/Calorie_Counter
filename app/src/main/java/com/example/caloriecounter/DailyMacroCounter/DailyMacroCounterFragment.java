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
    private TextView proteinText;
    private double totalProtein;


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


        proteinText = root.findViewById(R.id.protein_text);


        return root;


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void getDailyMacroNutrients(){

        date = dailyMacroCounterActivity.getLocalDate();

        long userId = getUserId();
        User_Daily_Consumption userDailyConsumption = dbh.get_User_Daily_Consumption(date, userId);
        List<User_Food_Item> userFoodItems = dbh.get_User_Food_Items_By_DailyId(userDailyConsumption.getId());
        int totalCalories = 0;
        totalProtein = 0;
        double totalFiber = 0;
        double totalSugar = 0;
        double totalUnsaturatedFat = 0;
        double totalSaturatedFat = 0;
        double totalTransFat = 0;
        double totalCholesterol = 0;
        double totalSodium = 0;
        if (userFoodItems != null) {
            for (User_Food_Item userFoodItem : userFoodItems) {
                food_Item foodItem = dbh.get_FoodItem_By_FoodItemId(userFoodItem.getFood_Id());
                totalCalories += foodItem.getCalories();
                MacroNutrient macroNutrient = dbh.get_MacroNutrient_By_MacroNutrientId(foodItem.getMacro_Id());
                totalProtein += macroNutrient.getProtein();
                totalFiber += macroNutrient.getFiber();
                totalSugar += macroNutrient.getSugar();
                totalUnsaturatedFat += macroNutrient.getUnsaturatedFat();
                totalSaturatedFat += macroNutrient.getSaturatedFat();
                totalTransFat += macroNutrient.getTrans_fat();
                totalCholesterol += macroNutrient.getCholesterol();
                totalSodium += macroNutrient.getSodium();
            }
        }
    }

    public long getUserId(){
        return 1;
    }

    public void setProteinText(){
        proteinText.setText(Double.toString(totalProtein));
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}