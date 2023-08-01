package com.example.nasaimagesearch;

import com.example.nasaimagesearch.controller.SceneManager;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

// Main class to launch the JavaFX application
public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        // Set the primary stage in the SceneManager
        SceneManager.setStage(primaryStage);

        // Set the title of the application window
        primaryStage.setTitle("SPACE Image Search App");

        // Load the icon image from the resources folder
        Image appIcon = new Image(MainApp.class.getResourceAsStream("/favicon.png"));

        // Set the icon of the application window
        primaryStage.getIcons().add(appIcon);

        // Show the main view
        SceneManager.showMainView();

        // Show the primary stage
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
