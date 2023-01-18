package com.mobimeo.productsell.di;



import com.google.gson.GsonBuilder;
import com.mobimeo.productsell.data.ApiInterface;
import com.mobimeo.productsell.repository.ProductListRepository;
import com.mobimeo.productsell.repository.ProductListResponseImp;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import org.jetbrains.annotations.NotNull;
import retrofit2.Retrofit;
import retrofit2.Converter.Factory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn({SingletonComponent.class})
public  class AppModule {



    @Singleton
    @Provides
    @NotNull
    public  ProductListRepository provideProductRepo(@NotNull ApiInterface api) {
        return new ProductListResponseImp(api);
    }

    @Singleton
    @Provides
    @NotNull
    public  OkHttpClient OkHttpClient() {
        return (new Builder()).connectTimeout(60L, TimeUnit.SECONDS).writeTimeout(60L, TimeUnit.SECONDS).readTimeout(60L, TimeUnit.SECONDS).build();
    }

    @Singleton
    @Provides
    @NotNull
    public  ApiInterface provideRetrofit() {
        Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .addConverterFactory((Factory)GsonConverterFactory
                        .create(new GsonBuilder().setLenient().create()))
                .baseUrl("https://fakestoreapi.com/")
                .client(this.OkHttpClient())
                .build();


        return retrofit.create(ApiInterface.class);
    }


}

