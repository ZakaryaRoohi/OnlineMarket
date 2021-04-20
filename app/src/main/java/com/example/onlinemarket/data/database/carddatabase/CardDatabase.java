package com.example.onlinemarket.data.database.carddatabase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.onlinemarket.data.database.dao.CardDao;
import com.example.onlinemarket.data.database.entity.CardProduct;

@Database(entities = {CardProduct.class}, version = 1, exportSchema = false)

public abstract class CardDatabase extends RoomDatabase {

    public abstract CardDao cardDao();
}
