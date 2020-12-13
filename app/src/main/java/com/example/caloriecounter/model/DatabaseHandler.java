package com.example.caloriecounter.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.caloriecounter.sqlite.Table;
import com.example.caloriecounter.sqlite.TableFactory;

public class DatabaseHandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "CalorieCounter.db";
    public static final String TABLE1 = "CalorieCounter.db";
    public static final String TABLE2 = "CalorieCounter.db";
    public static final String TABLE3 = "CalorieCounter.db";
    public static final String TABLE4 = "CalorieCounter.db";
    public static final int DATABASE_VERSION = 1;

    //Tables
    private final Table<food_Item> food_item_Table;
    private final Table<MacroNutrient> macro_nutrient_table;
    private final Table<User_Food_Item> user_food_item_table;
    private final Table<User_Daily_Consumption> user_daily_consumption_table;
    private final Table<user> user_table;


    public DatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);


        food_item_Table = TableFactory.makeFactory(this,food_Item.class)
                .withSeedData(SampleData.generateFoodDisplayList())
                .build();
        macro_nutrient_table = TableFactory.makeFactory(this,MacroNutrient.class)
                .build();
        user_food_item_table = TableFactory.makeFactory(this,User_Food_Item.class)
                .build();
        user_daily_consumption_table = TableFactory.makeFactory(this,User_Daily_Consumption.class)
                .build();
        user_table = TableFactory.makeFactory(this,user.class)
                .build();

    }

    public Table<food_Item> getFood_item_Table() {
        return food_item_Table;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        user_table.createTable(db);
        food_item_Table.createTable(db);
        macro_nutrient_table.createTable(db);

        user_food_item_table.createTable(db);
        user_daily_consumption_table.createTable(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        food_item_Table.dropTable(db);
        user_table.dropTable(db);
        macro_nutrient_table.dropTable(db);
        user_food_item_table.dropTable(db);
        user_daily_consumption_table.dropTable(db);
        this.onCreate(db);
    }
}