package com.doubleclick.ecommerce.Repository;

import androidx.annotation.NonNull;

import com.doubleclick.ecommerce.model.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created By Eslam Ghazy on 1/6/2022
 */
public class UserRepository {

    DatabaseReference reference;
    public UserInter userInter;

    public UserRepository(UserInter userInter) {
        this.userInter = userInter;
    }

    public void getUser(){
        reference = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid().toString());
        reference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                DataSnapshot snapshot = task.getResult();
                Users user = snapshot.getValue(Users.class);
                userInter.user(user);
            }
        });

    }
    public interface UserInter{
        void user(Users user);
    }


}
