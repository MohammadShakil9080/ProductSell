package com.mobimeo.productsell.ui.cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mobimeo.productsell.R;
import com.mobimeo.productsell.data.model.response.product.ProductListResponseItem;
import com.mobimeo.productsell.ui.product.ProductListViewModel;
import com.mobimeo.productsell.utils.CommondataClass;
import com.mobimeo.productsell.utils.allInterface.CartAddDeleteInterface;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CartListActivity extends AppCompatActivity implements CartAddDeleteInterface {

    private ProductListViewModel productListViewModel;
    CartAdapter cartAdapter;
    RecyclerView rvCardList;
    ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);
        getSupportActionBar().hide();
        initialization();
    }

    private void initialization() {

        productListViewModel = new ViewModelProvider(this).get(ProductListViewModel.class);
        spinner = findViewById(R.id.productCardProgressBar);
        cartAdapter =  new CartAdapter(CommondataClass.cardList,this);
        rvCardList = findViewById(R.id.rvCartList);
        rvCardList.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        rvCardList.setAdapter(cartAdapter);
    }

    @Override
    public void cartAdd(ProductListResponseItem productListResponseItem) {
        spinner.setVisibility(View.VISIBLE);
        productListViewModel.getCartAdd(productListResponseItem).observe(this, cardListResponse -> {
            spinner.setVisibility(View.GONE);
            Toast.makeText(this,"Product Added in your cart", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void cartDelete(ProductListResponseItem productListResponseItem) {
        spinner.setVisibility(View.VISIBLE);
        productListViewModel.getCardDelete(productListResponseItem).observe(this, cardListResponse -> {
            spinner.setVisibility(View.GONE);
            Toast.makeText(this,"Product Delete from your cart", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void cartUpdate(ProductListResponseItem productListResponseItem) {
        spinner.setVisibility(View.VISIBLE);
        productListViewModel.getCartUpdate(productListResponseItem).observe(this, cardListResponse -> {
            spinner.setVisibility(View.GONE);
            Toast.makeText(this,"Product Update from your cart", Toast.LENGTH_SHORT).show();
        });
    }
}