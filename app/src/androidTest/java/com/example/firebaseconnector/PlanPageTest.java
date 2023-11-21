package com.example.firebaseconnector;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.espresso.matcher.ViewMatchers;

import com.example.firebaseconnector.UserApplicationLayer.PlanPage;
import com.example.firebaseconnector.UserApplicationLayer.RouteCreator;

import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.view.View;

import java.util.ArrayList;

@RunWith(AndroidJUnit4.class)
public class PlanPageTest {

	@Rule
	public ActivityScenarioRule<PlanPage> activityRule = new ActivityScenarioRule<>(PlanPage.class);

	@Before
	public void setUp() {
		Intents.init();
	}

	@After
	public void tearDown() {
		Intents.release();
	}


	@Test
	public void testDayTitle_isDisplayed() {
		// Assuming the dayTitle TextView should display "Day 1" initially
		onView(withId(R.id.day)).check(matches(withText("Day 1")));

	}

	@Test
	public void testClickingCarRouteButton_startsRouteCreatorWithDriveMode() {
		// Check that the button is displayed
		onView(withId(R.id.carRouteButton)).check(matches(isDisplayed()));

		// Now perform the click action
		onView(withId(R.id.carRouteButton)).perform(clickOnButton());

		// Check if the intent has the correct extras
		intended(allOf(
				hasComponent(RouteCreator.class.getName()),
				hasExtra("IS_DRIVE_MODE", true)
		));

	}

	@Test
	public void testClickingPublicTransportButton_startsRouteCreatorWithPublicMode() {
		// Check that the button is displayed
		onView(withId(R.id.publicTransportbutton)).check(matches(isDisplayed()));

		// Click on the public transport button
		onView(withId(R.id.publicTransportbutton)).perform(clickOnButton());

		// Check if the intent has the correct extras
		intended(allOf(
				hasComponent(RouteCreator.class.getName()),
				hasExtra("IS_DRIVE_MODE", false)
		));
	}

	public static ViewAction clickOnButton() {
		return new ViewAction() {
			@Override
			public Matcher<View> getConstraints() {
				return ViewMatchers.isEnabled(); // only if the button is enabled
			}

			@Override
			public String getDescription() {
				return "Click on button";
			}

			@Override
			public void perform(UiController uiController, View view) {
				view.performClick(); // perform click without using Espresso touch interceptors
			}
		};
	}

}
