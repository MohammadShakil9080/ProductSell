package com.mobimeo.productsell.data.model.dataSource;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.mobimeo.productsell.data.model.response.product.ProductListResponseItem;

@Database(
        entities ={ProductListResponseItem.class} ,
        version = 1,
        exportSchema = false
)
public abstract class ProductListDatabase extends RoomDatabase {
    public abstract ProductListDao userDao();
    public static String DATABASE_NAME = "product_db";
}
