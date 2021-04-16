package com.example.onlinemarket.util;

import android.net.Uri;

import com.example.onlinemarket.data.model.Category;
import com.example.onlinemarket.data.model.Product;
import com.example.onlinemarket.data.model.Product.Images;

import java.util.List;

public class ImageUtil {

    public static String getFirstImageUrlOfProduct(Product product) {
        List<Images> images = product.getImages();
        if (images != null && !images.isEmpty())
            return images.get(0).getSrc();
        else
            throw new NullPointerException("this product doesn't have any images");
    }
    public static String convertResourceIdToUrl(int resourceId) {
        return Uri.parse("android.resource://com.example.onlinemarket/" + resourceId).toString();
    }
}
