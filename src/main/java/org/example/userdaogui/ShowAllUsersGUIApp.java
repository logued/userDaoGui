package org.example.userdaogui;  // Feb 2025

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class ShowAllUsersGUIApp extends Application {

    /// We create a JavaFX Application by writing a class that
    /// extends javafx.application.Application. (as above)
    /// The Application is started in its own JavaFX Application thread by
    /// calling the launch() method.
    /// The start() method is then called by the JavaFX runtime.
    /// [Application class API](https://docs.oracle.com/javase/8/javafx/api/javafx/application/Application.html)
    /// [javafx API](https://docs.oracle.com/javase/8/javafx/api/index.html?javafx/application/Application.html)

    public static void main(String[] args) {
        launch(); // launch() is a static method defined in JavaFX Application class (inherited)
    }

    /// start() is called by the JavaFX runtime system, after our JavaFX Application
    /// is launched.  This is the entry point to our program where we load UI and Controllers
    @Override
    public void start(Stage stage) throws IOException {

        // get the FXML view resource
        FXMLLoader loader = new FXMLLoader(ShowAllUsersGUIApp.class.getResource("users-listview.fxml"));
        Parent root = loader.load();  // load in the root (Parent container) of UI, and instantiate the Controller
        // note that the Controller is identified in the FXML and is
        // instantiated automatically.

        // Load the Scene (containing GUI Controls)
        Scene scene = new Scene(root, 450, 650);
        stage.setScene(scene);
        stage.setTitle("User GUI Application");
        stage.show();
    }
}



