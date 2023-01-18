package com.mobimeo.productsell.ui.cart;


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
import com.mobimeo.productsell.ui.product.ProductListAdapter;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartHolder>{

    Context context;
    List<ProductListResponseItem> productListResponseItems = new ArrayList<>();

    public CartAdapter(List<ProductListResponseItem> cardList) {
        this.productListResponseItems = cardList;
    }

    @NonNull
    @Override
    public CartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart_product,parent,false);
        return new CartHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartHolder holder, int position) {
        holder.tvTitle.setText(productListResponseItems.get(position).getTitle());
        holder.tvPrice.setText("৳ "+productListResponseItems.get(position).getPrice());
        double totalprice =(productListResponseItems.get(position).getPrice()*
                productListResponseItems.get(position).getCount());
        holder.tvTotalPrice.setText("৳ "+totalprice);
        holder.tvCount.setText(""+productListResponseItems.get(position).getCount());
        Glide.with(context).load(productListResponseItems.get(position).getImage()).into(holder.ivProductImg);
    }

    @Override
    public int getItemCount() {
        return productListResponseItems.size();
    }

    class CartHolder extends RecyclerView.ViewHolder {
        AppCompatTextView tvPrice,tvTitle,tvTotalPrice,tvCount;
        AppCompatImageView ivProductImg;
        public CartHolder(@NonNull View itemView) {
            super(itemView);
            tvPrice = itemView.findViewById(R.id.tvCardPrice);
            tvTotalPrice = itemView.findViewById(R.id.tvTotalPrice);
            tvTitle=itemView.findViewById(R.id.tvCardProductName);
            tvCount = itemView.findViewById(R.id.tvCardProductCount);
            ivProductImg = itemView.findViewById(R.id.ivCardProduct);
        }
    }

}
