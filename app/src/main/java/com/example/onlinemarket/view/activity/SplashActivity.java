package com.example.onlinemarket.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.onlinemarket.view.fragment.SplashFragment;

public class SplashActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context context){
        return new Intent(context , SplashActivity.class);
    }
    @Override
    protected Fragment createFragment() {
        return SplashFragment.newInstance();
    }


}