package at.ac.fhcampuswien.fhmdb.DataLayer;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;

public class Database {
    private final String DB_URL;
    private final String username;
    private final String password;
    private ConnectionSource connectionSource;
    private Dao<WatchlistEntity, Long> dao;

    public Database(String DB_URL, String username, String password, ConnectionSource connectionSource, Dao<WatchlistEntity, Long> dao) {
        this.DB_URL = DB_URL;
        this.username = username;
        this.password = password;
        this.connectionSource = connectionSource;
        this.dao = dao;
    }

    private void createConnectionSource() {

    }

    ConnectionSource getConnectionSource() {
        return null;
    }

    void createTables() {

    }

    Dao<WatchlistEntity, Long> getWatchlistDao(){
        return null;
    }
}
