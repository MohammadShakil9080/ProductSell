package com.mobimeo.productsell.ui.cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.mobimeo.productsell.R;
import com.mobimeo.productsell.utils.CommondataClass;

public class CartListActivity extends AppCompatActivity {

    CartAdapter cartAdapter;
    RecyclerView rvCardList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);
        getSupportActionBar().hide();
        initialization();
    }

    private void initialization() {

        cartAdapter =  new CartAdapter(CommondataClass.cardList);
        rvCardList = findViewById(R.id.rvCartList);
        rvCardList.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));

        rvCardList.setAdapter(cartAdapter);
    }
}