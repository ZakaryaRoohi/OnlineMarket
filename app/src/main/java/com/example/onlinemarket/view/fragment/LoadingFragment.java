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
import com.example.onlinemarket.adapter.ImageSliderAdapter;
import com.example.onlinemarket.databinding.FragmentLoadingBinding;
import com.example.onlinemarket.viewmodel.ProductDetailViewModel;
public class LoadingFragment extends Fragment {

    private FragmentLoadingBinding mBinding;
    private Integer mProductId;
    private ImageSliderAdapter mImageSliderAdapter;
    private ProductDetailViewModel mViewModel;


    public LoadingFragment() {
        // Required empty public constructor
    }

    public static LoadingFragment newInstance() {
        LoadingFragment fragment = new LoadingFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProductId = LoadingFragmentArgs.fromBundle(getArguments()).getProductId();
        mViewModel = new ViewModelProvider(this).get(ProductDetailViewModel.class);

        mViewModel.fetchProductFromServer(mProductId);
        mViewModel.getStartNavigationLiveData().observe(this, aBoolean -> {

            if (aBoolean) {
                LoadingFragmentDirections.ActionLoadingFragmentToProductDetailFragment action =
                        LoadingFragmentDirections
                                .actionLoadingFragmentToProductDetailFragment
                                        (mViewModel.getProductMutableLiveData().getValue());
                Navigation.findNavController(getView()).navigate(action);
            }
        });
        mViewModel.getIsError().observe(this, aBoolean -> {
            if (aBoolean)
                loadInternetError();
        });
        mViewModel.getIsLoading().observe(this, aBoolean -> {
            if (!aBoolean)
                loadInternetError();
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.
                inflate(inflater, R.layout.fragment_loading, container, false);


        return inflater.inflate(R.layout.fragment_loading, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding.buttonRetry.setOnClickListener(v -> {
            mViewModel.fetchProductFromServer(mProductId);
            mViewModel.getIsError().setValue(false);
            mViewModel.getIsLoading().setValue(true);
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