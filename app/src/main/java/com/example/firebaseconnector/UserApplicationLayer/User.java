package com.example.firebaseconnector.UserApplicationLayer;

import java.util.List;

public class User {
    public String email;
    public String password;
    // user's saved attractions list
    public List<Attraction> attractionList;

    public int numDays;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User( String email,String password, List<Attraction> attractionList,int numDays) {
        this.password = password;
        this.email = email;
        this.attractionList=attractionList;
        this.numDays = numDays;
    }
}

