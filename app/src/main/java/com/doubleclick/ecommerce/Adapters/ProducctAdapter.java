package com.doubleclick.ecommerce.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.doubleclick.ecommerce.R;
import com.doubleclick.ecommerce.model.Allprodusts;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProducctAdapter extends RecyclerView.Adapter<ProducctAdapter.ProductViewHolder> {

    public ProducctAdapter(ArrayList<Allprodusts> allprodusts) {
        this.allprodusts = allprodusts;
    }

    ArrayList<Allprodusts> allprodusts = new ArrayList<>();
    @NonNull
    @Override
    public ProducctAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product,parent,false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProducctAdapter.ProductViewHolder holder, int position) {

//        holder.image.setImageResource(allprodusts.get(position).getDrwableimage());
        Picasso.get().load(allprodusts.get(position).getImage()).into(holder.image);
        holder.name.setText(allprodusts.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return allprodusts.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        CircleImageView image;
        TextView name;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.product_image);
            name = itemView.findViewById(R.id.product_title);
        }
    }
}
