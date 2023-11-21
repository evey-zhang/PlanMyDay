package com.example.firebaseconnector;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.firebaseconnector.UserApplicationLayer.AttractionList;
import com.example.firebaseconnector.UserApplicationLayer.LandingPage;
import com.example.firebaseconnector.UserApplicationLayer.Login;
import com.example.firebaseconnector.UserApplicationLayer.PlanPage;
import com.example.firebaseconnector.UserApplicationLayer.SavedAttractionList;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class LandingPageTest {
    @Rule
    public ActivityScenarioRule<LandingPage> activityScenarioRule =
            new ActivityScenarioRule<>(LandingPage.class);
    @Before
    public void setup() {
        Intents.init();
    }
    @After
    public void tearDown() {
        Intents.release();
    }
    @Test
    public void navigationToAttractionListTest() {
        onView(withId(R.id.attractionListButton)).perform(click());
        intended(hasComponent(AttractionList.class.getName()));
    }

    @Test
    public void navigationToSavedAttractionListTest() {
        onView(withId(R.id.SavedAttractionButton)).perform(click());
        intended(hasComponent(SavedAttractionList.class.getName()));
    }

    @Test
    public void navigationToPlanPageTest() {
        onView(withId(R.id.planButton)).perform(click());
        intended(hasComponent(PlanPage.class.getName()));
    }

    @Test
    public void logoutNavigationToLoginPageTest() {
        onView(withId(R.id.logout)).perform(click());
        intended(hasComponent(Login.class.getName()));
    }

    @Test
    public void checkDisplayTest() {
        onView(withId(R.id.applogo)).check(matches(isDisplayed()));
        onView(withId(R.id.landingText)).check(matches(withText("Plan My Day")));
        onView(withId(R.id.viewAttractionsText)).check(matches(withText("View Attractions")));
        onView(withId(R.id.attraction)).check(matches(isDisplayed()));
        onView(withId(R.id.savedAttractionsText)).check(matches(withText("Saved Attractions")));
        onView(withId(R.id.savedAttraction)).check(matches(isDisplayed()));
    }
}
