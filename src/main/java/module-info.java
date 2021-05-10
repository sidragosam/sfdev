module org.openjfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires com.fasterxml.jackson.databind;
    requires json.simple;

    opens org.openjfx to javafx.fxml;
    exports org.openjfx;
}