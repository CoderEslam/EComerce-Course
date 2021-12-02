package com.doubleclick.ecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {


    FirebaseAuth.AuthStateListener listener;
    FirebaseAuth  mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        /*listener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user!=null){

                }
            }
        };*/
    }

    public void Create(View view) {

        startActivity(new Intent(MainActivity.this,RegisterActiviy.class));

    }

    public void SignIn(View view) {

        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(intent);

    }


    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                Intent intent = new Intent(MainActivity.this,Home.class);
                startActivity(intent);
            }
        });
    }
}