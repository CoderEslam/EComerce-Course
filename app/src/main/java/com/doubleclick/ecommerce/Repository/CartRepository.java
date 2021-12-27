package com.doubleclick.ecommerce.Repository;

import androidx.annotation.NonNull;

import com.doubleclick.ecommerce.model.Cart;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created By Eslam Ghazy on 12/23/2021
 */
public class CartRepository {

    DatabaseReference reference;
    ArrayList<Cart> cartArrayList = new ArrayList<>();
    CartInterface cartInterface;

    public CartRepository(CartInterface cartInterface) {
        this.cartInterface = cartInterface;
    }


    public void getAllCart() {

        reference = FirebaseDatabase.getInstance().getReference().child("Cart");
        reference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                DataSnapshot snapshot = task.getResult();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Cart cart = dataSnapshot.getValue(Cart.class);
                    if (cart.getBuyerId().equals(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())){
                        cartArrayList.add(cart);
                        cartInterface.getAllCart(cartArrayList);
                    }
                }
            }
        });

    }


    public interface CartInterface {

        void getAllCart(ArrayList<Cart> carts);

    }

}
