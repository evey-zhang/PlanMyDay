package com.example.firebaseconnector;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.firebaseconnector.UserApplicationLayer.Attraction;
import com.example.firebaseconnector.UserApplicationLayer.MyAdapter;

import java.util.ArrayList;

public class SavedAttractionListAdapterUnitTest {

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
    private Button mockRemoveButton;

    private MyAdapter.MyViewHolder viewHolder;
    private MyAdapter adapter;
    private ArrayList<Attraction> attractionList;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        attractionList = new ArrayList<>();
        adapter = new MyAdapter(mockContext, attractionList);
    }


    // Check if viewHolder binds data correctly
    @Test
    public void testOnBindViewHolder() {
        when(mockView.findViewById(R.id.textname)).thenReturn(mockNameTextView);
        when(mockView.findViewById(R.id.cell_time)).thenReturn(mockOperatingTimeTextView);
        when(mockView.findViewById(R.id.addressText)).thenReturn(mockAddressTextView);
        when(mockView.findViewById(R.id.removeButton)).thenReturn(mockRemoveButton);

        viewHolder = new MyAdapter.MyViewHolder(mockView);
        Attraction testAttraction = new Attraction("Test Name", "Test Address", "5 PM", "9 PM");
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
