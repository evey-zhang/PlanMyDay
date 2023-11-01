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

	public PageFragment() {
		// Required empty public constructor
	}

	public static PageFragment newInstance(int pageNumber) {
		PageFragment fragment = new PageFragment();
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
		recyclerView.setAdapter(new PlanPageAdapter(getDataForPage(getArguments().getInt(ARG_PAGE_NUMBER))));
		return view;
	}

	private List<String> getDataForPage(int pageNumber) {
		// Sample data for demonstration
		List<String> data = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			data.add("Item " + (i + 1) + " of Page " + (pageNumber + 1));
		}
		return data;
	}
}
