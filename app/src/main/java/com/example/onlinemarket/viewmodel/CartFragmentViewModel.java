package com.example.onlinemarket.viewmodel;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.onlinemarket.data.database.entity.Customer;
import com.example.onlinemarket.data.model.product.Product;
import com.example.onlinemarket.data.repository.CartRepository;
import com.example.onlinemarket.data.repository.CustomerRepository;
import com.example.onlinemarket.util.enums.ConnectionState;

import java.util.List;

public class CartFragmentViewModel extends AndroidViewModel {

    private final CartRepository mCartRepository;
    private final CustomerRepository mCustomerRepository;


    public CartFragmentViewModel(@NonNull Application application) {
        super(application);
        mCartRepository = CartRepository.getInstance(application);
        mCustomerRepository = CustomerRepository.getInstance(application);
    }

    public void fetchCartProducts() {
        mCartRepository.fetchCartProducts();
    }

    public LiveData<List<Product>> getCartProducts() {
        return mCartRepository.getProductsLiveData();
    }

    public LiveData<ConnectionState> getConnectionStateLiveData() {
        return mCartRepository.getConnectionStateMutableLiveData();
    }
    public LiveData<String> getTotalPriceLiveData(){
        return mCartRepository.getTotalPriceMutableLiveData();
    }
    public Customer getCurrentLoginCustomer() {
        return mCustomerRepository.getCurrentLoginCustomer();
    }

    public void fetchCurrentCustomerLogin() {
        mCustomerRepository.fetchCustomerByEmail(mCustomerRepository.getCurrentLoginCustomer().getEmail());
    }

    public LiveData<com.example.onlinemarket.data.model.customer.Customer> getCustomerLiveData() {
        return mCustomerRepository.getCustomerLiveData();
    }

}
