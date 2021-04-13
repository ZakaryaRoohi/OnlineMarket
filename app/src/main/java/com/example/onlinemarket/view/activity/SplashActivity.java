package com.example.onlinemarket.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.onlinemarket.view.fragment.SplashFragment;

public class SplashActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return SplashFragment.newInstance();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (getIntent().getBooleanExtra("EXTRA", false)){
            finish();
        }
    }
}