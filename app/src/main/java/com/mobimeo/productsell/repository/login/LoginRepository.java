package com.mobimeo.productsell.repository.login;


import androidx.lifecycle.MutableLiveData;

import com.mobimeo.productsell.data.model.request.LoginRequest;
import com.mobimeo.productsell.data.model.response.login.LoginResponse;
import com.mobimeo.productsell.data.model.response.product.ProductListResponseItem;

import java.util.List;

public interface LoginRepository {
    MutableLiveData<LoginResponse> getLogin(LoginRequest loginRequest);
}
