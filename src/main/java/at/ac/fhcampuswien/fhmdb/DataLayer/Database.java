package at.ac.fhcampuswien.fhmdb.DataLayer;

import at.ac.fhcampuswien.fhmdb.LogicLayer.model.Movie;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;

public class Database {
    private String DB_URL = "jdbc:h2:file:./data/myDB";
    private String username = "admin";
    private String password = "admin";
    private ConnectionSource connectionSource;
    private Dao<WatchlistEntity, Long> dao;

    public Database(/*String DB_URL, String username, String password, */ConnectionSource connectionSource, Dao<WatchlistEntity, Long> dao) {
        //this.DB_URL = DB_URL;
        //this.username = username;
        //this.password = password;
        this.connectionSource = connectionSource;
        this.dao = dao;
    }

    private void createConnectionSource() {
        try {
            connectionSource = new JdbcConnectionSource(DB_URL, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

    Dao<WatchlistEntity, Long> getWatchlistDao(){
        return dao;
    }
}
