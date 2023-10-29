package com.example.firebaseconnector.UserApplicationLayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
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
import java.util.List;

public class AttractionList extends AppCompatActivity {

	private RecyclerView recyclerView;
	private AttractionListAdapter adapter;
	private RecyclerView.LayoutManager layoutManager;
	private ArrayList<Attraction> attractionList;
	private DatabaseReference db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_attraction_list);


		recyclerView = findViewById(R.id.recycler_view);

		// Use a linear layout manager
		layoutManager = new LinearLayoutManager(this);
		recyclerView.setLayoutManager(layoutManager);

		// Specify an adapter
		db = FirebaseDatabase.getInstance().getReference("attractions");
		attractionList = new ArrayList<>();
		adapter = new AttractionListAdapter(this, attractionList);
		recyclerView.setAdapter(adapter);

		// Add Event listener for DB
		db.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot snapshot) {
				for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

					Attraction attraction = dataSnapshot.getValue(Attraction.class);
					attractionList.add(attraction);
				}
				adapter.notifyDataSetChanged();
			}

			@Override
			public void onCancelled(@NonNull DatabaseError error) {

			}
		});

		//LISTENER FOR BACK BUTTON
		ImageButton backButton = findViewById(R.id.backButton);
		backButton.setOnClickListener(new View.OnClickListener(){
			public void onClick(View view){
				Intent intent = new Intent(getApplicationContext(), LandingPage.class);
				startActivity(intent);
				finish();
			}
		});

	}

}
