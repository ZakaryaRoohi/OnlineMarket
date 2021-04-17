package com.example.onlinemarket.view.fragment;

import android.os.Bundle;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.navigation.NavArgs;

import com.example.onlinemarket.data.model.Product;

import java.io.Serializable;
import java.util.HashMap;

public class ProductDetailFragmentArgs implements NavArgs {
    private final HashMap arguments = new HashMap();

    private ProductDetailFragmentArgs() {
    }

    private ProductDetailFragmentArgs(HashMap argumentsMap) {
        this.arguments.putAll(argumentsMap);
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public static ProductDetailFragmentArgs fromBundle(@NonNull Bundle bundle) {
        ProductDetailFragmentArgs __result = new ProductDetailFragmentArgs();
        bundle.setClassLoader(ProductDetailFragmentArgs.class.getClassLoader());
        if (bundle.containsKey("product")) {
            Product product;
            if (Parcelable.class.isAssignableFrom(Product.class) || Serializable.class.isAssignableFrom(Product.class)) {
                product = (Product) bundle.get("product");
            } else {
                throw new UnsupportedOperationException(Product.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
            }
            if (product == null) {
                throw new IllegalArgumentException("Argument \"product\" is marked as non-null but was passed a null value.");
            }
            __result.arguments.put("product", product);
        } else {
            throw new IllegalArgumentException("Required argument \"product\" is missing and does not have an android:defaultValue");
        }
        return __result;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public Product getProduct() {
        return (Product) arguments.get("product");
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle toBundle() {
        Bundle __result = new Bundle();
        if (arguments.containsKey("product")) {
            Product product = (Product) arguments.get("product");
            if (Parcelable.class.isAssignableFrom(Product.class) || product == null) {
                __result.putParcelable("product", Parcelable.class.cast(product));
            } else if (Serializable.class.isAssignableFrom(Product.class)) {
                __result.putSerializable("product", Serializable.class.cast(product));
            } else {
                throw new UnsupportedOperationException(Product.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
            }
        }
        return __result;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        ProductDetailFragmentArgs that = (ProductDetailFragmentArgs) object;
        if (arguments.containsKey("product") != that.arguments.containsKey("product")) {
            return false;
        }
        if (getProduct() != null ? !getProduct().equals(that.getProduct()) : that.getProduct() != null) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + (getProduct() != null ? getProduct().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ProductDetailFragmentArgs{"
                + "product=" + getProduct()
                + "}";
    }

    public static class Builder {
        private final HashMap arguments = new HashMap();

        public Builder(ProductDetailFragmentArgs original) {
            this.arguments.putAll(original.arguments);
        }

        public Builder(@NonNull Product product) {
            if (product == null) {
                throw new IllegalArgumentException("Argument \"product\" is marked as non-null but was passed a null value.");
            }
            this.arguments.put("product", product);
        }

        @NonNull
        public ProductDetailFragmentArgs build() {
            ProductDetailFragmentArgs result = new ProductDetailFragmentArgs(arguments);
            return result;
        }

        @NonNull
        public Builder setProduct(@NonNull Product product) {
            if (product == null) {
                throw new IllegalArgumentException("Argument \"product\" is marked as non-null but was passed a null value.");
            }
            this.arguments.put("product", product);
            return this;
        }

        @SuppressWarnings("unchecked")
        @NonNull
        public Product getProduct() {
            return (Product) arguments.get("product");
        }
    }
}