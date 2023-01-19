package com.mobimeo.productsell.repository.cardUpdate;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.mobimeo.productsell.data.model.remote.ApiInterface;
import com.mobimeo.productsell.data.model.request.CardAddRequest;
import com.mobimeo.productsell.data.model.response.cart.CardListResponse;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CardUpdateRepositoryImp implements CardUpdateRepository{
    ApiInterface myApi;

    @Inject
    public CardUpdateRepositoryImp(ApiInterface myApi) {
        this.myApi = myApi;
    }
    @Override
    public MutableLiveData<CardListResponse> updateCard(CardAddRequest cardAddRequest) {
        final MutableLiveData<CardListResponse> data = new MutableLiveData<>();
        myApi.updateCard(cardAddRequest)
                .enqueue(new Callback<CardListResponse>() {


                    @Override
                    public void onResponse(Call<CardListResponse> call, Response<CardListResponse> response) {

                        Log.e("productList", "onResponse: " );
                        if (response.body() != null) {

                            data.setValue(response.body());

                        }
                    }
                    @Override
                    public void onFailure(Call<CardListResponse> call, Throwable t) {
                        Log.e("productList", "onFailure: "+t.toString());
                        data.setValue(null);
                    }
                });
        return data;
    }
}
