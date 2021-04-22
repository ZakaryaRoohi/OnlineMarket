package com.example.onlinemarket.view.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import com.example.onlinemarket.R;
import com.example.onlinemarket.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;
    private BroadcastReceiver mInternetState;

    public static Intent newIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        NavController navController = Navigation.findNavController(this, R.id.main_nav_host);
        mBinding.bottomNavigation.setSelectedItemId(R.id.nav_fragHome);
        NavigationUI.setupWithNavController(mBinding.bottomNavigation, navController);

        mInternetState = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (!internetConnected(MainActivity.this)) {
                    Snackbar snackbar = Snackbar.make(mBinding.getRoot(), R.string.no_internet, BaseTransientBottomBar.LENGTH_SHORT);
                    View snackBarView = snackbar.getView();
                    snackBarView.setTranslationY(-(convertDpToPixel(48, MainActivity.this)));
                    snackbar.show();
                }
            }
        };

    }

    public static float convertDpToPixel(float dp, Context context) {
        return dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);

    }

    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(mInternetState, new IntentFilter("android.net.com.CONNECTIVITY_CHANGE"));
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(mInternetState);
    }

    private boolean internetConnected(Context context) {
        try {
            ConnectivityManager connectivityManager =
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return (networkInfo != null && networkInfo.isConnected());
        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }
}