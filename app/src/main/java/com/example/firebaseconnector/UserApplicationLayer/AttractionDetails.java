package com.example.firebaseconnector.UserApplicationLayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.firebaseconnector.R;

public class AttractionDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction_details);

		// Get data passed from the previous Activity
		Attraction attraction = getIntent().getParcelableExtra("DETAIL_DATA");
		System.out.println("Hi");

		// Set the data to your views
		// TextView detailTextView = findViewById(R.id.detail_text_view);
		TextView name = findViewById(R.id.cell_name);
		TextView operatingTime = findViewById(R.id.cell_time);
		TextView address = findViewById(R.id.cell_address);
		TextView description = findViewById(R.id.description_text);

		name.setText(attraction.getName());
		operatingTime.setText(attraction.getOpenTime() + " - " + attraction.getCloseTime());
		address.setText(attraction.getAddress());
		description.setText(attraction.getDescription());
		// detailTextView.setText(data);
    }

    //function for searching if specific attraction is already added or nah
    //save attraction
    //remove attraction
}
