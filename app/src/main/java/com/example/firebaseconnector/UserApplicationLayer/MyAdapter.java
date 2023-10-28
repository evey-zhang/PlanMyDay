package com.example.firebaseconnector.UserApplicationLayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebaseconnector.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ArrayList<Attraction> attractionList;
    private Context context;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // Define your views here
        TextView name, operatingTime, address;

        public MyViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.cell_name);
            operatingTime = v.findViewById(R.id.cell_time);
            address = v.findViewById(R.id.cell_address);
        }
    }

    // Constructor for the adapter
    public MyAdapter(Context context, ArrayList<Attraction> attractionList) {
        this.context = context;
        this.attractionList = attractionList;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(context)
                .inflate(R.layout.activity_attraction_list_cell, parent, false);
        return new MyViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyAdapter.MyViewHolder holder, int position) {
        Attraction attraction = attractionList.get(position);
        holder.name.setText(attraction.getName());
        holder.address.setText(attraction.getAddress());

        String operatingTimeString = attraction.getOpenTime() + " - " + attraction.getCloseTime();
        holder.address.setText(operatingTimeString);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return attractionList.size();
    }


}

