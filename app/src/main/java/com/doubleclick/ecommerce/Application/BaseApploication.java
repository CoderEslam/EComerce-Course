package com.doubleclick.ecommerce.Application;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class BaseApploication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
