package com.doubleclick.ecommerce.Repository;

import androidx.annotation.NonNull;

import com.doubleclick.ecommerce.model.AllCategorys;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AllCategory {

    DatabaseReference reference;
    ArrayList<AllCategorys> allCategorysArrayList = new ArrayList<>();
    ProductInterface productInterface;

    public AllCategory(ProductInterface productInterface) {
        this.productInterface = productInterface;
    }

    public void getAllProduct(){

        reference = FirebaseDatabase.getInstance().getReference().child("AllCategory");
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
                allCategorysArrayList.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    AllCategorys allCategorys = dataSnapshot.getValue(AllCategorys.class);
                    allCategorysArrayList.add(allCategorys);
                    productInterface.getProducts(allCategorysArrayList);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public interface ProductInterface{
        void getProducts(ArrayList<AllCategorys> allCategorysArrayList);
    }


}
