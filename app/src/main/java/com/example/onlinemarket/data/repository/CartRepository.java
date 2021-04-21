package com.example.onlinemarket.data.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.example.onlinemarket.data.database.cartdatabase.CartDatabase;
import com.example.onlinemarket.data.database.dao.CartDao;
import com.example.onlinemarket.data.model.Product;

import java.util.List;

public class CartRepository {

    private static CartRepository sCartRepository;
    private final CartDao mCartDao;

    private CartRepository(Context context) {
        CartDatabase mRoomDataBase = CartDatabase.getDataBase(context);
        mCartDao = mRoomDataBase.cartDao();

    }

    public static CartRepository getInstance(Context context) {
        if (sCartRepository == null)
            sCartRepository = new CartRepository(context);
        return sCartRepository;
    }


    public void insertToCart(Product product) {
        mCartDao.insert(product);
    }

    public void deleteFromCart(Product product) {
        mCartDao.delete(product);
    }

    public LiveData<Product> getProductFromCart(Integer id) {
        return mCartDao.getById(id);
    }


    public LiveData<List<Product>> getAllFromCart() {
        return mCartDao.getAll();
    }


}
