package com.example.onlinemarket.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.onlinemarket.data.model.customer.Customer;
import com.example.onlinemarket.network.RetrofitInstance;
import com.example.onlinemarket.network.WooApi;
import com.example.onlinemarket.util.enums.ConnectionState;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationCheckViewModel extends ViewModel {

    private final MutableLiveData<ConnectionState> mConnectionStateMutableLiveData;
    private final MutableLiveData<Customer> mCustomerMutableLiveData;
    private final WooApi mWooApi;

    public RegistrationCheckViewModel() {
        mWooApi = RetrofitInstance.getInstance().create(WooApi.class);
        mCustomerMutableLiveData = new MutableLiveData<>();
        mConnectionStateMutableLiveData = new MutableLiveData<>();
    }

    public LiveData<ConnectionState> getConnectionStateLiveData() {
        return mConnectionStateMutableLiveData;
    }

    public LiveData<Customer> getCustomerLiveData() {
        return mCustomerMutableLiveData;
    }

    public void getCustomerByEmail(String email) {
        mConnectionStateMutableLiveData.postValue(ConnectionState.LOADING);
        mWooApi.getCustomer(email).enqueue(new Callback<List<Customer>>() {
            @Override
            public void onResponse(Call<List<Customer>> call, Response<List<Customer>> response) {
                if (response.isSuccessful()) {
                    if (!response.body().isEmpty())
                        mCustomerMutableLiveData.setValue(response.body().get(0));
                    mConnectionStateMutableLiveData.setValue(ConnectionState.START_ACTIVITY);
                }
            }

            @Override
            public void onFailure(Call<List<Customer>> call, Throwable t) {
                mConnectionStateMutableLiveData.setValue(ConnectionState.ERROR);
            }
        });
    }

}
