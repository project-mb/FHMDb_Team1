package at.ac.fhcampuswien.fhmdb.LogicLayer.observer;

import at.ac.fhcampuswien.fhmdb.DataLayer.WatchlistEntity;

public interface IObservable {
    void add(IObserver observer, ObserverEvent event);
    void remove(IObserver observer, ObserverEvent event);
    void notifyObservers(ObserverEvent event, WatchlistEntity movie);
}
