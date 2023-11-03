package com.example.firebaseconnector.UserApplicationLayer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.firebaseconnector.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Attr;

import java.util.ArrayList;


public class PlanPage extends AppCompatActivity {

	private ViewPager2 viewPager;
	private TextView dayTitle;

	private FirebaseAuth mAuth = FirebaseAuth.getInstance();
	private FirebaseUser currentUser = mAuth.getCurrentUser();
	private DatabaseReference db;

	private Button buttonLeft, buttonRight;
	private int dayNumber = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_plan_page);

		viewPager = findViewById(R.id.viewPager);
		dayTitle = findViewById(R.id.day);
//		buttonLeft = findViewById(R.id.buttonLeft);
//		buttonRight = findViewById(R.id.buttonRight);

		ArrayList<ArrayList<Attraction>> tripPlan = getTripPlan();

		viewPager.setAdapter(new PagerAdapter(this, 0, tripPlan));

		viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
			@Override
			public void onPageSelected(int position) {
				dayTitle.setText("Day " + (position+1));
				dayNumber = position ;
//				buttonLeft.setVisibility(position == 0 ? View.GONE : View.VISIBLE);
//				buttonRight.setVisibility(position == 2 ? View.GONE : View.VISIBLE);
			}
		});
//
//		buttonLeft.setOnClickListener(v -> viewPager.setCurrentItem(viewPager.getCurrentItem() - 1, true));
//		buttonRight.setOnClickListener(v -> viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true));

		Button carRouteButton = findViewById(R.id.carRouteButton);
		carRouteButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent intent = new Intent(getApplicationContext(), RouteCreator.class);
				intent.putExtra("IS_DRIVE_MODE", true);
				intent.putExtra("DAY_NUMBER", dayNumber);
				startActivity(intent);
				finish();
			}
		});

		Button publicTransportbutton = findViewById(R.id.publicTransportbutton);
		publicTransportbutton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent intent = new Intent(getApplicationContext(), RouteCreator.class);
				intent.putExtra("IS_DRIVE_MODE", false);
				intent.putExtra("DAY_NUMBER", dayNumber);
				startActivity(intent);
				finish();
			}
		});

	}

	private ArrayList<ArrayList<Attraction>> getTripPlan() {
		ArrayList<ArrayList<Attraction>> tripPlan = new ArrayList<>();
		String uid = "";
		if (currentUser != null) {
			uid = currentUser.getUid();
			System.out.println("Current user's UID: " + uid);
		} else {
			System.out.println("No signed-in user");
		}
		db = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("tripPlan");

		// Add Event listener for DB
		db.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot snapshot) {
				for (DataSnapshot snapshotDay : snapshot.getChildren()) {
					ArrayList<Attraction> dayPlan = new ArrayList<>();
					for (DataSnapshot snapshotAttraction : snapshotDay.getChildren() ) {
						System.out.println("ERROR IS HERE" + snapshotAttraction.getValue(Attraction.class).getAddress() );
						Attraction attraction = snapshotAttraction.getValue(Attraction.class);
						dayPlan.add(attraction);
					}
					tripPlan.add(dayPlan);
				}

				for (ArrayList<Attraction> a : tripPlan) {
					for (Attraction b : a) {
						System.out.println(b.getName());
					}
					System.out.println("---");
				}

				updateViewPager(tripPlan.size());
			}

			@Override
			public void onCancelled(@NonNull DatabaseError error) {

			}
		});

		return tripPlan;
	}

	private void updateViewPager(int pageCount) {
		PagerAdapter adapter = (PagerAdapter) viewPager.getAdapter();
		if (adapter != null) {
			adapter.setPageCount(pageCount);
			adapter.notifyDataSetChanged();
		}
	}

}
