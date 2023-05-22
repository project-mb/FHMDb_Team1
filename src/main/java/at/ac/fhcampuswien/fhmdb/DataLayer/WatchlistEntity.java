package at.ac.fhcampuswien.fhmdb.DataLayer;

import at.ac.fhcampuswien.fhmdb.LogicLayer.model.Genre;
import at.ac.fhcampuswien.fhmdb.LogicLayer.model.Movie;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Objects;

@DatabaseTable(tableName = "Watchlist")
public class WatchlistEntity {
    @DatabaseField(generatedId = true)
    public long id;
    @DatabaseField()
    public String apild;
    @DatabaseField()
    public String title;
    @DatabaseField()
    public String description;
    @DatabaseField()
    public String genres;
    @DatabaseField()
    public int releaseYear;
    @DatabaseField()
    public String imgUrl;
    @DatabaseField()
    public int lengthInMinutes;
    @DatabaseField()
    public double rating;


    public WatchlistEntity() { }

    public WatchlistEntity(Movie movie) {
        this.apild = movie.getId();
        this.title = movie.getTitle();
        this.description = movie.getDescription();
        this.genres = genresToString(movie.getGenres());
        this.releaseYear = movie.getReleaseYear();
        this.imgUrl = movie.getImgUrl();
        this.lengthInMinutes = movie.getLengthInMinutes();
        this.rating = movie.getRating();
    }


    public long getId() { return id; }
    public String getApild() { return apild; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getGenres() { return genres; }
    public int getReleaseYear() { return releaseYear; }
    public String getImgUrl() { return imgUrl; }
    public int getLengthInMinutes() { return lengthInMinutes; }
    public double getRating() { return rating; }

    public String genresToString(EnumSet<Genre> genres) {
        StringBuilder out = new StringBuilder();

        for (Genre genre : genres) {
            out.append(genre.toString()).append(",");
        }

        out.deleteCharAt(out.length() - 1);

        return out.toString();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WatchlistEntity that = (WatchlistEntity) o;

        return Objects.equals(this.title, that.title);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", genres=" + genres +
                ", releaseYear=" + releaseYear +
                ", description='" + description + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", lengthInMinutes=" + lengthInMinutes +
                ", rating=" + rating +
                '}';
    }
}
