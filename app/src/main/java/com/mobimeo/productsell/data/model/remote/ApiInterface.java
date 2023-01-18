package com.mobimeo.productsell.data.model.remote;


import com.mobimeo.productsell.data.model.request.CardAddRequest;
import com.mobimeo.productsell.data.model.request.LoginRequest;
import com.mobimeo.productsell.data.model.response.cart.CardListResponse;
import com.mobimeo.productsell.data.model.response.login.LoginResponse;
import com.mobimeo.productsell.data.model.response.product.ProductListResponse;
import com.mobimeo.productsell.data.model.response.product.ProductListResponseItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("products")
    Call<List<ProductListResponseItem>> getProductList();
    @GET("carts/user/{id}")
    Call<CardListResponse> getCartList(@Path("id")String id);

    @POST("auth/login")
    Call<LoginResponse> getLogin(@Body LoginRequest loginRequest);

    @POST("carts")
    Call<CardListResponse> newCardAdd(@Body CardAddRequest cardAddRequest);

    @PUT("carts/7")
    Call<CardListResponse> updateCard(@Body CardAddRequest cardAddRequest);

    @DELETE("carts/{id}")
    Call<CardListResponse> deleteCard(@Path("id")String id);

}
