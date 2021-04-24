package com.example.onlinemarket.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.onlinemarket.R;

public class FinishShoppingFragment extends Fragment {

    public FinishShoppingFragment() {
        // Required empty public constructor
    }


    public static FinishShoppingFragment newInstance() {
        return new FinishShoppingFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_finish_shopping, container, false);
    }
}