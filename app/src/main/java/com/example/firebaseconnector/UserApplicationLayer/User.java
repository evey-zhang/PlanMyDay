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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getNumDays() {
        return numDays;
    }

    public void setNumDays(int numDays) {
        this.numDays = numDays;
    }
}

