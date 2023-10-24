package com.example.firebaseconnector.UserApplicationLayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.firebaseconnector.R;

public class AttractionList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction_list);
    }

    //fetch from database and convert attractions to attraction objects
    // Display all the attractions available

}