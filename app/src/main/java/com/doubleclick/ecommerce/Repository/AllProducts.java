package com.doubleclick.ecommerce.Repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.doubleclick.ecommerce.model.ItemProduct;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created By Eslam Ghazy on 12/16/2021
 */
public class AllProducts extends ViewModel {

    DatabaseReference referenceProduct;
     ArrayList<ItemProduct> item = new ArrayList<>();
    ProductListener productListener;
//    MutableLiveData<ArrayList<ItemProduct>> mutableLiveData = new MutableLiveData<>();

    public AllProducts(ProductListener productListener) {
        this.productListener = productListener;
    }


    public void getAllProducts(){

        referenceProduct = FirebaseDatabase.getInstance().getReference().child("product");
        referenceProduct.keepSynced(false);
        // addValueEventListener => تكرار للداتا  اذا صا ر فيها تعدير او اضافه او مسح
        referenceProduct.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                DataSnapshot snapshot = task.getResult();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    ItemProduct product = dataSnapshot.getValue(ItemProduct.class);
                    item.add(product);
                    productListener.getAllProducts(item);
//                    mutableLiveData.setValue(item);
                }
            }
        });

    }

//    public LiveData<ArrayList<ItemProduct>> getLive(){
//        return mutableLiveData;
//    }
    public interface ProductListener{
        void getAllProducts(ArrayList<ItemProduct> interfaceItem);
    }

}
