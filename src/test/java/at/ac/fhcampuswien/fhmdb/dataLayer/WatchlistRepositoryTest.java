package at.ac.fhcampuswien.fhmdb.dataLayer;

import at.ac.fhcampuswien.fhmdb.DataLayer.WatchlistRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WatchlistRepositoryTest {
    @Test
    void watchlistRepository_is_singleton() {
        WatchlistRepository expected = WatchlistRepository.getInstance();

        WatchlistRepository actual = WatchlistRepository.getInstance();

        assertEquals(expected, actual);
    }
}
