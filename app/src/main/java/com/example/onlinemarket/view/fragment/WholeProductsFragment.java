package com.example.onlinemarket.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlinemarket.R;
import com.example.onlinemarket.databinding.FragmentWholeProductsBinding;
import com.example.onlinemarket.viewmodel.WholeProductFragmentViewModel;

public class WholeProductsFragment extends Fragment {


    private FragmentWholeProductsBinding mBinding;
    private WholeProductFragmentViewModel mViewModel;


    public WholeProductsFragment() {
        // Required empty public constructor
    }

    public static WholeProductsFragment newInstance() {
        WholeProductsFragment fragment = new WholeProductsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String orderBy = WholeProductsFragmentArgs.fromBundle(getArguments()).getOrderBy();

        mViewModel = new ViewModelProvider(this).get(WholeProductFragmentViewModel.class);

        mViewModel.getStringOrderByLiveData().observe(this, s -> {
            mViewModel.fetchDataFromRepository();
            mViewModel.getWholeProductsAdapter().notifyDataSetChanged();
        });

        mViewModel.getStringOrderByLiveData().setValue(orderBy);
        mViewModel.fetchDataFromRepository();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_whole_products, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel.initAdapter();

        mBinding.recyclerViewWholeProducts.setAdapter(mViewModel.getWholeProductsAdapter());


    }
}