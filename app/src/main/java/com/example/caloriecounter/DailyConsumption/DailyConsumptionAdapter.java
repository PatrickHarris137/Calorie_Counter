package com.example.caloriecounter.DailyConsumption;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caloriecounter.R;
import com.example.caloriecounter.model.DatabaseHandler;
import com.example.caloriecounter.model.food_Item;

import java.util.List;

public class DailyConsumptionAdapter extends RecyclerView.Adapter<DailyConsumptionAdapter.FoodViewHolder> {


    private  List<food_Item> mealsOfDayList;
    private DailyConsumptionAdapter adapter=this;
    private DailyConsumptionFragment dailyConsumptionFragment;

    public List<food_Item> getMealsOfDayList() {
        return mealsOfDayList;
    }
    public void addFoodItem(food_Item food){

        boolean isInList=false;

            for(int i=0; i<mealsOfDayList.size(); i++){
                if(mealsOfDayList.get(i).getName().equals(food.getName())){
                    mealsOfDayList.get(i).setServing_Size(mealsOfDayList.get(i).getServing_Size()+1);
                    isInList=true;
                    break;
                }
            }

            if(!isInList)
                mealsOfDayList.add(food);

        notifyDataSetChanged();
    }
    public DailyConsumptionAdapter(List<food_Item> mealsOfDayList,DailyConsumptionFragment dailyConsumptionFragment) {
        this.mealsOfDayList=mealsOfDayList;
        this.dailyConsumptionFragment=dailyConsumptionFragment;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FoodViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.dailyconsumption_list_item, parent, false),this
        );
    }

    @Override
    public void onBindViewHolder(@NonNull DailyConsumptionAdapter.FoodViewHolder holder, int position) {
        holder.set(mealsOfDayList.get(position), position);
    }
    @Override
    public int getItemCount() {
        return mealsOfDayList.size();
    }
    public class FoodViewHolder extends RecyclerView.ViewHolder {

        private TextView foodName_TextView;
        private TextView foodQuantity_TextView;
        private TextView foodCalorie_TextView;
        private int position;
        private int servingSize;
        private int calories;
        private food_Item food;
       // private Food food;
        private final View root;
        private final DatabaseHandler dbh;


        public FoodViewHolder(@NonNull final View root,final DailyConsumptionAdapter dailyConsumptionAdapter) {
            super(root);
            this.root=root;

            dbh = new DatabaseHandler(root.getContext());


            foodName_TextView = root.findViewById(R.id.foodName_TextView);
            foodQuantity_TextView = root.findViewById(R.id.foodQuantity_TextView);
            foodCalorie_TextView = root.findViewById(R.id.foodCalorie_TextView);
            ImageButton increaseButton = root.findViewById(R.id.increaseQuantity_Button);
            ImageButton decreaseButton = root.findViewById(R.id.decreaseQuantity_Button);
            increaseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mealsOfDayList.get(position).setServing_Size(servingSize+1);
                    dailyConsumptionAdapter.notifyDataSetChanged();
                    dailyConsumptionFragment.calculateTotalCalories(mealsOfDayList);
                }
            });
            decreaseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mealsOfDayList.get(position).getServing_Size()==0)
                    {
                        mealsOfDayList.remove(position);
                    }
                    else
                        mealsOfDayList.get(position).setServing_Size(servingSize-1);
                    dailyConsumptionAdapter.notifyDataSetChanged();
                    dailyConsumptionFragment.calculateTotalCalories(mealsOfDayList);

                }
            });

        }

        public void set(food_Item food, int position) {
            this.food = food;
            this.position = position;
            this.servingSize=food.getServing_Size();
            this.calories=food.getCalories();
            foodName_TextView.setText(food.getName());
            foodQuantity_TextView.setText(Integer.toString(food.getServing_Size()));
            foodCalorie_TextView.setText(Integer.toString(food.getCalories()*food.getServing_Size()));

        }

    }
}
