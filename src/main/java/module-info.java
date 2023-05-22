module at.ac.fhcampuswien.fhmdb {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.jfoenix;

    requires com.google.gson;
    requires okhttp3;
    requires annotations;
    requires com.h2database;
    requires ormlite.jdbc;
    requires java.sql;

    opens at.ac.fhcampuswien.fhmdb to javafx.fxml;
    exports at.ac.fhcampuswien.fhmdb;
    exports at.ac.fhcampuswien.fhmdb.LogicLayer.model;
    exports at.ac.fhcampuswien.fhmdb.LogicLayer;
    exports at.ac.fhcampuswien.fhmdb.DataLayer;
    opens at.ac.fhcampuswien.fhmdb.LogicLayer to javafx.fxml;
    opens at.ac.fhcampuswien.fhmdb.LogicLayer.model to javafx.fxml;
    exports at.ac.fhcampuswien.fhmdb.PresentationLayer;
    opens at.ac.fhcampuswien.fhmdb.PresentationLayer to javafx.fxml;
    exports at.ac.fhcampuswien.fhmdb.api;
    opens at.ac.fhcampuswien.fhmdb.api to javafx.fxml;
}