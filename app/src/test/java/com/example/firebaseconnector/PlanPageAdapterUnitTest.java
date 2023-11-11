package com.example.firebaseconnector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.firebaseconnector.UserApplicationLayer.Attraction;
import com.example.firebaseconnector.UserApplicationLayer.PageFragment;
import com.example.firebaseconnector.UserApplicationLayer.PagerAdapter;
import com.example.firebaseconnector.UserApplicationLayer.PlanPageAdapter;

import static org.mockito.Mockito.*;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

public class PlanPageAdapterUnitTest {

	@Test
	public void TestGetItemCount() {
		// Setup
		ArrayList<Attraction> testData = new ArrayList<>();
		testData.add(mock(Attraction.class));
		testData.add(mock(Attraction.class));
		PlanPageAdapter adapter = new PlanPageAdapter(testData);

		// Verify
		assertEquals(2, adapter.getItemCount());
	}

	@Test
	public void TestGetItemCount_WithNullItem() {
		// Setup
		ArrayList<Attraction> testData = new ArrayList<>();
		testData.add(new Attraction("Attraction 1", "Address 1", "08:00", "18:00"));
		testData.add(null); // Add a null item to the list.
		testData.add(new Attraction("Attraction 3", "Address 3", "09:00", "19:00"));
		PlanPageAdapter adapter = new PlanPageAdapter(testData);

		// Verify
		assertEquals("getItemCount should count null items as valid list entries", 3, adapter.getItemCount());
	}


	@Test
	public void TestOnBindViewHolder() {
		// Setup
		Attraction testAttraction = new Attraction("Test Attraction", "Test Address", "09:00", "17:00");
		ArrayList<Attraction> testData = new ArrayList<>();
		testData.add(testAttraction);
		PlanPageAdapter adapter = new PlanPageAdapter(testData);

		PlanPageAdapter.ViewHolder holder = mock(PlanPageAdapter.ViewHolder.class);
		holder.name = mock(TextView.class);
		holder.address = mock(TextView.class);
		holder.operatingTime = mock(TextView.class);

		// When
		adapter.onBindViewHolder(holder, 0);

		// Then
		verify(holder.name).setText("Test Attraction");
		verify(holder.address).setText("Test Address");
		verify(holder.operatingTime).setText("09:00 - 17:00");
	}


}
