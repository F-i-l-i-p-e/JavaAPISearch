package com.example.nasaimagesearch.controller;

import com.example.nasaimagesearch.model.Image;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DetailController {
    // ImageView to display the image
    @FXML
    private ImageView imageView;

    // Label to display the image title
    @FXML
    private Label titleLabel;

    // Label to display the image description
    @FXML
    private Label descriptionLabel;

    // Image object to hold the selected image
    private Image selectedImage;

    // Method to set the image and update the view
    public void setImage(Image image) {
        selectedImage = image;
        updateDetailView();
    }

    // Method to update the view with the selected image details
    private void updateDetailView() {
        if (selectedImage != null) {
            // Load the image and set it to the ImageView
            javafx.scene.image.Image fxImage = new javafx.scene.image.Image(selectedImage.getHref());
            imageView.setImage(fxImage);

            // Set the title and description labels
            titleLabel.setText("Title: " + selectedImage.getTitle());
            descriptionLabel.setText("Description: " + selectedImage.getDescription());

            // Set an on-click event for the ImageView
            imageView.setOnMouseClicked(this::openImageFullscreen);
        }
    }

    // Method to open the image in fullscreen mode
    private void openImageFullscreen(MouseEvent event) {
        // Create a new ImageView for fullscreen display
        ImageView fullscreenImageView = new ImageView(imageView.getImage());
        fullscreenImageView.setPreserveRatio(true);
        fullscreenImageView.setFitHeight(1080); // Set to Full HD height

        // Create a StackPane to display the fullscreen image
        StackPane root = new StackPane(fullscreenImageView);

        // Set the background color to semi-transparent black
        root.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");

        // Create a Scene with the StackPane as the root
        Scene scene = new Scene(root);

        // Create a new Stage for the fullscreen display
        Stage stage = new Stage();

        // Allow interaction with other windows
        stage.initModality(Modality.NONE);

        // Make the window's background transparent
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);

        // Close the stage when the image is clicked
        fullscreenImageView.setOnMouseClicked(e -> stage.close());

        // Close the stage when Esc is pressed
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE) {
                stage.close();
            }
        });

        // Show the fullscreen stage
        stage.show();
    }

    // Method to go back to the main view
    public void goBack(ActionEvent actionEvent) {
        try {
            SceneManager.showMainView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}