package com.example.caloriecounter.DailyConsumption;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.caloriecounter.R;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.fragment.NavHostFragment;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DailyConsumptionFragment extends Fragment {
    public TextView dayOfWeek;
    private View root;
    private Button breakfast_Button;
    private Button lunch_Button;
    private Button dinner_Button;
    private Button snacks_Button;
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_daily_consumption, container, false);
        dayOfWeek=root.findViewById(R.id.day_TextView);
        dayOfWeek.setText(getToday());

        breakfast_Button=root.findViewById(R.id.breakfast_Button);
        lunch_Button=root.findViewById(R.id.lunch_Button);
        dinner_Button=root.findViewById(R.id.dinner_Button);
        snacks_Button=root.findViewById(R.id.snacks_Button);
        breakfast_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"breakfast",Toast.LENGTH_SHORT).show();
            }
        });
        lunch_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"lunch",Toast.LENGTH_SHORT).show();
            }
        });
        dinner_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"dinner",Toast.LENGTH_SHORT).show();
            }
        });
        snacks_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"snacks",Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }
    public String getToday(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");

        String today = simpleDateFormat.format(new Date());
        return today;
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}