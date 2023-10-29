package com.example.firebaseconnector.UserApplicationLayer;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebaseconnector.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
//ADAPTER FOR "SAVED ATTRACTIONS"
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ArrayList<Attraction> attractionList;
    private Context context;

    DatabaseReference databaseReference;
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // Define your views here
        TextView name, operatingTime, address;
        Button removeButton;

        public MyViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.textname);
            operatingTime = v.findViewById(R.id.cell_time);
            address = v.findViewById(R.id.addressText);
            removeButton = v.findViewById(R.id.removeButton);
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
                .inflate(R.layout.attractioncard, parent, false);
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


        holder.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, SavedAttractionList.class);
                System.out.println("NEXT PAGE");
                intent.putExtra("Remove Attraction", attraction);
                context.startActivity(intent);

            }
        });
    }



    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return attractionList.size();
    }


}

