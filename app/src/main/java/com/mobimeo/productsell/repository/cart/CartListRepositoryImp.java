package com.mobimeo.productsell.repository.cart;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.mobimeo.productsell.data.model.remote.ApiInterface;
import com.mobimeo.productsell.data.model.response.cart.CardListResponse;
import com.mobimeo.productsell.data.model.response.login.LoginResponse;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartListRepositoryImp implements CartRepository{
    ApiInterface myApi;

    @Inject
    public CartListRepositoryImp(ApiInterface myApi) {
        this.myApi = myApi;
    }

    @Override
    public MutableLiveData<List<CardListResponse>> getCartList(String id) {
        final MutableLiveData<List<CardListResponse>> data = new MutableLiveData<>();
        myApi.getCartList(id)
                .enqueue(new Callback<List<CardListResponse>>() {


                    @Override
                    public void onResponse(Call<List<CardListResponse>> call, Response<List<CardListResponse>> response) {

                        Log.e("productList", "onResponse: " );
                        if (response.body() != null) {

                            data.setValue(response.body());

                        }
                    }
                    @Override
                    public void onFailure(Call<List<CardListResponse>> call, Throwable t) {
                        Log.e("productList", "onFailure: "+t.toString());
                        data.setValue(null);
                    }
                });
        return data;
    }
}
