package com.doubleclick.ecommerce.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.doubleclick.ecommerce.R;
import com.doubleclick.ecommerce.model.AllCategorys;
import com.doubleclick.ecommerce.ui.Admin.UploadActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ProductViewHolder> {

    public CategoryAdapter(ArrayList<AllCategorys> allprodusts) {
        this.allprodusts = allprodusts;
    }

    ArrayList<AllCategorys> allprodusts = new ArrayList<>();

    @NonNull
    @Override
    public CategoryAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product,parent,false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ProductViewHolder holder, int position) {

//        holder.image.setImageResource(allprodusts.get(position).getDrwableimage());
        Picasso.get().load(allprodusts.get(position).getImage()).into(holder.image);
        holder.name.setText(allprodusts.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), UploadActivity.class);
                intent.putExtra("Category",allprodusts.get(position).getName());
                holder.itemView.getContext().startActivity(intent);
            }
        });

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
