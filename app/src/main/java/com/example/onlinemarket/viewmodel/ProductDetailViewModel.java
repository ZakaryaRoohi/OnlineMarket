package com.example.onlinemarket.viewmodel;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.onlinemarket.data.database.entity.CartProduct;
import com.example.onlinemarket.data.model.Product;
import com.example.onlinemarket.data.repository.CartRepository;
import com.example.onlinemarket.data.repository.ProductRepository;
import com.example.onlinemarket.network.RetrofitInstance;
import com.example.onlinemarket.network.WooApi;
import com.example.onlinemarket.util.enums.ConnectionState;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailViewModel extends AndroidViewModel {

    private final ProductRepository mProductRepository;

    private CartRepository mCartRepository;
    private LiveData<Product> mProduct;

    public ProductDetailViewModel(@NonNull Application application) {
        super(application);
        mProduct = new MutableLiveData<>();
        mProductRepository = ProductRepository.getInstance();
        mCartRepository = CartRepository.getInstance(getApplication());

    }


    public void fetchProductById(Integer productId) {
        mProductRepository.fetchProductById(productId);
    }

    public LiveData<Product> getProductMutableLiveData() {
        mProduct = mProductRepository.getProductByIdMutableLiveData();
        return mProductRepository.getProductByIdMutableLiveData();
    }

    public MutableLiveData<ConnectionState> getConnectionStateLiveData() {
        return mProductRepository.getConnectionStateLiveData();
    }

    public void onClick(View v) {
        CartProduct cartProduct = new CartProduct(mProduct.getValue().getId(),"",1);
        mCartRepository.insertToCart(cartProduct);
    }
}
