package com.example.onlinemarket.data.database;

import com.example.onlinemarket.data.model.Product;

import java.util.List;

public class productRepository {

    private static productRepository sRepository;
    private List<Product> mAllProducts;
    private List<Product> mAllOfferProducts;

    private productRepository(){

    }
    public static productRepository getInstance(){
        if(sRepository==null){
            sRepository = new productRepository();
        }
        return sRepository;
    }

    public List<Product> getAllProducts() {
        return mAllProducts;
    }

    public void setAllProducts(List<Product> allProducts) {
        mAllProducts = allProducts;
    }

    public List<Product> getAllOfferProducts() {
        return mAllOfferProducts;
    }

    public void setAllOfferProducts(List<Product> allOfferProducts) {
        mAllOfferProducts = allOfferProducts;
    }
}
