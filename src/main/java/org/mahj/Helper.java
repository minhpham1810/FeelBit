package org.mahj;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * Helper class to help navigate and load pages.
 */
public class Helper {

    private final AnchorPane displayPane;

    /**
     * Constructor to initialize the displayPane for navigation and loading sections.
     *
     * @param displayPane - The AnchorPane from the controller.
     */
    public Helper(AnchorPane displayPane) {
        this.displayPane = displayPane;
    }

    /**
     * Helper function to help move from one page to another.
     *
     * @param fxmlFile - The FXML file to load.
     */
    public void navigateTo(String fxmlFile) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(Helper.class.getResource(fxmlFile)));
            Stage stage = (Stage) displayPane.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Utility method to load a section into the content area.
     *
     * @param fxmlFile - The FXML file to load.
     */
    public void loadSection(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(Helper.class.getResource(fxmlFile));
            AnchorPane newSection = loader.load();
            displayPane.getChildren().setAll(newSection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
