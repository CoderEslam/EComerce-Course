package com.doubleclick.ecommerce.ui.Profile;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.doubleclick.ecommerce.Adapters.OrderAdapter;
import com.doubleclick.ecommerce.R;
import com.doubleclick.ecommerce.model.Order;
import com.doubleclick.ecommerce.model.Users;
import com.doubleclick.ecommerce.viewModel.OrderViewModel;
import com.doubleclick.ecommerce.viewModel.UserViewModel;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileFragment extends Fragment {

    DatabaseReference referenceUser;
    FirebaseAuth mAuth;
    TextView name;
    private final int request_code = 500;
    Uri imageUri;
    CircleImageView image;
    ImageView changeimage;
    ProgressBar loading;
    String id;
    private StorageReference referenceProfile;
    OrderViewModel orderViewModel;
    RecyclerView oldOrder;
    UserViewModel userViewModel;


    public ProfileFragment() {
        // Required empty public constructor
    }


    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        referenceProfile = FirebaseStorage.getInstance().getReference().child("ProfilesImage");
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        oldOrder = view.findViewById(R.id.recentOrder);
        mAuth = FirebaseAuth.getInstance();
        image = view.findViewById(R.id.image);
        loading = view.findViewById(R.id.loading);
        changeimage = view.findViewById(R.id.changeimage);
        id = mAuth.getCurrentUser().getUid().toString();
        orderViewModel = new ViewModelProvider(this).get(OrderViewModel.class);
        orderViewModel.getLivedate().observe(getViewLifecycleOwner(), new Observer<ArrayList<Order>>() {
            @Override
            public void onChanged(ArrayList<Order> orders) {
                OrderAdapter orderAdapter=new OrderAdapter(orders);
                oldOrder.setAdapter(orderAdapter);
            }
        });
        referenceUser = FirebaseDatabase.getInstance().getReference().child("Users").child(id);
        name = view.findViewById(R.id.name);

        userViewModel.UserLiveData().observe(getViewLifecycleOwner(), new Observer<Users>() {
            @Override
            public void onChanged(Users users) {
                name.setText(users.getName());
                Picasso.get().load(users.getImage()).placeholder(R.drawable.saturn).into(image);
            }
        });
        changeimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGellery("image/*");
            }
        });


        return view;
    }


    private void openGellery(String type) {
        Intent intent = new Intent();
        intent.setType(type);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, request_code);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == request_code && resultCode == RESULT_OK  && data.getData() != null) {
            imageUri = data.getData();
            image.setImageURI(imageUri);
            uploadImage();
        }
    }


    private void uploadImage() {
        if (imageUri!=null){
            loading.setVisibility(View.VISIBLE);
            StorageReference reference = referenceProfile.child(id+".jpg");
            UploadTask task = reference.putFile(imageUri);
            task.continueWithTask(new Continuation<UploadTask.TaskSnapshot,Task<Uri>>() {
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
                    map.put("Image",image);
                    referenceUser.updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            loading.setVisibility(View.GONE);
                        }
                    });

                }
            });

        }

    }

}