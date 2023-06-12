package at.ac.fhcampuswien.fhmdb.LogicLayer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    public TabPane tp_main;
    @FXML
    public Tab ti_home;
    @FXML
    public Tab ti_watchlist;
    @FXML
    public Tab ti_about;

    private static MainController _instance;
    private MainController() { }
    public static MainController getInstance() {
        if (_instance == null) _instance = new MainController();
        return _instance;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tp_main.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    switch (newValue.getId()) {
                        case "ti_home" -> HomeController.getInstance().updateView();
                        case "ti_watchlist" -> WatchlistController.getInstance().updateView();
                        default -> { }
                    }
                }
        );
    }
}
