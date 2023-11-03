package com.example.firebaseconnector.UserApplicationLayer;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Collections;

public class TripPlanner {
    private User user;

    public TripPlanner(User u) {
        user = u;
    }

    public ArrayList<ArrayList<Attraction>> generateTrip() {
        ArrayList<ArrayList<Attraction>> trip = new ArrayList<>();
        for (int i = 0; i < user.numDays; i++) {
            ArrayList<Attraction> day = new ArrayList<>();
            for (int j = i; j < user.attractionList.size(); j += user.numDays) {
                day.add(user.attractionList.get(j));
            }
            sortByCloseTime(day);
            trip.add(day);
        }
        return trip;
    }

    private void sortByCloseTime(ArrayList<Attraction> day) {
        // bubble sort
        for(int i=0; i<day.size()-1; i++) {
            for(int j=0; j<day.size()-i-1; j++) {
                int current = Integer.parseInt(day.get(j).getCloseTime().replace(":",""));
                int next = Integer.parseInt(day.get(j+1).getCloseTime().replace(":",""));
                if(next < current) {
                    Collections.swap(day, j, j+1);
                }
            }
        }
    }
}
