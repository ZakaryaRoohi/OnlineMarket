package com.example.onlinemarket.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.onlinemarket.data.database.entity.Customer;
import com.example.onlinemarket.data.repository.CustomerRepository;
import com.example.onlinemarket.util.enums.ConnectionState;

public class LoadingLoginViewModel extends AndroidViewModel {

    private final CustomerRepository mCustomerRepository;


    public LoadingLoginViewModel(@NonNull Application application) {
        super(application);
        mCustomerRepository = CustomerRepository.getInstance(application);
    }

    public Customer getCurrentLoginCustomerFromDataBase() {
        return mCustomerRepository.getCurrentLoginCustomer();
    }

    public Customer getCustomerByEmailFromDatabase(String email) {
        return mCustomerRepository.getCustomerByEmail(email);
    }

    public void fetchCustomerFromServer(String email) {
        mCustomerRepository.fetchCustomerByEmail(email);
    }

    public LiveData<ConnectionState> getConnectionStateLiveData() {
        return mCustomerRepository.getConnectionStateLiveData();
    }

    public LiveData<com.example.onlinemarket.data.model.customer.Customer> getCustomerLiveData() {
        return mCustomerRepository.getCustomerLiveData();
    }


}
