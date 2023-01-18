package com.mobimeo.productsell.repository.cart;


import androidx.lifecycle.MutableLiveData;

import com.mobimeo.productsell.data.model.request.LoginRequest;
import com.mobimeo.productsell.data.model.response.cart.CardListResponse;
import com.mobimeo.productsell.data.model.response.login.LoginResponse;

import java.util.List;

public interface CartRepository {
    MutableLiveData<List<CardListResponse>> getCartList(String id);
}
