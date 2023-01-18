package com.mobimeo.productsell.ui.product;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mobimeo.productsell.data.model.response.cart.CardListResponse;
import com.mobimeo.productsell.data.model.response.product.ProductListResponseItem;
import com.mobimeo.productsell.repository.cart.CartRepository;
import com.mobimeo.productsell.repository.product.ProductListRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ProductListViewModel extends ViewModel {

    private MutableLiveData<List<ProductListResponseItem>> productListResponse;
    private MutableLiveData<List<CardListResponse>> cardListResponseMutableLiveData;
    ProductListRepository productListRepository;
    CartRepository cartRepository;
    @Inject
    public ProductListViewModel(ProductListRepository productListRepository, CartRepository cartRepository) {
        Log.e("productList", "ProductListViewModel: " );
        this.productListRepository = productListRepository;
        this.cartRepository = cartRepository;
    }
    public MutableLiveData<List<ProductListResponseItem>> getProductListLiveData() {
        Log.e("productList", "getProductListLiveData: " );
        productListResponse = productListRepository.getProductList();
        return productListResponse;
    }
     public MutableLiveData<List<CardListResponse>> getCartListLiveData() {
        Log.e("productList", "getProductListLiveData:Cart " );
        cardListResponseMutableLiveData = cartRepository.getCartList("2");
        return cardListResponseMutableLiveData;
    }
}
