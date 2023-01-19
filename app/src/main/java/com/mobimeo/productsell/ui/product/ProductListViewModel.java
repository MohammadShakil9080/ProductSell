package com.mobimeo.productsell.ui.product;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mobimeo.productsell.data.model.request.CardAddRequest;
import com.mobimeo.productsell.data.model.response.cart.CardListResponse;
import com.mobimeo.productsell.data.model.response.cart.Product;
import com.mobimeo.productsell.data.model.response.product.ProductListResponseItem;
import com.mobimeo.productsell.repository.cardAdd.CardAddRepository;
import com.mobimeo.productsell.repository.cardDelete.CardDeleteRepository;
import com.mobimeo.productsell.repository.cardUpdate.CardUpdateRepository;
import com.mobimeo.productsell.repository.cartList.CartRepository;
import com.mobimeo.productsell.repository.local_database.ProductDataGetFromLocalRepository;
import com.mobimeo.productsell.repository.product.ProductListRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ProductListViewModel extends ViewModel {

    private MutableLiveData<List<ProductListResponseItem>> productListResponse;
    private MutableLiveData<List<CardListResponse>> cardListResponseMutableLiveData;
    private MutableLiveData<CardListResponse> cardAddResponse;


    ProductListRepository productListRepository;
    CartRepository cartRepository;
    CardAddRepository cardAddRepository;
    CardUpdateRepository cardUpdateRepository;
    CardDeleteRepository cardDeleteRepository;
    ProductDataGetFromLocalRepository productDataGetFromLocalRepository;

    @Inject
    public ProductListViewModel(ProductListRepository productListRepository,
                                CartRepository cartRepository,CardAddRepository cardAddRepository,
                                CardUpdateRepository cardUpdateRepository,
                                CardDeleteRepository cardDeleteRepository,
                                ProductDataGetFromLocalRepository productDataGetFromLocalRepository) {
        Log.e("productList", "ProductListViewModel: " );
        this.productListRepository = productListRepository;
        this.cartRepository = cartRepository;
        this.cardAddRepository = cardAddRepository;
        this.cardUpdateRepository = cardUpdateRepository;
        this.cardDeleteRepository = cardDeleteRepository;
        this.productDataGetFromLocalRepository = productDataGetFromLocalRepository;
    }
    public MutableLiveData<List<ProductListResponseItem>> getProductListLiveData() {
        Log.e("productList", "getProductListLiveData: " );
        productListResponse = productListRepository.getProductList();
        return productListResponse;
    }
     public MutableLiveData<List<CardListResponse>> getCartListLiveData() {
        Log.e("productList", "getProductListLiveData:Cart " );
        cardListResponseMutableLiveData = cartRepository.getCartList("2");
        return cardListResponseMutableLiveData;
    }

    public MutableLiveData<CardListResponse> getCartUpdate(ProductListResponseItem productListResponseItem) {
        Log.e("productList", "getProductListLiveData:Cart " );
        Product product = new Product();
        product.setProductId(productListResponseItem.getId());
        product.setQuantity(productListResponseItem.getCount());
        List<Product> products = new ArrayList<>();
        products.add(product);
        CardAddRequest cardAddRequest = new CardAddRequest(2,"2020-02-03",products);
        cardAddResponse = cardUpdateRepository.updateCard(cardAddRequest);
        return cardAddResponse;
    }

    public MutableLiveData<CardListResponse> getCartAdd(ProductListResponseItem productListResponseItem) {
        Log.e("productList", "getProductListLiveData:Cart " );
        Product product = new Product();
        product.setProductId(productListResponseItem.getId());
        product.setQuantity(productListResponseItem.getCount());
        List<Product> products = new ArrayList<>();
        products.add(product);
        CardAddRequest cardAddRequest = new CardAddRequest(2,"2020-02-03",products);
        cardAddResponse = cardAddRepository.addCard(cardAddRequest);
        return cardAddResponse;
    }
    public MutableLiveData<CardListResponse> getCardDelete (ProductListResponseItem productListResponseItem) {
        cardAddResponse = cardDeleteRepository.deleteCard(""+productListResponseItem.getId());
        return cardAddResponse;
    }

    public List<ProductListResponseItem> getFromLocalDatabase(){

        return productDataGetFromLocalRepository.getProductListFrom();
    }
    public void insertProductInLocalDatabase ( ProductListResponseItem productListResponseItem){
        productDataGetFromLocalRepository.productInsertData(productListResponseItem);
    }

}
