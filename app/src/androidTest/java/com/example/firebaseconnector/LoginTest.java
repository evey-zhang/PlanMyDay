package com.example.firebaseconnector;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static org.junit.Assert.assertEquals;


import android.content.Context;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.firebaseconnector.UserApplicationLayer.Login;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginTest {
    @Rule
    public ActivityScenarioRule<Login> activityRule = new ActivityScenarioRule<>(Login.class);
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.firebaseconnector", appContext.getPackageName());
    }

    @Test
    public void ForgotPasswordDisplayed() {
        // Find the email and password EditText fields and type in values.
        onView(withText("Forgot Password?")).check(matches(isDisplayed()));

    }
    @Test
    public void testSignFailAuthentication() {
        // Find the email and password EditText fields and type in values.
        ViewInteraction emailField = onView(ViewMatchers.withId(R.id.email));
        ViewInteraction passwordField = onView(ViewMatchers.withId(R.id.password));


        emailField.perform(ViewActions.typeText("wrongUser@gmail.com"));
        passwordField.perform(ViewActions.typeText("wrongUser"));

        // Close the soft keyboard (if it's open).
        Espresso.closeSoftKeyboard();

        // Find the login button and click it.
        ViewInteraction loginButton = onView(ViewMatchers.withId(R.id.loginButton));
        loginButton.perform(ViewActions.click());

        // Validate that the login was successful (e.g., check for a success message).
        String expectedToastText = "Authentication failed.";
        Espresso.onView(ViewMatchers.withText(expectedToastText)).inRoot(ToastMatcher.isToast()).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

    }

    @Test
    public void missingEmailField() {
        // Find the email and password EditText fields and type in values.
        ViewInteraction emailField = onView(ViewMatchers.withId(R.id.email));
        ViewInteraction passwordField = onView(ViewMatchers.withId(R.id.password));


        emailField.perform(ViewActions.typeText(""));
        passwordField.perform(ViewActions.typeText("doesntwork"));

        // Close the soft keyboard (if it's open).
        Espresso.closeSoftKeyboard();

        // Find the login button and click it.
        ViewInteraction loginButton = onView(ViewMatchers.withId(R.id.loginButton));
        loginButton.perform(ViewActions.click());

        // Validate that the login was successful (e.g., check for a success message).
        String expectedToastText = "Email is missing.";
        Espresso.onView(ViewMatchers.withText(expectedToastText)).inRoot(ToastMatcher.isToast()).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

    }
    @Test
    public void missingPasswordField() {
        // Find the email and password EditText fields and type in values.
        ViewInteraction emailField = onView(ViewMatchers.withId(R.id.email));
        ViewInteraction passwordField = onView(ViewMatchers.withId(R.id.password));


        emailField.perform(ViewActions.typeText("hello@gmail.com"));
        passwordField.perform(ViewActions.typeText(""));

        // Close the soft keyboard (if it's open).
        Espresso.closeSoftKeyboard();

        // Find the login button and click it.
        ViewInteraction loginButton = onView(ViewMatchers.withId(R.id.loginButton));
        loginButton.perform(ViewActions.click());

        // Validate that the login was successful (e.g., check for a success message).
        String expectedToastText = "Password is missing.";
        Espresso.onView(ViewMatchers.withText(expectedToastText)).inRoot(ToastMatcher.isToast()).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

    }
    @Test
    public void testSignInSuccess() throws InterruptedException {
        // Find the email and password EditText fields and type in values.
        Thread.sleep(1000);
        ViewInteraction emailField = onView(ViewMatchers.withId(R.id.email));
        ViewInteraction passwordField = onView(ViewMatchers.withId(R.id.password));

        //make sure the field is cleared first
        emailField.perform(ViewActions.typeText(""));
        passwordField.perform(ViewActions.typeText(""));
        emailField.perform(ViewActions.typeText("newUser@gmail.com"));
        passwordField.perform(ViewActions.typeText("abcdef"));
        Thread.sleep(2000);


        // Close the soft keyboard (if it's open).
        Espresso.closeSoftKeyboard();

        // Find the login button and click it.
        ViewInteraction loginButton = onView(ViewMatchers.withId(R.id.loginButton));
        loginButton.perform(ViewActions.click());

        // Validate that the login was successful (e.g., check for a success message).
        String expectedToastText = "Login successful.";
        Thread.sleep(1000);
        Espresso.onView(ViewMatchers.withText(expectedToastText)).inRoot(ToastMatcher.isToast()).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

    }


}