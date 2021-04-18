package com.example.onlinemarket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.example.onlinemarket.R;
import com.example.onlinemarket.databinding.ImageSliderCustomLayoutBinding;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ImageSliderAdapter extends SliderViewAdapter<ImageSliderAdapter.SliderAdapterViewHolder> {
    private List<String> mStringImageUrl = new ArrayList<>();

    public void setStringImageUrl(List<String> stringImageUrl) {
        mStringImageUrl = stringImageUrl;
    }

    public ImageSliderAdapter(Context context) {

    }

    @Override
    public SliderAdapterViewHolder onCreateViewHolder(ViewGroup parent) {
        ImageSliderCustomLayoutBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.image_slider_custom_layout,
                        parent,
                        false);
        return new SliderAdapterViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(SliderAdapterViewHolder viewHolder, int position) {
    viewHolder.bindImage(mStringImageUrl.get(position));
    }

    @Override
    public int getCount() {
        return mStringImageUrl.size();
    }

    class SliderAdapterViewHolder extends SliderViewAdapter.ViewHolder {

        private String mStringUrl;
        private ImageSliderCustomLayoutBinding mBinding;

        public SliderAdapterViewHolder(ImageSliderCustomLayoutBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        public void bindImage(String stringUrl) {
            mStringUrl = stringUrl;
            Picasso.get()
                    .load(stringUrl)
                    .placeholder(R.drawable.place_holder)
                    .into(mBinding.imageViewAutoImageSlider);

        }
    }
}
