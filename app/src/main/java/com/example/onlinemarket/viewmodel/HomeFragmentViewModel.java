package com.example.onlinemarket.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.onlinemarket.adapter.ProductRecyclerAdapter;
import com.example.onlinemarket.data.model.Product;
import com.example.onlinemarket.data.repository.ProductRepository;

import java.util.List;

public class HomeFragmentViewModel extends ViewModel {

    private final ProductRepository mProductRepository;

   public HomeFragmentViewModel(){
       mProductRepository = ProductRepository.getInstance();
   }



    public LiveData<List<Product>> getOfferedProductsLiveData() {
        return mProductRepository.getOnSaleProductsLiveData();
    }

    public LiveData<List<Product>> getLatestProductsLiveData() {
        return mProductRepository.getLatestProductsLiveData();
    }

    public LiveData<List<Product>> getTopRatingProductsLiveData() {
        return mProductRepository.getTopRatingProductsLiveData();
    }

    public LiveData<List<Product>> getPopularProductsLiveData() {
        return mProductRepository.getPopularProductsLiveData();
    }

}
