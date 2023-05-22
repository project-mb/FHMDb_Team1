package at.ac.fhcampuswien.fhmdb.DataLayer;

import at.ac.fhcampuswien.fhmdb.exceptions.DatabaseException;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class WatchlistRepository {
    private final Dao<WatchlistEntity, Long> dao;

    public WatchlistRepository() { this.dao = Database.getDatabase().getWatchlistDao(); }

    public List<WatchlistEntity> getAll() {
        try {
            return dao.queryForAll();
        } catch (SQLException sqle) {
            throw new DatabaseException("dbError on retrieve", sqle);
        }
    }

    private WatchlistEntity checkIfExists(WatchlistEntity movie) {
        List<WatchlistEntity> allEntities = getAll();
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
                    throw  new DatabaseException("Movie already in watchlist");
                }
            }
            dao.create(movie);

            //            if(checkIfExists(movie) != null) throw new DatabaseException("Movie is already watchlisted!");
//            dao.create(checkIfExists(movie));
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
}
