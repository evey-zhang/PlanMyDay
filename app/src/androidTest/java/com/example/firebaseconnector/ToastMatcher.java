package com.example.firebaseconnector;
import android.view.View;
import android.view.WindowManager;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.Root;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;


public class ToastMatcher {

    public static TypeSafeMatcher<Root> isToast() {
        return new TypeSafeMatcher<Root>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("is toast");
            }

            @Override
            public boolean matchesSafely(Root root) {
                int type = root.getWindowLayoutParams().get().type;
                if ((type == WindowManager.LayoutParams.TYPE_TOAST)
                        || (type == WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY)) {
                    return true;
                }
                return false;
            }
        };
    }
}

