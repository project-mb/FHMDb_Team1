package at.ac.fhcampuswien.fhmdb.DataLayer;

import at.ac.fhcampuswien.fhmdb.exceptions.DatabaseException;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class WatchlistRepository {
    private final Dao<WatchlistEntity, Long> dao;

    //Database.getDatabase().getDao();
    public WatchlistRepository() { this.dao = null; }

    public List<WatchlistEntity> getAll() {
        try {
            return Objects.requireNonNull(dao).queryForAll();
        } catch (SQLException sqle) {
            throw new DatabaseException("dbError on retrieve", sqle);
        }
    }

    private WatchlistEntity checkIfExists(WatchlistEntity movie) {
        List<WatchlistEntity> allEntities = getAll();
        for (WatchlistEntity e : getAll()) {
            if (allEntities.equals(movie)) return e;
        }
        return null;
    }

    public void addToWatchlist(WatchlistEntity movie) throws DatabaseException {
        try {
            Objects.requireNonNull(dao).create(checkIfExists(movie));
        } catch (NullPointerException npe) {
            throw new DatabaseException("Movie is already watchlisted!");
        } catch (SQLException sqle) {
            throw new DatabaseException("dbError on add", sqle);
        }
    }

    public void removeFromWatchlist(WatchlistEntity movie) {
        try {
            Objects.requireNonNull(dao).delete(checkIfExists(movie));
        } catch (NullPointerException npe) {
            throw new DatabaseException("Movie is not in watchlisted");
        } catch (SQLException sqle) {
            throw new DatabaseException("dbError on remove", sqle);
        }
    }
}
