package com.example.onlinemarket.view.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.onlinemarket.R;
import com.example.onlinemarket.databinding.ActivityMainBinding;

public abstract class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.page_fragment_container);
        if (fragment == null) {
            fragmentManager.beginTransaction()
                    .add(R.id.page_fragment_container, createFragment(), getFragmentTag())
                    .commit();
        } else {
            fragmentManager.beginTransaction()
                    .replace(R.id.page_fragment_container, createFragment(), getFragmentTag())
                    .commit();
        }

        mBinding.bottomNavigation.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home_page:
                    HomeActivity.newIntent(MainActivity.this);
                    return true;
                case R.id.category_page:
                    //Todo create a category fragment
                    return true;
                case R.id.cart_page:
                    //Todo
                    return true;
                default:
                    return true;
            }
        });
    }

    protected abstract String getFragmentTag();

    protected abstract Fragment createFragment();

}