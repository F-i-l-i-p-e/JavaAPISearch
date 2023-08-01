// Module declaration for the JavaFX application
module com.example.nasaimagesearch {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens com.example.nasaimagesearch to javafx.fxml;
    opens com.example.nasaimagesearch.controller to javafx.fxml;
    opens com.example.nasaimagesearch.model to com.google.gson;

    exports com.example.nasaimagesearch;
}
