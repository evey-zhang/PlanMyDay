package com.example.firebaseconnector.UserApplicationLayer;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebaseconnector.R;

import java.util.ArrayList;
import java.util.List;

public class PageFragment extends Fragment {

	private static final String ARG_PAGE_NUMBER = "page_number";
	static int numPages = 0;

	private ArrayList<Attraction> dayPlan;

	public PageFragment(ArrayList<Attraction> dayPlan) {
		this.dayPlan = dayPlan;
	}

	public static PageFragment newInstance(int pageNumber, ArrayList<Attraction> dayPlan) {
		PageFragment fragment = new PageFragment(dayPlan);
		Bundle args = new Bundle();
		args.putInt(ARG_PAGE_NUMBER, pageNumber);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_page, container, false);
		RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
		recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
		recyclerView.setAdapter(new PlanPageAdapter(getDataForPage()));
		return view;
	}

	private ArrayList<Attraction> getDataForPage() {
		return dayPlan;
	}
}
