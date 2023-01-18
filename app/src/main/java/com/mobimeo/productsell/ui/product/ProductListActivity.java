package com.mobimeo.productsell.ui.product;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.mobimeo.productsell.R;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ProductListActivity extends AppCompatActivity {


    private ProductListViewModel productListViewModel;
    private ProductListAdapter productListAdapter;
    AppCompatTextView tvCartCount;
    ImageView ivSearch,ivCart;
    RecyclerView rvProductList;
    ProgressBar spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        getSupportActionBar().hide();

        initialization();
        getMovieArticles();
        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
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

    }

    private void getMovieArticles() {
        spinner.setVisibility(View.VISIBLE);
        productListViewModel.getProductListLiveData().observe(this, productResponse -> {
            spinner.setVisibility(View.GONE);
            if (productResponse != null) {
                productListAdapter.updateProductList(productResponse);
            }else{

            }
        });
    }


}