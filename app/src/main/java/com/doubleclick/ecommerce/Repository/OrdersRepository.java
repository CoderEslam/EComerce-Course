package com.doubleclick.ecommerce.Repository;

import android.util.Log;

import androidx.annotation.NonNull;

import com.doubleclick.ecommerce.model.Order;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created By Eslam Ghazy on 12/30/2021
 */
public class OrdersRepository {

    DatabaseReference OrdersRef;
    ArrayList<Order> orderArrayList = new ArrayList<>();
    OrdersInterface orders;

    public OrdersRepository(OrdersInterface orders) {
        this.orders = orders;
    }


    public void getOrders(){
        OrdersRef= FirebaseDatabase.getInstance().getReference().child("Orders");
        OrdersRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                DataSnapshot snapshot = task.getResult();
                Log.e("DataSnapShot",task.getResult().toString());
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Order order = dataSnapshot.getValue(Order.class);
                    orderArrayList.add(order);
                    orders.getAllOrders(orderArrayList);
                }
            }
        });
    }

    public interface OrdersInterface{
        void getAllOrders(ArrayList<Order> orders);
    }


}
