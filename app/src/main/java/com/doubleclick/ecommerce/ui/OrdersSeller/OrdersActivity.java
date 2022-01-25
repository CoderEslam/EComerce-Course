package com.doubleclick.ecommerce.ui.OrdersSeller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.doubleclick.ecommerce.Adapters.OrderAdapter;
import com.doubleclick.ecommerce.R;
import com.doubleclick.ecommerce.model.Order;
import com.doubleclick.ecommerce.viewModel.OrderViewModel;

import java.util.ArrayList;

public class OrdersActivity extends AppCompatActivity {


    RecyclerView R_orders;
    OrderViewModel orderViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        R_orders = findViewById(R.id.orders);
        orderViewModel = new ViewModelProvider(this).get(OrderViewModel.class);
        orderViewModel.getLivedate().observe(this, new Observer<ArrayList<Order>>() {
            @Override
            public void onChanged(ArrayList<Order> orders) {
                OrderAdapter orderAdapter = new OrderAdapter(orders);
                R_orders.setAdapter(orderAdapter);
            }
        });



    }
}