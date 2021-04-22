package com.example.onlinemarket.data.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.onlinemarket.data.database.cartdatabase.CartDatabase;
import com.example.onlinemarket.data.database.dao.CartDao;
import com.example.onlinemarket.data.database.entity.CartProduct;

import java.util.List;


public class CartRepository {

    private static CartRepository sCartRepository;
    private final CartDao mCartDao;

    private CartRepository(Context context) {
        CartDatabase dataBase = CartDatabase.getDataBase(context);
        mCartDao = dataBase.cartDao();

    }

    public static CartRepository getInstance(Context context) {
        if (sCartRepository == null)
            sCartRepository = new CartRepository(context);
        return sCartRepository;
    }


    public void insertToCart(CartProduct cartProduct) {
        mCartDao.insert(cartProduct);
    }

    public void deleteFromCart(CartProduct cartProduct) {
        mCartDao.delete(cartProduct);
    }

    public LiveData<CartProduct> getProductFromCart(Integer id) {
        return mCartDao.getById(id);
    }


    public List<CartProduct> getAllProducts() {
        return mCartDao.getAll();
    }


}
