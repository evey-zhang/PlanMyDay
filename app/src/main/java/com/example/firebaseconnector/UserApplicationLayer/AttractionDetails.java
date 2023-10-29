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

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction_details);
		setUpDatabaseReferenceForUser();

		// Get data passed from the previous Activity
		Attraction attraction = getIntent().getParcelableExtra("DETAIL_DATA");

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
		String uid = "";
		if (currentUser != null) {
			uid = currentUser.getUid();
			// Now, 'uid' contains the UID of the current user
			System.out.println("Current user's UID: " + uid);
		} else {
			// There is no signed-in user
			System.out.println("No signed-in user");
		}
		db = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("attractionList");
	}

	// save attraction
	private void saveAttractionToDB(Attraction targetAttraction) {
		db.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				boolean exists = false;
				for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
					Attraction existingAttraction = snapshot.getValue(Attraction.class);
					System.out.println(existingAttraction.getId());
					System.out.println(targetAttraction.getId());
					if (existingAttraction != null && existingAttraction.getId().equals(targetAttraction.getId())) {
						exists = true;
						break;
					}
				}
				if (exists) {
					Toast.makeText(AttractionDetails.this, "Attraction already exists!", Toast.LENGTH_SHORT).show();
				} else {
					db.push().setValue(targetAttraction)
							.addOnSuccessListener(aVoid -> Toast.makeText(AttractionDetails.this, "Attraction added successfully!", Toast.LENGTH_SHORT).show())
							.addOnFailureListener(e -> Toast.makeText(AttractionDetails.this, "Failed to add attraction.", Toast.LENGTH_SHORT).show());
				}
			}

			@Override
			public void onCancelled(DatabaseError databaseError) {
				Toast.makeText(AttractionDetails.this, "Error accessing database.", Toast.LENGTH_SHORT).show();
			}
		});
	}



	//remove attraction
	private void removeAttractionFromDB(Attraction targetAttraction) {
		db.addListenerForSingleValueEvent(new ValueEventListener() {
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
