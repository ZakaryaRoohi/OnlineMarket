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
import com.example.onlinemarket.databinding.FragmentLoadingBinding;
import com.example.onlinemarket.util.enums.ConnectionState;
import com.example.onlinemarket.viewmodel.ProductDetailViewModel;

public class ProductDetailLoadingFragment extends Fragment {

    private FragmentLoadingBinding mBinding;

    private Integer mProductId;
    private ProductDetailViewModel mViewModel;

    public ProductDetailLoadingFragment() {
        // Required empty public constructor
    }

    public static ProductDetailLoadingFragment newInstance() {
        return new ProductDetailLoadingFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        assert getArguments() != null;
        mProductId = ProductDetailLoadingFragmentArgs.fromBundle(getArguments()).getProductId();
        mViewModel = new ViewModelProvider(this).get(ProductDetailViewModel.class);

        mViewModel.fetchProductById(mProductId);

        mViewModel.getConnectionStateLiveData().observe(this, connectionState -> {
            switch (connectionState) {
                case LOADING:
                    showLoadingUi();
                    break;
                case ERROR:
                    loadInternetError();
                    break;
                case START_ACTIVITY:
                    ProductDetailLoadingFragmentDirections.ActionLoadingFragmentToProductDetailFragment action =
                            ProductDetailLoadingFragmentDirections
                                    .actionLoadingFragmentToProductDetailFragment
                                            (mViewModel.getProductMutableLiveData().getValue());
                    Navigation.findNavController(getView()).navigate(action);
                    break;
                default:
                    break;
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.
                inflate(inflater, R.layout.fragment_loading, container, false);


        return inflater.inflate(R.layout.fragment_loading, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding.buttonRetry.setOnClickListener(v -> {
            mViewModel.fetchProductById(mProductId);
            mViewModel.getConnectionStateLiveData().setValue(ConnectionState.LOADING);
            showLoadingUi();
        });
    }

    private void showLoadingUi() {
        mBinding.buttonRetry.setVisibility(View.GONE);
        mBinding.textViewNoInternet.setVisibility(View.GONE);
        mBinding.progressBarLoadingFragment.setVisibility(View.VISIBLE);
        mBinding.progressBarLoadingFragment.show();
    }


    private void loadInternetError() {
        mBinding.textViewNoInternet.setVisibility(View.VISIBLE);
        mBinding.buttonRetry.setVisibility(View.VISIBLE);
        mBinding.progressBarLoadingFragment.setVisibility(View.GONE);
        mBinding.progressBarLoadingFragment.hide();
    }

}