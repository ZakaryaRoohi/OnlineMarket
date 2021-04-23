package com.example.onlinemarket.data.repository;

import com.example.onlinemarket.data.model.product.Category;
import com.example.onlinemarket.util.CategoryUtil;

import java.util.ArrayList;
import java.util.List;

public class CategoryRepository {

    private static CategoryRepository sCategoryRepository;

    private List<Category> mAllCategories;
    private List<Category> mParentCategories;

    private List<Category> mChildOfParent1;
    private List<Category> mChildOfParent2;
    private List<Category> mChildOfParent3;
    private List<Category> mChildOfParent4;
    private List<Category> mChildOfParent5;
    private List<Category> mChildOfParent6;

    private CategoryRepository() {
        mAllCategories = new ArrayList<>();
        mParentCategories = new ArrayList<>();
        mChildOfParent1 = new ArrayList<>();
        mChildOfParent2 = new ArrayList<>();
        mChildOfParent3 = new ArrayList<>();
        mChildOfParent4 = new ArrayList<>();
        mChildOfParent5 = new ArrayList<>();
        mChildOfParent6 = new ArrayList<>();
    }

    public static CategoryRepository getInstance() {
        if (sCategoryRepository == null)
            sCategoryRepository = new CategoryRepository();
        return sCategoryRepository;
    }

    public List<Category> getAllCategories() {
        return mAllCategories;
    }

    public void setAllCategories(List<Category> allCategories) {
        mAllCategories = allCategories;
    }

    public List<Category> getParentCategories() {
        return mParentCategories;
    }

    public void fetchChildCategories() {
        mChildOfParent1 = CategoryUtil.childCategories(mAllCategories, mParentCategories.get(0).getId());
        mChildOfParent2 = CategoryUtil.childCategories(mAllCategories, mParentCategories.get(1).getId());
        mChildOfParent3 = CategoryUtil.childCategories(mAllCategories, mParentCategories.get(2).getId());
        mChildOfParent4 = CategoryUtil.childCategories(mAllCategories, mParentCategories.get(3).getId());
        mChildOfParent5 = CategoryUtil.childCategories(mAllCategories, mParentCategories.get(4).getId());
        mChildOfParent6 = CategoryUtil.childCategories(mAllCategories, mParentCategories.get(5).getId());
    }

    public void setParentCategories(List<Category> parentCategories) {
        mParentCategories = parentCategories;
    }

    public List<Category> getChildOfParent1() {
        mChildOfParent1.addAll(CategoryUtil.childCategories(mAllCategories, mParentCategories.get(0).getId()));
        return mChildOfParent1;
    }

    public List<Category> getChildOfParent2() {
        mChildOfParent2.addAll(CategoryUtil.childCategories(mAllCategories, mParentCategories.get(1).getId()));
        return mChildOfParent2;
    }


    public List<Category> getChildOfParent3() {
        mChildOfParent3.addAll(CategoryUtil.childCategories(mAllCategories, mParentCategories.get(2).getId()));
        return mChildOfParent3;
    }


    public List<Category> getChildOfParent4() {
        mChildOfParent4.addAll(CategoryUtil.childCategories(mAllCategories, mParentCategories.get(3).getId()));
        return mChildOfParent4;
    }


    public List<Category> getChildOfParent5() {
        mChildOfParent5.addAll(CategoryUtil.childCategories(mAllCategories, mParentCategories.get(4).getId()));
        return mChildOfParent5;
    }

    public List<Category> getChildOfParent6() {
        mChildOfParent6.addAll(CategoryUtil.childCategories(mAllCategories, mParentCategories.get(5).getId()));
        return mChildOfParent6;
    }

}
