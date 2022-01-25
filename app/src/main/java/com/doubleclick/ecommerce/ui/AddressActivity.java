package com.doubleclick.ecommerce.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.doubleclick.ecommerce.R;
import com.doubleclick.ecommerce.model.Cart;
import com.doubleclick.ecommerce.model.Order;
import com.doubleclick.ecommerce.viewModel.CartViewModel;
import com.doubleclick.ecommerce.viewModel.OrderViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class AddressActivity extends AppCompatActivity {


    Button order;
    EditText name, email, phone, address;
    LottieAnimationView animationView;
    DatabaseReference OrdersReference;
    private CartViewModel cartViewModel;
    ArrayList<Cart> cartArrayList = new ArrayList<>();
    OrderViewModel orderViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        order = findViewById(R.id.okay);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        address = findViewById(R.id.address);
        animationView = findViewById(R.id.animationView);
        orderViewModel = new ViewModelProvider(this).get(OrderViewModel.class);
        orderViewModel.getLivedate().observe(this, new Observer<ArrayList<Order>>() {
            @Override
            public void onChanged(ArrayList<Order> orders) {
                Log.e("orders", orders.toString());
            }
        });
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        cartViewModel.getLiveDataCart().observe(this, new Observer<ArrayList<Cart>>() {
            @Override
            public void onChanged(ArrayList<Cart> carts) {
                cartArrayList = carts;
                Log.e("cartArrayList", cartArrayList.toString());
            }
        });
        OrdersReference = FirebaseDatabase.getInstance().getReference().child("Orders");
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Check()) {
                    for (Cart cart : cartArrayList) {
                        String pushId = OrdersReference.push().getKey().toString();
                        HashMap<String, Object> mapOrders = new HashMap<>();
                        mapOrders.put("name", name.getText().toString()); // name of parson
                        mapOrders.put("email", email.getText().toString());
                        mapOrders.put("phone", phone.getText().toString());
                        mapOrders.put("address", address.getText().toString());
                        mapOrders.put("buyer", cart.getBuyerId()); // FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
                        mapOrders.put("price", cart.getPrice());
                        mapOrders.put("seller", cart.getSellerID());
                        mapOrders.put("quantity", cart.getQuntity());
                        mapOrders.put("image", cart.getIamge());
                        mapOrders.put("nameProduct", cart.getName()); // name of product
                        mapOrders.put("pushId", pushId);
                        OrdersReference.child(pushId).setValue(mapOrders).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    animationView.setVisibility(View.VISIBLE);
                                }
                            }
                        });
                    }
                    name.setText("");
                    phone.setText("");
                    email.setText("");
                    address.setText("");
                }

                // send text by whatsapp

               /* try {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
                sendIntent.setType("text/plain");
                sendIntent.setPackage("com.whatsapp");
                startActivity(Intent.createChooser(sendIntent, ""));
                startActivity(sendIntent);
                }catch (ActivityNotFoundException e){
                    Toast.makeText(AddressActivity.this, "you don't have whatsapp", Toast.LENGTH_SHORT).show();
                    *//*String urlString = "https://whatsapp-messenger.ar.uptodown.com/android/download";
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlString));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setPackage("com.android.chrome");
                    try {
                        startActivity(intent);
                    } catch (ActivityNotFoundException ex) {
                        // Chrome browser presumably not installed so allow user to choose instead
                        intent.setPackage(null);
                        startActivity(intent);
                    }*//*

                    *//*Intent i = new Intent(android.content.Intent.ACTION_VIEW);
                    i.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.whatsapp"));
                    startActivity(i);*//*

                }*/


            }
        });


    }


    public boolean Check() {
        if (!name.getText().toString().equals("") && !phone.getText().toString().equals("") && !address.getText().toString().equals("") && !email.getText().toString().equals("")) {
            return true;
        } else {
            Toast.makeText(AddressActivity.this, "Check all input", Toast.LENGTH_SHORT).show();
            return false;
        }

    }

}