package com.example.onlinemarket.data.database;

import com.example.onlinemarket.data.model.Product;

import java.util.List;

public class ProductRepository {

    private static ProductRepository sRepository;
    private List<Product> mAllProducts;
    private List<Product> mAllOfferProducts;

    private ProductRepository(){

    }
    public static ProductRepository getInstance(){
        if(sRepository==null){
            sRepository = new ProductRepository();
        }
        return sRepository;
    }

    public List<Product> getAllProducts() {
        return mAllProducts;
    }

    public void setAllProducts(List<Product> allProducts) {
        mAllProducts = allProducts;
    }

    public List<Product> getOfferProducts() {
        return mAllOfferProducts;
    }

    public void setOfferProducts(List<Product> allOfferProducts) {
        mAllOfferProducts = allOfferProducts;
    }
}
