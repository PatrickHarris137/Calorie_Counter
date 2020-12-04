package com.example.caloriecounter.DailyMacroCounter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.caloriecounter.FoodApplication;
import com.example.caloriecounter.R;
import com.example.caloriecounter.login.LoginDialogFragment;
import com.example.caloriecounter.login.LoginManager;
import com.example.caloriecounter.login.LoginManagerStub;
import com.example.caloriecounter.login.OnLoginListener;

public class DailyMacroCounterFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState

    ) {
        // Inflate the layout for this fragment
        //FoodApplication application =  (FoodApplication) getActivity().getApplication();
        final LoginDialogFragment dialogFragment = new LoginDialogFragment();
        LoginManager loginManager=new LoginManagerStub(getContext());
        loginManager.setOnLoginListener(new OnLoginListener() {
            @Override
            public void onLogin(String uuid) {
                dialogFragment.dismiss();
            }
            @Override
            public void onLogout() {

            }
            @Override
            public void onRegister(String uuid) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Account Created")
                        .setMessage("Please login with your new credentials")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .create()
                        .show();
            }

            @Override
            public void onError(String message) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Problem with login.")
                        .setMessage(message)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .create()
                        .show();
            }
        });

        if(!loginManager.isLoggedIn())
            dialogFragment.show(getChildFragmentManager(),"login-fragment");
        return inflater.inflate(R.layout.fragment_daily_macro_counter, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}