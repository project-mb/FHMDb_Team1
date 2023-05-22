package at.ac.fhcampuswien.fhmdb.PresentationLayer;

import at.ac.fhcampuswien.fhmdb.LogicLayer.model.ClickEventHandler;
import at.ac.fhcampuswien.fhmdb.LogicLayer.model.Movie;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MovieCell extends ListCell<Movie> {
    private final Label title = new Label();
    private final Label detail = new Label();
    private final Label genres = new Label();
    private final Button showDetails = new Button();
    private final Button bt_setWatchlist = new Button();

    private final Region blank = new Region();
    private final HBox layoutHorizontal = new HBox(title, blank, showDetails, bt_setWatchlist);
    private final VBox layout = new VBox(layoutHorizontal, detail, genres);
    public MovieCell(ClickEventHandler onClicked_addToWatchlist, String remove) {
        super();
        bt_setWatchlist.setOnMouseClicked(mouseEvent -> onClicked_addToWatchlist.onCLick(getItem()));
        bt_setWatchlist.setText(remove);
    }

    @Override
    protected void updateItem(Movie movie, boolean empty) {
        super.updateItem(movie, empty);

        if (empty || movie == null) {
            setText(null);
            setGraphic(null);
        } else {
            this.getStyleClass().add("movie-cell");
            title.setText(movie.title);
            detail.setText(
                    movie.description != null
                            ? movie.description
                            : "No description available"
            );
            genres.setText(movie.genres.toString().replaceAll("\\[|]|,", ""));


            // color scheme
            title.getStyleClass().add("text-yellow");
            detail.getStyleClass().add("text-white");
            genres.getStyleClass().add("text-white");
            layout.setBackground(new Background(new BackgroundFill(Color.web("#454545"), null, null)));

            HBox.setHgrow(blank, Priority.ALWAYS);
            showDetails.setPrefWidth(100);
            showDetails.textProperty().setValue("Show Details");
            bt_setWatchlist.setPrefWidth(100);
            bt_setWatchlist.textProperty().setValue("Watchlist");

            // layout
            title.fontProperty().set(Font.font(20));
            detail.setMaxWidth(this.getScene().getWidth() - 30);
            detail.setWrapText(true);
            layout.setPadding(new Insets(10));
            layout.spacingProperty().set(10);
            layout.alignmentProperty().set(javafx.geometry.Pos.CENTER_LEFT);
            setGraphic(layout);
        }
    }
}

