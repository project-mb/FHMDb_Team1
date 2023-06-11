package at.ac.fhcampuswien.fhmdb.DataLayer;

import at.ac.fhcampuswien.fhmdb.LogicLayer.observer.IObservable;
import at.ac.fhcampuswien.fhmdb.LogicLayer.observer.IObserver;
import at.ac.fhcampuswien.fhmdb.LogicLayer.observer.ObserverEvent;
import at.ac.fhcampuswien.fhmdb.exceptions.DatabaseException;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.*;

public class WatchlistRepository implements IObservable {
    private static WatchlistRepository _instance;
    private WatchlistRepository() {
        this.dao = Database.getDatabase().getWatchlistDao();
        this.observers = new HashMap<>();
        Arrays.stream(ObserverEvent.values()).forEach(event -> this.observers.put(event, new ArrayList<>()));
    }
    public static WatchlistRepository getInstance() {
        if (_instance == null) _instance = new WatchlistRepository();
        return _instance;
    }

    private final Map<ObserverEvent, List<IObserver>> observers;
    private final Dao<WatchlistEntity, Long> dao;

    public List<WatchlistEntity> getAll() {
        try {
            return dao.queryForAll();
        } catch (SQLException sqle) {
            throw new DatabaseException("dbError on retrieve", sqle);
        }
    }

    private WatchlistEntity checkIfExists(WatchlistEntity movie) {
        for (WatchlistEntity e : getAll()) {
            if (e.equals(movie)) return e;
        }
        return null;
    }

    public void addToWatchlist(WatchlistEntity movie) throws DatabaseException {
        try {
            if (checkIfExists(movie) == null) {
                dao.create(movie);
                notifyObservers(ObserverEvent.ADD, movie);
            } else notifyObservers(ObserverEvent.ADD, null);
        } catch (SQLException sqle) {
            throw new DatabaseException("dbError on add", sqle);
        }
    }

    public void removeFromWatchlist(WatchlistEntity movie) {
        try {
            dao.delete(checkIfExists(movie));
            notifyObservers(ObserverEvent.REMOVE, movie);
        } catch (NullPointerException npe) {
            throw new DatabaseException("Movie is not in watchlist");
        } catch (SQLException sqle) {
            throw new DatabaseException("dbError on remove", sqle);
        }
    }
    @Override
    public void add(IObserver observer, ObserverEvent event) { observers.get(event).add(observer); }
    @Override
    public void remove(IObserver observer, ObserverEvent event) { observers.get(event).remove(observer); }
    @Override
    public void notifyObservers(ObserverEvent event, WatchlistEntity movie) { observers.get(event).forEach(observer -> observer.update(event, movie)); }
}
