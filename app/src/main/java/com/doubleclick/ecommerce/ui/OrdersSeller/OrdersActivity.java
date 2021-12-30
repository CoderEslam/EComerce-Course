package com.doubleclick.ecommerce.ui.OrdersSeller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.doubleclick.ecommerce.R;

public class OrdersActivity extends AppCompatActivity {


    RecyclerView orders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        orders = findViewById(R.id.orders);



    }
}