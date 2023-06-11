package at.ac.fhcampuswien.fhmdb.api;

import at.ac.fhcampuswien.fhmdb.LogicLayer.model.Genre;
import at.ac.fhcampuswien.fhmdb.LogicLayer.model.Movie;
import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MovieAPI {
    public static final String MOVIES_ENDPOINT = "http://prog2.fh-campuswien.ac.at/movies";

    public static List<Movie> get(String requestURL, String query, Genre genre, String releaseYear, String ratingFrom) { return responseParser(requestGenerator(requestURL, query, genre, releaseYear, ratingFrom)); }
    public static List<Movie> get(String requestURL, String query, Genre genre) { return responseParser(requestGenerator(requestURL, query, genre, "", "")); }
    public static List<Movie> get(String requestURL) { return responseParser(requestGenerator(requestURL, "", Genre.__NONE__, "", "")); }

    protected static String requestGenerator(String requestURL, String query, Genre genre, String releaseYear, String ratingFrom) {
        OkHttpClient client = new OkHttpClient();
        String url = new URLBuilder().requestURL(requestURL)
                .query(query)
                .genre(genre.name())
                .releaseYear(releaseYear)
                .ratingFrom(ratingFrom)
                .build()
                .toString();

        Request request = new Request.Builder()
                .header("User-Agent", "http.agent")
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return Objects.requireNonNull(response.body()).string();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    protected static List<Movie> responseParser(String jsonArray) {
        final Gson gson = new Gson();

        try {
            return Arrays.stream(gson.fromJson(jsonArray, Movie[].class)).toList();
        } catch (Exception e) {
//            System.out.println(e.getMessage());
            return null;
        }
    }
}