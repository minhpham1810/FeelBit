/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2024
 * Instructor: Prof. Lily
 *
 * Name: MINH PHAM
 * Section: YOUR SECTION
 * Date: 12/5/2024
 * Time: 11:36 AM
 *
 * Project: csci205_final_project
 * Package: org.mahj
 * Class: MoodEntryController
 *
 * Description:
 *
 * ****************************************
 */
package org.mahj;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import com.mongodb.client.MongoCollection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;
import org.bson.Document;
import org.mahj.feelBitLogin.MongoDBConnect;
import org.mahj.feelBitMoodTracking.*;

public class LogEmotionController {

    @FXML
    private AnchorPane displayPane;

    @FXML
    private TextArea journalTextArea;

    @FXML
    private Button submitBtn, backBtn;

    @FXML
    private ComboBox<String> triggerChoiceBox;

    @FXML
    private HBox emojiChoices;

    @FXML
    private ToggleButton happyBtn, sadBtn, blehBtn, confusedBtn, angryBtn, prayingBtn;

    public UserLog currentLog;
    public MoodEntry emoji;
    public static String journalDisplay;
    public static String suggestionDisplay;

    /**
     * Initializes the controller and sets up event handlers.
     */
    @FXML
    void initialize() {
        initializeTriggerChoices();
        initializeToggleGroupWithEffects();
        applyHoverEffects(submitBtn, "#93E1D8", "#7EC1B4", "#65A394");
        applyHoverEffects(backBtn, "transparent", "#d0d0d0", "#c0c0c0");
        initializeEventHandlers();
    }

    /**
     * Populates the trigger choice box with predefined options.
     */
    private void initializeTriggerChoices() {
        triggerChoiceBox.getItems().addAll("<TRIGGER>", "WORK", "SCHOOL", "SOCIAL", "LOVE", "FAMILY", "CUSTOM");
    }

