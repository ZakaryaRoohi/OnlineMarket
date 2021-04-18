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
import com.example.onlinemarket.adapter.ImageSliderAdapter;
import com.example.onlinemarket.databinding.FragmentProductDetailBinding;
import com.example.onlinemarket.viewmodel.ProductDetailViewModel;

public class ProductDetailFragment extends Fragment {


    private ImageSliderAdapter mImageSliderAdapter;
    private ProductDetailViewModel mViewModel;
    private FragmentProductDetailBinding mBinding;

    public ProductDetailFragment() {
        // Required empty public constructor
    }

    public static ProductDetailFragment newInstance() {
        return new ProductDetailFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(ProductDetailViewModel.class);
        mViewModel.setProductMutableLiveData
                (ProductDetailFragmentArgs.fromBundle(getArguments()).getProduct());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil
                .inflate(inflater,
                        R.layout.fragment_product_detail,
                        container,
                        false);

        mBinding.setViewModel(mViewModel);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}