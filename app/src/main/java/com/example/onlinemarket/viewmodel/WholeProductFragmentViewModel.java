package com.example.onlinemarket.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.onlinemarket.adapter.WholeProductsAdapter;
import com.example.onlinemarket.data.model.Product;
import com.example.onlinemarket.data.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class WholeProductFragmentViewModel extends AndroidViewModel {

    private ProductRepository mProductRepository;
    private List<Product> mProducts = new ArrayList<>();
    private final MutableLiveData<String> mStringMutableLiveData = new MutableLiveData<>();
    private WholeProductsAdapter mWholeProductsAdapter;

    public WholeProductFragmentViewModel(@NonNull Application application) {
        super(application);
        mProductRepository =  ProductRepository.getInstance();
    }

    public void fetchDataFromRepository(){
        switch (mStringMutableLiveData.getValue()){
            case "date":
                mProducts = mProductRepository.getLatestProducts();
                break;
            case "popularity":
                mProducts = mProductRepository.getPopularProducts();
                break;
            case "rating":
                mProducts = mProductRepository.getTopRatingProducts();
                break;
            default:
                break;
        }
    }
    public void initAdapter(){
        mWholeProductsAdapter = new WholeProductsAdapter(getApplication());
        mWholeProductsAdapter.setProducts(mProducts);
    }

    public WholeProductsAdapter getWholeProductsAdapter(){
        return mWholeProductsAdapter;
    }

    public MutableLiveData<String> getStringMutableLiveData(){
        return mStringMutableLiveData;
    }
}
