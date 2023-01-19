package com.mobimeo.productsell.di;



import android.app.Application;
import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.google.gson.GsonBuilder;
import com.mobimeo.productsell.data.model.dataSource.ProductListDatabase;
import com.mobimeo.productsell.data.model.remote.ApiInterface;
import com.mobimeo.productsell.data.model.request.CardAddRequest;
import com.mobimeo.productsell.data.model.response.cart.CardListResponse;
import com.mobimeo.productsell.repository.cardAdd.CardAddRepository;
import com.mobimeo.productsell.repository.cardAdd.CardAddRepositoryImp;
import com.mobimeo.productsell.repository.cardDelete.CardDeleteRepository;
import com.mobimeo.productsell.repository.cardDelete.CardDeleteRepositoryImp;
import com.mobimeo.productsell.repository.cardUpdate.CardUpdateRepository;
import com.mobimeo.productsell.repository.cardUpdate.CardUpdateRepositoryImp;
import com.mobimeo.productsell.repository.cartList.CartListRepositoryImp;
import com.mobimeo.productsell.repository.cartList.CartRepository;
import com.mobimeo.productsell.repository.local_database.ProductDataGetFromLocalRepository;
import com.mobimeo.productsell.repository.local_database.ProductDataGetFromLocalRepositoryImp;
import com.mobimeo.productsell.repository.login.LoginRepository;
import com.mobimeo.productsell.repository.login.LoginRepositoryImp;
import com.mobimeo.productsell.repository.product.ProductListRepository;
import com.mobimeo.productsell.repository.product.ProductListResponseImp;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;

import kotlin.jvm.internal.Intrinsics;
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
    public final ProductListDatabase provideProductDatabase(@NotNull Application app) {
        return Room.databaseBuilder(app, ProductListDatabase.class, ProductListDatabase.DATABASE_NAME).allowMainThreadQueries().build();
    }
    @Singleton
    @Provides
    @NotNull
    public final ProductDataGetFromLocalRepository provideProductLocalRepo(@NotNull ProductListDatabase productListDatabase) {
        return new ProductDataGetFromLocalRepositoryImp(productListDatabase.userDao());
    }

    @Singleton
    @Provides
    @NotNull
    public CardUpdateRepository provideCardUpdateRepo(@NotNull ApiInterface api) {
        return new CardUpdateRepositoryImp(api);
    }
    @Singleton
    @Provides
    @NotNull
    public CardAddRepository provideCardAddRepo(@NotNull ApiInterface api) {
        return new CardAddRepositoryImp(api);
    }
    @Singleton
    @Provides
    @NotNull
    public CardDeleteRepository provideCardDeleteRepo(@NotNull ApiInterface api) {
        return new CardDeleteRepositoryImp(api);
    }

    @Singleton
    @Provides
    @NotNull
    public  ProductListRepository provideProductRepo(@NotNull ApiInterface api) {
        return new ProductListResponseImp(api);
    }
    @Singleton
    @Provides
    @NotNull
    public LoginRepository provideLoginRepo(@NotNull ApiInterface api) {
        return new LoginRepositoryImp(api);
    }

    @Singleton
    @Provides
    @NotNull
    public CartRepository provideCartRepo(@NotNull ApiInterface api) {
        return new CartListRepositoryImp(api);
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

