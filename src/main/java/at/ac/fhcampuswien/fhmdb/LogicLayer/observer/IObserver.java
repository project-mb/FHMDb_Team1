package at.ac.fhcampuswien.fhmdb.LogicLayer.observer;

import at.ac.fhcampuswien.fhmdb.DataLayer.WatchlistEntity;

public interface IObserver {
    void update(ObserverEvent event, WatchlistEntity movie);
}
