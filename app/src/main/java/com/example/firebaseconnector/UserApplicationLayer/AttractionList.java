package com.example.firebaseconnector.UserApplicationLayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.firebaseconnector.R;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AttractionList extends AppCompatActivity {

	private RecyclerView recyclerView;
	private USCAttractionListAdapter USCAdapter;
	private LAAttractionListAdapter LAAdapter;
	private RecyclerView.LayoutManager layoutManager;
	private ArrayList<Attraction> uscAttractionList, LAattractionList;
	private DatabaseReference db;



	public void onBackPressed() {
		super.onBackPressed();
		//INPUT startactivity to lead back to home page
//        startActivity;
		Intent intent = new Intent(getApplicationContext(), LandingPage.class);
		startActivity(intent);
		finish();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_attraction_list);


		// SETUP USC Attraction List
		recyclerView = findViewById(R.id.usc_recycler_view);

		// Use a linear layout manager
		layoutManager = new LinearLayoutManager(this);
		recyclerView.setLayoutManager(layoutManager);

		// Specify an adapter
		db = FirebaseDatabase.getInstance().getReference("attractions");
		uscAttractionList = new ArrayList<>();
		USCAdapter = new USCAttractionListAdapter(this, uscAttractionList);
		recyclerView.setAdapter(USCAdapter);

		// Add Event listener for DB
		db.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot snapshot) {
				for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

					Attraction attraction = dataSnapshot.getValue(Attraction.class);
					if (attraction.isAtUSC())
						uscAttractionList.add(attraction);
				}
				USCAdapter.notifyDataSetChanged();
			}

			@Override
			public void onCancelled(@NonNull DatabaseError error) {

			}
		});



		// SETUP LA Attraction List
		recyclerView = findViewById(R.id.la_recycler_view);

		// Use a linear layout manager
		layoutManager = new LinearLayoutManager(this);
		recyclerView.setLayoutManager(layoutManager);

		// Specify an adapter
		db = FirebaseDatabase.getInstance().getReference("attractions");
		LAattractionList = new ArrayList<>();
		LAAdapter = new LAAttractionListAdapter(this, LAattractionList);
		recyclerView.setAdapter(LAAdapter);

		// Add Event listener for DB
		db.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot snapshot) {
				for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

					Attraction attraction = dataSnapshot.getValue(Attraction.class);
					if (!attraction.isAtUSC())
						LAattractionList.add(attraction);
				}
				LAAdapter.notifyDataSetChanged();
			}

			@Override
			public void onCancelled(@NonNull DatabaseError error) {

			}
		});





//		//LISTENER FOR BACK BUTTON
//		ImageButton backButton = findViewById(R.id.backButton);
//		backButton.setOnClickListener(new View.OnClickListener(){
//			public void onClick(View view){
//				Intent intent = new Intent(getApplicationContext(), LandingPage.class);
//				startActivity(intent);
//				finish();
//			}
//		});

	}

}
