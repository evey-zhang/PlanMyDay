package com.example.firebaseconnector;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.example.firebaseconnector.UserApplicationLayer.Attraction;
import com.example.firebaseconnector.UserApplicationLayer.TripPlanner;

public class TripPlanAlgorithmTest {
    ArrayList<Attraction> savedAttractions;
    ArrayList<ArrayList<Attraction>> trip;
    ArrayList<ArrayList<Attraction>> expectedTripPlan;

    @Before
    public void initialize() {
        savedAttractions = new ArrayList<>();
    }

    @Test
    public void allAttractionsDistributedTest() {
        Attraction a = new Attraction("Attraction 1", "324 Knight Way", "0:00", "24:00");
        Attraction b = new Attraction("Attraction 2", "1164 W 37th Pl", "0:00", "24:00");
        Attraction c = new Attraction("Attraction 3", "1353 W 35th Pl", "0:00", "24:00");

        // generate test plan
        savedAttractions.add(a);
        savedAttractions.add(b);
        savedAttractions.add(c);
        TripPlanner t = new TripPlanner(savedAttractions, 3);
        trip = t.generateTrip();

        assertEquals("attraction one: ", a, trip.get(0).get(0));
        assertEquals("attraction two: ", b, trip.get(1).get(0));
        assertEquals("attraction three: ", c, trip.get(2).get(0));
    }
    @Test
    public void orderByCloseTimeTest() {
        Attraction a = new Attraction("Attraction 1", "324 Knight Way", "0:00", "24:00");
        Attraction b = new Attraction("Attraction 2", "1164 W 37th Pl", "5:00", "16:00");
        Attraction c = new Attraction("Attraction 3", "1353 W 35th Pl", "12:00", "14:00");
        Attraction d = new Attraction("Attraction 4", "3584 S Figueroa St", "9:00", "18:00");

        // expected plan
        expectedTripPlan = new ArrayList<>();
        ArrayList<Attraction> dayOne = new ArrayList<>();
        dayOne.add(c);
        dayOne.add(b);
        dayOne.add(d);
        dayOne.add(a);
        expectedTripPlan.add(dayOne);

        // generate test plan
        savedAttractions.add(a);
        savedAttractions.add(b);
        savedAttractions.add(c);
        savedAttractions.add(d);
        TripPlanner t = new TripPlanner(savedAttractions, 1);
        trip = t.generateTrip();

        // compare
        assertEquals(expectedTripPlan, trip);
    }

    @Test
    public void evenDayDistributionTest() {
        Attraction a = new Attraction("Attraction 1", "324 Knight Way", "0:00", "24:00");
        Attraction b = new Attraction("Attraction 2", "1164 W 37th Pl", "5:00", "16:00");
        Attraction c = new Attraction("Attraction 3", "1353 W 35th Pl", "12:00", "14:00");
        Attraction d = new Attraction("Attraction 4", "3584 S Figueroa St", "9:00", "18:00");

        // generate test plan
        savedAttractions.add(a);
        savedAttractions.add(b);
        savedAttractions.add(c);
        savedAttractions.add(d);
        TripPlanner t = new TripPlanner(savedAttractions, 2);
        trip = t.generateTrip();

        // test
        assertEquals("number of days in trip", 2, trip.size());
        assertEquals("number of attractions in day one", 2, trip.get(0).size());
        assertEquals("number of attractions in day two", 2, trip.get(1).size());
    }

    @Test
    public void oddDayDistributionTest() {
        Attraction a = new Attraction("Attraction 1", "324 Knight Way", "0:00", "24:00");
        Attraction b = new Attraction("Attraction 2", "1164 W 37th Pl", "5:00", "16:00");
        Attraction c = new Attraction("Attraction 3", "1353 W 35th Pl", "12:00", "14:00");
        Attraction d = new Attraction("Attraction 4", "3584 S Figueroa St", "9:00", "18:00");
        Attraction e = new Attraction("Attraction 5", "2122 Camino Dr", "11:00", "17:00");

        // generate test plan
        savedAttractions.add(a);
        savedAttractions.add(b);
        savedAttractions.add(c);
        savedAttractions.add(d);
        savedAttractions.add(e);
        TripPlanner t = new TripPlanner(savedAttractions, 3);
        trip = t.generateTrip();

        // test
        assertEquals("number of days in trip", 3, trip.size());
        assertEquals("number of attractions in day one", 2, trip.get(0).size());
        assertEquals("number of attractions in day two", 2, trip.get(1).size());
        assertEquals("number of attractions in day three", 1, trip.get(2).size());
    }

    @Test
    public void moreDaysThanAttractionsTest() {
        Attraction a = new Attraction("Attraction 1", "324 Knight Way", "0:00", "24:00");
        Attraction b = new Attraction("Attraction 2", "1164 W 37th Pl", "5:00", "16:00");
        Attraction c = new Attraction("Attraction 3", "1353 W 35th Pl", "12:00", "14:00");
        Attraction d = new Attraction("Attraction 4", "3584 S Figueroa St", "9:00", "18:00");
        savedAttractions.add(a);
        savedAttractions.add(b);
        savedAttractions.add(c);
        savedAttractions.add(d);
        TripPlanner t = new TripPlanner(savedAttractions, 5);
        trip = t.generateTrip();
        assertEquals("days expected in trip: ", 4, trip.size());
    }
}
