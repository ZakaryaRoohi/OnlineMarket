package com.example.onlinemarket.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;

import com.example.onlinemarket.R;

import java.util.HashMap;

public class HomeFragmentDirections {

    private HomeFragmentDirections() {
    }

    @NonNull
    public static ActionNavFragHomeToProductDetailFragment actionNavFragHomeToDetailFragment(
            @NonNull String orderBy) {
        return new ActionNavFragHomeToProductDetailFragment(orderBy);
    }


    public static class ActionNavFragHomeToProductDetailFragment implements NavDirections {

        private final HashMap arguments = new HashMap();

        private ActionNavFragHomeToProductDetailFragment(@NonNull String orderBy) {
            if (orderBy == null) {
                throw new IllegalArgumentException("Argument \"orderBy\" is marked as non-null but was passed a null value.");
            }
            this.arguments.put("orderBy", orderBy);

        }

        @NonNull
        public ActionNavFragHomeToProductDetailFragment setOrderBy(@NonNull String orderBy) {
            if (orderBy == null) {
                throw new IllegalArgumentException("Argument \"orderBy\" is marked as non-null but was passed a null value.");
            }
            this.arguments.put("orderBy", orderBy);
            return this;
        }

        @Override
        public int getActionId() {
            return R.id.action_homeFragment_to_wholeProductsFragment;
        }

        @NonNull
        @SuppressWarnings("unchecked")
        @Override
        public Bundle getArguments() {
            Bundle __result = new Bundle();
            if (arguments.containsKey("orderBy")) {
                String orderBy = (String) arguments.get("orderBy");
                __result.putString("orderBy", orderBy);
            }
            return __result;
        }

        @SuppressWarnings("unchecked")
        @NonNull
        public String getOrderBy() {
            return (String) arguments.get("orderBy");
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ActionNavFragHomeToProductDetailFragment that = (ActionNavFragHomeToProductDetailFragment) o;
            if (arguments.containsKey("orderBy") != that.arguments.containsKey("orderBy")) {
                return false;
            }
            getOrderBy();
            if (!getOrderBy().equals(that.getOrderBy())) {
                return false;
            }
            if (getActionId() != that.getActionId()) {
                return false;
            }
            return true;
        }

        @Override
        public int hashCode() {
            int result = 1;
            result = 31 * result + (getOrderBy() != null ? getOrderBy().hashCode() : 0);
            result = 31 * result + getActionId();
            return result;
        }

        @Override
        public String toString() {
            return "ActionHomeFragmentToWholeProductsFragment{" +
                    "arguments=" + arguments +
                    '}';
        }
    }


}
