package com.doubleclick.ecommerce.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.doubleclick.ecommerce.Repository.UserRepository;
import com.doubleclick.ecommerce.model.Users;

/**
 * Created By Eslam Ghazy on 1/6/2022
 */
public class UserViewModel extends ViewModel implements UserRepository.UserInter {

    UserRepository userRepository = new UserRepository(this);
    MutableLiveData<Users> mutableLiveData = new MutableLiveData<>();

    public UserViewModel() {

        userRepository.getUser();

    }


    public LiveData<Users> UserLiveData(){
        return mutableLiveData;
    }


    @Override
    public void user(Users user) {
       mutableLiveData.setValue(user);
    }
}
