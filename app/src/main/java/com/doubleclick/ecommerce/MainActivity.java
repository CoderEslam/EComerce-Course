package com.doubleclick.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void Create(View view) {

        startActivity(new Intent(MainActivity.this,RegisterActiviy.class));

    }

    public void SignIn(View view) {

        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(intent);

    }
}