package com.example.onlinemarket.data.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


import com.example.onlinemarket.data.database.entity.CartProduct;

import java.util.List;

@Dao
public interface CartDao {


    @Query("Select * From cart_product")
    List<CartProduct> getAll();

    @Query("Select * From cart_product Where productId == :productId ")
    LiveData<CartProduct> getById(int productId);
    @Insert
    void insert(CartProduct cartProduct);

    @Delete
    void delete(CartProduct cartProduct);

}
