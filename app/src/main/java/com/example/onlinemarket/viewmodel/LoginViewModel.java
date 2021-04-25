package com.example.onlinemarket.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.onlinemarket.data.database.entity.Customer;
import com.example.onlinemarket.data.repository.CartRepository;
import com.example.onlinemarket.data.repository.CustomerRepository;
import com.example.onlinemarket.util.enums.ConnectionState;

public class LoginViewModel extends AndroidViewModel {

    private final CustomerRepository mCustomerRepository;
    private final CartRepository mCartRepository;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        mCustomerRepository = CustomerRepository.getInstance(application);
        mCartRepository = CartRepository.getInstance(application);
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

    public void changeStateToLogOut() {
        mCustomerRepository.changeStateToLogOut();
    }

    public void changeStateToLogIn(String email) {
        mCustomerRepository.changeStateToLogIn(email);
    }

    public boolean authorizePassword(String email, String password) {
        return mCustomerRepository.authorizePassword(email, password);
    }
    public void clearCart(){
        mCartRepository.deleteAll();
    }


}
