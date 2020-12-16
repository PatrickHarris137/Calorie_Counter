package com.example.caloriecounter.FoodDisplay;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caloriecounter.R;
import com.example.caloriecounter.model.DatabaseHandler;
import com.example.caloriecounter.model.MacroNutrient;
import com.example.caloriecounter.model.SampleData;
import com.example.caloriecounter.model.food_Item;


import java.util.ArrayList;
import java.util.List;

public class FoodDisplayFragment extends Fragment {

    private List<food_Item> foodData;
    private List<MacroNutrient>macroData;
    private DatabaseHandler dbh;
    private FoodDisplayAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root=inflater.inflate(R.layout.fragment_food_display, container, false);

        RecyclerView foodListRecyclerView = root.findViewById(R.id.foodList_RecyclerView);

        dbh= new DatabaseHandler(root.getContext());
        try{
           foodData=dbh.get_Food_Item_Table().readAll();
           macroData=dbh.get_Macro_Nutrient_Table().readAll();
        }
        catch (Exception e){

        }

        //foodData= SampleData.generateFoodDisplayList();

        adapter = new FoodDisplayAdapter(foodData,macroData);

        foodListRecyclerView.setAdapter(adapter);

        foodListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return root;

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public void setFoodItem(food_Item food, MacroNutrient macro,String method){
        adapter.setFoodItem(food,macro,dbh,method);
        adapter.notifyDataSetChanged();
    }


}