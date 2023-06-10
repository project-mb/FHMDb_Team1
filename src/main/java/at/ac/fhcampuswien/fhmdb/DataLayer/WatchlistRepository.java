package at.ac.fhcampuswien.fhmdb.DataLayer;

import at.ac.fhcampuswien.fhmdb.LogicLayer.observer.IObservable;
import at.ac.fhcampuswien.fhmdb.LogicLayer.observer.IObserver;
import at.ac.fhcampuswien.fhmdb.exceptions.DatabaseException;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WatchlistRepository implements IObservable {
    private static WatchlistRepository _instance;
    private WatchlistRepository() { this.dao = Database.getDatabase().getWatchlistDao(); }
    public static WatchlistRepository getInstance() {
        if (_instance == null) _instance = new WatchlistRepository();
        return _instance;
    }

    private final List<IObserver> observers = new ArrayList<>();
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
            System.out.println("checked: " + movie.toString() + "\nto be: " + checkIfExists(movie));

            List<WatchlistEntity> entities = dao.queryForAll();
            for (WatchlistEntity entity : entities) {
                if (entity.equals(movie)) {
                    throw new DatabaseException("Movie already in watchlist");
                }
            }
            dao.create(movie);

        } catch (SQLException sqle) {
            throw new DatabaseException("dbError on add", sqle);
        }
    }

    public void removeFromWatchlist(WatchlistEntity movie) {
        try {
            dao.delete(checkIfExists(movie));
        } catch (NullPointerException npe) {
            throw new DatabaseException("Movie is not in watchlisted");
        } catch (SQLException sqle) {
            throw new DatabaseException("dbError on remove", sqle);
        }
    }
    @Override
    public void add(IObserver observer) { observers.add(observer); }
    @Override
    public void remove(IObserver observer) { observers.remove(observer); }
    @Override
    public void notifyObservers() {
        observers.forEach(IObserver::update);
    }
}
