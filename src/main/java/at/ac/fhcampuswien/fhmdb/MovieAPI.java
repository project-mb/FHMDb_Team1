package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.model.Movie;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import okhttp3.*;

public class MovieAPI {

    public static List<Movie> get(String url) {
        requestGenerator(url);
        return null;
    }
    public static final String MOVIES_ENDPOINT = "http://prog2.fh-campuswien.ac.at/movies";

    /*public static List<Movie> get(String url) {
        return responseParser(requestGenerator(url));
    }*/

    public static String requestGenerator(String requestURL) {
        //TODO: Eduard

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

    public static List<Movie> responseParser(String jsonArray) {
        //TODO: Manuel
        final Gson gson = new Gson();

        try {
            return Arrays.stream(gson.fromJson(jsonArray, Movie[].class)).toList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}