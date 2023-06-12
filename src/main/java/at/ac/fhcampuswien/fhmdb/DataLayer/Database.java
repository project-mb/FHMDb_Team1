package at.ac.fhcampuswien.fhmdb.DataLayer;

import at.ac.fhcampuswien.fhmdb.exceptions.DatabaseException;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class Database {
    public static final String DB_URL = "jdbc:h2:file:./db/myDB";
    public static final String username = "admin";
    public static final String password = "admin";
    private static ConnectionSource connectionSource;
    private final Dao<WatchlistEntity, Long> dao;

    private static Database instance;

    private Database() throws DatabaseException {
        try {
            connectionSource = createConnectionSource();
            this.dao = DaoManager.createDao(connectionSource, WatchlistEntity.class);
            createTables();
        } catch (SQLException e) {
            throw new DatabaseException("dbError on dao creation");
        }
    }

    public Dao<WatchlistEntity, Long> getWatchlistDao() { return dao; }

    public ConnectionSource getConnectionSource() { return connectionSource; }

    public static Database getInstance() {
        return (instance == null) ? instance = new Database() : instance;
    }

    private static ConnectionSource createConnectionSource() throws DatabaseException {
        try {
            return new JdbcConnectionSource(DB_URL, username, password);
        } catch (SQLException e) {
            throw new DatabaseException("dbError on createConnectionSource!");
        }
    }

    private static void createTables() throws DatabaseException {
        try {
            TableUtils.createTableIfNotExists(connectionSource, WatchlistEntity.class);
        } catch (SQLException e) {
            throw new DatabaseException("dbError on createTables");
        }
    }
}
