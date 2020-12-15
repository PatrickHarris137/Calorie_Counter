package com.example.caloriecounter.FoodDisplay;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.caloriecounter.R;
import com.example.caloriecounter.model.DatabaseHandler;
import com.example.caloriecounter.model.MacroNutrient;
import com.example.caloriecounter.model.food_Item;
import com.example.caloriecounter.sqlite.DatabaseException;

import java.util.List;

import javax.crypto.Mac;

public class FoodDisplayAdapter extends RecyclerView.Adapter<FoodDisplayViewHolder> {

    private Context context;

    private FragmentManager fragManager;

    private List<food_Item> foodData;
    private List<MacroNutrient> macroData;

    public FoodDisplayAdapter(List<food_Item> foodData, List<MacroNutrient> macroData){

        this.foodData=foodData;
        this.macroData=macroData;
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
       holder.set(foodData.get(position));
    }

    @Override
    public int getItemCount() {
        return foodData.size();
    }


    public void setFoodItem(food_Item food, MacroNutrient macro, DatabaseHandler dbh){


        try {


            dbh.get_Macro_Nutrient_Table().create(macro);
            macroData=dbh.get_Macro_Nutrient_Table().readAll();

            food.setMacroNutrient((long)macroData.size());
            foodData.add(food);
            dbh.get_Food_item_Table().create(food);
        } catch (DatabaseException e) {

        }


    }
}
