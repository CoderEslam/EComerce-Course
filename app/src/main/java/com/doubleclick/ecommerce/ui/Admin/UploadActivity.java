package com.doubleclick.ecommerce.ui.Admin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.doubleclick.ecommerce.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UploadActivity extends AppCompatActivity {


    ImageView Item_image;
    EditText Item_name,price,priceAfter,description,trade;
    private final int request_code = 600;
    Uri imageUri;
    Button upload;
    ProgressBar loading;
    StorageReference ImageProducts;
    DatabaseReference ProductRef;
    String Category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        Item_image = findViewById(R.id.Item_image);
        Item_name = findViewById(R.id.Item_name);
        price = findViewById(R.id.price);
        priceAfter = findViewById(R.id.priceAfter);
        description = findViewById(R.id.description);
        trade = findViewById(R.id.trade);
        loading = findViewById(R.id.loading);
        ImageProducts = FirebaseStorage.getInstance().getReference().child("ProductsImage");
        ProductRef = FirebaseDatabase.getInstance().getReference().child("product");
        Category = getIntent().getStringExtra("Category");
        findViewById(R.id.upload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadProduct();
            }
        });

        Item_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGellery();
            }
        });

    }

    private void uploadProduct() {
        if (imageUri!=null){
            loading.setVisibility(View.VISIBLE);
            Date date = new Date();
            StorageReference reference = ImageProducts.child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString()+date.getTime()+".jpg");
            UploadTask task = reference.putFile(imageUri);
            task.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (task.isSuccessful()){
                        return reference.getDownloadUrl();
                    }
                    return null;
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    Uri uri = task.getResult();
                    String image = uri.toString();
                    String pushId = ProductRef.push().getKey().toString();
                    Map<String,Object> map = new HashMap<>();
                    map.put("image",image);
                    map.put("Name",Item_name.getText().toString());
                    map.put("Description",description.getText().toString());
                    map.put("userId",FirebaseAuth.getInstance().getCurrentUser().getUid().toString());
                    map.put("price",price.getText().toString());
                    map.put("discountPrice",priceAfter.getText().toString());
                    map.put("trade",trade.getText().toString());
                    map.put("Category",Category);
                    map.put("pushId",pushId);
                    ProductRef.child(pushId).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            loading.setVisibility(View.GONE);
                        }
                    });
                }
            });
        }
    }


    private void openGellery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, request_code);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imageUri = data.getData();
        Item_image.setImageURI(imageUri);

    }
}