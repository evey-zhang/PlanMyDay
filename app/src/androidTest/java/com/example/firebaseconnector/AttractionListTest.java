package com.example.firebaseconnector;

import androidx.collection.CircularArray;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;

import com.example.firebaseconnector.UserApplicationLayer.Attraction;
import com.example.firebaseconnector.UserApplicationLayer.AttractionDetails;
import com.example.firebaseconnector.UserApplicationLayer.AttractionList;
import com.example.firebaseconnector.UserApplicationLayer.AttractionListAdapter;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
@RunWith(AndroidJUnit4.class)
public class AttractionListTest {

	ArrayList<Attraction> mockAttractions;

	@Rule
	public ActivityScenarioRule<AttractionList> activityScenarioRule
			= new ActivityScenarioRule<>(AttractionList.class);

	@Before
	public void setup() {
		mockAttractions = createMockAttractions();

		// Launch the activity and update the RecyclerView's adapter with the mock list
		activityScenarioRule.getScenario().onActivity(activity -> {
			RecyclerView recyclerView = activity.findViewById(R.id.recycler_view);
			AttractionListAdapter adapter = (AttractionListAdapter) recyclerView.getAdapter();

			if (adapter != null) {
				adapter.setAttractionList(mockAttractions);
				adapter.notifyDataSetChanged();
			}
		});

		Intents.init();
	}


	@After
	public void tearDown() {
		Intents.release();
	}

	private ArrayList<Attraction> createMockAttractions() {
		ArrayList<Attraction> attractions = new ArrayList<>();
		attractions.add(new Attraction("Attraction 1", "Address 1", "09:00", "18:00"));
		attractions.add(new Attraction("Attraction 2", "Address 2", "10:00", "19:00"));
		attractions.add(new Attraction("Attraction 3", "Address 3", "11:00", "20:00"));

		return attractions;
	}

	@Test
	public void testRecyclerViewVisibility() {
		onView(withId(R.id.recycler_view))
				.check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
	}

	@Test
	public void testRecyclerViewItemContent() {
		onView(withId(R.id.recycler_view))
				.perform(RecyclerViewActions.scrollToPosition(0))
				.check(matches(hasDescendant(withText("Attraction 1"))));

		onView(withId(R.id.recycler_view))
				.perform(RecyclerViewActions.scrollToPosition(1))
				.check(matches(hasDescendant(withText("Attraction 2"))));

		onView(withId(R.id.recycler_view))
				.perform(RecyclerViewActions.scrollToPosition(2))
				.check(matches(hasDescendant(withText("Attraction 3"))));

	}


	 //Test if clicking on an item opens the AttractionDetails activity
	@Test
	public void testNavigationToAttractionDetails() {

		onView(withId(R.id.recycler_view))
				.perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

		// Check if the intent to start AttractionDetails activity is sent
		intended(allOf(
				hasComponent(AttractionDetails.class.getName()),
				hasExtra("DETAIL_DATA", mockAttractions.get(0))
		));
	}
}
