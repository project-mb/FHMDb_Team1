package at.ac.fhcampuswien.fhmdb.api;

public class URL {

    private final String requestURL;
    private final String query;
    private final String genre;
    private final String releaseYear;
    private final String rating;

    protected URL(String requestURL, String query, String genre, String releaseYear, String rating) {
        this.requestURL = requestURL;
        this.query = query;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return requestURL + (query.replaceAll(" ", "%20") + genre + releaseYear + rating).replaceFirst("&", "?");
    }
}
