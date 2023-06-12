package at.ac.fhcampuswien.fhmdb.LogicLayer.State;
import at.ac.fhcampuswien.fhmdb.LogicLayer.model.Movie;

import java.util.Comparator;
import java.util.List;

public class Sorter {
    public State state;
    protected List<Movie> movieList;

    public Sorter(List<Movie> movieList) {
        this.state = new Unsorted(this);
        this.movieList = movieList;
    }
    protected void sortAscending() {
        movieList.sort(Comparator.comparing(Movie::getTitle));
    }
    protected void sortDescending() {
        movieList.sort(Comparator.comparing(Movie::getTitle).reversed());
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }
}