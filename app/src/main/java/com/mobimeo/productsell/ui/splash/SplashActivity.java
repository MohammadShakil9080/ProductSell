package com.mobimeo.productsell.ui.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.mobimeo.productsell.R;
import com.mobimeo.productsell.ui.login.LoginActivity;
import com.mobimeo.productsell.ui.product.ProductListActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        getSupportActionBar().hide();
        SharedPreferences sh = getSharedPreferences("MyStore", MODE_PRIVATE);
        Boolean state = sh.getBoolean("isLoginState", false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               if (state){
                   Intent i = new Intent(SplashActivity.this, ProductListActivity.class);
                   startActivity(i);
                   finish();
               }else {
                   Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                   startActivity(i);
                   finish();
               }
            }
        }, 2000);
    }
}