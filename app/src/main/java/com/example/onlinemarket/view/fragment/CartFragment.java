package com.example.onlinemarket.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.onlinemarket.R;
import com.example.onlinemarket.adapter.CartRecyclerAdapter;
import com.example.onlinemarket.databinding.FragmentCartBinding;
import com.example.onlinemarket.viewmodel.CartFragmentViewModel;

public class CartFragment extends Fragment {

    private CartRecyclerAdapter mCartRecyclerAdapter;
    private FragmentCartBinding mBinding;
    private CartFragmentViewModel mViewModel;

    public CartFragment() {
        // Required empty public constructor
    }

    public static CartFragment newInstance() {
        return new CartFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CartFragmentViewModel.class);

        mViewModel.getCartProducts().observe(
                this, products -> {
                    mCartRecyclerAdapter.notifyDataSetChanged();
                    if (products.isEmpty())
                        mBinding.buttonFinishShopping.setEnabled(false);
                });

        mViewModel.getTotalPriceLiveData().observe(
                this, s -> mBinding.textViewSumOfCart.setText(s));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_cart,
                container,
                false);

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUi();
        mBinding.buttonFinishShopping.setOnClickListener(v -> {

            if (mViewModel.getCurrentLoginCustomer() == null && !mViewModel.getCartProducts().getValue().isEmpty()) {
                Toast.makeText(getContext(), R.string.login_for_payment, Toast.LENGTH_SHORT).show();
            } else if (mViewModel.getCustomerLiveData().getValue() != null) {
                CartFragmentDirections.ActionCartFragmentToFinishShoppingFragment action =
                        CartFragmentDirections.actionCartFragmentToFinishShoppingFragment(mViewModel.getCustomerLiveData().getValue()
                                , mViewModel.getTotalPriceLiveData().getValue());
                Navigation.findNavController(v).navigate(action);
            }
        });
    }

    private void initUi() {
        if (mViewModel.getCartProducts().getValue().isEmpty())
            mBinding.buttonFinishShopping.setEnabled(false);
        mBinding.textViewSumOfCart.setText(mViewModel.getTotalPriceLiveData().getValue());
        mCartRecyclerAdapter = new CartRecyclerAdapter(getContext());
        mCartRecyclerAdapter.setProducts(mViewModel.getCartProducts().getValue());
        mBinding.recyclerViewCart.setAdapter(mCartRecyclerAdapter);
    }
}