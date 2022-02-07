package com.doubleclick.ecommerce.Repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.doubleclick.ecommerce.model.AllCategorys;
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
    DatabaseReference referenceAllCategory;
    ArrayList<ItemProduct> items = new ArrayList<>();
    ArrayList<AllCategorys> allCategory = new ArrayList<>();
    ArrayList<ArrayList<ItemProduct>> arrayListOfItemProducts = new ArrayList<>();
    ProductListener productListener;
//    MutableLiveData<ArrayList<ItemProduct>> mutableLiveData = new MutableLiveData<>();

    public AllProducts(ProductListener productListener) {
        this.productListener = productListener;
    }


    public void getAllProducts() {

        referenceProduct = FirebaseDatabase.getInstance().getReference().child("product");
        referenceAllCategory = FirebaseDatabase.getInstance().getReference().child("AllCategory");
        referenceProduct.keepSynced(false);

        referenceAllCategory.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                DataSnapshot snapshot = task.getResult();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    AllCategorys allCategorys = dataSnapshot.getValue(AllCategorys.class);
                    allCategory.add(allCategorys);
                    Log.e("AllCategory",allCategorys.toString());
                }
//                AllCategorys allCategorys = dataSnapshot.getValue(AllCategorys.class);
//                AllCategorys allCategorys = task.getResult().getValue(AllCategorys.class);

            }
        });

        referenceProduct.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                DataSnapshot snapshot = task.getResult();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ItemProduct product = dataSnapshot.getValue(ItemProduct.class);
                    items.add(product);

                    Log.e("AllProducts",items.toString());
                }

                for (int i = 0; i < allCategory.size(); i++) {
                    ArrayList<ItemProduct> itemArrayList = new ArrayList<>();
                    for (ItemProduct item : items) {
                        if (item.getCategory().equals(allCategory.get(i).getName())) { // equals
                            itemArrayList.add(item);
                        }
                    }
                    arrayListOfItemProducts.add(itemArrayList);
                    productListener.getAllProducts(arrayListOfItemProducts);
                }
            }
        });
        // addValueEventListener => تكرار للداتا  اذا صا ر فيها تعدير او اضافه او مسح

    }

    //    public LiveData<ArrayList<ItemProduct>> getLive(){
//        return mutableLiveData;
//    }
    public interface ProductListener {
        void getAllProducts(ArrayList<ArrayList<ItemProduct>> interfaceItem);
    }

}
