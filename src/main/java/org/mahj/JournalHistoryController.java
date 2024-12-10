package org.mahj;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;


import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.bson.Document;
import org.mahj.feelBitLogin.MongoDBConnect;


import static org.mahj.LoginController.userObjectId;


public class JournalHistoryController {


    @FXML
    private ResourceBundle resources;


    @FXML
    private Button backBtn;


    @FXML
    private Label description;


    @FXML
    private ComboBox<String> journalComboBox;


    @FXML
    private TextArea fullEntryTextArea;  // Added TextArea to display full entry


    @FXML
    private AnchorPane displayPane;


    @FXML
    private TextArea textDisplay;


    private String loggedInUser;
    private List<Document> journalEntries; // Store entries to fetch full content later


    // Method to set the logged-in user ID from the LoginController
    public void setLoggedInUser(String userId) {
        this.loggedInUser = userId;
    }


    @FXML
    void initialize() {
        assert backBtn != null : "fx:id=\"backBtn\" was not injected: check your FXML file 'Journal History.fxml'.";
        assert description != null : "fx:id=\"description\" was not injected: check your FXML file 'Journal History.fxml'.";
        assert journalComboBox != null : "fx:id=\"journalComboBox\" was not injected: check your FXML file 'Journal History.fxml'.";
        assert fullEntryTextArea != null : "fx:id=\"fullEntryTextArea\" was not injected: check your FXML file 'Journal History.fxml'.";  // Assert TextArea is injected
        assert displayPane != null : "fx:id=\"displayPane\" was not injected: check your FXML file 'Journal History.fxml'.";
        assert textDisplay != null : "fx:id=\"textDisplay\" was not injected: check your FXML file 'Journal History.fxml'.";
        initEventHandlers();
        loadJournalHistory();


    }


    private void navigateTo(String fxmlFile) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlFile)));
            Stage stage = (Stage) displayPane.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void loadSection(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            AnchorPane newSection = loader.load();
            displayPane.getChildren().setAll(newSection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void initEventHandlers() {
        backBtn.setOnAction(event -> {
            navigateTo("/MainInterface.fxml");
            loadSection("/MainInterface.fxml");
        });


        journalComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            String selectedEntryLabel = journalComboBox.getSelectionModel().getSelectedItem();
            textDisplay.setText(displayFullEntry(selectedEntryLabel));
            textDisplay.setEditable(false);
        });
    }

    /**
     * Loads the journal entries for the logged-in user from MongoDB
     * and fills the ComboBox with labeled entries.
     */
    private void loadJournalHistory() {
        List<String> entries = new ArrayList<>();
        journalEntries = new ArrayList<>(); // Initialize list to store journal entries


        MongoCollection<Document> userLogDatabase = MongoDBConnect.getDatabase().getCollection("userLogs");


        // Query for all entries of the logged-in user
        Document query = new Document("userId", loggedInUser); // Assuming 'userId' is stored in the document
        MongoCursor<Document> cursor = userLogDatabase.find(query).iterator(); // Find all logs for this user


        try {
            int count = 1;
            while (cursor.hasNext()) {
                Document entry = cursor.next();
                String entryText = entry.getString("journalEntry");  // Assuming the journal text is stored as 'journalEntry'
                if (entryText != null) {
                    entries.add(formatEntryLabel(count++, entryText));
                    journalEntries.add(entry); // Store the full entry document for later use
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        // Populate the ComboBox with the entries
        ObservableList<String> observableEntries = FXCollections.observableArrayList(entries);
        journalComboBox.setItems(observableEntries);
    }

    /**
     * Displays the full journal entry when clicked.
     */
    private String displayFullEntry(String selectedEntryLabel) {
        // Extract the index from the label (e.g., "1st Entry: First entry text")
        int entryIndex = Integer.parseInt(selectedEntryLabel.split(" ")[0].replaceAll("[^0-9]", "")) - 1;


        if (entryIndex >= 0 && entryIndex < journalEntries.size()) {
            Document selectedEntry = journalEntries.get(entryIndex);


            // Retrieve the full details from the selected entry
            String moodEntry = selectedEntry.getString("moodEntry");
            String journalEntry = selectedEntry.getString("journalEntry");
            String triggerEntry = selectedEntry.getString("triggerEntry");
            String suggestion = selectedEntry.getString("suggestion");


            // Format the full entry content
            String fullEntry = "Mood: " + moodEntry + "\n\n" +
                    "Journal Entry: " + journalEntry + "\n\n" +
                    "Trigger: " + triggerEntry + "\n\n" +
                    "Suggestions:\n" + suggestion;


            // Display the full entry in the TextArea
            return fullEntry;
        }
        return "";
    }

    /**
     * Formats the journal entry with a label.
     *
     * @param index     the index of the entry (1-based)
     * @param entryText the text of the journal entry
     * @return the formatted entry label
     */
    private String formatEntryLabel(int index, String entryText) {
        String label;
        switch (index) {
            case 1:
                label = "First Entry: ";
                break;
            case 2:
                label = "Second Entry: ";
                break;
            case 3:
                label = "Third Entry: ";
                break;
            default:
                label = index + "th Entry: ";
        }
        return label + entryText;
    }
}
