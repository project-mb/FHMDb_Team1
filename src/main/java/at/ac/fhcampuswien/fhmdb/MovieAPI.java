package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.model.Movie;
import com.google.gson.Gson;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class MovieAPI {
    public static final String MOVIES_ENDPOINT = "http://prog2.fh-campuswien.ac.at/movies";

    public static List<Movie> get(String url, String... attributes) {
        return responseParser(requestGenerator(url));
    }

    protected static String requestGenerator(String requestURL) {
        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(requestURL).newBuilder();
        String url = urlBuilder.toString();

        Request request = new Request.Builder()
                .header("User-Agent", "http.agent")
                .url(url)
                .build();

        try {
            Response response = client.newCall(request).execute();
            return response.body().string();

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
            System.out.println(e.getMessage());
            return null;
        }
    }
}