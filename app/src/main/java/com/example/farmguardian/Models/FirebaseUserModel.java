package com.example.farmguardian.Models;

public class FirebaseUserModel {

    private String username;
    private String email;
    private String password;

    // Empty constructor for communication with Firebase
    public FirebaseUserModel() {
    }

    public FirebaseUserModel(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
