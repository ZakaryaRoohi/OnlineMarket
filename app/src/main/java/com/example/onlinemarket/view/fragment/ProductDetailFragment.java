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
import com.example.onlinemarket.data.model.product.Product;
import com.example.onlinemarket.databinding.FragmentProductDetailBinding;
import com.example.onlinemarket.util.ImageUtil;
import com.example.onlinemarket.viewmodel.ProductDetailViewModel;

public class ProductDetailFragment extends Fragment {

    private ImageSliderAdapter mImageSliderAdapter;
    private ProductDetailViewModel mViewModel;
    private FragmentProductDetailBinding mBinding;
    private Product mProduct;

    public ProductDetailFragment() {
        // Required empty public constructor
    }

    public static ProductDetailFragment newInstance() {
        return new ProductDetailFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        assert getArguments() != null;
        mProduct = ProductDetailFragmentArgs.fromBundle(getArguments()).getProduct();

        mViewModel = new ViewModelProvider(this).get(ProductDetailViewModel.class);

        mViewModel.fetchProductById(mProduct.getId());


        mViewModel.getConnectionStateLiveData().observe(this, connectionState -> {
            switch (connectionState) {
                case LOADING:
                    showLoadingUi();
                    break;
                case ERROR:
                    loadInternetError();
                    break;
                case START_ACTIVITY:
                    setUpSlider();
                    mBinding.setViewModel(mViewModel);
                    mBinding.loadingView.getRoot().setVisibility(View.GONE);
                    mBinding.mainView.setVisibility(View.VISIBLE);
                    break;
                default:
                    break;
            }
        });

    }

    private void setUpSlider() {
        mImageSliderAdapter = new ImageSliderAdapter(getContext());
        mImageSliderAdapter
                .setStringImageUrl(
                        ImageUtil.getAllImageUrlOfProduct(
                                mViewModel.getProductMutableLiveData().getValue()));
        mBinding.imageSliderProductDetailImages.setSliderAdapter(mImageSliderAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil
                .inflate(inflater,
                        R.layout.fragment_product_detail,
                        container,
                        false);

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBinding.loadingView.buttonRetry.setOnClickListener(v -> {
            mViewModel.fetchProductById(mProduct.getId());
            showLoadingUi();
        });

    }

    private void showLoadingUi() {
        mBinding.loadingView.buttonRetry.setVisibility(View.GONE);
        mBinding.loadingView.textViewNoInternet.setVisibility(View.GONE);
        mBinding.loadingView.progressBarLoadingFragment.setVisibility(View.VISIBLE);
        mBinding.loadingView.progressBarLoadingFragment.show();
    }


    private void loadInternetError() {
        mBinding.loadingView.buttonRetry.setVisibility(View.VISIBLE);
        mBinding.loadingView.textViewNoInternet.setVisibility(View.VISIBLE);
        mBinding.loadingView.progressBarLoadingFragment.setVisibility(View.GONE);
        mBinding.loadingView.progressBarLoadingFragment.hide();
    }

}