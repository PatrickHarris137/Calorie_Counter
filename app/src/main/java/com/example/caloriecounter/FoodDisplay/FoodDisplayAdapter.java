package com.example.caloriecounter.FoodDisplay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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


       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               //grab the location of the itemview on the screen
               int [] location = new int[2];
               view.getLocationOnScreen(location);

               //item view's x and y coordinate
               int xPos = location[0];
               int yPos = location[1];

               //itemview's height and width
               int itemViewHeight = view.getHeight();
               int itemViewWidth = view.getWidth();


               //pop up the action mode
               Activity activity = (Activity) view.getContext();
               activity.startActionMode(new ActionMode.Callback2() {

                   //Set where the action mode is going to show up
                   @Override
                   public void onGetContentRect(ActionMode mode, View view, Rect outRect) {
                       outRect.set(xPos,yPos+itemViewHeight/2,xPos+itemViewWidth,yPos+itemViewHeight);
                   }

                   //Create the action mode
                   @Override
                   public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                       MenuInflater inflater = actionMode.getMenuInflater();
                       inflater.inflate(R.menu.menu_action_mode,menu);
                       return true;
                   }

                   @Override
                   public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                       return false;
                   }

                   //Choose a selection on the actionMode
                   @Override
                   public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {

                       Activity intentActivity= (Activity)view.getContext();
                       switch (menuItem.getItemId()){

                           //Reminder
                           case R.id.action_add:
                               Intent intent=intentActivity.getIntent();
                               intent.putExtra("foodToAdd", foodData.get(position));
                               intent.putExtra("returnType","FoodDisplay");
                               intentActivity.setResult(intentActivity.RESULT_OK, intent);
                               intentActivity.finish();
                               break;


                           case R.id.action_edit:
                               food_Item food=foodData.get(position);
                               MacroNutrient macro=macroData.get(position);



                               Intent editIntent = new Intent(activity, AddFoodItemActivity.class);
                               editIntent.putExtra("newFood", food);
                               editIntent.putExtra("newMacro",macro);
                               editIntent.putExtra("method", "edit");
                               activity.startActivityForResult(editIntent,1);


                               break;

                           //Close
                           case R.id.action_close:
                               break;
                       }
                       actionMode.finish();
                       return true;
                   }

                   @Override
                   public void onDestroyActionMode(ActionMode actionMode) {

                   }
               }, ActionMode.TYPE_FLOATING);

           }
       });
    }

    @Override
    public int getItemCount() {
        return foodData.size();
    }


    public void setFoodItem(food_Item food, MacroNutrient macro, DatabaseHandler dbh, String method){


        if(method.equals("add")){
            try {
                dbh.get_Macro_Nutrient_Table().create(macro);
                macroData=dbh.get_Macro_Nutrient_Table().readAll();

                food.setMacro_Id((long)macroData.size());
                foodData.add(food);
                dbh.get_Food_Item_Table().create(food);
            } catch (DatabaseException e) {

            }
        }
        else if(method.equals("edit")){
            try {

                for(int i=0; i<macroData.size(); i++){
                    if(macroData.get(i).getId()==macro.getId()){
                        macroData.set(i,macro);
                        break;
                    }
                }
                dbh.get_Macro_Nutrient_Table().update(macro);
                
                food.setMacro_Id(macro.getId());

                for(int i=0; i<foodData.size(); i++){
                   if(foodData.get(i).getId()==food.getId()){
                       foodData.set(i,food);
                       break;
                   }
                }
                dbh.get_Food_Item_Table().update(food);

            } catch (DatabaseException e) {

            }
        }

    }
}
