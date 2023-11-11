package com.example.firebaseconnector.UserApplicationLayer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebaseconnector.R;


import org.w3c.dom.Attr;

import java.util.ArrayList;
import java.util.List;

public class PlanPageAdapter extends RecyclerView.Adapter<PlanPageAdapter.ViewHolder> {
	private final ArrayList<Attraction> dayPlan;

	public PlanPageAdapter(ArrayList<Attraction> data) {
		this.dayPlan = data;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.activity_attraction_list_cell, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		Attraction attraction = dayPlan.get(position);
		holder.name.setText(attraction.getName());
		holder.address.setText(attraction.getAddress());

		String operatingTimeString = attraction.getOpenTime() + " - " + attraction.getCloseTime();
		holder.operatingTime.setText(operatingTimeString);
	}

	@Override
	public int getItemCount() {
		return dayPlan.size();
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {
		public TextView name;
		public TextView operatingTime;
		public TextView address;

		ViewHolder(View v) {
			super(v);
			name = v.findViewById(R.id.cell_name);
			operatingTime = v.findViewById(R.id.cell_time);
			address = v.findViewById(R.id.cell_address);
		}
	}
}
