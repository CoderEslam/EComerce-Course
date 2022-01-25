package com.doubleclick.ecommerce.Repository;

import androidx.annotation.NonNull;

import com.doubleclick.ecommerce.model.Favorit;
import com.doubleclick.ecommerce.model.ItemProduct;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created By Eslam Ghazy on 1/6/2022
 */
public class FavoritRepository {

    DatabaseReference referenceFavorit;
    ArrayList<ItemProduct> itemProductArrayList = new ArrayList<>();
    ItemInter itemInter;

    public FavoritRepository(ItemInter itemInter) {
        this.itemInter = itemInter;
    }


    public void getFavorit() {
        referenceFavorit = FirebaseDatabase.getInstance().getReference().child("Favorit");
        referenceFavorit.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                DataSnapshot snapshot = task.getResult();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Favorit favorit = dataSnapshot.getValue(Favorit.class);
                    if (favorit.getUserId().equals(FirebaseAuth.getInstance().getCurrentUser().getUid().toString())){
                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("product").child(favorit.getPushIdItem());
                        reference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DataSnapshot> task) {
                                DataSnapshot snapshot = task.getResult();
                                ItemProduct itemProduct = snapshot.getValue(ItemProduct.class);
                                itemProductArrayList.add(itemProduct);
                                itemInter.getItem(itemProductArrayList);
                            }
                        });

                    }
                }
            }
        });
    }

    public interface ItemInter{
        void  getItem(ArrayList<ItemProduct> itemProducts);
    }

}
