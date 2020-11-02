package com.example.firebasemvvm.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.firebasemvvm.models.User;
import com.example.firebasemvvm.repositories.UserDataInterfaceImplemented;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class UserViewModel extends ViewModel {
    public MutableLiveData<ArrayList<User>> liveData;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference myRef = database.getReference("user");
    public void init(Context context){
        if(liveData!=null)
        {
            return;
        }
        liveData= UserDataInterfaceImplemented.getInstance().getUsers();
    }
    public LiveData<ArrayList<User>> getUserData()
    {
        return liveData;
    }

    public void Add_User(User obj){
        String key = myRef.push().getKey();
        myRef.child(key).setValue(obj);
    }


}
