package com.example.firebaseconnector.UserApplicationLayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;

import com.example.firebaseconnector.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Array;
import java.util.ArrayList;

public class SavedAttractionList extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Attraction> attractionList;
    DatabaseReference savedAttractionReference;
    MyAdapter adapter;

    // Get the current Firebase user
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser currentUser = mAuth.getCurrentUser();

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //INPUT startactivity to lead back to home page
//        startActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_attraction_list);
        recyclerView = findViewById(R.id.recycleView);
        attractionList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));//get current user's ID
        if (currentUser != null) {
            String uid = currentUser.getUid();
            // Now, 'uid' contains the UID of the current user
            System.out.println("Current user's UID: " + uid);
        } else {
            // There is no signed-in user
            System.out.println("No signed-in user");
        }
        savedAttractionReference = FirebaseDatabase.getInstance().getReference().child("Users").child("uid").child("attractionList");
        adapter = new MyAdapter(this,attractionList);
        recyclerView.setAdapter(adapter);
        Log.d("adapterwoo","should continue");

        savedAttractionReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Log.d("Attraction Data", dataSnapshot.getValue().toString());

                    Attraction attraction = dataSnapshot.getValue(Attraction.class);
                    attractionList.add(attraction);
                    Log.d("my saved attractions!", attraction.getName());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    //list of all the saved attractions

    //Loads from database user's saved attractions
    //Remove saved attraction function
    //Button click of "route creation"
    //select how many days to plan for
    //"back' button to lading page
}