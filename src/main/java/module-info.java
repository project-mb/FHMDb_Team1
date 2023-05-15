module at.ac.fhcampuswien.fhmdb {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.jfoenix;

    requires com.google.gson;
    requires okhttp3;
    requires annotations;
    requires com.h2database;
    requires ormlite.jdbc;

    opens at.ac.fhcampuswien.fhmdb to javafx.fxml;
    exports at.ac.fhcampuswien.fhmdb;
    exports at.ac.fhcampuswien.fhmdb.LogicLayer.model;
    exports at.ac.fhcampuswien.fhmdb.LogicLayer;
    opens at.ac.fhcampuswien.fhmdb.LogicLayer to javafx.fxml;
    opens at.ac.fhcampuswien.fhmdb.LogicLayer.model to javafx.fxml;
}