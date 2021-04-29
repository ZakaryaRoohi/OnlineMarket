package com.example.onlinemarket.view.fragment;

import android.nfc.FormatException;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.onlinemarket.R;
import com.example.onlinemarket.databinding.FragmentNotificationSettingBinding;
import com.example.onlinemarket.viewmodel.NotificationViewModel;

public class NotificationSettingFragment extends Fragment {

    private NotificationViewModel mViewModel;
    private FragmentNotificationSettingBinding mBinding;
    private Integer mHour = 3;


    public NotificationSettingFragment() {
        // Required empty public constructor
    }

    public static NotificationSettingFragment newInstance() {
        return new NotificationSettingFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(NotificationViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_notification_setting,
                container,
                false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initSpinner();

        mBinding.buttonChangeTiming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mViewModel.scheduleWork(mHour);
                Toast.makeText(getActivity(),
                            "زمان ارسال نوتیفیکشن به "+mHour+" ساعت تغییر یافت",
                            Toast.LENGTH_SHORT)
                            .show();
            }
        });

        mBinding.editTextCustomTime.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {
                    mHour = Integer.parseInt(mBinding.editTextCustomTime.getText().toString());
                } catch (NumberFormatException e) {
                    //if input format type wasn't valid show toast to user
//                    Toast.makeText(getActivity(),
//                            "invalid number format",
//                            Toast.LENGTH_SHORT)
//                            .show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        mBinding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if (selectedItem.equals(getString(R.string.per_3_hours))) {
                    mHour = 3;
                } else if (selectedItem.equals(getString(R.string.per_5_hours))) {
                    mHour = 5;

                } else if (selectedItem.equals(getString(R.string.per_8_hours))) {
                    mHour = 8;

                } else if (selectedItem.equals(getString(R.string.per_12_hours))) {
                    mHour = 12;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void initSpinner() {
        ArrayAdapter<CharSequence> arrayAdapter =
                ArrayAdapter.createFromResource(
                        getContext(),
                        R.array.time_items_array,
                        android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mBinding.spinner.setAdapter(arrayAdapter);
    }
}