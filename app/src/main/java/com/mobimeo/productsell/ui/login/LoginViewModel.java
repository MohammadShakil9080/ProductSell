package com.mobimeo.productsell.ui.login;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mobimeo.productsell.data.model.request.LoginRequest;
import com.mobimeo.productsell.data.model.response.login.LoginResponse;
import com.mobimeo.productsell.repository.login.LoginRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class LoginViewModel  extends ViewModel {

    private MutableLiveData<LoginResponse> loginResponse;
    LoginRepository loginRepository;
    @Inject
    public LoginViewModel(LoginRepository loginRepository) {
        Log.e("productList", "ProductListViewModel: " );
        this.loginRepository = loginRepository;
    }
    public MutableLiveData<LoginResponse> getLogin(LoginRequest loginRequest) {
        Log.e("productList", "getProductListLiveData: " );
        loginResponse = loginRepository.getLogin(loginRequest);
        return loginResponse;
    }
}
