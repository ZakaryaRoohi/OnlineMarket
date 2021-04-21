package com.example.onlinemarket.viewmodel;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.onlinemarket.data.model.Product;
import com.example.onlinemarket.data.repository.CartRepository;

import java.util.List;

public class CartFragmentViewModel extends AndroidViewModel {

    private final CartRepository mCartRepository;
    private LiveData<List<Product>> mProductLiveData;

    public CartFragmentViewModel(@NonNull Application application) {
        super(application);

        mCartRepository = CartRepository.getInstance(getApplication());
        mProductLiveData = new MutableLiveData<>();
        fetchAllProducts();
    }

    public void fetchAllProducts() {
        mProductLiveData = mCartRepository.getAllFromCart();
    }

    public LiveData<List<Product>> getProductsLiveData() {
        return mProductLiveData;
    }
}
