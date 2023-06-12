package at.ac.fhcampuswien.fhmdb.LogicLayer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WatchlistControllerTest {
    @Test
    void watchlistController_is_singleton() {
        WatchlistController expected = WatchlistController.getInstance();

        WatchlistController actual = WatchlistController.getInstance();

        assertEquals(expected, actual);
    }
}
