package com.example.onlinemarket.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.onlinemarket.data.model.Product;
import com.example.onlinemarket.network.RetrofitInstance;
import com.example.onlinemarket.network.WooApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailViewModel extends ViewModel {

    private MutableLiveData<Product> mProductMutableLiveData;
    private WooApi mWooApi;

    public ProductDetailViewModel(){
        mProductMutableLiveData = new MutableLiveData<>();
        mWooApi = RetrofitInstance.getInstance().create(WooApi.class);

    }

    public void fetchProductFromServer(Integer productId){
        mWooApi.getProductById(productId).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if(response.isSuccessful()){
                    mProductMutableLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
            try {
                throw new Exception("response not Successful");
            }catch (Exception e){
                e.printStackTrace();
            }

            }
        });
    }

    public LiveData<Product> getProductMutableLiveData(){
        return  mProductMutableLiveData;
    }

    public void setProductMutableLiveData(Product product) {
        mProductMutableLiveData.setValue(product);
    }
}
