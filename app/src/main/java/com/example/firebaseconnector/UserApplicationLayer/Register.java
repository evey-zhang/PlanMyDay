package com.example.firebaseconnector.UserApplicationLayer;

import static android.content.ContentValues.TAG;

import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firebaseconnector.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Register extends AppCompatActivity {

    EditText editTextEmail, editTextPassword;
    Button buttonRegister;
    FirebaseAuth mAuth;
    TextView textView;
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
    //Add new user to database
    private void addUser(String userID, String email, String password) {

        User newUser = new User(email, password, 1);
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");

        mDatabase.child(userID).setValue(newUser).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(Register.this, "Successfully added new user to database.", Toast.LENGTH_SHORT).show();
            }
        });
		mDatabase.child("users").child(userID).child("attractionList").setValue(null);
		mDatabase.child("users").child(userID).child("tripPlan").setValue(null);

	}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth= FirebaseAuth.getInstance();
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        buttonRegister= findViewById(R.id.registerButton);
        textView = findViewById(R.id.loginNow);

        //Clicked "Click to Login", use login activity
        textView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }

        });


        //Click "Register" to register user with firebase
        buttonRegister.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = String.valueOf(editTextEmail.getText());
                String password = String.valueOf(editTextPassword.getText());

                //check if email and/or password is empty
                if (email.isEmpty()){
                    Toast.makeText(Register.this, "Email is missing.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.isEmpty()){
                    Toast.makeText(Register.this, "Password is missing.", Toast.LENGTH_SHORT).show();
                    return;
                }

                //create user in firebase -- code from firebase docs:https://firebase.google.com/docs/auth/android/password-auth#java_1
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Register Successful
                                    Toast.makeText(Register.this, "Register Successful.",
                                            Toast.LENGTH_SHORT).show();

                                    //add user to database
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    String userID = user.getUid();
                                    addUser(userID, email,password);

									// Direct to landing page
									Intent intent = new Intent(getApplicationContext(), LandingPage.class);
									startActivity(intent);
									finish();
                                } else {
                                    // If Register fails, display a message to the user. Note: password must be at least 6 characters
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(Register.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        }));
    }
}
