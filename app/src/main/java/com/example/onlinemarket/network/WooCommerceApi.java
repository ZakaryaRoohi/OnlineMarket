package com.example.onlinemarket.network;

import com.example.onlinemarket.data.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.example.onlinemarket.network.RetrofitInstance.BASE_URL;
import static com.example.onlinemarket.network.RetrofitInstance.WOOCOMMERCE_REST_AUTHENTICATION_KEY;

public interface WooCommerceApi {

    @GET(BASE_URL + "products" + WOOCOMMERCE_REST_AUTHENTICATION_KEY + "&on_sale=true")
    Call<List<Product>> getSaleProducts(@Query("per_page") int perPage, @Query("page") int numberOfPage);

    @GET(BASE_URL + "products" + WOOCOMMERCE_REST_AUTHENTICATION_KEY)
    Call<List<Product>> getProducts(@Query("per_page") int perPage,
                                    @Query("page") int numberOfPage,
                                    @Query("orderby") String orderBy);



}
