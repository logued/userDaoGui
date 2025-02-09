module org.example.userdaogui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens org.example.userdaogui to javafx.fxml;
    exports org.example.userdaogui;
    exports org.example.userdaogui.GUI;
    exports org.example.userdaogui.DTOs;

    opens org.example.userdaogui.GUI to javafx.fxml;
}