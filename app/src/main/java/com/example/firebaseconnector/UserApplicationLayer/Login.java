package com.example.firebaseconnector.UserApplicationLayer;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firebaseconnector.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {

    EditText editTextEmail, editTextPassword;
    Button buttonLogin;
    FirebaseAuth mAuth;
    TextView registerNow;
    TextView forgotPassword;


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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth= FirebaseAuth.getInstance();
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        buttonLogin = findViewById(R.id.loginButton);
        registerNow = findViewById(R.id.registerNow);
        forgotPassword = findViewById(R.id.forgotpsswd);


        //Clicked "Click to Register", use REGISTER ACTIVITY
        registerNow.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                System.out.println("clicked");
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
                finish();
            }
        });
        //FORGOT password "clicked"
        forgotPassword.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                setContentView(R.layout.activity_forgot_password);
                Button resetButton = findViewById(R.id.resetButton);
                EditText editTextEmail = findViewById(R.id.forgotEmail);
                TextView returnHome = findViewById(R.id.backToSignin);
                String email = editTextEmail.getText().toString();

                //clicked RESET BUTTON
                resetButton.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View view) {
                        // In your button click listener or where you initiate the password reset process
                        mAuth.sendPasswordResetEmail(email)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            // Password reset email sent successfully
                                            Toast.makeText(Login.this, "Password reset email sent.", Toast.LENGTH_SHORT).show();
                                        } else {
                                            // Password reset email failed to send
                                            Toast.makeText(Login.this, "Failed to send password reset email.", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                });
                returnHome.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(), Login.class);
                        startActivity(intent);
                        finish();


                    }
                });

            }
        });


        buttonLogin.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = String.valueOf(editTextEmail.getText());
                String password = String.valueOf(editTextPassword.getText());

                //check if email and/or password is empty
                if (email.isEmpty()){
                    Toast.makeText(Login.this, "Email is missing.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.isEmpty()){
                    Toast.makeText(Login.this, "Password is missing.", Toast.LENGTH_SHORT).show();
                    return;
                }

                //create user in firebase -- code from firebase docs:https://firebase.google.com/docs/auth/android/password-auth#java_1
                mAuth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(Login.this, "Login successful.",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), LandingPage.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    // If Login fails, display a message to the user.
                                    Toast.makeText(Login.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        }));
    }
}
