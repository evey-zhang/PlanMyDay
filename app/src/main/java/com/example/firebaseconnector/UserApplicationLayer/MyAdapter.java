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
    Context context;
    ArrayList<Attraction> attractionList;

    public MyAdapter(Context context, ArrayList<Attraction> attractionList) {
        this.context = context;
        this.attractionList = attractionList;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.attractioncard,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        Attraction attraction = attractionList.get(position);
        holder.name.setText(attraction.getName());
        holder.openTime.setText(attraction.getOpenTime());
        holder.closeTime.setText(attraction.getCloseTime());
        holder.address.setText(attraction.getAddress());

    }

    @Override
    public int getItemCount() {
        return attractionList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,openTime,closeTime ,address;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textname);
            openTime = itemView.findViewById(R.id.openTimeText);
            closeTime = itemView.findViewById(R.id.closeTimeText);
            address = itemView.findViewById(R.id.addressText);
        }


    }
}
