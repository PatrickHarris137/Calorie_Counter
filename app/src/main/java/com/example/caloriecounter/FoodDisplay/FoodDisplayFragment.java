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
import com.example.caloriecounter.model.SampleData;
import com.example.caloriecounter.model.food_Item;


import java.util.ArrayList;
import java.util.List;

public class FoodDisplayFragment extends Fragment {

    private List<food_Item> foodData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root=inflater.inflate(R.layout.fragment_food_display, container, false);

        RecyclerView foodListRecyclerView = root.findViewById(R.id.foodList_RecyclerView);


        foodData= SampleData.generateFoodDisplayList();

        FoodDisplayAdapter adapter = new FoodDisplayAdapter(foodData);

        foodListRecyclerView.setAdapter(adapter);

        foodListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return root;

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);






    }
}