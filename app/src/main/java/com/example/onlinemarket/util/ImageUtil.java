package com.example.onlinemarket.util;

import android.net.Uri;

import com.example.onlinemarket.data.model.product.Product;
import com.example.onlinemarket.data.model.product.Image;

import java.util.ArrayList;
import java.util.List;

public class ImageUtil {

    public static String getFirstImageUrlOfProduct(Product product) {
        List<Image> images = product.getImages();
        if (images != null && !images.isEmpty())
            return images.get(0).getSrc();
        else
            throw new NullPointerException("this product doesn't have any images");
    }
    public static String convertResourceIdToUrl(int resourceId) {
        return Uri.parse("android.resource://com.example.onlinemarket/" + resourceId).toString();
    }
    public static List<String> getAllImageUrlOfProduct(Product product) {
        List<String> urls = new ArrayList<>();
        for (Image image : product.getImages()) {
            urls.add(image.getSrc());
        }
        return urls;
    }
}
