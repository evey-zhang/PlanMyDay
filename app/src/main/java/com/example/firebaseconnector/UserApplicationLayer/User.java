package com.example.firebaseconnector.UserApplicationLayer;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class User {
    public String email;
    public String password;
    public int numDays;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String email, String password, int numDays) {
        this.password = password;
        this.email = email;
        this.numDays = numDays;
    }
}

