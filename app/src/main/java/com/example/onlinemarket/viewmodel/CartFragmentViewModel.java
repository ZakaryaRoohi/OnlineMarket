package com.example.onlinemarket.viewmodel;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.onlinemarket.data.database.entity.CartProduct;
import com.example.onlinemarket.data.model.Product;
import com.example.onlinemarket.data.repository.CartRepository;
import com.example.onlinemarket.data.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class CartFragmentViewModel extends AndroidViewModel {

    private final ProductRepository mProductRepository;
    private final CartRepository mCartRepository;
    private final MutableLiveData<List<Product>> mProductLiveData;

    public CartFragmentViewModel(@NonNull Application application) {
        super(application);

        mProductRepository = ProductRepository.getInstance();
        mCartRepository = CartRepository.getInstance(application);
        mProductLiveData = new MutableLiveData<>();
        mProductLiveData.postValue(allCartProducts(mCartRepository.getAllProducts()));
    }

    public List<Product> allCartProducts(List<CartProduct> cartProducts) {

        List<Product> products = new ArrayList<>();
        for (CartProduct cartproduct : cartProducts) {
            products.add(mProductRepository.findProductById(cartproduct.mId));
        }
        return products;
    }

    public LiveData<List<Product>> getProductsLiveData() {
        return mProductLiveData;
    }
}