    /**
     * Sets hover, press, and retain effects for emoji buttons.
     */
    private void initializeToggleGroupWithEffects() {
        ToggleGroup group = new ToggleGroup();
        ToggleButton[] toggleButtons = {happyBtn, sadBtn, blehBtn, confusedBtn, angryBtn, prayingBtn};

        for (ToggleButton btn : toggleButtons) {
            btn.setToggleGroup(group);
            applyHoverAndSelectionEffects(btn, "transparent", "#d1e7dd", "#b8cfd1", "#7EC1B4");
        }

        group.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue != null) {
                resetButtonStyle((ToggleButton) oldValue, "transparent");
            }
            if (newValue != null) {
                emoji = new MoodEntry();
                ToggleButton selectedButton = (ToggleButton) newValue;
                switch (selectedButton.getId()) {
                    case "happyBtn" -> emoji.setEmoji(Emoji.HAPPY);
                    case "sadBtn" -> emoji.setEmoji(Emoji.SAD);
                    case "blehBtn" -> emoji.setEmoji(Emoji.BLEH);
                    case "confusedBtn" -> emoji.setEmoji(Emoji.CONFUSED);
                    case "angryBtn" -> emoji.setEmoji(Emoji.ANGRY);
                    case "prayingBtn" -> emoji.setEmoji(Emoji.GRATEFUL);
                }
                retainSelectedStyle(selectedButton, "#7EC1B4");
            }
        });
    }

    /**
     * Sets hover, press, and retain styles for buttons or toggle buttons.
     *
     * @param button       The button to apply effects to.
     * @param defaultColor The default background color.
     * @param hoverColor   The background color on hover.
     * @param pressColor   The background color on press.
     * @param selectedColor The background color when selected.
     */
    private void applyHoverAndSelectionEffects(ToggleButton button, String defaultColor, String hoverColor, String pressColor, String selectedColor) {
        String baseStyle = "-fx-background-radius: 20px; -fx-text-fill: black;";
        button.setStyle(baseStyle + "-fx-background-color: " + defaultColor + ";");

        button.setOnMouseEntered(event -> {
            if (!button.isSelected()) {
                button.setStyle(baseStyle + "-fx-background-color: " + hoverColor + ";");
            }
        });

        button.setOnMouseExited(event -> {
            if (!button.isSelected()) {
                button.setStyle(baseStyle + "-fx-background-color: " + defaultColor + ";");
            }
        });

        button.setOnMousePressed(event -> button.setStyle(baseStyle + "-fx-background-color: " + pressColor + ";"));
        button.setOnMouseReleased(event -> {
            if (!button.isSelected()) {
                button.setStyle(baseStyle + "-fx-background-color: " + hoverColor + ";");
            }
        });
    }

    /**
     * Resets a button to its default style.
     *
     * @param button       The button to reset.
     * @param defaultColor The default background color.
     */
    private void resetButtonStyle(ToggleButton button, String defaultColor) {
        String baseStyle = "-fx-background-radius: 20px; -fx-text-fill: black;";
        button.setStyle(baseStyle + "-fx-background-color: " + defaultColor + ";");
    }

    /**
     * Retains the selected style for a button.
     *
     * @param button        The button to retain the selected style.
     * @param selectedColor The background color when selected.
     */
    private void retainSelectedStyle(ToggleButton button, String selectedColor) {
        String baseStyle = "-fx-background-radius: 20px; -fx-text-fill: black;";
        button.setStyle(baseStyle + "-fx-background-color: " + selectedColor + ";");
    }

    /**
     * Sets up hover effects for regular buttons.
     *
     * @param button       The button to apply effects to.
     * @param defaultColor The default background color.
     * @param hoverColor   The background color on hover.
     * @param pressColor   The background color on press.
     */
    private void applyHoverEffects(Button button, String defaultColor, String hoverColor, String pressColor) {
        String baseStyle = "-fx-background-radius: 20px; -fx-text-fill: #223843;";
        button.setStyle(baseStyle + "-fx-background-color: " + defaultColor + ";");

        button.setOnMouseEntered(event -> button.setStyle(baseStyle + "-fx-background-color: " + hoverColor + ";"));
        button.setOnMouseExited(event -> button.setStyle(baseStyle + "-fx-background-color: " + defaultColor + ";"));
        button.setOnMousePressed(event -> button.setStyle(baseStyle + "-fx-background-color: " + pressColor + ";"));
        button.setOnMouseReleased(event -> button.setStyle(baseStyle + "-fx-background-color: " + hoverColor + ";"));
    }

    /**
     * Sets up event handlers for navigation and submit functionality.
     */
    private void initializeEventHandlers() {
        backBtn.setOnAction(event -> navigateTo("/MainInterface.fxml"));

        submitBtn.setOnAction(event -> {
            String inputText = journalTextArea.getText();
            JournalEntry journalEntry = new JournalEntry(inputText);

            String selectedTrigger = triggerChoiceBox.getValue();
            TriggerEntry trigger = new TriggerEntry();
            if (selectedTrigger != null) {
                switch (selectedTrigger) {
                    case "WORK" -> trigger.setTrigger(Trigger.WORK);
                    case "SCHOOL" -> trigger.setTrigger(Trigger.SCHOOL);
                    case "SOCIAL" -> trigger.setTrigger(Trigger.SOCIAL);
                    case "LOVE" -> trigger.setTrigger(Trigger.LOVE);
                    case "FAMILY" -> trigger.setTrigger(Trigger.FAMILY);
                }
            }

            Suggestion suggestion = new Suggestion();
            suggestion.generateSuggestion(journalEntry, trigger, emoji);

            currentLog = new UserLog(LoginController.userObjectId, emoji, journalEntry, trigger, suggestion);
            MongoCollection<Document> userLogDatabase = MongoDBConnect.getDatabase().getCollection("userLogs");
            userLogDatabase.insertOne(convertUserLogToDocument(currentLog));

            journalDisplay = journalEntry.toString();
            suggestionDisplay = suggestion.toString();

            navigateTo("/MoodSuggestionHub.fxml");
        });
    }

    /**
     * Navigates to a specified FXML page.
     * @param fxmlFile The path to the FXML file.
     */
    private void navigateTo(String fxmlFile) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlFile)));
            Stage stage = (Stage) displayPane.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Converts a UserLog object into a MongoDB document.
     * @param userLog The UserLog object to convert.
     * @return The MongoDB document representation of the UserLog.
     */
    public Document convertUserLogToDocument(UserLog userLog) {
        return new Document()
                .append("logId", userLog.getLogId())
                .append("date", userLog.getDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .append("moodEntry", userLog.getMoodEntry().toString())
                .append("journalEntry", userLog.getJournalEntry().toString())
                .append("triggerEntry", userLog.getTriggerEntry().toString())
                .append("suggestion", userLog.getSuggestion().toString());
    }
}
