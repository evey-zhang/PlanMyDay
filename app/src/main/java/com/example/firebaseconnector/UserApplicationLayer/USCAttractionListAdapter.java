package com.example.firebaseconnector.UserApplicationLayer;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebaseconnector.R;


import java.util.ArrayList;

public class USCAttractionListAdapter extends RecyclerView.Adapter<USCAttractionListAdapter.USCAttractionViewHolder> {

	private ArrayList<Attraction> attractionList;
	private Context context;

	public ArrayList<Attraction> getAttractionList() {
		return attractionList;
	}

	public static class USCAttractionViewHolder extends RecyclerView.ViewHolder {
		// Define your views here
		public TextView name;
		public TextView operatingTime;
		public TextView address;
		CardView cell;

		public USCAttractionViewHolder(View v) {
			super(v);
			name = v.findViewById(R.id.cell_name);
			operatingTime = v.findViewById(R.id.cell_time);
			address = v.findViewById(R.id.cell_address);
			cell = v.findViewById(R.id.cell_container);
		}
	}

	// Constructor for the adapter
	public USCAttractionListAdapter(Context context, ArrayList<Attraction> attractionList) {
		this.context = context;
		this.attractionList = attractionList;
	}

	@NonNull
	@Override
	public USCAttractionListAdapter.USCAttractionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(context)
				.inflate(R.layout.activity_attraction_list_cell, parent, false);
		return new USCAttractionViewHolder(v);
	}

	@Override
	public void onBindViewHolder(@NonNull USCAttractionListAdapter.USCAttractionViewHolder holder, int position) {
		Attraction attraction = attractionList.get(position);
		holder.name.setText(attraction.getName());
		holder.address.setText(attraction.getAddress());

		String operatingTimeString = attraction.getOpenTime() + " - " + attraction.getCloseTime();
		holder.operatingTime.setText(operatingTimeString);

		holder.cell.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Context context = view.getContext();
				Intent intent = new Intent(context, AttractionDetails.class);
				System.out.println("NEXT PAGE");
				intent.putExtra("DETAIL_DATA", attraction);
				context.startActivity(intent);
			}
		});
	}

	// Return the size of your dataset (invoked by the layout manager)
	@Override
	public int getItemCount() {
		return attractionList.size();
	}


	public void setAttractionList(ArrayList<Attraction> list) {
		this.attractionList = list;
	}
}
