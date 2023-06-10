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

    exports at.ac.fhcampuswien.fhmdb;
    exports at.ac.fhcampuswien.fhmdb.LogicLayer;
    exports at.ac.fhcampuswien.fhmdb.LogicLayer.model;
    exports at.ac.fhcampuswien.fhmdb.LogicLayer.observer;
    exports at.ac.fhcampuswien.fhmdb.LogicLayer.State;
    exports at.ac.fhcampuswien.fhmdb.DataLayer;
    exports at.ac.fhcampuswien.fhmdb.exceptions;
    exports at.ac.fhcampuswien.fhmdb.PresentationLayer;
    exports at.ac.fhcampuswien.fhmdb.api;

    opens at.ac.fhcampuswien.fhmdb to javafx.fxml;
    opens at.ac.fhcampuswien.fhmdb.PresentationLayer to javafx.fxml;
    opens at.ac.fhcampuswien.fhmdb.LogicLayer to javafx.fxml;
    opens at.ac.fhcampuswien.fhmdb.LogicLayer.model to javafx.fxml;
    opens at.ac.fhcampuswien.fhmdb.api to javafx.fxml;
    opens at.ac.fhcampuswien.fhmdb.DataLayer to ormlite.jdbc;
}