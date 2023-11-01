package com.example.firebaseconnector.UserApplicationLayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.firebaseconnector.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.DirectionsStep;
import com.google.maps.model.TravelMode;


import java.util.ArrayList;
import java.util.List;

public class RouteCreator extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_creator);

        //setup map view
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.maps);

        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

    }
    @Override // triggered to add contents to the map
    public void onMapReady(@NonNull GoogleMap googleMap) {
       final List<String> addressesToVisit = new ArrayList<>();

        getAddressList(new DataCallback() {
            @Override
            public void onDataReceived(ArrayList<String> data) {
                addressesToVisit.clear();
                addressesToVisit.addAll(data);
                mMap = googleMap;
            }
        });

    }
    public void getAddressList(final DataCallback callback) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        //database configuration
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(currentUser.getUid()).child("attractionList");
        ArrayList<String> addressesToVisit = new ArrayList<>();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                long numberOfChildren = snapshot.getChildrenCount();
                System.out.println("Number of objects in the databaseReference: " + numberOfChildren);
                addressesToVisit.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    Attraction attraction = dataSnapshot.getValue(Attraction.class);

                    String addressStr = attraction.getAddress();
                    addressesToVisit.add(addressStr);
                    System.out.println("this is the address" + addressesToVisit);

                }
                // Pass the data back using the callback
                callback.onDataReceived(addressesToVisit);

                //MAP SETUP SEQUENCE
                // Set up the GeoApiContext with your API key
                GeoApiContext context = new GeoApiContext.Builder()
                        .apiKey("AIzaSyAbn_NBmNZ-SNkxstD7Amh2v6x1NXdgnHs")
                        .build();

                // Use the Google Maps Directions API to fetch route information
                String origin ="Hollywood Blvd, Los Angeles, CA 90028, USA";
                String destination = "2800 E Observatory Rd, Los Angeles, CA 90027, USA";
                List<DirectionsApiRequest.Waypoint> waypointArr = new ArrayList<>();
                String waypointsStr = "";


                for (String attraction : addressesToVisit){
                    DirectionsApiRequest.Waypoint waypt = new DirectionsApiRequest.Waypoint(attraction);
                    waypointArr.add(waypt);
                }


                try {
                    DirectionsResult result = DirectionsApi.newRequest(context)
                            .mode(TravelMode.DRIVING)
                            .origin(origin)
                            .destination(destination)
                            .waypoints(waypointArr.toArray(new DirectionsApiRequest.Waypoint[0]))
                            .await();



                    //-------------ADD MARKERS____________________
                    // Extract and display the route on the map
                    if (result.routes.length > 0) {
                        DirectionsRoute route = result.routes[0];
                        PolylineOptions polylineOptions = new PolylineOptions();
                        for (DirectionsLeg leg : route.legs){
                            mMap.addMarker(new MarkerOptions()
                                    .position(new LatLng(leg.startLocation.lat, leg.startLocation.lng))
                                    .title("Step Begin")
                            );
                            mMap.addMarker(new MarkerOptions()
                                    .position(new LatLng(leg.endLocation.lat, leg.endLocation.lng))
                                    .title("Step End")
                            );
                            //create string for URI
                            waypointsStr += "|";
                            waypointsStr += leg.endLocation.lat + "," + leg.endLocation.lng;

                            for (DirectionsStep step : leg.steps) {
                                polylineOptions.add(new LatLng(step.startLocation.lat, step.startLocation.lng));
                            }
                        }
                        mMap.addPolyline(polylineOptions);
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(route.legs[0].startLocation.lat, route.legs[0].startLocation.lng), 10));
                        //mMap.setTrafficEnabled(true);
                    }

                    //---------------------ENABLE OPENING MAPS APP EXTERNALLY-------------------------------

                    Button openInApp = findViewById(R.id.openInApp);

                    String finalWaypointsStr = waypointsStr;
                    openInApp.setOnClickListener(new View.OnClickListener(){
                        public void onClick(View view){

                            // Create a URI for the Google Maps app with the route
                            Uri gmmIntentUri = Uri.parse("google.navigation:q=" + origin + "&d=" + destination + "&waypoints=" + finalWaypointsStr);

                            // Create an Intent to launch the Google Maps app
                            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                            mapIntent.setPackage("com.google.android.apps.maps"); // Specify the Google Maps app

                            // Check if the Google Maps app is installed
                            if (mapIntent.resolveActivity(getPackageManager()) != null) {
                                // Open the Google Maps app with the route
                                System.out.println("google maps external activated");
                                startActivity(mapIntent);
                            } else {
                                // If the Google Maps app is not installed, you can handle this case by providing a fallback option or directing the user to install the app from the Play Store.
                                Toast.makeText(RouteCreator.this, "Your app needs to be configured. Please download and sign in the google maps app", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }

    public interface DataCallback{
        void onDataReceived(ArrayList<String> data);
    }







}