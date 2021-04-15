package com.example.onlinemarket.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.navigation.NavArgs;

import java.util.HashMap;

public class WholeProductsFragmentArgs implements NavArgs {
    private final HashMap arguments = new HashMap();

    private WholeProductsFragmentArgs() {
    }

    private WholeProductsFragmentArgs(HashMap argumentsMap) {
        this.arguments.putAll(argumentsMap);
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public static WholeProductsFragmentArgs fromBundle(@NonNull Bundle bundle) {
        WholeProductsFragmentArgs __result = new WholeProductsFragmentArgs();
        bundle.setClassLoader(WholeProductsFragmentArgs.class.getClassLoader());
        if (bundle.containsKey("orderBy")) {
            String orderBy;
            orderBy = bundle.getString("orderBy");
            if (orderBy == null) {
                throw new IllegalArgumentException("Argument \"orderBy\" is marked as non-null but was passed a null value.");
            }
            __result.arguments.put("orderBy", orderBy);
        } else {
            throw new IllegalArgumentException("Required argument \"orderBy\" is missing and does not have an android:defaultValue");
        }
        return __result;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public String getOrderBy() {
        return (String) arguments.get("orderBy");
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle toBundle() {
        Bundle __result = new Bundle();
        if (arguments.containsKey("orderBy")) {
            String orderBy = (String) arguments.get("orderBy");
            __result.putString("orderBy", orderBy);
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
        WholeProductsFragmentArgs that = (WholeProductsFragmentArgs) object;
        if (arguments.containsKey("orderBy") != that.arguments.containsKey("orderBy")) {
            return false;
        }
        if (getOrderBy() != null ? !getOrderBy().equals(that.getOrderBy()) : that.getOrderBy() != null) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + (getOrderBy() != null ? getOrderBy().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "WholeProductsFragmentArgs{"
                + "orderBy=" + getOrderBy()
                + "}";
    }

    public static class Builder {
        private final HashMap arguments = new HashMap();

        public Builder(WholeProductsFragmentArgs original) {
            this.arguments.putAll(original.arguments);
        }

        public Builder(@NonNull String orderBy) {
            if (orderBy == null) {
                throw new IllegalArgumentException("Argument \"orderBy\" is marked as non-null but was passed a null value.");
            }
            this.arguments.put("orderBy", orderBy);
        }

        @NonNull
        public WholeProductsFragmentArgs build() {
            WholeProductsFragmentArgs result = new WholeProductsFragmentArgs(arguments);
            return result;
        }

        @NonNull
        public Builder setOrderBy(@NonNull String orderBy) {
            if (orderBy == null) {
                throw new IllegalArgumentException("Argument \"orderBy\" is marked as non-null but was passed a null value.");
            }
            this.arguments.put("orderBy", orderBy);
            return this;
        }

        @SuppressWarnings("unchecked")
        @NonNull
        public String getOrderBy() {
            return (String) arguments.get("orderBy");
        }
    }
}
