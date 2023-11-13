package com.example.firebaseconnector;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import android.widget.Toast;

import com.example.firebaseconnector.UserApplicationLayer.Attraction;
import com.example.firebaseconnector.UserApplicationLayer.AttractionDetails;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;

@RunWith(MockitoJUnitRunner.class)
public class AttractionDetailsUnitTest {

    @Mock
    private DatabaseReference mockDatabaseReference;

    @Mock
    private DataSnapshot mockDataSnapshot;

    @Mock
    private Toast mockToast;

    private AttractionDetails attractionDetails;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        attractionDetails = new AttractionDetails("9vkbtPVcpGTJcNFtVjFWuKuYjAS2", mockDatabaseReference);
    }

    @Test
    public void testSaveAttractionToDB_AttractionListDoesNotExist() {
        // Arrange
        Attraction targetAttraction = new Attraction("Attraction1", "Address1", "9 AM", "5 PM");

        when(mockDataSnapshot.exists()).thenReturn(false);
        when(mockDatabaseReference.child("attractionList")).thenReturn(mockDatabaseReference);
        when(mockDatabaseReference.push()).thenReturn(mockDatabaseReference);

        attractionDetails.saveAttractionToDB(targetAttraction);


        verify(mockDatabaseReference).setValue(targetAttraction);
        verify(mockToast).show(); // You might want to verify that the success toast is shown
    }

    @Test
    public void testSaveAttractionToDB_AttractionListExists_AttractionExists() {
        // Arrange
        Attraction targetAttraction = new Attraction("Attraction1", "Address1", "9 AM", "5 PM");

        when(mockDataSnapshot.exists()).thenReturn(true);
        when(mockDataSnapshot.getChildren()).thenReturn((Iterable<DataSnapshot>) Collections.singletonList(mockDataSnapshot).iterator());
        when(mockDataSnapshot.getValue(Attraction.class)).thenReturn(targetAttraction);
        when(mockDatabaseReference.child("attractionList")).thenReturn(mockDatabaseReference);

        // Act
        attractionDetails.saveAttractionToDB(targetAttraction);

        // Assert
        verify(mockDatabaseReference).addListenerForSingleValueEvent(any(ValueEventListener.class));
        // You might want to verify other expected interactions
    }

}