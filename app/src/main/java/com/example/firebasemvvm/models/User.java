package com.example.firebasemvvm.models;

public class User {
    private String key;
    private String name;
    private String occupation;

    public User() {}

    public User(String key, String name, String occupation) {
        this.key = key;
        this.name = name;
        this.occupation = occupation;
    }

    public User(String name, String occupation) {
        this.name = name;
        this.occupation = occupation;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }
}
