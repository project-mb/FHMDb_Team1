package at.ac.fhcampuswien.fhmdb.LogicLayer.State;

import at.ac.fhcampuswien.fhmdb.LogicLayer.model.Movie;

import java.util.List;

public abstract class State {
    protected Sorter sorter;

    public State(Sorter sorter) {
        this.sorter = sorter;
    }
    public abstract void onSort();
    public abstract void onCurrent();
    public abstract String getName();
}
