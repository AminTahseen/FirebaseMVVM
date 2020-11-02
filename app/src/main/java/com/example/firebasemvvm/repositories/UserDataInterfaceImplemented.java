package com.example.firebasemvvm.repositories;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.firebasemvvm.models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserDataInterfaceImplemented{
    static UserDataInterfaceImplemented INSTANCE;
    private ArrayList<User> userList =new ArrayList<>();

    public static UserDataInterfaceImplemented getInstance(){
        if(INSTANCE==null)
        {
            INSTANCE=new UserDataInterfaceImplemented();
        }
        return INSTANCE;
    }
    public MutableLiveData<ArrayList<User>> getUsers()
    {
        LoadUserData();
        MutableLiveData<ArrayList<User>> arrayListMutableLiveData=new MutableLiveData<>();
        arrayListMutableLiveData.setValue(userList);
        return arrayListMutableLiveData;
    }
    public void LoadUserData(){
        userList.clear();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("user");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    User obj=snapshot.getValue(User.class);
                    obj.setKey(snapshot.getKey());
                    userList.add(obj);
                    Log.d("Impl",obj.getKey());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
