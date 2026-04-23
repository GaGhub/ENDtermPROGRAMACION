module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
//Añado la línea de debajo para que javaFX pueda acceder.
    opens model to javafx.base;

    opens gui to javafx.fxml;
    exports gui;
    exports console;
    opens console to javafx.fxml;
    exports app;
    opens app to javafx.fxml;
    exports Controller;
    opens Controller to javafx.fxml;
}