package com.doubleclick.ecommerce.ui.gallery;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.doubleclick.ecommerce.Adapters.CartAdapter;
import com.doubleclick.ecommerce.databinding.FragmentCartBinding;
import com.doubleclick.ecommerce.model.Cart;
import com.doubleclick.ecommerce.viewModel.CartViewModel;

import java.util.ArrayList;


public class CartFragment extends Fragment {

    private FragmentCartBinding binding;
    private CartViewModel cartViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCartBinding.inflate(inflater, container, false);
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        cartViewModel.getLiveDataCart().observe(getViewLifecycleOwner(), new Observer<ArrayList<Cart>>() {
            @Override
            public void onChanged(ArrayList<Cart> carts) {

                CartAdapter cartAdapter = new CartAdapter(carts);
                binding.CartRecycler.setAdapter(cartAdapter);


            }
        });
        return binding.getRoot();
    }

}