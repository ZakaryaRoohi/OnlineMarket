package com.example.onlinemarket.view.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
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
import com.example.onlinemarket.databinding.FragmentRegistrationCheckBinding;
import com.example.onlinemarket.viewmodel.RegistrationCheckViewModel;

public class RegistrationCheckFragment extends Fragment {

    private RegistrationCheckViewModel mViewModel;
    private FragmentRegistrationCheckBinding mBinding;

    public RegistrationCheckFragment() {
        // Required empty public constructor
    }

    public static RegistrationCheckFragment newInstance() {
        return new RegistrationCheckFragment();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(RegistrationCheckViewModel.class);
        mViewModel.getConnectionStateLiveData().observe(this, connectionState -> {
            switch (connectionState) {
                case START_ACTIVITY:
                    if (mViewModel.getCustomerLiveData().getValue() != null) {
                        RegistrationCheckFragmentDirections.ActionNavFragLoginToLoginFragment action =
                                RegistrationCheckFragmentDirections.actionNavFragLoginToLoginFragment(
                                        mBinding.editTextEmail.getText().toString());
                        Navigation.findNavController(getView()).navigate(action);
                    } else {
                        RegistrationCheckFragmentDirections.ActionNavFragLoginToSignUpFragment action =
                                RegistrationCheckFragmentDirections.actionNavFragLoginToSignUpFragment(
                                        mBinding.editTextEmail.getText().toString());
                        Navigation.findNavController(getView()).navigate(action);
                    }
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_registration_check,
                container,
                false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBinding.buttonLoginStepOne.setOnClickListener(v -> {
            if (isValidEmail(mBinding.editTextEmail.getText().toString())){
                mViewModel.getCustomerByEmail(mBinding.editTextEmail.getText().toString());
            }
        });
    }
    public static boolean isValidEmail(CharSequence target){
        return (!TextUtils.isEmpty(target)&& Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
    public void showErrorUi() {
        mBinding.progressBarCheckRegistration.hide();
        mBinding.textViewCheckRegistration.setVisibility(View.VISIBLE);
    }

    public void showLoadingUi() {
        mBinding.progressBarCheckRegistration.show();
        mBinding.textViewCheckRegistration.setVisibility(View.GONE);
    }
}
