
package com.openclassrooms.entrevoisins.neighbour_list;

import android.graphics.Color;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.contrib.ViewPagerActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;


import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;


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
        onView(allOf(withId(R.id.list_neighbours), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        //check if NeighbourDetails is open
        onView(allOf(withId(R.id.neighbours_details), isDisplayed())).check(matches(isDisplayed()));
    }

    /**
     * When we open the details of an items, we check if the name is correct
     */
    @Test
    public void NeighboursDetails_checkName() {
        //check list is not empty
        onView(allOf(withId(R.id.list_neighbours), isDisplayed())).check(withItemCount(ITEMS_COUNT));
        //perform a click on item at position 1
        onView(allOf(withId(R.id.list_neighbours), isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        //check if the name is correct
        onView(allOf(withId(R.id.name_in_image), isDisplayed())).equals(name);
    }

    /**
     * we verify if in the tab "FAVORITES" we have only favorites neighbours
     */
    @Test
    public void NeighboursList_onlyFavoriteAllowed() {
        //perform a click on item at position 1
        onView(allOf(withId(R.id.list_neighbours), isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        //perform a click on floating action button to add the neighbour to the favorite
        onView(withId(R.id.FAB_detail)).perform(click());
        //back to the previous page with the back arrow il the toolbar
        onView(withContentDescription("Navigate up")).perform(click());
        //scroll in the container to go in the favorite tab
        onView(withId(R.id.container)).perform(ViewPagerActions.scrollRight());
        //perform a click on item at position 1
        onView(allOf(withId(R.id.list_neighbours), isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        //check if the color of the fab is yellow(mean is a favorite neighbour)
        onView(withId(R.id.FAB_detail)).equals(Color.YELLOW);
    }
}