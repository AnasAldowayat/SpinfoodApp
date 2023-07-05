module com.main.sp23_gruppe2aldowayafahammmokadambadawi {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;
    requires org.junit.jupiter.api;
    requires org.junit.platform.commons;
    requires org.everit.json.schema;
    requires json;
    requires com.fasterxml.jackson.databind;

    opens com.main.model to javafx.fxml;
    opens com.main.export to com.fasterxml.jackson.databind;
    exports com.main.model;
    exports com.main.controller;
    opens com.main.controller to javafx.fxml;
    exports com.main.view;
    opens com.main.view to javafx.fxml;
}
