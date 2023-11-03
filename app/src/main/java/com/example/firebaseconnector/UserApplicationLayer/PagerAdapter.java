package com.example.firebaseconnector.UserApplicationLayer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.firebaseconnector.R;

import java.util.ArrayList;
import java.util.List;

public class PagerAdapter extends FragmentStateAdapter {

	private int pageCount = 0;
	private ArrayList<ArrayList<Attraction>> tripPlan;

	public PagerAdapter(@NonNull FragmentActivity fragmentActivity, int initialPageCount, ArrayList<ArrayList<Attraction>> tripPlan) {
		super(fragmentActivity);
		this.pageCount = initialPageCount;
		this.tripPlan = tripPlan;
	}

	@Override
	public Fragment createFragment(int position) {
		// Return a new fragment instance with a list for each position
		return PageFragment.newInstance(position, tripPlan.get(position));
	}

	@Override
	public int getItemCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
}

