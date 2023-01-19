package com.mobimeo.productsell.repository.cartList;


import androidx.lifecycle.MutableLiveData;

import com.mobimeo.productsell.data.model.response.cart.CardListResponse;

import java.util.List;

public interface CartRepository {
    MutableLiveData<List<CardListResponse>> getCartList(String id);
}
