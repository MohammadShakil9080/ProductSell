package com.mobimeo.productsell.data;


import com.mobimeo.productsell.data.model.response.product.ProductListResponse;
import com.mobimeo.productsell.data.model.response.product.ProductListResponseItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("products")
    Call<List<ProductListResponseItem>> getProductList();
}
