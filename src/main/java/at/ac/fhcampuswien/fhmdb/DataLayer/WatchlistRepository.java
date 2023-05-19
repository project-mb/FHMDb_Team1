package at.ac.fhcampuswien.fhmdb.DataLayer;

import com.j256.ormlite.dao.Dao;

import java.util.List;

public class WatchlistRepository {
    private Dao<WatchlistMovieEntity, Long> dao;

    public WatchlistRepository(Dao<WatchlistMovieEntity, Long> dao) {
        this.dao = dao;
    }

    void removeFromWatchlist(WatchlistMovieEntity movie) {

    }

    List<WatchlistMovieEntity> getAll() {
        return null;
    }

    void addToWatchlist(WatchlistMovieEntity movie) {

    }
}
