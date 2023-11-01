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

	public PagerAdapter(@NonNull FragmentActivity fragmentActivity) {
		super(fragmentActivity);
	}

	@Override
	public Fragment createFragment(int position) {
		// Return a new fragment instance with a list for each position
		return PageFragment.newInstance(position);
	}

	@Override
	public int getItemCount() {
		return 3; // Three pages
	}
}

