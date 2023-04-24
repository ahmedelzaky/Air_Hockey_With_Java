module com.example.air_hockey_with_java {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.air_hockey_with_java to javafx.fxml;
    exports com.example.air_hockey_with_java;
}