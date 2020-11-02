package com.example.firebasemvvm.repositories;

import com.example.firebasemvvm.models.User;

import java.util.List;

public interface UserDataInterface {
    public List<User> getUserData();
    public void AddUser(User obj);
}
