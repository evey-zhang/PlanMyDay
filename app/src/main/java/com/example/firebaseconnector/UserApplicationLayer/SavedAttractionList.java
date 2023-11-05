package com.example.firebaseconnector.UserApplicationLayer;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.firebaseconnector.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Attr;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SavedAttractionList extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    MyAdapter adapter;

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser currentUser = mAuth.getCurrentUser();

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //INPUT startactivity to lead back to home page
//        startActivity;
        Intent intent = new Intent(getApplicationContext(), LandingPage.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_attraction_list);

		databaseReference = FirebaseDatabase.getInstance().getReference("attractions");
		ArrayList<Attraction> attractionList = new ArrayList<>();

        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(this,attractionList);
        recyclerView.setAdapter(adapter);

        String uid = "";
        if (currentUser != null) {
            uid = currentUser.getUid();
            // Now, 'uid' contains the UID of the current user
            System.out.println("Current user's UID: " + uid);
        } else {
            // There is no signed-in user
            System.out.println("No signed-in user");
        }
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("attractionList");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                long numberOfChildren = snapshot.getChildrenCount();
                System.out.println("Number of objects in the databaseReference: " + numberOfChildren);
                attractionList.clear();
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){

                    Attraction attraction = dataSnapshot.getValue(Attraction.class);
                    attractionList.add(attraction);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //REMOVE ATTRACTION
        Attraction attraction = getIntent().getParcelableExtra("Remove Attraction");
        if (attraction != null){
            removeAttractionFromDB(attraction);
            adapter.notifyDataSetChanged();
        }
        else{
            System.out.println("No attraction to remove");
        }


        //LISTENER FOR BACK BUTTON
        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), LandingPage.class);
                startActivity(intent);
                finish();
            }
        });

        //CREATE ROUTE BUTTON + SET DAYS OF TRIP:
        Button createRoute = findViewById(R.id.createRouteButton);
        EditText numDays = findViewById(R.id.numberDays);
        createRoute.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                String numberDays = (String.valueOf(numDays.getText()));
                try{//is integer
                    int intNumberDays = Integer.parseInt(numberDays);
                    if (intNumberDays > 0){ setDays(intNumberDays);}
                    else{
                        Toast.makeText(SavedAttractionList.this, "Days cannot be less than 1", Toast.LENGTH_SHORT).show();
                    }
//					uploadDummyPlanToDB();
					generateTripPlan(attractionList, intNumberDays);
                    //SET INTENT TO CREATE ROUTE ACTIVITY

                    Intent intent = new Intent(getApplicationContext(), PlanPage.class);
                    startActivity(intent);
                    finish();

                } catch (NumberFormatException e) {
                    // not an integer!
                    Toast.makeText(SavedAttractionList.this, "Please Enter a valid number of at least 1", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

	private void generateTripPlan(ArrayList<Attraction> savedAttractions, int numDays) {
		System.out.println("BITCHHHHH");

		TripPlanner tripPlanner = new TripPlanner(savedAttractions, numDays);
		ArrayList<ArrayList<Attraction>> tripPlan = tripPlanner.generateTrip();
        System.out.println("Trip: " + tripPlan);
		System.out.println("HELLOOOOOO");
		for (ArrayList<Attraction> a: tripPlan) {
			for (Attraction b: a) {
				System.out.println(b.getName());
			}
			System.out.println("----");
		}

		// Upload plan to firestore
		String uid = "";
		if (currentUser != null) {
			uid = currentUser.getUid();
			// Now, 'uid' contains the UID of the current user
			System.out.println("Current user's UID: " + uid);
		} else {
			// There is no signed-in user
			System.out.println("No signed-in user");
		}

		// Set the value of "tripPlan" under the user's node
		databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);
		databaseReference.child("tripPlan").setValue(tripPlan)
				.addOnSuccessListener(new OnSuccessListener<Void>() {
					@Override
					public void onSuccess(Void aVoid) {
						// Write was successful!
						Log.d(TAG, "tripPlan successfully written!");
					}
				})
				.addOnFailureListener(new OnFailureListener() {
					@Override
					public void onFailure(@NonNull Exception e) {
						// Write failed
						Log.w(TAG, "Error writing tripPlan", e);
					}
				});
	}

	private void uploadDummyPlanToDB(ArrayList<Attraction> attractionList) {
		ArrayList<ArrayList<Attraction>> tripPlan = new ArrayList<>();
		int cnt = 0;
		ArrayList<Attraction> dayPlan = new ArrayList<>();
		for (int i = 0; i < attractionList.size(); i++) {
			if (cnt == 2) {
				cnt = 0;
				ArrayList<Attraction> copy = (ArrayList<Attraction>) dayPlan.clone();
				tripPlan.add(copy);
				dayPlan.clear();
			}
			dayPlan.add(attractionList.get(i));
			cnt++;
		}
		tripPlan.add(dayPlan);

//		System.out.println("HELLOOOOOO");
//		for (ArrayList<Attraction> a: tripPlan) {
//			for (Attraction b: a) {
//				System.out.println(b.getName());
//			}
//			System.out.println("----");
//		}


		String uid = "";
		if (currentUser != null) {
			uid = currentUser.getUid();
			// Now, 'uid' contains the UID of the current user
			System.out.println("Current user's UID: " + uid);
		} else {
			// There is no signed-in user
			System.out.println("No signed-in user");
		}

		// Set the value of "tripPlan" under the user's node
		databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);
		databaseReference.child("tripPlan").setValue(tripPlan)
				.addOnSuccessListener(new OnSuccessListener<Void>() {
					@Override
					public void onSuccess(Void aVoid) {
						// Write was successful!
						Log.d(TAG, "tripPlan successfully written!");
					}
				})
				.addOnFailureListener(new OnFailureListener() {
					@Override
					public void onFailure(@NonNull Exception e) {
						// Write failed
						Log.w(TAG, "Error writing tripPlan", e);
					}
				});
	}

    //Enter Number Of Days
    private void setDays(int days) {

        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getUid()).child("numDays");

        mDatabase.setValue(days).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(SavedAttractionList.this, "Saved new Route Days.", Toast.LENGTH_SHORT).show();
            }
        });
    }
    //REMOVE ATTRACTION:
    private void removeAttractionFromDB(Attraction targetAttraction) {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String keyToRemove = null;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Attraction existingAttraction = snapshot.getValue(Attraction.class);
                    if (existingAttraction != null && existingAttraction.getId().equals(targetAttraction.getId())) {
                        keyToRemove = snapshot.getKey();
                        break;
                    }
                }
                if (keyToRemove != null) {
                    databaseReference.child(keyToRemove).removeValue()
                            .addOnSuccessListener(aVoid -> Toast.makeText(SavedAttractionList.this, "Attraction removed successfully!", Toast.LENGTH_SHORT).show())
                            .addOnFailureListener(e -> Toast.makeText(SavedAttractionList.this, "Failed to remove attraction.", Toast.LENGTH_SHORT).show());
                } else {
                    Toast.makeText(SavedAttractionList.this, "Attraction not found in list.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(SavedAttractionList.this, "Error accessing database.", Toast.LENGTH_SHORT).show();
            }
        });
    }
    //list of all the saved attractions

    //Loads from database user's saved attractions
    //Remove saved attraction function
    //Button click of "route creation"
    //select how many days to plan for
    //"back' button to lading page
}
