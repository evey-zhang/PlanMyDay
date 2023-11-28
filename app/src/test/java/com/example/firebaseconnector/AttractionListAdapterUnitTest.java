package com.example.firebaseconnector;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.example.firebaseconnector.UserApplicationLayer.Attraction;
import com.example.firebaseconnector.UserApplicationLayer.USCAttractionListAdapter;

import java.util.ArrayList;

public class AttractionListAdapterUnitTest {

	@Mock
	private Context mockContext;
	@Mock
	private View mockView;
	@Mock
	private TextView mockNameTextView;
	@Mock
	private TextView mockOperatingTimeTextView;
	@Mock
	private TextView mockAddressTextView;
	@Mock
	private CardView mockCellCardView;

	private USCAttractionListAdapter.USCAttractionViewHolder viewHolder;
	private USCAttractionListAdapter adapter;
	private ArrayList<Attraction> attractionList;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		attractionList = new ArrayList<>();
		adapter = new USCAttractionListAdapter(mockContext, attractionList);
	}


	// Check if viewHolder binds data correctly
	@Test
	public void testOnBindViewHolder() {
		when(mockView.findViewById(R.id.cell_name)).thenReturn(mockNameTextView);
		when(mockView.findViewById(R.id.cell_time)).thenReturn(mockOperatingTimeTextView);
		when(mockView.findViewById(R.id.cell_address)).thenReturn(mockAddressTextView);
		when(mockView.findViewById(R.id.cell_container)).thenReturn(mockCellCardView);

		viewHolder = new USCAttractionListAdapter.USCAttractionViewHolder(mockView);
		Attraction testAttraction = new Attraction("Test Name", "9 AM", "5 PM", "Test Address");
		attractionList.add(testAttraction);

		// When onBindViewHolder is called
		adapter.onBindViewHolder(viewHolder, 0);

		// Then verify that setText is called on the mock TextViews with the correct data
		verify(mockNameTextView).setText(testAttraction.getName());
		verify(mockOperatingTimeTextView).setText(testAttraction.getOpenTime() + " - " + testAttraction.getCloseTime());
		verify(mockAddressTextView).setText(testAttraction.getAddress());
	}


	// Test to return correct size
	@Test
	public void testGetItemCount() {
		attractionList.add(new Attraction("Attraction 1", "9 AM", "5 PM", "Address 1"));
		attractionList.add(new Attraction("Attraction 2", "10 AM", "6 PM", "Address 2"));
		attractionList.add(new Attraction("Attraction 3", "11 AM", "7 PM", "Address 3"));

		int itemCount = adapter.getItemCount();
		assertEquals("The item count should be equal to the number of attractions in the list", 3, itemCount);
	}

}
