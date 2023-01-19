package com.mobimeo.productsell.ui.cart;


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
import com.mobimeo.productsell.ui.product.ProductListAdapter;
import com.mobimeo.productsell.utils.allInterface.CartAddDeleteInterface;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartHolder>{

    Context context;
    List<ProductListResponseItem> productListResponseItems ;
    CartAddDeleteInterface cartAddDeleteInterface ;

    public CartAdapter(List<ProductListResponseItem> cardList, CartAddDeleteInterface cartAddDeleteInterface) {
        this.productListResponseItems = cardList;
        this.cartAddDeleteInterface = cartAddDeleteInterface;
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
        int pos=position;
        ProductListResponseItem productItem = productListResponseItems.get(position);
        holder.tvTitle.setText(productListResponseItems.get(position).getTitle());
        holder.tvPrice.setText("৳ "+productListResponseItems.get(position).getPrice());
        double totalprice =(productListResponseItems.get(position).getPrice()*
                productListResponseItems.get(position).getCount());
        holder.tvTotalPrice.setText("৳ "+totalprice);
        holder.tvProductCount.setText(""+productListResponseItems.get(position).getCount());
        Glide.with(context).load(productListResponseItems.get(position).getImage()).into(holder.ivProductImg);

        holder.tvPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.parseInt( holder.tvProductCount.getText().toString());
                count++;
                holder.tvProductCount.setText(""+count);
                productListResponseItems.get(pos).setCount(count);
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
        return productListResponseItems.size();
    }

    class CartHolder extends RecyclerView.ViewHolder {
        AppCompatTextView tvPrice,tvTitle,tvTotalPrice,tvCount,tvPlus,tvMinus,tvProductCount;
        AppCompatImageView ivProductImg;
        public CartHolder(@NonNull View itemView) {
            super(itemView);
            tvPrice = itemView.findViewById(R.id.tvCardPrice);
            tvTotalPrice = itemView.findViewById(R.id.tvTotalPrice);
            tvTitle=itemView.findViewById(R.id.tvCardProductName);
            ivProductImg = itemView.findViewById(R.id.ivCardProduct);
            tvPlus = itemView.findViewById(R.id.tvCardPlus);
            tvMinus = itemView.findViewById(R.id.tvCardMinus);
            tvProductCount = itemView.findViewById(R.id.tvCardProductCount);
        }
    }

}
