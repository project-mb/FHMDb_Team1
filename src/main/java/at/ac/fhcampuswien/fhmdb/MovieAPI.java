package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.model.Movie;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

public class MovieAPI {
    public static final String MOVIES_ENDPOINT = "http://prog2.fh-campuswien.ac.at/movies";

    public static List<Movie> get(String url) {
        return responseParser(requestGenerator(url));
    }

    protected static String requestGenerator(String url) {
        //TODO: Eduard
        return null;
    }

    protected static List<Movie> responseParser(String jsonArray) {
        final Gson gson = new Gson();

        try {
            return Arrays.stream(gson.fromJson(jsonArray, Movie[].class)).toList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}