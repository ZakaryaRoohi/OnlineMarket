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
import com.example.onlinemarket.data.repository.ProductRepository;
import com.example.onlinemarket.data.model.Product;
import com.example.onlinemarket.databinding.FragmentSplashBinding;
import com.example.onlinemarket.network.RetrofitInstance;
import com.example.onlinemarket.network.WooCommerceApi;
import com.example.onlinemarket.view.activity.HomeActivity;
import com.example.onlinemarket.view.activity.MainActivity;
import com.example.onlinemarket.viewmodel.SplashFragmentViewModel;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SplashFragment extends Fragment {

//    private WooCommerceApi mWooCommerceApi;
//    private ProductRepository mProductRepository;
    private SplashFragmentViewModel mViewModel;
    private FragmentSplashBinding mBinding;

    public SplashFragment() {
        // Required empty public constructor
    }


    public static SplashFragment newInstance() {
        SplashFragment fragment = new SplashFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(SplashFragmentViewModel.class);
        mViewModel.fetchInitData();

        mViewModel.getIsLoading().observe(this,aBoolean -> {
            if (!aBoolean){
                loadInternetError();
            }
        });

        mViewModel.getIsError().observe(this , aBoolean -> {
            if (aBoolean)
                loadInternetError();
        });

        mViewModel.getStartMainActivity().observe(this , aBoolean -> {
            if (aBoolean){
                Objects.requireNonNull(getActivity())
                        .startActivity(HomeActivity.newIntent(getContext()));
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_splash,
                container,
                false);
        return mBinding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBinding.textViewRetry.setOnClickListener(v -> {
            mViewModel.getIsError().setValue(false);
            mViewModel.getIsLoading().setValue(true);
            mViewModel.fetchInitData();
            showLoadingUi();
        });
    }

  private void showLoadingUi(){
        mBinding.textViewRetry.setVisibility(View.GONE);
        mBinding.textViewNoInternet.setVisibility(View.GONE);
        mBinding.progressBar.setVisibility(View.VISIBLE);
        mBinding.progressBar.show();
  }

    private void loadInternetError() {
        mBinding.textViewNoInternet.setVisibility(View.VISIBLE);
        mBinding.textViewRetry.setVisibility(View.VISIBLE);
    }


}