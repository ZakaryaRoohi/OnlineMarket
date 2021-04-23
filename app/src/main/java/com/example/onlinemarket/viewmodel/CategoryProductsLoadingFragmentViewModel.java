package com.example.onlinemarket.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.onlinemarket.data.model.product.Product;
import com.example.onlinemarket.data.repository.ProductRepository;
import com.example.onlinemarket.util.enums.ConnectionState;

import java.util.List;

public class CategoryProductsLoadingFragmentViewModel extends ViewModel {
    private final ProductRepository mProductRepository;

    public CategoryProductsLoadingFragmentViewModel() {
        mProductRepository = ProductRepository.getInstance();
    }
    public void fetchCategoryProducts(Integer productId){
        mProductRepository.fetchCategoryProducts(productId);
    }
    public LiveData<List<Product>> getCategoryProducts() {
        return mProductRepository.getCategoryProductsLiveData();
    }

    public MutableLiveData<ConnectionState> getConnectionStateLiveData() {
        return mProductRepository.getConnectionStateLiveData();
    }
}

