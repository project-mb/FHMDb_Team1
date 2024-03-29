package at.ac.fhcampuswien.fhmdb.LogicLayer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomeControllerTest {
    @Test
    void homeController_is_singleton() {
        HomeController expected = HomeController.getInstance();

        HomeController actual = HomeController.getInstance();

        assertEquals(expected, actual);
    }
}
