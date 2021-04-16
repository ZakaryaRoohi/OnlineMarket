package com.example.onlinemarket.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlinemarket.R;
import com.example.onlinemarket.adapter.ProductRecyclerAdapter;
import com.example.onlinemarket.databinding.FragmentHomeBinding;
import com.example.onlinemarket.viewmodel.HomeFragmentViewModel;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding mBinding;
    private HomeFragmentViewModel mViewModel;

    private ProductRecyclerAdapter mOfferedProductsAdapter;
    private ProductRecyclerAdapter mLatestProductsAdapter;
    private ProductRecyclerAdapter mTopRatingProductsAdapter;
    private ProductRecyclerAdapter mPopularProductsAdapter;


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
//        mViewModel.initAdapters();

        mViewModel.fetchDataFromRepository();
        initAdapters();

        mViewModel.getOfferedProductsLiveData().observe(this, products -> {
//            mViewModel.getOfferedProductsAdapter().notifyDataSetChanged();
            mOfferedProductsAdapter.notifyDataSetChanged();
        });

        mViewModel.getLatestProductsLiveData().observe(this, products -> {
//            mViewModel.getLatestProductsAdapter().notifyDataSetChanged();
            mLatestProductsAdapter.notifyDataSetChanged();
        });

        mViewModel.getTopRatingProductsLiveData().observe(this, products -> {
//            mViewModel.getTopRatingProductsAdapter().notifyDataSetChanged();
            mTopRatingProductsAdapter.notifyDataSetChanged();
        });

        mViewModel.getPopularProductsLiveData().observe(this, products -> {
//            mViewModel.getOfferedProductsAdapter().notifyDataSetChanged();
            mPopularProductsAdapter.notifyDataSetChanged();
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBinding.recyclerViewOfferedProduct.setAdapter(mOfferedProductsAdapter);
        mBinding.recyclerViewOfferedProduct.setAdapter(mLatestProductsAdapter);
        mBinding.recyclerViewOfferedProduct.setAdapter(mTopRatingProductsAdapter);
        mBinding.recyclerViewOfferedProduct.setAdapter(mPopularProductsAdapter);

        setListeners();


    }

    private void setListeners() {
        mBinding.textViewWholeLatestProducts.setOnClickListener(v -> {

            HomeFragmentDirections.ActionHomeFragmentToWholeProductsFragment action = HomeFragmentDirections
                    .actionHomeFragmentToWholeProductsFragment("date");

            Navigation.findNavController(v)
                    .navigate(action);

        });

        mBinding.textViewWholePopularProducts.setOnClickListener(v -> {
            HomeFragmentDirections.ActionHomeFragmentToWholeProductsFragment action = HomeFragmentDirections
                    .actionHomeFragmentToWholeProductsFragment("popularity");

            Navigation.findNavController(v)
                    .navigate(action);
        });

        mBinding.textViewWholeTopRatingProducts.setOnClickListener(v -> {
            HomeFragmentDirections.ActionHomeFragmentToWholeProductsFragment action = HomeFragmentDirections
                    .actionHomeFragmentToWholeProductsFragment("rating");

            Navigation.findNavController(v)
                    .navigate(action);

        });
    }

    public void initAdapters(){
        mOfferedProductsAdapter = new ProductRecyclerAdapter(getContext());
        mOfferedProductsAdapter.setProducts(mViewModel.getOfferedProductsLiveData().getValue());

        mLatestProductsAdapter = new ProductRecyclerAdapter(getContext());
        mLatestProductsAdapter.setProducts(mViewModel.getLatestProductsLiveData().getValue());

        mTopRatingProductsAdapter = new ProductRecyclerAdapter(getContext());
        mTopRatingProductsAdapter.setProducts(mViewModel.getTopRatingProductsLiveData().getValue());

        mPopularProductsAdapter = new ProductRecyclerAdapter(getContext());
        mPopularProductsAdapter.setProducts(mViewModel.getPopularProductsLiveData().getValue());
    }


}