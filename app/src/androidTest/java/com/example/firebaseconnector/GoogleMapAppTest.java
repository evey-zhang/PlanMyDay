//package com.example.firebaseconnector;
//
//import static androidx.test.espresso.Espresso.onView;
//import static androidx.test.espresso.assertion.ViewAssertions.matches;
//import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
//import static androidx.test.espresso.matcher.ViewMatchers.withText;
//import static org.junit.Assert.assertEquals;
//
//import android.content.Context;
//
//import androidx.test.core.app.ActivityScenario;
//import androidx.test.espresso.Espresso;
//import androidx.test.espresso.ViewInteraction;
//import androidx.test.espresso.action.ViewActions;
//import androidx.test.espresso.assertion.ViewAssertions;
//import androidx.test.espresso.matcher.ViewMatchers;
//import androidx.test.ext.junit.rules.ActivityScenarioRule;
//import androidx.test.ext.junit.runners.AndroidJUnit4;
//import androidx.test.platform.app.InstrumentationRegistry;
//
//import com.example.firebaseconnector.UserApplicationLayer.LandingPage;
//import com.example.firebaseconnector.UserApplicationLayer.Login;
//
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//@RunWith(AndroidJUnit4.class)
//public class GoogleMapAppTest {
//
//    @Rule
//    public ActivityScenarioRule<Register> activityRule = new ActivityScenarioRule<>(Register.class);
//    @Test
//    public void useAppContext() {
//        // Context of the app under test.
//        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
//        assertEquals("com.example.firebaseconnector", appContext.getPackageName());
//    }
//
//    @Test
//    public void ForgotPasswordDisplayed() throws InterruptedException {
//        // Find the email and password EditText fields and type in values.
//        ViewInteraction emailField = onView(ViewMatchers.withId(R.id.email));
//        ViewInteraction passwordField = onView(ViewMatchers.withId(R.id.password));
//
//        //make sure the field is cleared first
//        emailField.perform(ViewActions.typeText(""));
//        passwordField.perform(ViewActions.typeText(""));
//        emailField.perform(ViewActions.typeText("newUser@gmail.com"));
//        passwordField.perform(ViewActions.typeText("abcdef"));
//        Thread.sleep(2000);
//
//
//        // Close the soft keyboard (if it's open).
//        Espresso.closeSoftKeyboard();
//
//        // Find the login button and click it.
//        ViewInteraction loginButton = onView(ViewMatchers.withId(R.id.loginButton));
//        loginButton.perform(ViewActions.click());
//
//        //launch landing page
//        ActivityScenario<LandingPage> landingPageScenario = ActivityScenario.launch(LandingPage.class);
//        landingPageScenario.onActivity(activityB -> {
//            Espresso.onView(ViewMatchers.withId(R.id.planButton))
//                    .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
//
//        });
//
//
//
//
//
//    }
//
//}