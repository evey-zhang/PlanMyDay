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
    DatabaseReference databaseReference;
    MyAdapter adapter;

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
        databaseReference = FirebaseDatabase.getInstance().getReference("attractions");
        attractionList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(this,attractionList);
        recyclerView.setAdapter(adapter);
        String uid = "";
        if (currentUser != null) {
            uid = currentUser.getUid();
            // Now, 'uid' contains the UID of the current user
            System.out.println("Current user's UID: " + uid);
        } else {
            // There is no signed-in user
            System.out.println("No signed-in user");
        }
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("attractionList");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                long numberOfChildren = snapshot.getChildrenCount();
                System.out.println("Number of objects in the databaseReference: " + numberOfChildren);

                for(DataSnapshot dataSnapshot: snapshot.getChildren()){

                    Attraction attraction = dataSnapshot.getValue(Attraction.class);
                    attractionList.add(attraction);
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