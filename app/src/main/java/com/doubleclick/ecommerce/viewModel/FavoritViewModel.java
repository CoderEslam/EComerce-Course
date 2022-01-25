package com.doubleclick.ecommerce.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.doubleclick.ecommerce.Repository.FavoritRepository;
import com.doubleclick.ecommerce.model.Favorit;
import com.doubleclick.ecommerce.model.ItemProduct;

import java.util.ArrayList;

/**
 * Created By Eslam Ghazy on 1/6/2022
 */
public class FavoritViewModel extends ViewModel implements FavoritRepository.ItemInter {


    MutableLiveData<ArrayList<ItemProduct>> mutableLiveData = new MutableLiveData<>();
    FavoritRepository favoritRepository = new FavoritRepository(this);

    public FavoritViewModel() {
        favoritRepository.getFavorit();
    }

    public LiveData<ArrayList<ItemProduct>> ItemLiveData(){
        return mutableLiveData;
    }

    @Override
    public void getItem(ArrayList<ItemProduct> itemProducts) {
        mutableLiveData.setValue(itemProducts);
    }
}
