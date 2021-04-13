package com.example.onlinemarket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinemarket.R;
import com.example.onlinemarket.data.database.ProductRepository;
import com.example.onlinemarket.data.model.Product;
import com.example.onlinemarket.databinding.RowItemProductBinding;
import com.example.onlinemarket.util.ImageUtil;
import com.example.onlinemarket.viewModel.ProductAdapterViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductRecyclerAdapter extends RecyclerView.Adapter<ProductRecyclerAdapter.ProductRecyclerViewHolder> {

    private ProductAdapterViewModel mViewModel;
    private List<Product> mProducts;
    private Context mContext;

    public ProductRecyclerAdapter(Context context) {
        mContext = context;
        mProducts = new ArrayList<>();
    }

    public void setProducts(List<Product> products) {
        mProducts = products;
    }

    @NonNull
    @Override
    public ProductRecyclerAdapter.ProductRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        RowItemProductBinding binding =
                DataBindingUtil.inflate(
                        inflater,
                        R.layout.row_item_product,
                        parent,
                        false);

//        inflater.inflate(R.id .)
        return new ProductRecyclerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductRecyclerAdapter.ProductRecyclerViewHolder holder, int position) {
        holder.bindProduct(mProducts.get(position));
    }

    @Override
    public int getItemCount() {
        return 0;//mProducts.size();
    }


    public class ProductRecyclerViewHolder extends RecyclerView.ViewHolder {

        private RowItemProductBinding mBinding;
        private Product mProduct;

        public ProductRecyclerViewHolder(@NonNull RowItemProductBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        public void bindProduct(Product product) {
            mProduct = product;
            mBinding.productLayoutTitle.setText(mProduct.getName());
            mBinding.productLayoutPrice.setText(mProduct.getPrice());
            Picasso.get()
                    .load(ImageUtil.getFirstImageUrlOfProduct(mProduct))
                    .placeholder(R.drawable.place_holder)
                    .into(mBinding.productLayoutCover);
        }

    }
}
