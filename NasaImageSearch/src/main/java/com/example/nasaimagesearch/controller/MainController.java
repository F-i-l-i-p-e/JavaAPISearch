package com.example.nasaimagesearch.controller;

import com.example.nasaimagesearch.model.ApiService;
import com.example.nasaimagesearch.model.Image;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.util.Callback;

import java.io.IOException;
import java.util.List;

public class MainController {
    @FXML
    private TextField searchField;

    @FXML
    private ListView<Image> resultsListView;

    @FXML
    private Button searchButton;

    private ApiService apiService = new ApiService();
    private List<Image> currentSearchResults;  // Store the current search results

    // Nested static class for ImageListCell
    static class ImageListCell extends ListCell<Image> {
        @Override
        protected void updateItem(Image image, boolean empty) {
            super.updateItem(image, empty);
            if (empty || image == null) {
                setText(null);
            } else {
                setText(image.getTitle());
            }
        }
    }

    @FXML
    public void initialize() {
        Platform.runLater(() -> searchButton.requestFocus());
        searchField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                searchImages();
            }
        });

        // Set the event handler for clicking on the list items
        resultsListView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { // Check for double-click
                Image selectedImage = resultsListView.getSelectionModel().getSelectedItem();
                if (selectedImage != null) {
                    try {
                        SceneManager.showDetailView();
                        DetailController detailController = SceneManager.getDetailController();
                        detailController.setImage(selectedImage);
                    } catch (IOException e) {
                        // Handle error, show a dialog or a message to the user
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @FXML
    public void searchImages() {
        String query = searchField.getText();
        try {
            currentSearchResults = apiService.searchImages(query);
            resultsListView.getItems().setAll(currentSearchResults);
            resultsListView.setCellFactory(param -> new ImageListCell());
        } catch (IOException e) {
            // Handle error, show a dialog or a message to the user
            e.printStackTrace();
        }
    }

    // New method for populating the ListView with the current search results
    public void populateSearchResults() {
        if (currentSearchResults != null) {
            resultsListView.getItems().setAll(currentSearchResults);
            resultsListView.setCellFactory(param -> new ImageListCell());
        }
    }
}
