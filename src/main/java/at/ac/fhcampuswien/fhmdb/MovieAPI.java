package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.model.Movie;
import com.google.gson.*;
import javafx.collections.ObservableList;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class MovieAPI {
    public List<Movie> get(String url) {
        return null;
    }

    private String requestGenerator() {
        //TODO: Eduard
        return null;
    }

    public static List<Movie> responseParser() {
        final OkHttpClient client = new OkHttpClient();
        final Gson gson = new Gson();
        final Movie[][] movies = new Movie[1][1];

        Request request = new Request.Builder()
                .header("User-Agent", "http.agent")
                .url("http://prog2.fh-campuswien.ac.at/movies?query=The Godfather")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                System.out.println("API_ERROR");
            }
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try {
                    String responseData = response.body().string();
                    movies[0] = gson.fromJson(responseData, Movie[].class);
                } catch (JsonIOException e) {
                    System.out.println("JSON_ERROR: " + e.getMessage());
                }
            }
        });

        //TODO: Manuel
        return Arrays.stream(movies[0]).toList();
    }
}