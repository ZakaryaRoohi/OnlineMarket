package com.example.onlinemarket.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.onlinemarket.R;
import com.example.onlinemarket.data.model.customer.Customer;
import com.example.onlinemarket.databinding.FragmentFinishShoppingBinding;
import com.example.onlinemarket.util.StringUtil;
import com.example.onlinemarket.util.enums.ConnectionState;
import com.example.onlinemarket.viewmodel.FinishShoppingFragmentViewModel;

public class FinishShoppingFragment extends Fragment {

    private FinishShoppingFragmentViewModel mViewModel;
    private FragmentFinishShoppingBinding mBinding;
    private Customer mCustomer;
    private String mTotalPrice;

    public FinishShoppingFragment() {
        // Required empty public constructor
    }


    public static FinishShoppingFragment newInstance() {
        return new FinishShoppingFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCustomer = FinishShoppingFragmentArgs.fromBundle(getArguments()).getCustomer();
        mTotalPrice = FinishShoppingFragmentArgs.fromBundle(getArguments()).getTotalPrice();

        mViewModel = new ViewModelProvider(this).get(FinishShoppingFragmentViewModel.class);

        mViewModel.getConnectionStateLiveData().observe(this, connectionState -> {
            if (connectionState == ConnectionState.START_ACTIVITY) {
                //TODO Send customer id to server to save which customer used this coupon once
                mTotalPrice = StringUtil
                        .approveCouponAmount(mTotalPrice
                                , mViewModel.getCouponLiveData().getValue().getAmount());
                mBinding.textViewTotalPriceNumber
                        .setText(mTotalPrice);
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_finish_shopping, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding.textViewAddress.setText(mCustomer.getShipping().getAddress1());
        mBinding.textViewTotalPriceNumber.setText(mTotalPrice);

        mBinding.buttonAddCoupon.setOnClickListener(v -> {
            if (!mBinding.editTextAddCoupon.getText().toString().trim().isEmpty())
                mViewModel.fetchCoupon(mBinding.editTextAddCoupon.getText().toString());
        });

        mBinding.toolbarFinishShopping.imageViewBack.setOnClickListener(v -> getActivity().onBackPressed());

        mBinding.buttonFinalPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO send orders to server

            }
        });

    }
}