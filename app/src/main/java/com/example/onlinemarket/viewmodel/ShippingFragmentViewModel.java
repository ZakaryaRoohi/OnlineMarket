package com.example.onlinemarket.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.onlinemarket.data.model.customer.Customer;
import com.example.onlinemarket.data.repository.CustomerRepository;
import com.example.onlinemarket.util.enums.ConnectionState;


public class ShippingFragmentViewModel extends AndroidViewModel {
    private final CustomerRepository mCustomerRepository;


    public ShippingFragmentViewModel(@NonNull Application application) {
        super(application);
        mCustomerRepository = CustomerRepository.getInstance(application);
    }


    public void postCustomerToServer(Customer customer) {
        mCustomerRepository.postCustomerToServer(customer);
    }

    public void postCustomerToDataBase(String email, String password) {
        com.example.onlinemarket.data.database.entity.Customer customerEntity =
                new com.example.onlinemarket.data.database.entity.Customer(email, password);
        mCustomerRepository.insert(customerEntity);
    }


    public LiveData<ConnectionState> getConnectionStateLiveData() {
        return mCustomerRepository.getConnectionStateLiveData();
    }









}
