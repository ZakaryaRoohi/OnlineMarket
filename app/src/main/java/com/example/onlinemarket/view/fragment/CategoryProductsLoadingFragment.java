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
import com.example.onlinemarket.databinding.FragmentLoadingBinding;
import com.example.onlinemarket.viewmodel.CategoryProductsLoadingFragmentViewModel;

public class CategoryProductsLoadingFragment extends Fragment {

    private CategoryProductsLoadingFragmentViewModel mViewModel;
    private FragmentLoadingBinding mBinding;
    private Integer mCategoryId;

    public CategoryProductsLoadingFragment() {
        // Required empty public constructor
    }

    public static CategoryProductsLoadingFragment newInstance() {
        return new CategoryProductsLoadingFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCategoryId = CategoryProductsLoadingFragmentArgs.fromBundle(getArguments()).getCategoryId();

        mViewModel = new ViewModelProvider(this).get(CategoryProductsLoadingFragmentViewModel.class);
        mViewModel.fetchCategoryProducts(mCategoryId);



        //TODO : refactor boilerplate here and loading fragment are same
        mViewModel.getConnectionStateLiveData().observe(this, connectionState -> {
            switch (connectionState) {
                case LOADING:
                    showLoadingUi();
                    break;
                case ERROR:
                    loadInternetError();
                    break;
                case START_ACTIVITY:
                    CategoryProductsLoadingFragmentDirections.ActionCategoryProductsLoadingFragmentToWholeProductsFragment action =
                            CategoryProductsLoadingFragmentDirections
                                    .actionCategoryProductsLoadingFragmentToWholeProductsFragment("category");
                    Navigation.findNavController(getView()).navigate(action);
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
        mBinding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_loading, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void showLoadingUi() {
        mBinding.buttonRetry.setVisibility(View.GONE);
        mBinding.textViewNoInternet.setVisibility(View.GONE);
        mBinding.progressBarLoadingFragment.setVisibility(View.VISIBLE);
        mBinding.progressBarLoadingFragment.show();
    }


    private void loadInternetError() {
        mBinding.textViewNoInternet.setVisibility(View.VISIBLE);
        mBinding.buttonRetry.setVisibility(View.VISIBLE);
        mBinding.progressBarLoadingFragment.setVisibility(View.GONE);
        mBinding.progressBarLoadingFragment.hide();
    }
}