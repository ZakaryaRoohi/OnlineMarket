package com.example.onlinemarket.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.onlinemarket.adapter.WholeProductsAdapter;
import com.example.onlinemarket.data.model.Product;
import com.example.onlinemarket.data.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class WholeProductFragmentViewModel extends ViewModel {

    private final ProductRepository mProductRepository;
    private final MutableLiveData<List<Product>> mProducts = new MutableLiveData<>();

    public WholeProductFragmentViewModel(){
        mProductRepository = ProductRepository.getInstance();
    }


    public void fetchDataFromRepository(String orderBy) {
        switch (orderBy) {
            case "date":
                mProducts.setValue(mProductRepository.getLatestProductsLiveData().getValue());
                break;
            case "popularity":
                mProducts.setValue(mProductRepository.getPopularProductsLiveData().getValue());
                break;
            case "rating":
                mProducts.setValue(mProductRepository.getTopRatingProductsLiveData().getValue());
                break;
            default:
                break;
        }
    }


    public LiveData<List<Product>> getProducts(){
        return mProducts;
    }

}
