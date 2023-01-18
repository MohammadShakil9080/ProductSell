package com.mobimeo.productsell.data.model.request;


import com.mobimeo.productsell.data.model.response.cart.Product;

import java.util.List;

public class CardAddRequest {
    int userId;
    String date;
    List<Product> products;

    public CardAddRequest(int userId, String date, List<Product> products) {
        this.userId = userId;
        this.date = date;
        this.products = products;
    }
}
