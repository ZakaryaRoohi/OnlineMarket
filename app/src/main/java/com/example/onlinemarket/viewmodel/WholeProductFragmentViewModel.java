package com.example.onlinemarket.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.onlinemarket.data.model.product.Product;
import com.example.onlinemarket.data.repository.ProductRepository;
import com.example.onlinemarket.util.enums.SearchState;

import java.util.List;

public class WholeProductFragmentViewModel extends ViewModel {

    private final ProductRepository mProductRepository;
    private final MutableLiveData<List<Product>> mProducts = new MutableLiveData<>();

    public WholeProductFragmentViewModel(){
        mProductRepository = ProductRepository.getInstance();
    }


    public void fetchDataFromRepository(String orderBy) {
        switch (orderBy) {
            case "onSale":
                mProducts.setValue(mProductRepository.getOnSaleProductsLiveData().getValue());
                break;
            case "date":
                mProducts.setValue(mProductRepository.getLatestProductsLiveData().getValue());
                break;
            case "popularity":
                mProducts.setValue(mProductRepository.getPopularProductsLiveData().getValue());
                break;
            case "rating":
                mProducts.setValue(mProductRepository.getTopRatingProductsLiveData().getValue());
                break;
            case "category":
                mProducts.setValue(mProductRepository.getCategoryProductsLiveData().getValue());
                break;
            case "search":
                mProducts.setValue(mProductRepository.getProductSearchLiveData().getValue());
                break;
            default:
                break;
        }
    }

    public void searchWithSorting(String search, String orderBy, String order) {
        mProductRepository.searchWithSorting(search, orderBy, order);
    }

    public LiveData<SearchState> getSearchState() {
        return mProductRepository.getSearchStateLiveData();
    }


    public LiveData<List<Product>> getProducts(){
        return mProducts;
    }

}
