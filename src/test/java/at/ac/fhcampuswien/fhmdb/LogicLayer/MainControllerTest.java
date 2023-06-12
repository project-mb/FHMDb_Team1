package at.ac.fhcampuswien.fhmdb.LogicLayer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainControllerTest {
    @Test
    void mainController_is_singleton() {
        MainController expected = MainController.getInstance();

        MainController actual = MainController.getInstance();

        assertEquals(expected, actual);
    }
}
