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
import com.example.onlinemarket.databinding.FragmentHomeBinding;
import com.example.onlinemarket.view.activity.WholeProductsActivity;
import com.example.onlinemarket.viewmodel.HomeFragmentViewModel;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding mBinding;
    private HomeFragmentViewModel mViewModel;
    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(HomeFragmentViewModel.class);
        mViewModel.initAdapters();

        mViewModel.getOfferedProductsLiveData().observe(this , products -> {
            mViewModel.getLatestProductsAdapter().notifyDataSetChanged();
        });

        mViewModel.getLatestProductsLiveData().observe(this , products -> {
            mViewModel.getLatestProductsAdapter().notifyDataSetChanged();
        });

        mViewModel.getTopRatingProductsLiveData().observe(this,products -> {
            mViewModel.getTopRatingProductsAdapter().notifyDataSetChanged();
        });

        mViewModel.getPopularProductsLiveData().observe(this,products -> {
            mViewModel.getPopularProductsAdapter().notifyDataSetChanged();
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_home,
                container,
                false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding.recyclerViewOfferedProduct.setAdapter(mViewModel.getOfferedProductsAdapter());
        mBinding.recyclerViewLatestProduct.setAdapter(mViewModel.getLatestProductsAdapter());
        mBinding.recyclerViewTopRatingProduct.setAdapter(mViewModel.getTopRatingProductsAdapter());
        mBinding.recyclerViewPopularProduct.setAdapter(mViewModel.getPopularProductsAdapter());

        setListeners();
    }
    private void setListeners(){
        mBinding.textViewWholeLatestProducts.setOnClickListener(v -> {
            startActivity(WholeProductsActivity.newIntent(getContext(),"date"));
        });
        mBinding.textViewWholePopularProducts.setOnClickListener(v -> {
            startActivity(WholeProductsActivity.newIntent(getContext(),"popularity"));

        });

        mBinding.textViewWholeTopRatingProducts.setOnClickListener(v -> {
            startActivity(WholeProductsActivity.newIntent(getContext(),"rating"));
        });
    }


}