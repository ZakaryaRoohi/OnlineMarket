package com.example.onlinemarket.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinemarket.R;
import com.example.onlinemarket.data.model.Product;
import com.example.onlinemarket.databinding.RowItemCartBinding;
import com.example.onlinemarket.util.ImageUtil;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CartRecyclerAdapter extends RecyclerView.Adapter<CartRecyclerAdapter.CartRecyclerViewHolder> {

    private List<Product> mProducts ;

    public void setProducts(List<Product> products) {
        mProducts = products;
    }

    @NonNull
    @Override
    public CartRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        RowItemCartBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.row_item_cart,
                        parent,
                        false);
        return new CartRecyclerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartRecyclerViewHolder holder, int position) {
        holder.bindProduct(mProducts.get(position));
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public static class CartRecyclerViewHolder extends RecyclerView.ViewHolder {

        private final RowItemCartBinding mBinding;

        public CartRecyclerViewHolder(@NonNull RowItemCartBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bindProduct(Product product){
            mBinding.textViewCartItemTitle.setText(product.getName());
            mBinding.textViewCartItemPrice.setText(product.getPrice());

            Picasso.get()
                    .load(ImageUtil.getFirstImageUrlOfProduct(product))
                    .placeholder(R.drawable.place_holder)
                    .into(mBinding.imageViewCartItemImage);
        }

    }
}
