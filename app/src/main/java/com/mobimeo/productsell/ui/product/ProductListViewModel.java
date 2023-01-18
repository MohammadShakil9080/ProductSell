package com.mobimeo.productsell.ui.product;


import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mobimeo.productsell.data.model.response.product.ProductListResponse;
import com.mobimeo.productsell.data.model.response.product.ProductListResponseItem;
import com.mobimeo.productsell.repository.ProductListRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ProductListViewModel extends ViewModel {

    private MutableLiveData<List<ProductListResponseItem>> productListResponse;

    ProductListRepository productListRepository;
    @Inject
    public ProductListViewModel( ProductListRepository productListRepository) {
        Log.e("productList", "ProductListViewModel: " );
        this.productListRepository = productListRepository;
    }
    public MutableLiveData<List<ProductListResponseItem>> getProductListLiveData() {
        Log.e("productList", "getProductListLiveData: " );
        productListResponse = productListRepository.getProductList();
        return productListResponse;
    }
}
