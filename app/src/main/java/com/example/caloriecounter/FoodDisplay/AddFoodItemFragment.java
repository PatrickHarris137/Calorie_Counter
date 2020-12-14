package com.example.caloriecounter.FoodDisplay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import com.example.caloriecounter.R;
import com.example.caloriecounter.model.Category;
import com.example.caloriecounter.model.MacroNutrient;
import com.example.caloriecounter.model.food_Item;

public class AddFoodItemFragment extends Fragment {

    private EditText nameEditText;
    private EditText categoryEditText;
    private EditText servingEditText;
    private EditText caloriesEditText;
    private EditText proteinEditText;
    private EditText cholesterolEditText;
    private EditText unsaturatedEditText;
    private EditText saturatedEditText;
    private EditText transEditText;
    private EditText fiberEditText;
    private EditText sugarEditText;
    private EditText sodiumEditText;

    private Button addFoodSubmitButton;

    private food_Item newFood;
    private MacroNutrient newMacro;
    private AddFoodItemActivity addFoodItemActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root=inflater.inflate(R.layout.fragment_add_food_item, container, false);

        addFoodItemActivity=(AddFoodItemActivity) getActivity();

        nameEditText=root.findViewById(R.id.name_EditText);
        categoryEditText=root.findViewById(R.id.category_EditText);
        servingEditText=root.findViewById(R.id.serving_EditText);
        caloriesEditText=root.findViewById(R.id.calories_EditText);
        proteinEditText=root.findViewById(R.id.protein_EditText);
        cholesterolEditText=root.findViewById(R.id.cholesterol_EditText);
        unsaturatedEditText=root.findViewById(R.id.unsaturated_EditText);
        saturatedEditText=root.findViewById(R.id.saturated_EditText);
        transEditText=root.findViewById(R.id.transfat_EditText);
        fiberEditText=root.findViewById(R.id.fiber_EditText);
        sugarEditText=root.findViewById(R.id.sugar_EditText);
        sodiumEditText=root.findViewById(R.id.sodium_EditText);


        servingEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
        caloriesEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
        proteinEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
        cholesterolEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
        unsaturatedEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
        saturatedEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
        transEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
        fiberEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
        sugarEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
        sodiumEditText.setInputType(InputType.TYPE_CLASS_NUMBER);


        addFoodSubmitButton=root.findViewById(R.id.addFoodSubmit_Button);

        addFoodSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               newFood=new food_Item();
               newMacro=new MacroNutrient();

               newFood.setName(nameEditText.getText().toString());
               newFood.setCategory(Category.valueOf(categoryEditText.getText().toString()));
               newFood.setServing_Size(Integer.parseInt(servingEditText.getText().toString()));
               newFood.setCalories(Integer.parseInt(caloriesEditText.getText().toString()));

               newMacro.setProtein(Double.parseDouble(proteinEditText.getText().toString()));
               newMacro.setCholesterol(Double.parseDouble(cholesterolEditText.getText().toString()));
               newMacro.setFiber(Double.parseDouble(fiberEditText.getText().toString()));
               newMacro.setSugar(Double.parseDouble(sugarEditText.getText().toString()));
               newMacro.setSodium(Double.parseDouble(sodiumEditText.getText().toString()));
               newMacro.setSaturatedFat(Double.parseDouble(saturatedEditText.getText().toString()));
               newMacro.setUnsaturatedFat(Double.parseDouble(unsaturatedEditText.getText().toString()));
               newMacro.setTrans_fat(Double.parseDouble(proteinEditText.getText().toString()));

               Intent intent = addFoodItemActivity.getIntent();
               intent.putExtra("newFood",newFood);
               //intent.putExtra("newMacro",newMacro);
               addFoodItemActivity.setResult(Activity.RESULT_OK,intent);
               addFoodItemActivity.finish();
            }
        });


        return root;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public food_Item getNewFood(){
        return newFood;
    }

    public MacroNutrient getMacro(){
        return newMacro;
    }
}