package com.doubleclick.ecommerce.ui.signin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.doubleclick.ecommerce.Home;
import com.doubleclick.ecommerce.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegisterActiviy extends AppCompatActivity {

    EditText name, email, password;
    Button CreateEmail;
    FirebaseAuth mAuth;
    DatabaseReference reference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        mAuth = FirebaseAuth.getInstance();
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        CreateEmail = findViewById(R.id.CreateEmail);
        CreateEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CraeteEmail(name.getText().toString(), email.getText().toString(), password.getText().toString());
            }
        });
    }

    private void CraeteEmail(String name, String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    String id = mAuth.getCurrentUser().getUid().toString();
                    Map<String,Object> map = new HashMap<>();
                    map.put("name",name);
                    map.put("email",email);
                    map.put("password",password);
                    map.put("id",id);
//                    Users users = new Users(name, email, password,id);
                    reference = FirebaseDatabase.getInstance().getReference().child("Users");
                    reference.child(id).setValue(map);
                    startActivity(new Intent(RegisterActiviy.this, Home.class));
                }

            }
        });

    }
}
