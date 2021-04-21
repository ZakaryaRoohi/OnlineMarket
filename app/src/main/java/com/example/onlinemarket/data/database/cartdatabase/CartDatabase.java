package com.example.onlinemarket.data.database.cartdatabase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.onlinemarket.data.database.dao.CartDao;
import com.example.onlinemarket.data.model.Product;

@Database(entities = {Product.class}, version = 1, exportSchema = false)

public abstract class CartDatabase extends RoomDatabase {

    public abstract CartDao cartDao();
}
