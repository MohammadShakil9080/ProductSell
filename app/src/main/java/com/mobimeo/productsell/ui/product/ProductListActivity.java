package com.mobimeo.productsell.ui.product;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.mobimeo.productsell.R;
import com.mobimeo.productsell.data.model.response.cart.CardListResponse;
import com.mobimeo.productsell.data.model.response.product.ProductListResponseItem;
import com.mobimeo.productsell.ui.cart.CartListActivity;
import com.mobimeo.productsell.ui.splash.SplashActivity;
import com.mobimeo.productsell.utils.CommondataClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ProductListActivity extends AppCompatActivity {


    private ProductListViewModel productListViewModel;
    private ProductListAdapter productListAdapter;
    AppCompatTextView tvCartCount;
    ImageView ivSearch,ivCart;
    RecyclerView rvProductList;
    ProgressBar spinner;
    List<CardListResponse> cardListResponses = new ArrayList<>();
    List<ProductListResponseItem> productListResponseItems = new ArrayList<>();
    List<ProductListResponseItem> newCardList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        getSupportActionBar().hide();

        initialization();

        ivCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCardListData();
                Intent intent = new Intent(ProductListActivity.this, CartListActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getCardListData() {
        if (!productListResponseItems.isEmpty()&&!cardListResponses.isEmpty()){
            newCardList.clear();
            for (int i =0;i<cardListResponses.get(0).getProducts().size();i++){
                for (int j=0;j<productListResponseItems.size();j++) {
                    if (Objects.equals(cardListResponses.get(0).getProducts()
                            .get(i).getProductId(), productListResponseItems.get(j).getId())){
                        productListResponseItems.get(j).setCount(cardListResponses.get(0).getProducts()
                                .get(i).getQuantity());
                        newCardList.add(productListResponseItems.get(j));
                        break;
                    }
                }
            }
            CommondataClass.cardList.clear();
            CommondataClass.cardList.addAll(newCardList);
        }
    }

    private void initialization() {
        ivSearch =  findViewById(R.id.ivSearch);
        rvProductList = findViewById(R.id.rvProductList);
        ivCart = findViewById(R.id.ivCart);
        tvCartCount = findViewById(R.id.tvCart);
        productListViewModel = new ViewModelProvider(this).get(ProductListViewModel.class);
        GridLayoutManager layoutManager=new GridLayoutManager(this,2);
        rvProductList.setLayoutManager(layoutManager);
        productListAdapter = new ProductListAdapter();
        rvProductList.setAdapter(productListAdapter);
        spinner = findViewById(R.id.productProgressBar);
        getProductList();

    }

    private void getProductList() {
        spinner.setVisibility(View.VISIBLE);
        productListViewModel.getProductListLiveData().observe(this, productResponse -> {
            spinner.setVisibility(View.GONE);
            if (productResponse != null) {
                this.productListResponseItems.clear();
                this.productListResponseItems.addAll(productResponse);
                productListAdapter.updateProductList(productResponse);
            }else{

            }
        });
        productListViewModel.getCartListLiveData().observe(this, cardListResponse -> {
            spinner.setVisibility(View.GONE);
            if (cardListResponse != null) {
                this.cardListResponses.clear();
                this.cardListResponses.addAll(cardListResponse);
                tvCartCount.setText(""+cardListResponse.get(0).getProducts().size());
            }else{

            }
        });
    }

}