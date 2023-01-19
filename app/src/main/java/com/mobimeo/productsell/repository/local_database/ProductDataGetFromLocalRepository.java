package com.mobimeo.productsell.repository.local_database;


import androidx.lifecycle.MutableLiveData;

import com.mobimeo.productsell.data.model.response.product.ProductListResponseItem;

import java.util.List;

public interface ProductDataGetFromLocalRepository {
    void productInsertData(ProductListResponseItem productListResponseItem);
    List<ProductListResponseItem> getProductListFrom();
}
