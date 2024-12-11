module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.demo.controller to javafx.fxml;
    opens com.example.demo.level to javafx.fxml;
    opens com.example.demo.level.levelview to javafx.fxml;
    opens com.example.demo.model to javafx.fxml, org.junit.platform.commons;
    opens com.example.demo.projectile to javafx.fxml;
    opens com.example.demo.view to javafx.fxml;

    exports com.example.demo.controller;
    exports com.example.demo.level;
    exports com.example.demo.level.levelview;
    exports com.example.demo.model;
    exports com.example.demo.projectile;
    exports com.example.demo.view;
}
