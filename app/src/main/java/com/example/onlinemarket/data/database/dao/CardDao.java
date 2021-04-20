package com.example.onlinemarket.data.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.onlinemarket.data.database.entity.CardProduct;

import java.util.List;

@Dao
public interface CardDao {


    @Query("Select * From CardProduct")
    LiveData<List<CardProduct>> getAll();

    @Query("Select * From cardproduct Where id == :id ")

    LiveData<CardProduct> getById(Integer id);
    @Insert
    void insert(CardProduct cardProduct);

    @Delete
    void delete(CardProduct cardProduct);

}
