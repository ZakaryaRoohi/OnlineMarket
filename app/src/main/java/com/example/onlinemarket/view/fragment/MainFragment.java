package com.example.onlinemarket.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlinemarket.R;
import com.example.onlinemarket.adapter.ProductRecyclerAdapter;
import com.example.onlinemarket.data.repository.ProductRepository;
import com.example.onlinemarket.databinding.FragmentMainBinding;
import com.example.onlinemarket.viewmodel.MainFragmentViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {

    private FragmentMainBinding mBinding;
    private MainFragmentViewModel mViewModel;
    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(MainFragmentViewModel.class);
        mViewModel.initAdapters();

        mViewModel.getOfferedProductsLiveData().observe(this , products -> {
            mViewModel.getLatestProductsAdapter().notifyDataSetChanged();
        });

        mViewModel.getLatestProductsLiveData().observe(this , products -> {
            mViewModel.getLatestProductsAdapter().notifyDataSetChanged();
        });

        mViewModel.getTopRatingProductsLiveData().observe(this,products -> {
            mViewModel.getTopRatingProductsAdapter().notifyDataSetChanged();
        });

        mViewModel.getPopularProductsLiveData().observe(this,products -> {
            mViewModel.getPopularProductsAdapter().notifyDataSetChanged();
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_main,
                container,
                false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding.recyclerViewOfferedProduct.setAdapter(mViewModel.getOfferedProductsAdapter());
        mBinding.recyclerViewLatestProduct.setAdapter(mViewModel.getLatestProductsAdapter());
        mBinding.recyclerViewTopRatingProduct.setAdapter(mViewModel.getTopRatingProductsAdapter());
        mBinding.recyclerViewPopularProduct.setAdapter(mViewModel.getPopularProductsAdapter());

    }


}