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
import com.example.onlinemarket.util.enums.ConnectionState;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailViewModel extends AndroidViewModel {

    private final ProductRepository mProductRepository;


    public ProductDetailViewModel(@NonNull Application application) {
        super(application);
        mProductRepository = ProductRepository.getInstance();
    }


    public void fetchProductById(Integer productId) {
        mProductRepository.fetchProductById(productId);
    }

    public LiveData<Product> getProductMutableLiveData() {
        return mProductRepository.getProductByIdMutableLiveData();
    }

    public MutableLiveData<ConnectionState> getConnectionStateLiveData() {
        return mProductRepository.getConnectionStateLiveData();
    }
}
