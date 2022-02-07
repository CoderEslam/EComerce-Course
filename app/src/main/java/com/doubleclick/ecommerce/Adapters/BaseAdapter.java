package com.doubleclick.ecommerce.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.doubleclick.ecommerce.R;
import com.doubleclick.ecommerce.model.ItemProduct;

import java.util.ArrayList;

/**
 * Created By Eslam Ghazy on 2/7/2022
 */
public class BaseAdapter extends RecyclerView.Adapter<BaseAdapter.BaseViewHolder> {

    ArrayList<ArrayList<ItemProduct>> arrayListItems = new ArrayList<>();

    public BaseAdapter(ArrayList<ArrayList<ItemProduct>> arrayListItems) {
        this.arrayListItems = arrayListItems;
    }

    @NonNull
    @Override
    public BaseAdapter.BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.nested_recycler_view,parent,false);
        return new BaseViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        ArrayList<ItemProduct> itemProducts = arrayListItems.get(holder.getAdapterPosition());
        productAdapter productAdapter = new productAdapter(itemProducts);
        holder.nestedRecycler.setAdapter(productAdapter);
    }



    @Override
    public int getItemCount() {
        return arrayListItems.size();
    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public class BaseViewHolder extends RecyclerView.ViewHolder {
        RecyclerView nestedRecycler;
        TextView name;
        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);
            nestedRecycler = itemView.findViewById(R.id.nestedRecycler);
            name = itemView.findViewById(R.id.name);
        }
    }
}
