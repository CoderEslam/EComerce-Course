package com.doubleclick.ecommerce.ui.OrderProduct;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.doubleclick.ecommerce.R;
import com.doubleclick.ecommerce.model.ItemProduct;
import com.doubleclick.ecommerce.model.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class OrderProductActivity extends AppCompatActivity {


    TextView name,des,trad,price,pricsDis,pricantage,ProductQuntaty;
    ImageView image;
    Button plusone,minsone,addToCart;
    int order = 0;
    ItemProduct itemProduct;
    FirebaseAuth auth;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_product);
        fab = findViewById(R.id.fab);
        itemProduct = (ItemProduct) getIntent().getSerializableExtra("itemProduct");
        image = findViewById(R.id.imageProduct);
        auth = FirebaseAuth.getInstance();
        String id = auth.getCurrentUser().getUid().toString();
        name = findViewById(R.id.name);
        des = findViewById(R.id.description_product);
        trad = findViewById(R.id.tardemarke);
        price = findViewById(R.id.price);
        pricsDis = findViewById(R.id.price_discount);
        pricantage = findViewById(R.id.precintage);
        plusone  = findViewById(R.id.plusone);
        minsone  = findViewById(R.id.minsone);
        addToCart  = findViewById(R.id.add_to_cart);
        ProductQuntaty = findViewById(R.id.ProductQuntaty);
        Picasso.get().load(itemProduct.getImage()).into(image);
        des.setText(itemProduct.getDescription());
        trad.setText(itemProduct.getTrade());
        name.setText(itemProduct.getName());
        if (Double.valueOf(itemProduct.getPrice()).equals(Double.valueOf(itemProduct.getDiscountPrice()))){
            pricsDis.setText(itemProduct.getPrice());
        }else {
            price.setText(itemProduct.getPrice());
            pricsDis.setText(itemProduct.getDiscountPrice());
            double l = Double.valueOf(itemProduct.getPrice());
            double s = Double.valueOf(itemProduct.getDiscountPrice());
            double pricntag = ((l-s)/l)*100.0;
            pricantage.setText(""+pricntag+"%");
            price.setVisibility(View.VISIBLE);
            pricantage.setVisibility(View.VISIBLE);
        }

        plusone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order++;
                ProductQuntaty.setText(String.valueOf(order));

            }
        });

        minsone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductQuntaty.setText(String.valueOf(order));
                if(order<0){
                    order = 0;
                    ProductQuntaty.setText(String.valueOf(order));
                    return;
                }else {
                    order--;
                }
            }
        });

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddToCart();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                putFavorite();
            }
        });

    }

    private void AddToCart() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Cart");
        String pushId = reference.push().getKey().toString();
        Map<String,Object> map = new HashMap<>();
        map.put("name",itemProduct.getName());
        map.put("iamge",itemProduct.getImage());
        map.put("SellerID",itemProduct.getUserId());
        map.put("price",itemProduct.getDiscountPrice());
        map.put("trade",itemProduct.getTrade());
        map.put("quntity",""+order);
        map.put("pushId",pushId);
        map.put("BuyerId",FirebaseAuth.getInstance().getCurrentUser().getUid().toString());
        reference.child(pushId).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(OrderProductActivity.this,"Done",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void putFavorite(){

        DatabaseReference favorit = FirebaseDatabase.getInstance().getReference().child("Favorit");
        String pushId = favorit.push().getKey().toString();
        HashMap<String,Object> map = new HashMap<>();
        map.put("pushIdItem",itemProduct.getPushId());
        map.put("pushIdFavorit",pushId);
        map.put("UserId",FirebaseAuth.getInstance().getCurrentUser().getUid().toString());
//        map.put("dddd",new Users("name","email","123456"));
//        map.put("fffff",new Users("name","email","123456"));

        favorit.child(pushId).setValue(map);

    }
}