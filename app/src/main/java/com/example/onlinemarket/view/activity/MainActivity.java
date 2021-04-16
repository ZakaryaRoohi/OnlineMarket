package com.example.onlinemarket.view.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.example.onlinemarket.R;
import com.example.onlinemarket.databinding.ActivityMainBinding;

public  class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;
    public static Intent newIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        NavController navController = Navigation.findNavController(this, R.id.main_nav_host);
       mBinding.navigationButton.setSelectedItemId(R.id.nav_fragHome);
        NavigationUI.setupWithNavController(mBinding.navigationButton,navController);
    }



}