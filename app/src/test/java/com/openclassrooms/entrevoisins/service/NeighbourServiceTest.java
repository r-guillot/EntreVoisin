package com.openclassrooms.entrevoisins.service;

import android.app.Service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;


/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    /**
     * we verify if we can get the neighbours
     */
    @Test
    public void getNeighboursWithSuccess() {
        //get the neighbour list with the method getNeighbours
        List<Neighbour> neighbours = service.getNeighbours();
        //get the neighbour list DUMMY_NEIGHBOURS in the DummyNeighbourGenerator class
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        //check if the items from the neighbour list contains the items from the DUMMY_NEIGHBOURS list
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    /**
     * we check if the delete action is work
     */
    @Test
    public void deleteNeighbourWithSuccess() {
        //get the neighbour at position 0
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        //delete him
        service.deleteNeighbour(neighbourToDelete);
        //check he's not in the favorite list anymore
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    /**
     *we check if we can get the favorites neighbours
     */
    @Test
    public void getFavoriteNeighboursWithSuccess() {
        //get the neighbour list
        List<Neighbour> favoriteNeighbours = service.getNeighbours();
        //modify the "isfavorite" value to true to make the neighbour at position 0 a favorite one
        favoriteNeighbours.get(0).setIsFavorite(true);
        //check if the favorite neighbour list contain items
        assertFalse(service.getFavoriteNeighbours().isEmpty());
    }

    /**
     * we add a neighbour to the favorites neighbours
     */
    @Test
    public void setNeighbourAsFavoriteWithSuccess() {
        //get the neighbour
        Neighbour neighbour = service.getNeighbours().get(1);
        //make him a favorite one
        service.isFavorite(neighbour);
        //check if the favorite neighbour list contain items
        assertFalse(service.getFavoriteNeighbours().isEmpty());
    }

    /**
     * we remove a favorite neighbour from the favorites
     */
    @Test
    public void removeFavoriteNeighbourWithSuccess() {
        //clear the favorite neighbour
        service.getFavoriteNeighbours().clear();
        //get the neighbour
        Neighbour neighbour = service.getNeighbours().get(2);
        //make him a favorite one
        service.isFavorite(neighbour);
        //check if the favorite neighbour list contain items
        assertFalse(service.getFavoriteNeighbours().isEmpty());
        //remove him from the favorites
        service.isFavorite(neighbour);
        //check he's not in the favorite list anymore
        assertFalse(service.getFavoriteNeighbours().contains(neighbour));
    }
}
