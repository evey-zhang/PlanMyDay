package com.example.firebaseconnector.UserApplicationLayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firebaseconnector.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AttractionDetails extends AppCompatActivity {

	DatabaseReference db;
	FirebaseAuth mAuth = FirebaseAuth.getInstance();
	FirebaseUser currentUser = mAuth.getCurrentUser();

	String uid;

	public void onBackPressed() {
		super.onBackPressed();
		//INPUT startactivity to lead back to home page
//        startActivity;
		Intent intent = new Intent(getApplicationContext(), AttractionList.class);
		startActivity(intent);
		finish();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction_details);
		setUpDatabaseReferenceForUser();

		// Get data passed from the previous Activity
		Attraction attraction = getIntent().getParcelableExtra("DETAIL_DATA");

		if (attraction == null) {
			// Handle the situation where attraction is not passed correctly
			throw new IllegalArgumentException("No Attraction was provided in the intent extras");
		}

		// Set the data to your views
		TextView name = findViewById(R.id.cell_name);
		TextView operatingTime = findViewById(R.id.cell_time);
		TextView address = findViewById(R.id.cell_address);
		TextView description = findViewById(R.id.description_text);

		name.setText(attraction.getName());
		operatingTime.setText(attraction.getOpenTime() + " - " + attraction.getCloseTime());
		address.setText(attraction.getAddress());
		description.setText(attraction.getDescription());

		// Add click event listener for buttons
		Button saveButton = findViewById(R.id.save_button);
		saveButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				saveAttractionToDB(attraction);
			}
		});

		Button removeButton = findViewById(R.id.remove_button);
		removeButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				removeAttractionFromDB(attraction);
			}
		});

		//LISTENER FOR BACK BUTTON
		ImageButton backButton = findViewById(R.id.backButton);
		backButton.setOnClickListener(new View.OnClickListener(){
			public void onClick(View view){
				Intent intent = new Intent(getApplicationContext(), AttractionList.class);
				startActivity(intent);
				finish();
			}
		});

	}

	private void setUpDatabaseReferenceForUser() {
		uid = "";
		if (currentUser != null) {
			uid = currentUser.getUid();
			// Now, 'uid' contains the UID of the current user
			System.out.println("Current user's UID: " + uid);
		} else {
			// There is no signed-in user
			System.out.println("No signed-in user");
		}
	}

	// save attraction
	public void saveAttractionToDB(Attraction targetAttraction) {
		db = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);
		DatabaseReference attractionListRef = db.child("attractionList");

		attractionListRef.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				if (!dataSnapshot.exists()) {
					// If the attractionList doesn't exist, create it and add the new attraction
					dataSnapshot.getRef().child(targetAttraction.getId()).setValue(targetAttraction)
							.addOnSuccessListener(aVoid -> Toast.makeText(AttractionDetails.this, "Attraction list created and attraction added successfully!", Toast.LENGTH_SHORT).show())
							.addOnFailureListener(e -> Toast.makeText(AttractionDetails.this, "Failed to create attraction list and add attraction.", Toast.LENGTH_SHORT).show());
				} else {
					// If the attractionList exists, check if the attraction already exists within it
					boolean exists = false;
					for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
						Attraction existingAttraction = snapshot.getValue(Attraction.class);
						if (existingAttraction.getId() != null && existingAttraction.getId().equals(targetAttraction.getId())) {
							exists = true;
							break;
						}
					}
					if (exists) {
						Toast.makeText(AttractionDetails.this, "Attraction already exists!", Toast.LENGTH_SHORT).show();
					} else {
						// If the attraction doesn't exist, add it to the attractionList
						dataSnapshot.getRef().push().setValue(targetAttraction)
								.addOnSuccessListener(aVoid -> Toast.makeText(AttractionDetails.this, "Attraction added successfully!", Toast.LENGTH_SHORT).show())
								.addOnFailureListener(e -> Toast.makeText(AttractionDetails.this, "Failed to add attraction.", Toast.LENGTH_SHORT).show());
					}
				}
			}

			@Override
			public void onCancelled(DatabaseError databaseError) {
				Toast.makeText(AttractionDetails.this, "Error accessing database.", Toast.LENGTH_SHORT).show();
			}
		});
	}



	//remove attraction
	public void removeAttractionFromDB(Attraction targetAttraction) {
		db = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("attractionList");
		db.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				String keyToRemove = null;
				for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
					Attraction existingAttraction = snapshot.getValue(Attraction.class);
					if (existingAttraction.getId() != null && existingAttraction.getId().equals(targetAttraction.getId())) {
						keyToRemove = snapshot.getKey();
						break;
					}
				}
				if (keyToRemove != null) {
					db.child(keyToRemove).removeValue()
							.addOnSuccessListener(aVoid -> Toast.makeText(AttractionDetails.this, "Attraction removed successfully!", Toast.LENGTH_SHORT).show())
							.addOnFailureListener(e -> Toast.makeText(AttractionDetails.this, "Failed to remove attraction.", Toast.LENGTH_SHORT).show());
				} else {
					Toast.makeText(AttractionDetails.this, "Attraction not found in list.", Toast.LENGTH_SHORT).show();
				}
			}

			@Override
			public void onCancelled(DatabaseError databaseError) {
				Toast.makeText(AttractionDetails.this, "Error accessing database.", Toast.LENGTH_SHORT).show();
			}
		});
	}

}
