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

    public static final String ARG_ORDER_BY = "com.example.onlineMarket.orderBy";
    private FragmentWholeProductsBinding mBinding;
    private WholeProductFragmentViewModel mViewModel;

    public WholeProductsFragment() {
        // Required empty public constructor
    }

    public static WholeProductsFragment newInstance(String orderBy) {
        WholeProductsFragment fragment = new WholeProductsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_ORDER_BY, orderBy);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(WholeProductFragmentViewModel.class);

        mViewModel.getStringMutableLiveData().observe(this, s -> {
            mViewModel.fetchDataFromRepository();
            mViewModel.getWholeProductsAdapter().notifyDataSetChanged();
        });

        mViewModel.getStringMutableLiveData().setValue(getArguments().getString(ARG_ORDER_BY));
        mViewModel.fetchDataFromRepository();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_whole_products,
                container,
                false
        );
        return mBinding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel.initAdapter();
        mBinding.recyclerViewWholeProducts.setAdapter(mViewModel.getWholeProductsAdapter());
    }
}