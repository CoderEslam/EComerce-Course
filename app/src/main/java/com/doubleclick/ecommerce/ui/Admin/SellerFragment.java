package com.doubleclick.ecommerce.ui.Admin;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.doubleclick.ecommerce.Adapters.ProducctAdapter;
import com.doubleclick.ecommerce.R;
import com.doubleclick.ecommerce.model.AllCategorys;
import com.doubleclick.ecommerce.viewModel.ProductViewModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SellerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SellerFragment extends Fragment {


    public SellerFragment() {
        // Required empty public constructor
    }

    private final int request_code = 600;
    private Uri imageUri;
    private ImageView imageView;
    private EditText name;
    private StorageReference referenceProducts;
    private DatabaseReference allProduct;
    private ProductViewModel productViewModel;

    public static SellerFragment newInstance(String param1, String param2) {
        SellerFragment fragment = new SellerFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin, container, false);
        RecyclerView Products = view.findViewById(R.id.Products);
        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        productViewModel.getLiveData().observe(this, new Observer<ArrayList<AllCategorys>>() {
            @Override
            public void onChanged(ArrayList<AllCategorys> allCategorysArrayList) {
                Log.e("Data", allCategorysArrayList.toString());
                ProducctAdapter producctAdapter = new ProducctAdapter(allCategorysArrayList);
                Products.setAdapter(producctAdapter);
            }
        });
        referenceProducts = FirebaseStorage.getInstance().getReference().child("ProductsImage");
        allProduct = FirebaseDatabase.getInstance().getReference().child("AllProducts");
//        ArrayList<Allprodusts> allprodusts = new ArrayList<>();
//        allprodusts.add(new Allprodusts("T",R.drawable.saturn));
//        allprodusts.add(new Allprodusts("R",R.drawable.saturn));
//        allprodusts.add(new Allprodusts("Y",R.drawable.saturn));
//        allprodusts.add(new Allprodusts("H",R.drawable.saturn));
//        allprodusts.add(new Allprodusts("J",R.drawable.saturn));
//        allprodusts.add(new Allprodusts("R",R.drawable.saturn));
//        allprodusts.add(new Allprodusts("W",R.drawable.saturn));
//        allprodusts.add(new Allprodusts("C",R.drawable.saturn));
//        ProducctAdapter producctAdapter = new ProducctAdapter(allprodusts);
//        Products.setAdapter(producctAdapter);
        return view;
    }

}