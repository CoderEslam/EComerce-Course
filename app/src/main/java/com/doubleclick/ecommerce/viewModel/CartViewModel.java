package com.doubleclick.ecommerce.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.doubleclick.ecommerce.Repository.CartRepository;
import com.doubleclick.ecommerce.model.Cart;

import java.util.ArrayList;

/**
 * Created By Eslam Ghazy on 12/23/2021
 */
public class CartViewModel extends ViewModel implements CartRepository.CartInterface {



    MutableLiveData<ArrayList<Cart>> mutableLiveData = new MutableLiveData<>();
    CartRepository cartRepository  =new CartRepository(this);

    public CartViewModel() {
        cartRepository.getAllCart();
    }

    public LiveData<ArrayList<Cart>> getLiveDataCart(){
        return mutableLiveData;
    }


    @Override
    public void getAllCart(ArrayList<Cart> carts) {
        mutableLiveData.setValue(carts);
    }
}
