package com.example.onlinemarket.data.repository;

import com.example.onlinemarket.data.model.Product;

import java.util.List;

public class ProductRepository {

    private static ProductRepository sRepository;
    private List<Product> mAllProducts;
    private List<Product> mOfferProducts;
    private List<Product> mLatestProducts;


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
        return mOfferProducts;
    }

    public void setOfferProducts(List<Product> allOfferProducts) {
        mOfferProducts = allOfferProducts;
    }
    public List<Product> getLatestProducts(){
        return mLatestProducts;

    }
    public void setLatestProducts(List<Product> latestProducts){
        mLatestProducts = latestProducts;
    }
}
