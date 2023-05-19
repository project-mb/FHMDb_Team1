package at.ac.fhcampuswien.fhmdb.DataLayer;

import at.ac.fhcampuswien.fhmdb.LogicLayer.model.Movie;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class Database {
    private final String DB_URL;
    private final String username;
    private final String password;
    private ConnectionSource connectionSource;
    private Dao<WatchlistMovieEntity, Long> dao;

    public Database(String DB_URL, String username, String password, ConnectionSource connectionSource, Dao<WatchlistMovieEntity, Long> dao) {
        this.DB_URL = DB_URL;
        this.username = username;
        this.password = password;
        this.connectionSource = connectionSource;
        this.dao = dao;
    }

    private void createConnectionSource() {
        connectionSource = new JdbcConnectionSource();
    }

    ConnectionSource getConnectionSource() {
        return connectionSource;
    }

    void createTables() {
        try {
            TableUtils.createTableIfNotExists(connectionSource, Movie.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    Dao<WatchlistMovieEntity, Long> getWatchlistDao(){
        return null;
    }
}
