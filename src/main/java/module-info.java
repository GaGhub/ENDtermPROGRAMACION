module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens gui to javafx.fxml;
    exports gui;
    exports console;
    opens console to javafx.fxml;
    exports app;
    opens app to javafx.fxml;
}