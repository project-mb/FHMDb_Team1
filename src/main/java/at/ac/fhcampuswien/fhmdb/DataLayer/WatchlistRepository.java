package at.ac.fhcampuswien.fhmdb.DataLayer;

import com.j256.ormlite.dao.Dao;

import java.util.List;

public class WatchlistRepository {
    private Dao<WatchlistEntity, Long> dao;

    public WatchlistRepository(Dao<WatchlistEntity, Long> dao) {
        this.dao = dao;
    }

    void removeFromWatchlist(WatchlistEntity movie) {

    }

    List<WatchlistEntity> getAll() {
        return null;
    }

    void addToWatchlist(WatchlistEntity movie) {

    }
}
