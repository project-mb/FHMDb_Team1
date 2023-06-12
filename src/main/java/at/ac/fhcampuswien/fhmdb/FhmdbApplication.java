package at.ac.fhcampuswien.fhmdb;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FhmdbApplication extends Application {
    private static final int WINDOW_WIDTH = 1024;
    private static final int WINDOW_HEIGHT = 768;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader mainLoader = new FXMLLoader(FhmdbApplication.class.getResource("main-view.fxml"));

        Scene mainScene = new Scene(mainLoader.load(), WINDOW_WIDTH, WINDOW_HEIGHT);

        stage.setTitle("FHMDb");
        stage.setScene(mainScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}