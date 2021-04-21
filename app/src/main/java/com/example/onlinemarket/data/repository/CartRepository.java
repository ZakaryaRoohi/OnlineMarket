package com.example.onlinemarket.data.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.example.onlinemarket.data.database.cartdatabase.CartDatabase;
import com.example.onlinemarket.data.model.Product;

import java.util.List;

public class CartRepository {

    public static final String CART_DATABASE_NAME = "cardDatabase.db";
    private static CartRepository sCartRepository;

    private MutableLiveData<com.example.onlinemarket.data.model.Product> mProductsLiveData;
    private final CartDatabase mDatabase;

    private CartRepository(Context context){
        mProductsLiveData = new MutableLiveData<>();
        mDatabase = Room.databaseBuilder(context.getApplicationContext(),
                CartDatabase.class, CART_DATABASE_NAME).build();
    }

    public static CartRepository getInstance(Context context){
        if (sCartRepository==null)
            sCartRepository =  new CartRepository(context);
        return sCartRepository;
    }

    public static CartRepository getCartRepository() {
        return sCartRepository;
    }

    public static void setCartRepository(CartRepository cartRepository) {
        sCartRepository = cartRepository;
    }
    public MutableLiveData<com.example.onlinemarket.data.model.Product> getProductsLiveData() {
        return mProductsLiveData;
    }

    public void setProductsLiveData(MutableLiveData<com.example.onlinemarket.data.model.Product> productsLiveData) {
        mProductsLiveData = productsLiveData;
    }

    public void insertToCart(Product product) {
        mDatabase.cartDao().insert(product);
    }

    public void deleteFromCart(Product product) {
        mDatabase.cartDao().delete(product);
    }

    public LiveData<Product> getProductFromCart(Integer id) {
        return mDatabase.cartDao().getById(id);
    }


    public LiveData<List<Product>> getAllFromCart() {
        return mDatabase.cartDao().getAll();
    }


}
