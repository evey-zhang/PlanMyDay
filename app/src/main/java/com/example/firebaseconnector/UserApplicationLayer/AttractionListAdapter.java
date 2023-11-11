package com.example.firebaseconnector.UserApplicationLayer;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebaseconnector.R;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AttractionListAdapter extends RecyclerView.Adapter<AttractionListAdapter.MyViewHolder> {

	private ArrayList<Attraction> attractionList;
	private Context context;

	public ArrayList<Attraction> getAttractionList() {
		return attractionList;
	}

	public static class MyViewHolder extends RecyclerView.ViewHolder {
		// Define your views here
		public TextView name;
		public TextView operatingTime;
		public TextView address;
		CardView cell;

		public MyViewHolder(View v) {
			super(v);
			name = v.findViewById(R.id.cell_name);
			operatingTime = v.findViewById(R.id.cell_time);
			address = v.findViewById(R.id.cell_address);
			cell = v.findViewById(R.id.cell_container);
		}
	}

	// Constructor for the adapter
	public AttractionListAdapter(Context context, ArrayList<Attraction> attractionList) {
		this.context = context;
		this.attractionList = attractionList;
	}

	// Create new views (invoked by the layout manager)
	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		// create a new view
		View v = LayoutInflater.from(context)
				.inflate(R.layout.activity_attraction_list_cell, parent, false);
		return new MyViewHolder(v);
	}

	// Replace the contents of a view (invoked by the layout manager)
	@Override
	public void onBindViewHolder(MyViewHolder holder, int position) {
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
