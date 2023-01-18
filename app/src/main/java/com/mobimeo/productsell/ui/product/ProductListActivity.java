package com.mobimeo.productsell.ui.product;

import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.mobimeo.productsell.R;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ProductListActivity extends AppCompatActivity {


    private ProductListViewModel productListViewModel;
    private ProductListAdapter productListAdapter;
    ImageView ivSearch;
    RecyclerView rvProductList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        getSupportActionBar().hide();


        initialization();
        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getMovieArticles();
            }
        });
    }

    private void initialization() {
        ivSearch =  findViewById(R.id.ivSearch);
        rvProductList = findViewById(R.id.rvProductList);
        productListViewModel = new ViewModelProvider(this).get(ProductListViewModel.class);
        GridLayoutManager layoutManager=new GridLayoutManager(this,2);
        rvProductList.setLayoutManager(layoutManager);
        productListAdapter = new ProductListAdapter();
        rvProductList.setAdapter(productListAdapter);
    }

    private void getMovieArticles() {
        productListViewModel.getProductListLiveData().observe(this, productResponse -> {
            if (productResponse != null) {
                productListAdapter.updateProductList(productResponse);
            }else{

            }
        });
    }


}