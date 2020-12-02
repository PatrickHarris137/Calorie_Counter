package com.example.caloriecounter.FoodDisplay;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.caloriecounter.R;

import java.util.List;

public class FoodDisplayAdapter extends RecyclerView.Adapter<FoodDisplayViewHolder> {

    private Context context;
    private List<Food> foodData;

    public FoodDisplayAdapter(){


    }

    @NonNull
    @Override
    public FoodDisplayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_food_item, parent, false);
        context=root.getContext();

        return new FoodDisplayViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodDisplayViewHolder holder, int position) {
        holder.set(data.get(position));
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
