package com.mobimeo.productsell.repository.cardDelete;


import androidx.lifecycle.MutableLiveData;

import com.mobimeo.productsell.data.model.response.cart.CardListResponse;

public interface CardDeleteRepository {
    MutableLiveData<CardListResponse> deleteCard(String id);
}
