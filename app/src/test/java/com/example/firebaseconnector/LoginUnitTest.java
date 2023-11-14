//package com.example.firebaseconnector;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
//import static org.mockito.Mockito.when;
//
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseAuthException;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//
//@RunWith(MockitoJUnitRunner.class)
//public class LoginUnitTest {
//    @Mock
//    FirebaseAuth mockFirebaseAuth;
//
//    @Mock
//    AuthManager authManager;
//
//    @Test
//    public void signInWithEmailAndPassword_success() {
//        // Mock successful authentication
//        when(mockFirebaseAuth.signInWithEmailAndPassword("newUser@gmail.com", "abcdef"))
//                .thenReturn(TaskMockUtil.mockSignInResult("testUserId"));
//
//        // Perform authentication
//        Task<AuthResult> result = authManager.signInWithEmailAndPassword("newUser@gmail.com", "abcdef");
//
//        // Verify successful authentication
//        assertTrue(result.isSuccessful());
////        assertEquals("testUserId", result.getResult().getUser().getUid());
//    }
//
////    @Test
////    public void signInWithEmailAndPassword_failure() {
////        // Mock authentication failure
////        when(mockFirebaseAuth.signInWithEmailAndPassword("invalid@example.com", "invalid"))
////                .thenReturn(TaskMockUtil.mockException(new FirebaseAuthException("ERROR_INVALID_EMAIL", "Invalid email")));
////
////        // Perform authentication
////        Task<AuthResult> result = authManager.signInWithEmailAndPassword("invalid@example.com", "invalid");
////
////        // Verify authentication failure
////        assertFalse(result.isSuccessful());
////        assertTrue(result.getException() instanceof FirebaseAuthException);
////        assertEquals("Invalid email", result.getException().getMessage());
////    }
//}
