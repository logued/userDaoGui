module org.example.userdaogui {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.userdaogui to javafx.fxml;
    exports org.example.userdaogui;
}