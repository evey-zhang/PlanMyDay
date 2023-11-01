package com.example.firebaseconnector.UserApplicationLayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.firebaseconnector.R;

public class LandingPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        //1) NAVIGATE TO ATTRACTION LIST PAGE
        Button AttractionListButton = findViewById(R.id.attractionListButton);
        //Clicked "AttractionList", go to view Attraction list view
        AttractionListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AttractionList.class);
                startActivity(intent);
                finish();
            }
        });

        //1) NAVIGATE TO SAVED ATTRACTION LIST PAGE
        Button savedAttractionButton = findViewById(R.id.SavedAttractionButton);
        //Clicked "SAVED ATTRACTIONS", go to view Attraction list view
        savedAttractionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SavedAttractionList.class);
                startActivity(intent);
                finish();
            }
        });

		//1) NAVIGATE TO SAVED ATTRACTION LIST PAGE
		Button planButton = findViewById(R.id.planButton);
		//Clicked "SAVED ATTRACTIONS", go to view Attraction list view
		planButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(getApplicationContext(), PlanPage.class);
				startActivity(intent);
				finish();
			}
		});

    }

}
