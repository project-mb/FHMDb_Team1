package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.model.Movie;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import okhttp3.*;

public class MovieAPI {

    public List<Movie> get(String url) {
        requestGenerator(url);
        return null;
    }

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

    public static List<Movie> responseParser() {
        //TODO: Manuel
        return null;
    }
}