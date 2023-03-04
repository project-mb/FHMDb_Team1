package at.ac.fhcampuswien.fhmdb.models;

import java.util.ArrayList;
import java.util.List;

public class Genre {
    static public List<Genre> genres = new ArrayList<>() {{
        new Genre("ACTION");
        new Genre("ADVENTURE");
        new Genre("ANIMATION");
        new Genre("BIOGRAPHY");
        new Genre("COMEDY");
        new Genre("CRIME");
        new Genre("DRAMA");
        new Genre("DOCUMENTARY");
        new Genre("FAMILY");
        new Genre("FANTASY");
        new Genre("HISTORY");
        new Genre("HORROR");
        new Genre("MUSICAL");
        new Genre("MYSTERY");
        new Genre("ROMANCE");
        new Genre("SCIENCE_FICTION");
        new Genre("SPORT");
        new Genre("THRILLER");
        new Genre("WAR");
        new Genre("WESTERN");
    }};
    private String genre;

    public Genre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre; 
    }


}
