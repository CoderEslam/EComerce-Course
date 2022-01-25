package com.doubleclick.ecommerce.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.doubleclick.ecommerce.R;
import com.doubleclick.ecommerce.model.Order;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created By Eslam Ghazy on 1/3/2022
 */
public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    ArrayList<Order> orders = new ArrayList<>();

    public OrderAdapter(ArrayList<Order> orders) {
        this.orders = orders;
    }

    @NonNull
    @Override
    public OrderAdapter.OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_layout,parent,false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.OrderViewHolder holder, int position) {
        Order order = orders.get(position);
        Picasso.get().load(order.getImage()).into(holder.imageCart);
        holder.name.setText(order.getNameProduct());
        holder.name_buyer.setText(order.getName());
        holder.quntity.setText(order.getQuantity());
        holder.price.setText(order.getPrice());
        holder.email.setText(order.getEmail());
        holder.address.setText(order.getAddress());

    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {
        CircleImageView imageCart;
        TextView name,price,quntity,name_buyer,email,address;
        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            imageCart = itemView.findViewById(R.id.imageCart);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            quntity = itemView.findViewById(R.id.quntity);
            name_buyer = itemView.findViewById(R.id.buyer);
            email = itemView.findViewById(R.id.email);
            address = itemView.findViewById(R.id.address);
        }
    }
}
