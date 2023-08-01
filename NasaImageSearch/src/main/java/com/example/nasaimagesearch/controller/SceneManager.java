package com.example.nasaimagesearch.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

// Class to manage scene transitions and stage settings
// Class to manage scene transitions and stage settings
public class SceneManager {
    // Reference to the primary stage
    private static Stage stage;

    // Reference to the DetailController for communication between scenes
    private static DetailController detailController;

    // Fields to store the stage dimensions before changing scenes
    private static double stageWidth;
    private static double stageHeight;

    // Method to set the primary stage
    public static void setStage(Stage stage) {
        SceneManager.stage = stage;
    }

    // Method to show the main view
    public static void showMainView() throws IOException {
        // Store the stage dimensions before changing scenes
        stageWidth = stage.getWidth();
        stageHeight = stage.getHeight();

        // Load the MainView.fxml file
        Parent root = FXMLLoader.load(SceneManager.class.getResource("/com/example/nasaimagesearch/MainView.fxml"));

        // Create a new Scene with the loaded root and set it to the stage
        Scene scene = new Scene(root, 800, 600); // Adjust size as needed
        scene.getStylesheets().add(SceneManager.class.getResource("/styles.css").toExternalForm());
        stage.setScene(scene);

        // Restore the stage dimensions after changing scenes
        stage.setWidth(stageWidth);
        stage.setHeight(stageHeight);
    }

    // Method to show the detail view
    public static void showDetailView() throws IOException {
        // Store the stage dimensions before changing scenes
        stageWidth = stage.getWidth();
        stageHeight = stage.getHeight();

        // Load the DetailView.fxml file using FXMLLoader
        FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource("/com/example/nasaimagesearch/DetailView.fxml"));
        Parent root = loader.load();

        // Create a new Scene with the loaded root and set it to the stage
        Scene scene = new Scene(root, 800, 600); // Adjust size as needed
        scene.getStylesheets().add(SceneManager.class.getResource("/styles.css").toExternalForm());
        stage.setScene(scene);

        // Restore the stage dimensions after changing scenes
        stage.setWidth(stageWidth);
        stage.setHeight(stageHeight);

        // Get the DetailController instance from the FXMLLoader and store it in the SceneManager
        detailController = loader.getController();
    }

    // Method to get the DetailController instance
    public static DetailController getDetailController() {
        return detailController;
    }
}
