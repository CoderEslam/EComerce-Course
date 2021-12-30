package com.doubleclick.ecommerce.ui.Cart;

import android.content.Intent;
import android.os.Bundle;
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
import com.doubleclick.ecommerce.ui.AddressActivity;
import com.doubleclick.ecommerce.viewModel.CartViewModel;

import java.util.ArrayList;


public class CartFragment extends Fragment {

    private FragmentCartBinding binding;
    private CartViewModel cartViewModel;
    ArrayList<Cart> cartArrayList;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCartBinding.inflate(inflater, container, false);
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        cartViewModel.getLiveDataCart().observe(getViewLifecycleOwner(), new Observer<ArrayList<Cart>>() {
            @Override
            public void onChanged(ArrayList<Cart> carts) {
                cartArrayList = carts;
                double totalPrice = 0;
                CartAdapter cartAdapter = new CartAdapter(carts);
                binding.CartRecycler.setAdapter(cartAdapter);
                for (int i = 0;i<carts.size();i++){
                    totalPrice = totalPrice +  Double.parseDouble(carts.get(i).getPrice())*Double.parseDouble(carts.get(i).getQuntity());
                    binding.price.setText(""+totalPrice);
                }


            }
        });

        binding.confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), AddressActivity.class));
//                Intent intent = new Intent(getContext(), AddressActivity.class);
//                intent.putExtra("array",cartArrayList);
            }
        });


        return binding.getRoot();
    }

}