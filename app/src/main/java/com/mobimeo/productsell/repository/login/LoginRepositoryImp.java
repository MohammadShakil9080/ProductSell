package com.mobimeo.productsell.repository.login;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.mobimeo.productsell.data.model.remote.ApiInterface;
import com.mobimeo.productsell.data.model.request.LoginRequest;
import com.mobimeo.productsell.data.model.response.login.LoginResponse;
import com.mobimeo.productsell.data.model.response.product.ProductListResponseItem;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepositoryImp implements LoginRepository {
    ApiInterface myApi;

    @Inject
    public LoginRepositoryImp(ApiInterface myApi) {
        this.myApi = myApi;
    }
    @Override
    public MutableLiveData<LoginResponse> getLogin(LoginRequest loginRequest) {
        final MutableLiveData<LoginResponse> data = new MutableLiveData<>();
        myApi.getLogin(loginRequest)
                .enqueue(new Callback<LoginResponse>() {


                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                        Log.e("productList", "onResponse: " );
                        if (response.body() != null) {

                            data.setValue(response.body());

                        }
                    }
                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Log.e("productList", "onFailure: "+t.toString());
                        data.setValue(null);
                    }
                });
        return data;
    }
}
