package com.example.onlinemarket.data.repository;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.example.onlinemarket.data.database.customerdatabase.CustomerDatabase;
import com.example.onlinemarket.data.database.dao.CustomerDao;
import com.example.onlinemarket.data.database.entity.Customer;
import com.example.onlinemarket.data.model.customer.Order;
import com.example.onlinemarket.network.RetrofitInstance;
import com.example.onlinemarket.network.WooApi;
import com.example.onlinemarket.util.enums.ConnectionState;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerRepository {

    public static final String TAG = "CustomerRepository";
    private final MutableLiveData<ConnectionState> mConnectionStateMutableLiveData;
    private final WooApi mWooApi;
    private static CustomerRepository sCustomerRepository;
    private final CustomerDao mCustomerDoa;
    private final MutableLiveData<com.example.onlinemarket.data.model.customer.Customer> mCustomerMutableLiveData;
    private final MutableLiveData<Order> mOrderMutableLiveData;

    private CustomerRepository(Context context) {
        CustomerDatabase customerDatabase = CustomerDatabase.getInstance(context);
        mCustomerDoa = customerDatabase.customerDao();
        mConnectionStateMutableLiveData = new MutableLiveData<>();
        mWooApi = RetrofitInstance.getInstance().create(WooApi.class);
        mCustomerMutableLiveData = new MutableLiveData<>();
        mOrderMutableLiveData = new MutableLiveData<>();
    }


    public static CustomerRepository getInstance(Application application) {
        if (sCustomerRepository == null)
            sCustomerRepository = new CustomerRepository(application);
        return sCustomerRepository;
    }

    public void insert(Customer customer) {
        CustomerDatabase.databaseWriteExecutor.execute(() -> mCustomerDoa.insert(customer));
    }

    public void delete(Customer customer) {
        CustomerDatabase.databaseWriteExecutor.execute(() -> mCustomerDoa.delete(customer));
    }

    public void update(Customer customer) {
        CustomerDatabase.databaseWriteExecutor.execute(() -> mCustomerDoa.update(customer));
    }

    public Customer getCustomerByEmail(String email) {
        return mCustomerDoa.getByEmail(email);
    }


    public Customer getCurrentLoginCustomer() {
        return mCustomerDoa.getCurrentLoginCustomer();
    }


    public List<Customer> getAll() {
        return mCustomerDoa.getAll();
    }


    public LiveData<ConnectionState> getConnectionStateLiveData() {
        return mConnectionStateMutableLiveData;
    }

    public void changeStateToLogOut() {
        Customer customer = getCurrentLoginCustomer();
        customer.setLoginState(false);
        update(customer);
    }

    public void changeStateToLogIn(String email) {
        Customer customer = getCustomerByEmail(email);
        customer.setLoginState(true);
        update(customer);
    }


    public boolean authorizePassword(String email, String password) {
        Customer customer = getCustomerByEmail(email);
        return customer.getPassword().equals(password);
    }

    public LiveData<Order> getOrderLiveData() {
        return mOrderMutableLiveData;
    }

    public void postOrderToServer(Order order) {
        mWooApi.postOrder(order).enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                if (response.isSuccessful()) {
                    mOrderMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
            }
        });
    }


    public LiveData<com.example.onlinemarket.data.model.customer.Customer> getCustomerLiveData() {
        return mCustomerMutableLiveData;
    }

    public void postCustomerToServer(com.example.onlinemarket.data.model.customer.Customer customer) {
        mConnectionStateMutableLiveData.setValue(ConnectionState.LOADING);
        mWooApi.registerCustomer(customer).enqueue(new Callback<com.example.onlinemarket.data.model.customer.Customer>() {
            @Override
            public void onResponse(Call<com.example.onlinemarket.data.model.customer.Customer> call, Response<com.example.onlinemarket.data.model.customer.Customer> response) {
                if (response.isSuccessful()) {
                    mConnectionStateMutableLiveData.setValue(ConnectionState.START_ACTIVITY);
                }
                mConnectionStateMutableLiveData.setValue(ConnectionState.NOTHING);
            }


            @Override
            public void onFailure(Call<com.example.onlinemarket.data.model.customer.Customer> call, Throwable t) {
                mConnectionStateMutableLiveData.setValue(ConnectionState.ERROR);
            }
        });
    }

    public void fetchCustomerByEmail(String email) {
        mConnectionStateMutableLiveData.postValue(ConnectionState.LOADING);
        mWooApi.getCustomer(email).enqueue(new Callback<List<com.example.onlinemarket.data.model.customer.Customer>>() {
            @Override
            public void onResponse(Call<List<com.example.onlinemarket.data.model.customer.Customer>> call, Response<List<com.example.onlinemarket.data.model.customer.Customer>> response) {
                if (response.isSuccessful()) {
                    if (!response.body().isEmpty())
                        mCustomerMutableLiveData.setValue(response.body().get(0));
                    else
                        mCustomerMutableLiveData.setValue(null);
                    mConnectionStateMutableLiveData.setValue(ConnectionState.START_ACTIVITY);
                }
                mConnectionStateMutableLiveData.setValue(ConnectionState.NOTHING);
            }

            @Override
            public void onFailure(Call<List<com.example.onlinemarket.data.model.customer.Customer>> call, Throwable t) {
                mConnectionStateMutableLiveData.setValue(ConnectionState.ERROR);
            }
        });
    }


}