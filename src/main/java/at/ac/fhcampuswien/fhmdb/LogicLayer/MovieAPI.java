package at.ac.fhcampuswien.fhmdb.LogicLayer;

import at.ac.fhcampuswien.fhmdb.LogicLayer.model.Movie;
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

    public static List<Movie> get(String requestURL, String query, String genre) { return responseParser(requestGenerator(requestURL, query, genre)); }
    public static List<Movie> get(String requestURL) { return responseParser(requestGenerator(requestURL, "", "")); }

    protected static String urlBuilder(String requestURL, String query, String genre){
        HttpUrl.Builder urlBuilder = HttpUrl.parse(requestURL).newBuilder();

        if (!query.equals("")) {
            urlBuilder.addQueryParameter("query", query);
        }
        if (!genre.equals("") && !genre.equals("__NONE__")) {
            urlBuilder.addQueryParameter("genre", genre);
        }

        return urlBuilder.toString();
    }

    protected static String requestGenerator(String requestURL, String query, String genre) {
        OkHttpClient client = new OkHttpClient();

        String url = urlBuilder(requestURL, query, genre);

        Request request = new Request.Builder()
                .header("User-Agent", "http.agent")
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
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