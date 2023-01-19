package com.mobimeo.productsell.repository.cardUpdate;

import androidx.lifecycle.MutableLiveData;

import com.mobimeo.productsell.data.model.request.CardAddRequest;
import com.mobimeo.productsell.data.model.response.cart.CardListResponse;

public interface CardUpdateRepository {
    MutableLiveData<CardListResponse> updateCard(CardAddRequest cardAddRequest);
}
