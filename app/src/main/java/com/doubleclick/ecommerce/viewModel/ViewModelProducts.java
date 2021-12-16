package com.doubleclick.ecommerce.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.doubleclick.ecommerce.Repository.AllProducts;
import com.doubleclick.ecommerce.model.ItemProduct;

import java.util.ArrayList;

/**
 * Created By Eslam Ghazy on 12/16/2021
 */
public class ViewModelProducts extends ViewModel  implements AllProducts.ProductListener {


    MutableLiveData<ArrayList<ItemProduct>> mutableLiveData = new MutableLiveData();
    AllProducts allProducts = new AllProducts(this);


    public ViewModelProducts() {
        allProducts.getAllProducts();
    }

    public LiveData<ArrayList<ItemProduct>> getLiveData(){
        return mutableLiveData;
    }

    @Override
    public void getAllProducts(ArrayList<ItemProduct> itemProducts) {
        mutableLiveData.setValue(itemProducts);
    }
}
