
package com.openclassrooms.entrevoisins.neighbour_list;

import android.graphics.Color;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.contrib.ViewPagerActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import androidx.test.espresso.intent.rule.IntentsTestRule;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.ui.neighbour_detail.NeighboursDetailsActivity;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.ui.neighbour_list.NeighbourFragment;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.IsNull.notNullValue;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.AllOf.allOf;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;





/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;

    private String name = "Jack";

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    /**
     * We ensure that our recyclerview is displaying at least on item
     */

    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        onView(allOf(withId(R.id.list_neighbours), isDisplayed())).check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
        onView(allOf(withId(R.id.list_neighbours), isDisplayed())).check(withItemCount(ITEMS_COUNT));
        // When : perform a click on a delete icon
        onView(allOf(withId(R.id.list_neighbours), isDisplayed())).perform(actionOnItemAtPosition(2, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(allOf(withId(R.id.list_neighbours), isDisplayed())).check(withItemCount(ITEMS_COUNT - 1));
    }

    /**
     * When we click an item, the details of it is open
     */
    @Test
    public void myNeighboursList_onItemClick_openDetail() {
        //perform a click on item
        onView(allOf(withId(R.id.list_neighbours),isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1,click() ));
        //check if NeighbourDetails is open
        onView(allOf(withId(R.id.neighbours_details), isDisplayed())).check(matches(isDisplayed()));
    }

    /**
     * When we open the details of an items, we check if the name is correct
     */
    @Test
    public void NeighboursDetails_checkName () {
        //check list is not empty
        onView(allOf(withId(R.id.list_neighbours), isDisplayed())).check(withItemCount(ITEMS_COUNT));
        //perform a click on item at position 1
        onView(allOf(withId(R.id.list_neighbours), isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(1,click()));
        //check if the name is correct
        onView(allOf(withId(R.id.name_in_image),isDisplayed())).equals(name);
    }

    @Test
    public void NeighboursList_onlyFavoriteAllowed () {
        onView(allOf(withId(R.id.list_neighbours), isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
        onView(allOf(withId(R.id.FAB_detail))).perform(click());
        onView(withContentDescription("Navigate up")).perform(click());
        onView(allOf(withId(R.id.container))).perform(ViewPagerActions.scrollRight());
        onView(allOf(withId(R.id.list_neighbours), isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
        onView(allOf(withId(R.id.FAB_detail))).equals(Color.YELLOW);
    }
}