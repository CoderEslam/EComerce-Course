package com.doubleclick.ecommerce.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.doubleclick.ecommerce.Adapters.productAdapter;
import com.doubleclick.ecommerce.R;
import com.doubleclick.ecommerce.Repository.AllProducts;
import com.doubleclick.ecommerce.databinding.FragmentHomeBinding;
import com.doubleclick.ecommerce.model.ItemProduct;
import com.doubleclick.ecommerce.viewModel.ViewModelProducts;

import java.util.ArrayList;

public class HomeFragment extends Fragment {


    ViewModelProducts viewModelProducts;
    RecyclerView All_product;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,parent,false);
        All_product = view.findViewById(R.id.All_product);

        viewModelProducts = new ViewModelProvider(this).get(ViewModelProducts.class);
        viewModelProducts.getLiveData().observe(this, new Observer<ArrayList<ItemProduct>>() {
            @Override
            public void onChanged(ArrayList<ItemProduct> itemProducts) {
                Log.e("HomeFragment",itemProducts.toString());
                productAdapter productAdapter = new productAdapter(itemProducts);
                All_product.setAdapter(productAdapter);
            }
        });

        return view;
    }
}