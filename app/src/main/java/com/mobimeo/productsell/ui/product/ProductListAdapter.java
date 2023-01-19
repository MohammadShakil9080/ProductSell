package com.mobimeo.productsell.ui.product;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mobimeo.productsell.R;
import com.mobimeo.productsell.data.model.response.product.ProductListResponseItem;
import com.mobimeo.productsell.utils.allInterface.CartAddDeleteInterface;

import java.util.ArrayList;
import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductListHolder>{
    Context context;
    List<ProductListResponseItem> productListResponse = new ArrayList<>();
    CartAddDeleteInterface cartAddDeleteInterface;

    public ProductListAdapter(CartAddDeleteInterface cartAddDeleteInterface) {
        this.cartAddDeleteInterface = cartAddDeleteInterface;
    }

    @NonNull
    @Override
    public ProductListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_product,parent,false);
        return new ProductListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListHolder holder, int position) {
        int pos = position;
        ProductListResponseItem productItem = productListResponse.get(position);
        holder.tvCategory.setText(productItem.getCategory());
        holder.tvPrice.setText("৳ "+productItem.getPrice());
        holder.tvProductName.setText(productItem.getTitle());
        Glide.with(context).load(productItem.getImage()).into(holder.ivProductPic);
        holder.tvProductCount.setText(""+productItem.getCount());

        holder.tvPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.parseInt( holder.tvProductCount.getText().toString());
                count++;
                holder.tvProductCount.setText(""+count);
                productListResponse.get(pos).setCount(count);
                if (count==1){
                    cartAddDeleteInterface.cartAdd(productItem);
                }else {
                    cartAddDeleteInterface.cartUpdate(productItem);
                }
            }
        });
        holder.tvMinus.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onClick(View v) {
                int count = Integer.parseInt( holder.tvProductCount.getText().toString());
                if (0<count)
                count--;
                if (count==0){
                    cartAddDeleteInterface.cartDelete(productItem);
                }else {
                    cartAddDeleteInterface.cartUpdate(productItem);
                }
                holder.tvProductCount.setText(""+count);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productListResponse.size();
    }

    class ProductListHolder extends RecyclerView.ViewHolder {
        AppCompatTextView tvProductName,tvCategory,tvPrice,tvMinus,tvPlus,tvProductCount;
        AppCompatImageView ivProductPic;
        public ProductListHolder(@NonNull View itemView) {
            super(itemView);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvCategory = itemView.findViewById(R.id.tvCategory);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            ivProductPic = itemView.findViewById(R.id.ivProduct);
            tvMinus = itemView.findViewById(R.id.tvMinus);
            tvPlus = itemView.findViewById(R.id.tvPlus);
            tvProductCount = itemView.findViewById(R.id.tvProductCount);
        }
    }

    void updateProductList(List<ProductListResponseItem> updateList){
        productListResponse.clear();
        productListResponse.addAll(updateList);
        notifyDataSetChanged();
    }

}
