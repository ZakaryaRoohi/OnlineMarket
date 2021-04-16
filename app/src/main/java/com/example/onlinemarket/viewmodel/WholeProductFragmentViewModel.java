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
    private final MutableLiveData<String> mStringOrderByLiveData = new MutableLiveData<>();

    public WholeProductFragmentViewModel(){
        mProductRepository = ProductRepository.getInstance();
    }


    public void fetchDataFromRepository() {
        switch (mStringOrderByLiveData.getValue()) {
            case "date":
                mProducts.setValue(mProductRepository.getLatestProducts());
                break;
            case "popularity":
                mProducts.setValue(mProductRepository.getPopularProducts());
                break;
            case "rating":
                mProducts.setValue(mProductRepository.getTopRatingProducts());
                break;
            default:
                break;
        }
    }

    public MutableLiveData<String> getStringOrderByLiveData() {
        return mStringOrderByLiveData;
    }

    public LiveData<List<Product>> getProducts(){
        return mProducts;
    }

}
