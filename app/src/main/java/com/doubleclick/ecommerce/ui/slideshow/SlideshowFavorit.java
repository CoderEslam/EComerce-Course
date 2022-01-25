package com.doubleclick.ecommerce.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.doubleclick.ecommerce.Adapters.productAdapter;
import com.doubleclick.ecommerce.R;
import com.doubleclick.ecommerce.model.ItemProduct;
import com.doubleclick.ecommerce.viewModel.FavoritViewModel;

import java.util.ArrayList;


public class SlideshowFavorit extends Fragment {


    FavoritViewModel favoritViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite,container,false);
        RecyclerView favorite = view.findViewById(R.id.favorite);
        favoritViewModel = new ViewModelProvider(this).get(FavoritViewModel.class);
        favoritViewModel.ItemLiveData().observe(getViewLifecycleOwner(), new Observer<ArrayList<ItemProduct>>() {
            @Override
            public void onChanged(ArrayList<ItemProduct> itemProducts) {
                productAdapter productAdapter = new productAdapter(itemProducts);
                favorite.setAdapter(productAdapter);
            }
        });

        return view;
    }

}