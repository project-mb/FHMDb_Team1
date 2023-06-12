package at.ac.fhcampuswien.fhmdb.dataLayer;

import at.ac.fhcampuswien.fhmdb.DataLayer.Database;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DatabaseTest {
    @Test
    void database_is_singleton() {
        Database expected = Database.getInstance();

        Database actual = Database.getInstance();

        assertEquals(expected, actual);
    }
}
