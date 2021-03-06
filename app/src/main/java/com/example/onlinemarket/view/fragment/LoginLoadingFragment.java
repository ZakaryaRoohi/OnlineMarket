package com.example.onlinemarket.view.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.onlinemarket.R;
import com.example.onlinemarket.data.database.entity.Customer;
import com.example.onlinemarket.databinding.FragmentLoadingBinding;
import com.example.onlinemarket.viewmodel.LoginViewModel;

public class LoginLoadingFragment extends Fragment {

    private LoginViewModel mViewModel;
    private FragmentLoadingBinding mBinding;
    private Customer mCustomer;

    public LoginLoadingFragment() {
        // Required empty public constructor
    }

    public static LoginLoadingFragment newInstance() {
        return new LoginLoadingFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        mCustomer = mViewModel.getCurrentLoginCustomerFromDataBase();

        mViewModel.getConnectionStateLiveData().observe(this, connectionState -> {
            switch (connectionState) {
                case START_ACTIVITY:
                    Navigation.findNavController(getView())
                            .navigate(LoginLoadingFragmentDirections.actionLoginLoadingFragmentToCustomerFragment());
                    break;
                case LOADING:
                    showLoadingUi();
                    break;
                case ERROR:
                    showErrorUi();
                    break;
                default:
                    break;
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_loading, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //TODO check current login customer then decide which fragment to show
        if (mCustomer == null) {
            Navigation.findNavController(view)
                    .navigate(LoginLoadingFragmentDirections
                            .actionLoginLoadingFragmentToCheckRegistrationFragment());
        } else {
            mViewModel.fetchCustomerFromServer(mCustomer.getEmail());
        }

    }

    public void showLoadingUi() {
        mBinding.textViewNoInternet.setVisibility(View.GONE);
        mBinding.progressBarLoadingFragment.show();
    }

    public void showErrorUi() {
        mBinding.progressBarLoadingFragment.hide();
        mBinding.textViewNoInternet.setVisibility(View.VISIBLE);
    }

}
