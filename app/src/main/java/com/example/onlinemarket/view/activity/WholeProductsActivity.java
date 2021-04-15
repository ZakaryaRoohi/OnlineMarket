package com.example.onlinemarket.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import com.example.onlinemarket.R;
import com.example.onlinemarket.data.model.Category;
import com.example.onlinemarket.view.fragment.WholeProductsFragment;

public class WholeProductsActivity extends MainActivity {

    public static final String WHOLE_PRODUCTS_FRAGMENT_TAG = "com.example.onlineMarket.wholeProductsFragment";
    public static final String EXTRA_ORDER_BY = "com.example.onlineMarket.orderBy";
    public static final String EXTRA_CATEGORY = "category";

    public static Intent newIntent(Context context, String orderBy) {
        Intent intent = new Intent(context, WholeProductsActivity.class);
        intent.putExtra(EXTRA_ORDER_BY, orderBy);
        return intent;
    }

    public static Intent newIntent(Context context, Category category) {
        Intent intent = new Intent(context, WholeProductsActivity.class);
        intent.putExtra(EXTRA_CATEGORY, (Parcelable) category);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected String getFragmentTag() {
        return WHOLE_PRODUCTS_FRAGMENT_TAG;
    }

    @Override
    protected Fragment createFragment() {
        return WholeProductsFragment.newInstance(getIntent().getStringExtra(EXTRA_ORDER_BY));

    }
}