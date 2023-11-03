package com.example.firebaseconnector.UserApplicationLayer;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class User {
    public String email;
    public String password;
    // user's saved attractions list
    public ArrayList<Attraction> attractionList;

	public ArrayList<ArrayList<Attraction>> tripPlan;

    public int numDays;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String email, String password, ArrayList<Attraction> attractionList, ArrayList<ArrayList<Attraction>> tripPlan, int numDays) {
        this.password = password;
        this.email = email;
        this.attractionList=attractionList;
        this.numDays = numDays;
		this.tripPlan = tripPlan;
    }
}

