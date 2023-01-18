package com.mobimeo.productsell.data.model.response.product;


import java.util.List;

public class ProductListResponse {
    private List<ProductListResponseItem> productList = null;

    public List<ProductListResponseItem> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductListResponseItem> productList) {
        this.productList = productList;
    }
}
