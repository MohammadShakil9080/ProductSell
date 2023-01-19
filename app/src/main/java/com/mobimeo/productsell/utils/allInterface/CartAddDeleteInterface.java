package com.mobimeo.productsell.utils.allInterface;


import com.mobimeo.productsell.data.model.response.product.ProductListResponseItem;

public interface CartAddDeleteInterface {
    void cartAdd(ProductListResponseItem productListResponseItem);
    void cartDelete(ProductListResponseItem productListResponseItem);
    void cartUpdate(ProductListResponseItem productListResponseItem);
}
