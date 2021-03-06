package com.example.onlinemarket.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.onlinemarket.data.database.entity.CartProduct;
import com.example.onlinemarket.data.model.customer.Billing;
import com.example.onlinemarket.data.model.customer.Customer;
import com.example.onlinemarket.data.model.customer.LineItem;
import com.example.onlinemarket.data.model.customer.Order;
import com.example.onlinemarket.data.model.product.Coupon;
import com.example.onlinemarket.data.model.product.Product;
import com.example.onlinemarket.data.repository.CartRepository;
import com.example.onlinemarket.data.repository.CustomerRepository;
import com.example.onlinemarket.data.repository.ProductRepository;
import com.example.onlinemarket.util.enums.ConnectionState;

import java.util.ArrayList;
import java.util.List;

public class FinishShoppingFragmentViewModel extends AndroidViewModel {

    private final ProductRepository mProductRepository;
    private final CustomerRepository mCustomerRepository;
    private final CartRepository mCartRepository;

    public FinishShoppingFragmentViewModel(@NonNull Application application) {
        super(application);
        mProductRepository = ProductRepository.getInstance();
        mCustomerRepository = CustomerRepository.getInstance(application);
        mCartRepository = CartRepository.getInstance(application);
    }

    public void fetchCoupon(String code) {
        mProductRepository.fetchCouponByCode(code);
    }

    public LiveData<Coupon> getCouponLiveData() {
        return mProductRepository.getCouponLiveData();
    }

    public void postOrdersToServer(Customer customer) {
        List<Product> products = mCartRepository.getProductsLiveData().getValue();
        List<LineItem> lineItems = new ArrayList<>();

        for (Product product : products) {
            CartProduct cartProduct = mCartRepository.get(product.getId());
            LineItem lineItem = new LineItem(
                    product.getId(),
                    cartProduct.getCount());

            lineItems.add(lineItem);
        }

        Billing billing = new Billing();
        billing.setFirstName(customer.getFirstName());
        billing.setLastName(customer.getLastName());
        billing.setAddress1(customer.getShipping().getAddress1());

        Order order = new Order(customer.getId(), billing, customer.getShipping(), lineItems);
        mCustomerRepository.postOrderToServer(order);
    }

    public LiveData<Order> getOrderLiveData() {
        return mCustomerRepository.getOrderLiveData();
    }
    public void clearCart() {
        mCartRepository.deleteAll();
    }


}
