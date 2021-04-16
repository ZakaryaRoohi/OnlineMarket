package com.example.onlinemarket.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.onlinemarket.data.model.Product;
import com.example.onlinemarket.data.repository.ProductRepository;
import com.example.onlinemarket.network.RetrofitInstance;
import com.example.onlinemarket.network.WooApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashFragmentViewModel extends AndroidViewModel {


    private final ProductRepository mProductRepository;
    private final WooApi mWooApi;

    private final MutableLiveData<Boolean> mIsLoading = new MutableLiveData<>();
    private final MutableLiveData<Boolean> mIsError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> mStartMainActivity = new MutableLiveData<>();

    public SplashFragmentViewModel(@NonNull Application application) {
        super(application);
        mProductRepository = ProductRepository.getInstance();
        mWooApi = RetrofitInstance.getInstance().create(WooApi.class);
    }


    private void initInternetError() {
        mIsLoading.setValue(false);
        mIsError.setValue(true);
    }

    public void fetchInitData() {
        mIsLoading.setValue(true);
        mIsError.setValue(false);
        mStartMainActivity.setValue(false);
        //offered products
        mWooApi.getSaleProducts(10, 1).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    mProductRepository.setOfferedProducts(response.body());
                    fetchLatestProducts();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                initInternetError();
            }
        });
    }


    private void fetchLatestProducts() {
        mWooApi.getProducts(10, 1, "date").enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    mProductRepository.setLatestProducts(response.body());
                    //top rating products
                    fetchBestProducts();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                initInternetError();
            }
        });
    }

    private void fetchBestProducts() {
        mWooApi.getProducts(10, 1, "rating").enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    mProductRepository.setTopRatingProducts(response.body());
                    //last step of fetch from api
                    fetchPopularProducts();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                initInternetError();
            }
        });
    }

    private void fetchPopularProducts() {
        mWooApi.getProducts(10, 1, "popularity").enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    mProductRepository.setPopularProducts(response.body());
                    //live data flag to start activity in Ui (SplashFragment) with observe this field
                    mStartMainActivity.setValue(true);
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                initInternetError();
            }
        });
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return mIsLoading;
    }

    public MutableLiveData<Boolean> getIsError() {
        return mIsError;
    }

    public LiveData<Boolean> getStartMainActivity() {
        return mStartMainActivity;
    }
}