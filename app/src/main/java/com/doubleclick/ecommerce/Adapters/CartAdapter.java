package com.doubleclick.ecommerce.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.doubleclick.ecommerce.R;
import com.doubleclick.ecommerce.model.Cart;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created By Eslam Ghazy on 12/23/2021
 */
public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {


    ArrayList<Cart> cartArrayList  =new ArrayList<>();

    public CartAdapter(ArrayList<Cart> cartArrayList) {
        this.cartArrayList = cartArrayList;
    }

    @NonNull
    @Override
    public CartAdapter.CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_layout,parent,false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.CartViewHolder holder, int position) {

        Cart cart = cartArrayList.get(position);
        holder.name.setText(cart.getName());
        //////////////////////////////////////////////////////
        holder.price.setText(cartArrayList.get(position).getPrice());
        ////////////////////////////////////////////////////////////////////
        holder.quntity.setText(cartArrayList.get(holder.getAdapterPosition()).getQuntity());

        Picasso.get().load(cartArrayList.get(position).getIamge()).into(holder.imageCart);

    }

    @Override
    public int getItemCount() {
        return cartArrayList.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        CircleImageView imageCart;
        TextView name,price,quntity;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            imageCart = itemView.findViewById(R.id.imageCart);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            quntity = itemView.findViewById(R.id.quntity);

        }
    }
}
