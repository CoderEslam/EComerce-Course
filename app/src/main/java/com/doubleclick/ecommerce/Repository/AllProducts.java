package com.doubleclick.ecommerce.Repository;

import androidx.annotation.NonNull;

import com.doubleclick.ecommerce.model.Allprodusts;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AllProducts {

    DatabaseReference reference;
    ArrayList<Allprodusts> allprodustsArrayList = new ArrayList<>();
    ProductInterface productInterface;

    public AllProducts(ProductInterface productInterface) {
        this.productInterface = productInterface;
    }

    public void getAllProduct(){

        reference = FirebaseDatabase.getInstance().getReference().child("AllProducts");
        reference.keepSynced(true);
//        reference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DataSnapshot> task) {
//                DataSnapshot snapshot = task.getResult();
//                allprodustsArrayList.clear();
//                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
//                    Allprodusts allprodusts = dataSnapshot.getValue(Allprodusts.class);
//                    allprodustsArrayList.add(allprodusts);
//                    productInterface.getProducts(allprodustsArrayList);
//                }
//            }
//        });
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                allprodustsArrayList.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Allprodusts allprodusts = dataSnapshot.getValue(Allprodusts.class);
                    allprodustsArrayList.add(allprodusts);
                    productInterface.getProducts(allprodustsArrayList);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public interface ProductInterface{
        void getProducts(ArrayList<Allprodusts> allprodustsArrayList);
    }


}
