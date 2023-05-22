package at.ac.fhcampuswien.fhmdb.DataLayer;

import at.ac.fhcampuswien.fhmdb.LogicLayer.model.Genre;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.List;

@DatabaseTable(tableName = "watchlist")
public class WatchlistEntity {
    @DatabaseField(id = true)
    private long id;
    @DatabaseField(columnName = "apild")
    private String apild;
    @DatabaseField(columnName = "title")
    private String title;
    @DatabaseField(columnName = "description")
    private String description;
    @DatabaseField(columnName = "genres")
    private String genres;
    @DatabaseField(columnName = "releaseYear")
    private int releaseYear;
    @DatabaseField(columnName = "imgUrl")
    private String imgUrl;
    @DatabaseField(columnName = "lengthInMinutes")
    private int lengthInMinutes;
    @DatabaseField(columnName = "rating")
    private double rating;


    public long getId() { return id; }
    public String getApild() { return apild; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getGenres() { return genres; }
    public int getReleaseYear() { return releaseYear; }
    public String getImgUrl() { return imgUrl; }
    public int getLengthInMinutes() { return lengthInMinutes; }
    public double getRating() { return rating; }

    public String genresToString(List<Genre> genres) {
        StringBuilder out = new StringBuilder();

        for (Genre genre : genres) {
            out.append(genre.toString()).append(",");
        }

        out.deleteCharAt(out.length() - 1);

        return out.toString();
    }
}
