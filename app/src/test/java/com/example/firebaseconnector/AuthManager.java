package com.example.firebaseconnector;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthManager {

    private FirebaseAuth firebaseAuth;

    public AuthManager() {
        // Initialize Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public Task<AuthResult> signInWithEmailAndPassword(String email, String password) {
        return firebaseAuth.signInWithEmailAndPassword(email, password);
    }

    // Other authentication methods can be added as needed

    public FirebaseUser getCurrentUser() {
        return firebaseAuth.getCurrentUser();
    }
}
