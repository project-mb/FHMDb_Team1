package at.ac.fhcampuswien.fhmdb.api;

import java.util.Objects;

public final class URLBuilder {

    private String requestURL;
    private String query;
    private String genre;
    private String releaseYear;
    private String ratingFrom;

    public URLBuilder requestURL(String requestURL) {
        this.requestURL = requestURL;
        return this;
    }
    public URLBuilder query(String query) {
        if(!query.equals("")) {
            this.query = "&query=" + query;
        }
        return this;
    }
    public URLBuilder genre(String genre) {
        if(!genre.equals("__NONE__") && !genre.equals("")) {
            this.genre = "&genre=" + genre;
        }
        return this;
    }
    public URLBuilder releaseYear(String releaseYear) {
        if(!releaseYear.equals("")) {
            this.releaseYear = "&releaseYear=" + releaseYear;
        }
        return this;
    }
    public URLBuilder ratingFrom(String ratingFrom) {
        if(!ratingFrom.equals("")) {
            this.ratingFrom = "&rating=" + ratingFrom;
        }
        return this;
    }

    public URL build() {

        if (query == null) {
            query = "";
        }
        if (Objects.equals(genre, "__NONE__") || genre == null) {
            genre = "";
        }
        if (releaseYear == null) {
            releaseYear = "";
        }
        if (ratingFrom == null) {
            ratingFrom = "";
        }

        return new URL(requestURL, query, genre, releaseYear, ratingFrom);
    }
}
