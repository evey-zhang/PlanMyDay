package com.example.firebaseconnector;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.firebaseconnector.UserApplicationLayer.User;

public class UserFunctionsUnitTest{

    private User user;

    @Before
    public void setUp() {
        // You can initialize the User object here if needed
        user = new User();
    }

    @Test
    public void testGettersAndSetters() {
        // Mock data
        String email = "test@example.com";
        String password = "password123";
        int numDays = 5;

        // Set data using setters
        user.setEmail(email);
        user.setPassword(password);
        user.setNumDays(numDays);

        // Verify data using getters
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
        assertEquals(numDays, user.getNumDays());
    }

    @Test
    public void testParameterizedConstructor() {
        // Mock data
        String email = "test@example.com";
        String password = "password123";
        int numDays = 5;

        // Create a User object using the parameterized constructor
        User user = new User(email, password, numDays);

        // Verify data using getters
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
        assertEquals(numDays, user.getNumDays());
    }

    @Test
    public void testDefaultConstructor() {
        // Create a User object using the default constructor
        User user = new User();

        // Verify default values (assuming your default values are null or 0)
        assertEquals(null, user.getEmail());
        assertEquals(null, user.getPassword());
        assertEquals(0, user.getNumDays());
    }
}


