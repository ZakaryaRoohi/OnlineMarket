package com.example.onlinemarket.adapter;

import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinemarket.R;
import com.example.onlinemarket.data.model.product.Product;
import com.example.onlinemarket.databinding.RowItemSearchResultBinding;
import com.example.onlinemarket.view.fragment.SearchFragment;
import com.example.onlinemarket.view.fragment.SearchFragmentDirections;

import java.util.ArrayList;
import java.util.List;

public class SearchRecyclerAdapter extends RecyclerView.Adapter<SearchRecyclerAdapter.SearchViewHolder> {

    private List<Product> mProducts = new ArrayList<>();

    public void setProducts(List<Product> products) {
        mProducts = products;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        RowItemSearchResultBinding binding =
                DataBindingUtil.inflate(
                        LayoutInflater.from(parent.getContext()),
                        R.layout.row_item_search_result,
                        parent,
                        false);
        return new SearchViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {

        holder.bindSearchItem(mProducts.get(position));
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }


    public class SearchViewHolder extends RecyclerView.ViewHolder {

        RowItemSearchResultBinding mBinding;
        Product mProduct;

        public SearchViewHolder(@NonNull RowItemSearchResultBinding binding) {
            super(binding.getRoot());

            mBinding = binding;
        }

        public void bindSearchItem(Product product) {
            mProduct = product;
            mBinding.textViewSearchTitle.setText(mProduct.getName());

            mBinding.getRoot().setOnClickListener(v -> {
                SearchFragmentDirections.ActionSearchFragmentToProductDetailLoadingFragment action =
                        SearchFragmentDirections.actionSearchFragmentToProductDetailLoadingFragment(mProduct.getId());
                Navigation.findNavController(v).navigate(action);
            });

            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                mBinding.textViewSearchDescription
                        .setText(Html.fromHtml(mProduct.getDescription(), Html.FROM_HTML_MODE_LEGACY));
            } else {
                mBinding.textViewSearchDescription.setText(Html.fromHtml(mProduct.getDescription()));
            }
        }

    }

}
