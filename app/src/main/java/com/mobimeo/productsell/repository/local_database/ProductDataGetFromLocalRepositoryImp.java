package com.mobimeo.productsell.repository.local_database;


import androidx.lifecycle.MutableLiveData;

import com.mobimeo.productsell.data.model.dataSource.ProductListDao;
import com.mobimeo.productsell.data.model.response.product.ProductListResponseItem;

import java.util.List;

import javax.inject.Inject;

public class ProductDataGetFromLocalRepositoryImp implements ProductDataGetFromLocalRepository{
    private ProductListDao productListDao;

    @Inject
    public ProductDataGetFromLocalRepositoryImp(ProductListDao productListDao) {
        this.productListDao = productListDao;
    }

    @Override
    public void productInsertData(ProductListResponseItem productListResponseItem) {
        productListDao.insertAll(productListResponseItem);
    }

    @Override
    public List<ProductListResponseItem> getProductListFrom() {
        return productListDao.getAll();
    }
}
