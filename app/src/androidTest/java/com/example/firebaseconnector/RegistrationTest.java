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
import com.example.firebaseconnector.UserApplicationLayer.Register;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class RegistrationTest {
    @Rule
    public ActivityScenarioRule<Register> activityRule = new ActivityScenarioRule<>(Register.class);
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.firebaseconnector", appContext.getPackageName());
    }

    @Test
    public void ClickToLoginDisplayed() {
        // Find the email and password EditText fields and type in values.
        onView(withText("Click to Login")).check(matches(isDisplayed()));

    }
    @Test
    public void alreadyInUseAccount() throws InterruptedException {
        // Find the email and password EditText fields and type in values.
        ViewInteraction emailField = onView(ViewMatchers.withId(R.id.email));
        ViewInteraction passwordField = onView(ViewMatchers.withId(R.id.password));


        emailField.perform(ViewActions.typeText("hello@gmail.com"));
        passwordField.perform(ViewActions.typeText("abcdef"));

        // Close the soft keyboard (if it's open).
        Espresso.closeSoftKeyboard();

        // Find the login button and click it.
        ViewInteraction loginButton = onView(ViewMatchers.withId(R.id.registerButton));
        loginButton.perform(ViewActions.click());

        // Validate that the login was successful (e.g., check for a success message).
        String expectedToastText = "Authentication failed.The email address is already in use by another account.";
        Espresso.onView(ViewMatchers.withText(expectedToastText)).inRoot(ToastMatcher.isToast()).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

    }


    @Test
    public void missingPasswordField() {
        // Find the email and password EditText fields and type in values.
        ViewInteraction emailField = onView(ViewMatchers.withId(R.id.email));
        ViewInteraction passwordField = onView(ViewMatchers.withId(R.id.password));


        emailField.perform(ViewActions.typeText("something@gmail.com"));
        passwordField.perform(ViewActions.typeText(""));

        // Close the soft keyboard (if it's open).
        Espresso.closeSoftKeyboard();

        // Find the login button and click it.
        ViewInteraction loginButton = onView(ViewMatchers.withId(R.id.registerButton));
        loginButton.perform(ViewActions.click());

        // Validate that the login was successful (e.g., check for a success message).
        String expectedToastText = "Password is missing.";
        Espresso.onView(ViewMatchers.withText(expectedToastText)).inRoot(ToastMatcher.isToast()).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

    }
    @Test
    public void RegisterSuccess() throws InterruptedException {
        // Find the email and password EditText fields and type in values.
        Thread.sleep(1000);
        ViewInteraction emailField = onView(ViewMatchers.withId(R.id.email));
        ViewInteraction passwordField = onView(ViewMatchers.withId(R.id.password));

        //make sure the field is cleared first
        emailField.perform(ViewActions.typeText(""));
        passwordField.perform(ViewActions.typeText(""));
        //--------------USER INPUT MUST CHANGE HERE --> ADD 1 TO NEWUSER TO CREATE NEW ACCOUNT --------------
        emailField.perform(ViewActions.typeText("newUser5@gmail.com"));
        passwordField.perform(ViewActions.typeText("abcdef"));
        Thread.sleep(2000);


        // Close the soft keyboard (if it's open).
        Espresso.closeSoftKeyboard();

        // Find the login button and click it.
        ViewInteraction loginButton = onView(ViewMatchers.withId(R.id.registerButton));
        loginButton.perform(ViewActions.click());

        // Validate that the login was successful (e.g., check for a success message).
        String expectedToastText = "Successfully added new user to database.";
        Thread.sleep(2000);
        Espresso.onView(ViewMatchers.withText(expectedToastText)).inRoot(ToastMatcher.isToast()).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

    }


}