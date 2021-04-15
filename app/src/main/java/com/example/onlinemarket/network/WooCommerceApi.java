package com.example.onlinemarket.network;

import com.example.onlinemarket.data.model.Category;
import com.example.onlinemarket.data.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.example.onlinemarket.network.RetrofitInstance.API_KEY;
import static com.example.onlinemarket.network.RetrofitInstance.BASE_URL;

public interface WooCommerceApi {

    @GET(BASE_URL + "products" + API_KEY + "&on_sale=true")
    Call<List<Product>> getSaleProducts(@Query("per_page") int perPage, @Query("page") int numberOfPage);

    @GET(BASE_URL + "products" + API_KEY)
    Call<List<Product>> getProducts(@Query("per_page") int perPage,
                                    @Query("page") int numberOfPage,
                                    @Query("orderby") String orderBy);

    @GET(BASE_URL + "products" + API_KEY)
    Call<List<Product>> getAllProducts();

    @GET(BASE_URL + "products" + "/categories" + API_KEY)
    Call<List<Category>> getAllCategories(@Query("per_page") int perPage, @Query("page") int page);


}
