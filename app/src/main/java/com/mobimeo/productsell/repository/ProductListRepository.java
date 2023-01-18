package com.mobimeo.productsell.repository;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mobimeo.productsell.data.model.response.product.ProductListResponse;
import com.mobimeo.productsell.data.model.response.product.ProductListResponseItem;

import java.util.List;

public interface ProductListRepository {
    MutableLiveData<List<ProductListResponseItem>> getProductList();
}
