package com.example.caloriecounter.DailyConsumption;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caloriecounter.R;

public class DailyConsumptionAdapter extends RecyclerView.Adapter<DailyConsumptionAdapter.FoodViewHolder> {



    @Override
    public void onBindViewHolder(@NonNull DailyConsumptionAdapter.FoodViewHolder holder, int position) {

    }
    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FoodViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.dailyconsumption_list_item, parent, false)
        );
    }
    @Override
    public int getItemCount() {
        return 0;
    }
    public class FoodViewHolder extends RecyclerView.ViewHolder {

        private TextView foodName_TextView;
        private TextView foodQuantity_TextView;
        private TextView foodCalorie_TextView;
        private int position;
       // private Food food;
        private final View root;
        public FoodViewHolder(@NonNull final View root) {
            super(root);
            this.root=root;

            foodName_TextView = root.findViewById(R.id.foodName_TextView);
            foodQuantity_TextView = root.findViewById(R.id.foodQuantity_TextView);
            foodCalorie_TextView = root.findViewById(R.id.foodCalorie_TextView);



        }


    }
}
