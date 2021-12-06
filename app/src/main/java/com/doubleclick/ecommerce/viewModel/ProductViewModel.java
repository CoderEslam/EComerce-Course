package com.doubleclick.ecommerce.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.doubleclick.ecommerce.Repository.AllProducts;
import com.doubleclick.ecommerce.model.Allprodusts;

import java.util.ArrayList;

public class ProductViewModel extends ViewModel implements AllProducts.ProductInterface {


    MutableLiveData<ArrayList<Allprodusts>> listMutableLiveData = new MutableLiveData<>();
    AllProducts allProducts = new AllProducts(this);

    public ProductViewModel() {
        allProducts.getAllProduct();
    }

    public LiveData<ArrayList<Allprodusts>> getLiveData(){
        return listMutableLiveData;
    }

    @Override
    public void getProducts(ArrayList<Allprodusts> allprodustsArrayList) {
        listMutableLiveData.setValue(allprodustsArrayList);
    }
}
