package com.mobimeo.productsell.repository.product;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.mobimeo.productsell.data.model.remote.ApiInterface;
import com.mobimeo.productsell.data.model.response.product.ProductListResponseItem;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListResponseImp implements ProductListRepository {

    ApiInterface myApi;

    @Inject
    public ProductListResponseImp(ApiInterface myApi) {
        this.myApi = myApi;
    }

    @Override
    public MutableLiveData<List<ProductListResponseItem>> getProductList() {
        Log.e("productList", "getProductList: " );
        final MutableLiveData<List<ProductListResponseItem>> data = new MutableLiveData<>();
        myApi.getProductList()
                .enqueue(new Callback<List<ProductListResponseItem>>() {


                    @Override
                    public void onResponse(Call<List<ProductListResponseItem>> call, Response<List<ProductListResponseItem>> response) {

                        Log.e("productList", "onResponse: " );
                        if (response.body() != null) {

                            data.setValue(response.body());

                        }
                    }
                    @Override
                    public void onFailure(Call<List<ProductListResponseItem>> call, Throwable t) {
                        Log.e("productList", "onFailure: "+t.toString());
                        data.setValue(null);
                    }
                });
        return data;
    }
}
