package com.mobimeo.productsell.data.model.dataSource;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.mobimeo.productsell.data.model.response.product.ProductListResponseItem;

import java.util.List;

@Dao
public interface ProductListDao {
    @Query("SELECT * FROM product_list")
    List<ProductListResponseItem> getAll();
    @Insert
    void insertAll(ProductListResponseItem productListResponseItem);
}
