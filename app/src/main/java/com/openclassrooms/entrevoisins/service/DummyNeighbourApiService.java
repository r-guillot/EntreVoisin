package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    @Override
    public List<Neighbour> getFavoriteNeighbours() {
        List<Neighbour> retList = new ArrayList<>();
        for (Neighbour i : getNeighbours()) {
            if (i.isFavorite()) {
                retList.add(i);
            }
        }
        return retList;
    }

    @Override
    public void isFavorite(Neighbour neighbour) {
        int position = neighbours.indexOf(neighbour);
        neighbours.get(position).setIsFavorite(!neighbour.isFavorite());
    }

}
