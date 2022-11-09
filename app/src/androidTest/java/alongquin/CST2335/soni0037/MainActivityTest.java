package alongquin.CST2335.soni0037;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    /**
     * testing if the textview is changing
     */
    @Test
    public void mainActivityTest() {
        ViewInteraction appCompatEditText = onView(withId(R.id.editText));
        appCompatEditText.perform(replaceText("12345"), closeSoftKeyboard());

        ViewInteraction materialButton = onView(withId(R.id.button));
        materialButton.perform(click());

        ViewInteraction textView = onView(withId(R.id.textView));
        textView.check(matches(withText("You shall not pass!")));
    }
    /**
     * tests if the password has an uppercase letter
     */
    @Test
    public void testMissingUpperCase() {
        //find the view s
        ViewInteraction appCompatEditText = onView( withId(R.id.editText) );
        //type in password123#$*
        appCompatEditText.perform(replaceText("password123#$*"));
        //find the button
        ViewInteraction materialButton = onView(withId(R.id.button));
        //Click the button
        materialButton.perform(click());
        //find the text view
        ViewInteraction textView = onView( withId(R.id.textView) );
        //check the text
        textView.check(matches(withText("You shall not pass!")));
    }

    /**
     * tests if the password has a lowercase letter
     */
    @Test
    public void testMissingLowerCase() {
        //find the view s
        ViewInteraction appCompatEditText = onView( withId(R.id.editText) );
        //type in PASSWORD123#$*
        appCompatEditText.perform(replaceText("PASSWORD123#$*"));
        //find the button
        ViewInteraction materialButton = onView(withId(R.id.button));
        //Click the button
        materialButton.perform(click());
        //find the text view
        ViewInteraction textView = onView( withId(R.id.textView) );
        //check the text
        textView.check(matches(withText("You shall not pass!")));
    }

    /**
     * tests if the password has a digit
     */
    @Test
    public void testMissingDigit() {
        //find the view s
        ViewInteraction appCompatEditText = onView( withId(R.id.editText) );
        //type in password#$*
        appCompatEditText.perform(replaceText("password#$*"));
        //find the button
        ViewInteraction materialButton = onView(withId(R.id.button));
        //Click the button
        materialButton.perform(click());
        //find the text view
        ViewInteraction textView = onView( withId(R.id.textView) );
        //check the text
        textView.check(matches(withText("You shall not pass!")));
    }

    /**
     * tests if the password has a special character
     */
    @Test
    public void testSpecialCharacter() {
        //find the view s
        ViewInteraction appCompatEditText = onView( withId(R.id.editText) );
        //type in password123
        appCompatEditText.perform(replaceText("password123"));
        //find the button
        ViewInteraction materialButton = onView(withId(R.id.button));
        //Click the button
        materialButton.perform(click());
        //find the text view
        ViewInteraction textView = onView( withId(R.id.textView) );
        //check the text
        textView.check(matches(withText("You shall not pass!")));
    }

    /**
     * if all requirements are met the password is complex enough and will succeed
     */
    @Test
    public void testPasswordSuccess() {
        //find the view s
        ViewInteraction appCompatEditText = onView( withId(R.id.editText) );
        //type in password123
        appCompatEditText.perform(replaceText("Password123#$*"));
        //find the button
        ViewInteraction materialButton = onView(withId(R.id.button));
        //Click the button
        materialButton.perform(click());
        //find the text view
        ViewInteraction textView = onView( withId(R.id.textView) );
        //check the text
        textView.check(matches(withText("Your password is complex enough")));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}