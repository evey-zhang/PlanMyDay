package com.example.firebaseconnector.UserApplicationLayer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.firebaseconnector.R;


public class PlanPage extends AppCompatActivity {

	private ViewPager2 viewPager;
	private Button buttonLeft, buttonRight;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_plan_page);

		viewPager = findViewById(R.id.viewPager);
		buttonLeft = findViewById(R.id.buttonLeft);
		buttonRight = findViewById(R.id.buttonRight);

		viewPager.setAdapter(new PagerAdapter(this));

		viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
			@Override
			public void onPageSelected(int position) {
				buttonLeft.setVisibility(position == 0 ? View.GONE : View.VISIBLE);
				buttonRight.setVisibility(position == 2 ? View.GONE : View.VISIBLE);
			}
		});

		buttonLeft.setOnClickListener(v -> viewPager.setCurrentItem(viewPager.getCurrentItem() - 1, true));
		buttonRight.setOnClickListener(v -> viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true));
	}
}
