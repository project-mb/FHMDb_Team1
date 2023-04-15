package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.model.Movie;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import okhttp3.*;

public class MovieAPI {

    public List<Movie> get(String url) {
        return null;
    }

    private String requestGenerator() throws IOException {
        //TODO: Eduard

        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse("http://prog2.fh-campuswien.ac.at/movies").newBuilder();
        //urlBuilder.addQueryParameter("");
        //urlBuilder.addQueryParameter("");
        String url = urlBuilder.toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            Response response = client.newCall(request).execute();
            return response.body().string();

        } catch (IOException e) {
            PrintStream errorOut = new PrintStream("error.txt");
            System.setErr(errorOut);
        }

        return null;
    }

    private List<Movie> responseParser() {
        //TODO: Manuel
        return null;
    }
}