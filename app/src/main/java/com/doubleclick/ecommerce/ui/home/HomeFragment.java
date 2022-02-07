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

import com.doubleclick.ecommerce.Adapters.BaseAdapter;
import com.doubleclick.ecommerce.Adapters.productAdapter;
import com.doubleclick.ecommerce.R;
import com.doubleclick.ecommerce.Repository.AllProducts;
import com.doubleclick.ecommerce.databinding.FragmentHomeBinding;
import com.doubleclick.ecommerce.model.AllCategorys;
import com.doubleclick.ecommerce.model.ItemProduct;
import com.doubleclick.ecommerce.viewModel.ProductViewModel;
import com.doubleclick.ecommerce.viewModel.ViewModelProducts;

import java.util.ArrayList;

public class HomeFragment extends Fragment {


    ViewModelProducts viewModelProducts;
    ProductViewModel AllCategoryViewModel;
    RecyclerView All_product;
    ArrayList<ItemProduct> arrayListitemProducts = new ArrayList<>();
    ArrayList<AllCategorys> arrayListallCategorys = new ArrayList<>();
    ArrayList<ArrayList<ItemProduct>> arrayListArrayList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,parent,false);
        All_product = view.findViewById(R.id.All_product);

        viewModelProducts = new ViewModelProvider(this).get(ViewModelProducts.class);
        AllCategoryViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        viewModelProducts.getLiveData().observe(getViewLifecycleOwner(), new Observer<ArrayList<ArrayList<ItemProduct>>>() {
            @Override
            public void onChanged(ArrayList<ArrayList<ItemProduct>> itemProducts) {
                Log.e("HomeFragment",itemProducts.toString());
                BaseAdapter baseAdapter = new BaseAdapter(itemProducts);
                All_product.setAdapter(baseAdapter);
//                productAdapter productAdapter = new productAdapter(itemProducts);
//                All_product.setAdapter(productAdapter);
//                arrayListitemProducts = itemProducts;
                Log.e("All",itemProducts.toString());

            }
        });

        AllCategoryViewModel.getLiveData().observe(getViewLifecycleOwner(), new Observer<ArrayList<AllCategorys>>() {
            @Override
            public void onChanged(ArrayList<AllCategorys> allCategorys) {
                arrayListallCategorys = allCategorys;
            }
        });

        for (int i = 0; i < arrayListallCategorys.size(); i++) {
            ArrayList<ItemProduct> itemArrayList = new ArrayList<>();
            for (ItemProduct item : arrayListitemProducts) {
                if (item.getCategory().equals(arrayListallCategorys.get(i))) { // equals
                    itemArrayList.add(item);
                }
            }
            arrayListArrayList.add(itemArrayList);
        }


        return view;
    }
}