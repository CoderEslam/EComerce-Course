package com.doubleclick.ecommerce.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.doubleclick.ecommerce.Repository.OrdersRepository;
import com.doubleclick.ecommerce.model.Order;

import java.util.ArrayList;

/**
 * Created By Eslam Ghazy on 12/30/2021
 */
public class OrderViewModel extends ViewModel implements OrdersRepository.OrdersInterface {


    OrdersRepository ordersRepository = new OrdersRepository(this);

    MutableLiveData<ArrayList<Order>> mutableLiveData = new MutableLiveData<>();

    public OrderViewModel() {

        ordersRepository.getOrders();

    }

    public LiveData<ArrayList<Order>> getLivedate(){
        return mutableLiveData;
    }

    @Override
    public void getAllOrders(ArrayList<Order> orders) {
        mutableLiveData.setValue(orders);
    }
}
