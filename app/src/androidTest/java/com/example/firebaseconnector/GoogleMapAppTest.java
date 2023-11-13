package com.example.firebaseconnector;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasPackage;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;


import android.content.Context;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.firebaseconnector.UserApplicationLayer.LandingPage;
import com.example.firebaseconnector.UserApplicationLayer.Login;
import com.example.firebaseconnector.UserApplicationLayer.RouteCreator;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class GoogleMapAppTest {

    @Rule
    public ActivityScenarioRule<Login> activityRule = new ActivityScenarioRule<>(Login.class);
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.firebaseconnector", appContext.getPackageName());
    }
    @Before
    public void setUp() throws InterruptedException {
        // Perform login before testing the activity
        onView(ViewMatchers.withId(R.id.email)).perform(ViewActions.typeText("newUser@gmail.com"));
        onView(ViewMatchers.withId(R.id.password)).perform(ViewActions.typeText("abcdef"));
        Espresso.closeSoftKeyboard();

        onView(ViewMatchers.withId(R.id.loginButton)).perform(ViewActions.click());

        Thread.sleep(3000);
        onView(ViewMatchers.withId(R.id.planButton)).perform(ViewActions.click());
        Thread.sleep(1000);

        onView(ViewMatchers.withId(R.id.carRouteButton)).perform(ViewActions.click());
        Thread.sleep(1000);

    }


    @Test
    public void clickButtonOpensMapsApp() throws InterruptedException {
        // Find the email and password EditText fields and type in values.
        Intents.init();
        ViewInteraction openButton = onView(ViewMatchers.withId(R.id.openInApp));
        openButton.perform(ViewActions.click());
        //launch google maps

        Intents.intended(hasPackage("com.google.android.apps.maps"));
        Intents.release();



    }



}

