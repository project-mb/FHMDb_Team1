package at.ac.fhcampuswien.fhmdb;

import com.jfoenix.controls.JFXButton;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class FhmdbApplication extends Application {
    private static final int WINDOW_WIDTH = 2048;
    private static final int WINDOW_HEIGHT = 1024;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader homeLoader = new FXMLLoader(FhmdbApplication.class.getResource("home-view.fxml"));
        FXMLLoader watchlistLoader = new FXMLLoader(FhmdbApplication.class.getResource("watchlist-view.fxml"));
        FXMLLoader aboutLoader = new FXMLLoader(FhmdbApplication.class.getResource("about-view.fxml"));
        FXMLLoader mainLoader = new FXMLLoader(FhmdbApplication.class.getResource("main-view.fxml"));

        BorderPane bp_mainLayout = new BorderPane();
        bp_mainLayout.getStyleClass().add(Color.DARKSLATEGRAY.toString());

        JFXButton bt_home = new JFXButton("Home");
        JFXButton bt_watchlist = new JFXButton("Watchlist");
        JFXButton bt_about = new JFXButton("About");

        bt_home.setOnAction(event -> {
            try { bp_mainLayout.setCenter(homeLoader.load()); } catch (IOException e) { throw new RuntimeException(e); }
        });
        bt_watchlist.setOnAction(event -> {
            try { bp_mainLayout.setCenter(watchlistLoader.load()); } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        bt_about.setOnAction(event -> {
            try { bp_mainLayout.setCenter(aboutLoader.load()); } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        VBox vb_nav = new VBox();
        vb_nav.setPadding(new Insets(5, 10, 5, 10));
        vb_nav.setSpacing(5);
        vb_nav.getStyleClass().add(Color.PINK.toString());
        vb_nav.getChildren().addAll(bt_home, bt_watchlist, bt_about);

        bp_mainLayout.setRight(vb_nav);
        bp_mainLayout.setCenter(homeLoader.load());
        bp_mainLayout.getStylesheets().add(Objects.requireNonNull(FhmdbApplication.class.getResource("styles.css")).toExternalForm());

        Scene mainScene = new Scene(mainLoader.load(), WINDOW_WIDTH, WINDOW_HEIGHT);
//        Scene homeScene = new Scene(homeLoader.load(), WINDOW_WIDTH, WINDOW_HEIGHT);
//        Scene watchlistScene = new Scene(watchlistLoader.load(), WINDOW_WIDTH, WINDOW_HEIGHT);
//        Scene aboutScene = new Scene(aboutLoader.load(), WINDOW_WIDTH, WINDOW_HEIGHT);


        stage.setTitle("FHMDb");
        stage.setScene(mainScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}