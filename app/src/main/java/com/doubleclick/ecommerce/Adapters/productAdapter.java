package com.doubleclick.ecommerce.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.doubleclick.ecommerce.R;
import com.doubleclick.ecommerce.model.ItemProduct;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created By Eslam Ghazy on 12/16/2021
 */
public class productAdapter extends RecyclerView.Adapter<productAdapter.ProdactViewHolder> {

    ArrayList<ItemProduct> itemProducts = new ArrayList<>();

    public productAdapter(ArrayList<ItemProduct> itemProducts) {
        this.itemProducts = itemProducts;
    }

    public void setArraList(ArrayList<ItemProduct> products){
        itemProducts = products;
    }

    @NonNull
    @Override
    public productAdapter.ProdactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.product,parent,false);
        return new ProdactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull productAdapter.ProdactViewHolder holder, int position) {
        ItemProduct itemProduct = itemProducts.get(position);
        Picasso.get().load(itemProduct.getImage()).into(holder.product_image);
        holder.name.setText(itemProduct.getName());
        holder.Desciption.setText(itemProduct.getDescription());

    }

    @Override
    public int getItemCount() {
        return itemProducts.size();
    }

    public class ProdactViewHolder extends RecyclerView.ViewHolder {
        ImageView product_image;
        TextView name ,Desciption;

        public ProdactViewHolder(@NonNull View itemView) {
            super(itemView);
            product_image = itemView.findViewById(R.id.product_image);
            name = itemView.findViewById(R.id.name);
            Desciption = itemView.findViewById(R.id.description);


        }
    }
}
