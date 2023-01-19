package com.mobimeo.productsell.repository.cardAdd;


import androidx.lifecycle.MutableLiveData;

import com.mobimeo.productsell.data.model.request.CardAddRequest;
import com.mobimeo.productsell.data.model.response.cart.CardListResponse;

import java.util.List;

public interface CardAddRepository {
    MutableLiveData<CardListResponse> addCard(CardAddRequest cardAddRequest);
}
