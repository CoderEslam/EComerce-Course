package com.doubleclick.ecommerce.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.doubleclick.ecommerce.Repository.AllCategory;
import com.doubleclick.ecommerce.model.AllCategorys;

import java.util.ArrayList;

public class ProductViewModel extends ViewModel implements AllCategory.ProductInterface {


    MutableLiveData<ArrayList<AllCategorys>> listMutableLiveData = new MutableLiveData<>();
    AllCategory allProducts = new AllCategory(this);

    public ProductViewModel() {
        allProducts.getAllProduct();
    }

    public LiveData<ArrayList<AllCategorys>> getLiveData(){
        return listMutableLiveData;
    }

    @Override
    public void getProducts(ArrayList<AllCategorys> allCategorysArrayList) {
        listMutableLiveData.setValue(allCategorysArrayList);
    }
}
