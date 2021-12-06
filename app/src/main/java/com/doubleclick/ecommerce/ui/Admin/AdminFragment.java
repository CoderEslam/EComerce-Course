package com.doubleclick.ecommerce.ui.Admin;

import static android.app.Activity.RESULT_OK;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.doubleclick.ecommerce.Adapters.ProducctAdapter;
import com.doubleclick.ecommerce.R;
import com.doubleclick.ecommerce.model.Allprodusts;
import com.doubleclick.ecommerce.viewModel.ProductViewModel;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdminFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminFragment extends Fragment {



    public AdminFragment() {
        // Required empty public constructor
    }
    private final int request_code = 600;
    private Uri imageUri;
    private ImageView imageView;
    private EditText name;
    private StorageReference referenceProducts;
    private DatabaseReference allProduct;
    private ProductViewModel productViewModel;

    public static AdminFragment newInstance(String param1, String param2) {
        AdminFragment fragment = new AdminFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin, container, false);
        RecyclerView Products = view.findViewById(R.id.Products);
        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        productViewModel.getLiveData().observe(this, new Observer<ArrayList<Allprodusts>>() {
            @Override
            public void onChanged(ArrayList<Allprodusts> allprodustsArrayList) {
                Log.e("Data",allprodustsArrayList.toString());
                ProducctAdapter producctAdapter = new ProducctAdapter(allprodustsArrayList);
                Products.setAdapter(producctAdapter);
            }
        });
        referenceProducts = FirebaseStorage.getInstance().getReference().child("ProductsImage");
        allProduct = FirebaseDatabase.getInstance().getReference().child("AllProducts");
//        ArrayList<Allprodusts> allprodusts = new ArrayList<>();
//        allprodusts.add(new Allprodusts("T",R.drawable.saturn));
//        allprodusts.add(new Allprodusts("R",R.drawable.saturn));
//        allprodusts.add(new Allprodusts("Y",R.drawable.saturn));
//        allprodusts.add(new Allprodusts("H",R.drawable.saturn));
//        allprodusts.add(new Allprodusts("J",R.drawable.saturn));
//        allprodusts.add(new Allprodusts("R",R.drawable.saturn));
//        allprodusts.add(new Allprodusts("W",R.drawable.saturn));
//        allprodusts.add(new Allprodusts("C",R.drawable.saturn));
//        ProducctAdapter producctAdapter = new ProducctAdapter(allprodusts);
//        Products.setAdapter(producctAdapter);
        return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.add_product,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                // Not implemented here
                Toast.makeText(getContext(), "Clicked", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View view = LayoutInflater.from(getContext()).inflate(R.layout.item_view,null,false);
                imageView = view.findViewById(R.id.image);
                name = view.findViewById(R.id.name_product);
                Button uplaod = view.findViewById(R.id.upload);
                Button cancel = view.findViewById(R.id.cancel);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openGellery();
                    }
                });
                uplaod.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        uploadImage();
                    }
                });
                builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        dialog.dismiss();
                    }
                });


                builder.setView(view);
                builder.show();
                return true;

        }
        return false;
    }

    private void openGellery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, request_code);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == request_code && resultCode == RESULT_OK  && data.getData() != null) {
            imageUri = data.getData();
            imageView.setImageURI(imageUri);

        }
    }

    private void uploadImage() {
        if (imageUri!=null){
            Date date = new Date();
            StorageReference reference = referenceProducts.child(date.getTime()+".jpg");
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
                    Map<String,Object> map = new HashMap<>();
                    map.put("image",image);
                    map.put("name",name.getText().toString());
                    allProduct.push().setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(getContext(), "Done", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            });

        }

    }

}