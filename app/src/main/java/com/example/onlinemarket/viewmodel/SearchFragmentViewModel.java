package com.example.onlinemarket.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.onlinemarket.data.model.product.Product;
import com.example.onlinemarket.data.repository.ProductRepository;
import com.example.onlinemarket.util.enums.SearchState;

import java.util.List;

public class SearchFragmentViewModel extends ViewModel {

    private final ProductRepository mProductRepository;

    public SearchFragmentViewModel() {
        mProductRepository = ProductRepository.getInstance();
    }

    public LiveData<List<Product>> getResultsLiveData() {
        return mProductRepository.getProductSearchLiveData();
    }

    public LiveData<SearchState> getSearchState (){
        return mProductRepository.getSearchStateLiveData();
    }

    public void fetchResults(String search) {
        mProductRepository.fetchProductsBySearch(search);
    }
}
