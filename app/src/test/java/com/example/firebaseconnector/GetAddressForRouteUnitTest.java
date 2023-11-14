package com.example.firebaseconnector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.firebaseconnector.UserApplicationLayer.Attraction;
import com.example.firebaseconnector.UserApplicationLayer.PageFragment;
import com.example.firebaseconnector.UserApplicationLayer.PagerAdapter;
import com.example.firebaseconnector.UserApplicationLayer.PlanPageAdapter;
import com.example.firebaseconnector.UserApplicationLayer.RouteCreator;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;


import static org.mockito.Mockito.*;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.ArrayList;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = "src/main/AndroidManifest.xml")
public class GetAddressForRouteUnitTest {
    @Mock
    private Context mockContext;
    @Mock
    private FirebaseAuth mockFirebaseAuth;

    @Mock
    private FirebaseUser mockFirebaseUser;

    @Mock
    private FirebaseDatabase mockFirebaseDatabase;

    @Mock
    private DatabaseReference mockDatabaseReference;

    @Mock
    private RouteCreator.DataCallback mockDataCallback;

    private RouteCreator routeCreator;

    @Before
    public void setUp() {
        FirebaseApp.initializeApp(RuntimeEnvironment.getApplication());

        MockitoAnnotations.initMocks(this);
        mockFirebaseUser = mock(FirebaseUser.class);
        mockFirebaseDatabase = mock(FirebaseDatabase.class);
        mockDatabaseReference = mock(DatabaseReference.class);

        when(mockFirebaseAuth.getCurrentUser()).thenReturn(mockFirebaseUser);
        when(mockFirebaseDatabase.getReference(any())).thenReturn(mockDatabaseReference);
        when(mockDatabaseReference.child(any())).thenReturn(mockDatabaseReference);

        routeCreator = new RouteCreator();
    }


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
    public void testGetAddressList_WithValidData() {
        // Arrange
        ArrayList<String> fakeAddresses = new ArrayList<>();
        fakeAddresses.add("Address1");
        fakeAddresses.add("Address2");
        fakeAddresses.add("Address3");


        // Act
        routeCreator.getAddressList(mockDataCallback, true, 1);
        // Assert
        verify(mockDataCallback).onDataReceived(fakeAddresses);
    }
}
