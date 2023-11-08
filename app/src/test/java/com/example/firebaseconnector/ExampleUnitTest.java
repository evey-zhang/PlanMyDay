package com.example.firebaseconnector;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;



import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import androidx.annotation.NonNull;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;



import com.example.firebaseconnector.UserApplicationLayer.Login;
import com.example.firebaseconnector.UserApplicationLayer.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleUnitTest {


    @Rule
    public ActivityScenarioRule<Login> activityRule = new ActivityScenarioRule<>(Login.class);



//    @Test
//    public void testSignInSuccess() {
//        // Find the email and password EditText fields and type in values.
//        ViewInteraction emailField = Espresso.onView(ViewMatchers.withId(R.id.email));
//        ViewInteraction passwordField = Espresso.onView(ViewMatchers.withId(R.id.password));
//
//        emailField.perform(ViewActions.typeText("newUser@gmail.com"));
//        passwordField.perform(ViewActions.typeText("abcdef"));
//
//        // Close the soft keyboard (if it's open).
//        Espresso.closeSoftKeyboard();
//
//        // Find the login button and click it.
//        ViewInteraction loginButton = Espresso.onView(ViewMatchers.withId(R.id.loginButton));
//        loginButton.perform(ViewActions.click());
//
//        // Validate that the login was successful (e.g., check for a success message).
//        ViewInteraction successMessage = Espresso.onView(ViewMatchers.withText("Login successful."));
//        successMessage.check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
//
//
//    }

    @Test
    public void testSignInFailure() {

    }

    @Test
    public void testSignUpSuccess() {

    }

    @Test
    public void testSignUpFailure() {

    }

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }


    //TEST: CHECK IF SAVING ATTRACTION ADDS IT TO THE SAVED ATTRACTION LIST
    @Test
    public void saveAttractionTest(){
//        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);
//        DatabaseReference attractionListRef = db.child("attractionList");

    }


}