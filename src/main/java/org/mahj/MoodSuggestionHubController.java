/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2024
 * Instructor: Prof. Lily
 *
 * Name: Aura Chuck Hernandez
 * Section: Section 1 - 9AM
 * Date: 12/7/24
 * Time: 4:55 PM
 *
 * Project: csci205_final_project
 * Package: org.mahj
 * Class: MoodSuggestionHubController
 *
 * Description:
 *
 * ****************************************
 */
package org.mahj;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Class that is used to implement the controllers in the moodSuggestionHub page.
 */
public class MoodSuggestionHubController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backToHomepageBtn;

    @FXML
    private ImageView Banner;

    @FXML
    private TextArea EntryDisplay;

    @FXML
    private Label EntryLabel;

    @FXML
    private TextArea SuggestionDisplay;

    @FXML
    private Label SuggestionLabel;

    @FXML
    private AnchorPane displayPane;

    private Helper helper;


    @FXML
    void initialize() throws InstantiationException, IllegalAccessException {
        assert backToHomepageBtn != null : "fx:id=\"backToHomepageBtn\" was not injected: check your FXML file 'MoodSuggestionHub.fxml'.";
        assert Banner != null : "fx:id=\"Banner\" was not injected: check your FXML file 'MoodSuggestionHub.fxml'.";
        assert EntryDisplay != null : "fx:id=\"EntryDisplay\" was not injected: check your FXML file 'MoodSuggestionHub.fxml'.";
        assert EntryLabel != null : "fx:id=\"EntryLabel\" was not injected: check your FXML file 'MoodSuggestionHub.fxml'.";
        assert SuggestionDisplay != null : "fx:id=\"SuggestionDisplay\" was not injected: check your FXML file 'MoodSuggestionHub.fxml'.";
        assert SuggestionLabel != null : "fx:id=\"SuggestionLabel\" was not injected: check your FXML file 'MoodSuggestionHub.fxml'.";

        // Initialize Helper with the displayPane
        helper = new Helper(displayPane);

        initEventHandlers();
        SuggestionDisplay.setText(LogEmotionController.suggestionDisplay);
        SuggestionDisplay.setEditable(false);
        EntryDisplay.setText(LogEmotionController.journalDisplay);
        EntryDisplay.setEditable(false);

    }

    /**
     * Implement the button events to navigate back to homepage.
     */
    private void initEventHandlers() {
        backToHomepageBtn.setOnAction(event -> {
            helper.navigateTo("/MainInterface.fxml");
            helper.loadSection("/MainInterface.fxml");

        });
    }
}
