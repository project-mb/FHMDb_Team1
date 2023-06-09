package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.LogicLayer.HomeController;
import at.ac.fhcampuswien.fhmdb.LogicLayer.model.Movie;
import javafx.scene.control.Alert;
import org.junit.jupiter.api.parallel.Resources;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import static at.ac.fhcampuswien.fhmdb.LogicLayer.model.Genre.*;

public class BaseTest {
    public static final List<Movie> testMovies = new ArrayList<>();
    public static final HomeController testController = new HomeController();
    public static final String queryTest;
    public static final String genreTest;
    public static final String jsonTest;

    static {
        testMovies.add(new Movie("id0", "title0", EnumSet.of(__NONE__), 0, "des0", "imgUrl0", 0, new String[]{"director01", "director02"}, new String[]{"writer01", "writer02"}, new String[]{"mainCast01", "mainCast02"}, 0));
        testMovies.add(new Movie("id1", "title1", EnumSet.of(ACTION), 1111, "des1", "imgUrl1", 1, new String[]{"director11", "director12"}, new String[]{"writer11", "writer12"}, new String[]{"mainCast11", "mainCast12"}, 1));
        testMovies.add(new Movie("id2", "title2", EnumSet.of(BIOGRAPHY, ANIMATION), 2222, "des2", "imgUrl2", 2, new String[]{"director21", "director22"}, new String[]{"writer21", "writer22"}, new String[]{"mainCast21", "mainCast22"}, 2));
        testMovies.add(new Movie("id3", "title3", EnumSet.of(ADVENTURE, CRIME, ROMANCE), 3333, "des3", "imgUrl3", 3, new String[]{"director31", "director32"}, new String[]{"writer31", "writer32"}, new String[]{"mainCast31", "mainCast32"}, 3));
        testMovies.add(new Movie("id4", "title4", EnumSet.of(__NONE__), 4444, "des4", "imgUrl4", 4, new String[]{"director41", "director42"}, new String[]{"writer41", "writer42"}, new String[]{"mainCast41", "mainCast41"}, 4));
        testMovies.add(new Movie("id5", "title5long", EnumSet.of(__NONE__), 5555, "des5", "imgUrl5", 5, new String[]{"director41", "director42"}, new String[]{"writer41", "writer42"}, new String[]{"mainCast41", "mainCast41"}, 5));

        System.out.println("Test " + System.getProperty("user.dir"));

        jsonTest = importJson(System.getProperty("user.dir") + "\\target\\test-classes\\jsonTest.json");
        queryTest = importJson(System.getProperty("user.dir") + "\\target\\test-classes\\queryTest.json");
        genreTest = importJson(System.getProperty("user.dir") + "\\target\\test-classes\\genreTest.json");
    }

    private static String importJson(String path){
        try {
            return new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            assert false;
            throw new RuntimeException(e);
        }
    }
}
