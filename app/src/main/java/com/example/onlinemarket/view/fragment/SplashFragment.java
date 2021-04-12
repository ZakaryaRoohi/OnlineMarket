package com.example.onlinemarket.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlinemarket.R;
import com.example.onlinemarket.data.database.ProductRepository;
import com.example.onlinemarket.data.model.Product;
import com.example.onlinemarket.databinding.FragmentSplashBinding;
import com.example.onlinemarket.network.RetrofitInstance;
import com.example.onlinemarket.network.WooCommerceApi;
import com.example.onlinemarket.view.activity.MainActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SplashFragment extends Fragment {

    private WooCommerceApi mWooCommerceApi;
    private ProductRepository mProductRepository;
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
        mProductRepository = ProductRepository.getInstance();
        mWooCommerceApi = RetrofitInstance
                .getInstance()
                .create(WooCommerceApi.class);
        requestForOfferedProducts();

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
        mBinding.textViewRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.textViewRetry.setVisibility(View.GONE);
                mBinding.textViewNoInternet.setVisibility(View.GONE);
                mBinding.progressBar.setVisibility(View.VISIBLE);
                mBinding.progressBar.show();
                requestForOfferedProducts();
            }
        });
    }

    private void requestForOfferedProducts() {
        mWooCommerceApi.getSaleProduct(8, 1).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(response.isSuccessful()){
                    mProductRepository.setOfferProducts(response.body());
                    mBinding.progressBar.setVisibility(View.GONE);
                    mBinding.progressBar.hide();
                    startActivity(MainActivity.newIntent(getContext()));
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                mBinding.textViewNoInternet.setVisibility(View.VISIBLE);
                mBinding.textViewRetry.setVisibility(View.VISIBLE);
            }
        });
    }
}