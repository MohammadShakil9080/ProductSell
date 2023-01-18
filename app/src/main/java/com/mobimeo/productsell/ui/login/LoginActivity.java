package com.mobimeo.productsell.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mobimeo.productsell.R;
import com.mobimeo.productsell.data.model.request.LoginRequest;
import com.mobimeo.productsell.ui.product.ProductListActivity;
import com.mobimeo.productsell.ui.product.ProductListViewModel;
import com.mobimeo.productsell.ui.splash.SplashActivity;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class LoginActivity extends AppCompatActivity {

    AppCompatTextView tvLogin;
    AppCompatEditText etEmail , etPassword;
    ImageView ivShowPass,ivHidePass;
    ProgressBar spinner;

    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        initilization();
        onClick();
    }

    private void onClick() {
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validation()){
                    spinner.setVisibility(View.VISIBLE);
                    LoginRequest loginRequest = new LoginRequest(etEmail.getText().toString(),etPassword.getText().toString());
                    loginViewModel.getLogin(loginRequest).observe(LoginActivity.this, loginResponse -> {
                        spinner.setVisibility(View.GONE);
                        if (loginResponse != null) {
                            SharedPreferences sharedPreferences = getSharedPreferences("MyStore", MODE_PRIVATE);
                            SharedPreferences.Editor myEdit = sharedPreferences.edit();
                            myEdit.putBoolean("isLoginState", true);
                            myEdit.apply();

                            Intent intent = new Intent(LoginActivity.this, ProductListActivity.class);
                            startActivity(intent);
                            finish();
                        }else{
                            Toast.makeText(LoginActivity.this,"Please try again",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    private boolean validation() {
        if(etEmail.getText().toString().isEmpty()){
            Toast.makeText(this,"Please enter valid email or mobile number",Toast.LENGTH_SHORT).show();
            return false;
        }else if (etPassword.getText().toString().isEmpty()){
            Toast.makeText(this,"Please enter valid password",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void initilization() {
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        tvLogin = findViewById(R.id.tvLogin);
        etEmail = findViewById(R.id.etUserMobile);
        etPassword = findViewById(R.id.etPassword);
        ivHidePass = findViewById(R.id.ivVHide);
        ivShowPass = findViewById(R.id.ivPView);
        spinner = findViewById(R.id.progressBar);
    }
}