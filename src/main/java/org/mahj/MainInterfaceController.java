package org.mahj;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * Class to implement the controllers in the Homepage
 * class for feelBit app.
 */
public class MainInterfaceController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView FeelBitLogo;

    @FXML
    private Button LogMoodButton;

    @FXML
    private Button ViewJournalEntryButton;

    @FXML
    private Button Logout;

    @FXML
    private Button ViewStatGraphButton;

    @FXML
    private ImageView WelcomeBanner;

    @FXML
    private AnchorPane displayPane;

    @FXML
    private Button logoutBtn;

    private Helper helper;

    @FXML
    void initialize() {
        assert FeelBitLogo != null : "fx:id=\"FeelBitLogo\" was not injected: check your FXML file 'MainInterface.fxml'.";
        assert LogMoodButton != null : "fx:id=\"LogMoodButton\" was not injected: check your FXML file 'MainInterface.fxml'.";
        assert ViewJournalEntryButton != null : "fx:id=\"ViewJournalEntryButton\" was not injected: check your FXML file 'MainInterface.fxml'.";
        assert Logout != null : "fx:id=\"Logout\" was not injected: check your FXML file 'MainInterface.fxml'.";
        assert ViewStatGraphButton != null : "fx:id=\"ViewStatGraphButton\" was not injected: check your FXML file 'MainInterface.fxml'.";
        assert WelcomeBanner != null : "fx:id=\"WelcomeBanner\" was not injected: check your FXML file 'MainInterface.fxml'.";
        assert displayPane != null : "fx:id=\"displayPane\" was not injected: check your FXML file 'MainInterface.fxml'.";
        assert logoutBtn != null : "fx:id=\"logoutBtn\" was not injected: check your FXML file 'MainInterface.fxml'.";

        // Initialize Helper with the displayPane
        helper = new Helper(displayPane);

        initEventHandlers();
        addHoverEffects();
    }

    /**
     * Implement the buttons in the Homepage.
     */
    public void initEventHandlers() {
        this.LogMoodButton.setOnAction(event -> {
            helper.navigateTo("/LogEmotion.fxml");
            helper.loadSection("/LogEmotion.fxml");
        });
        this.ViewJournalEntryButton.setOnAction(event -> {
            helper.navigateTo("/Journal History.fxml");
            helper.loadSection("/Journal History.fxml");
        });
        this.ViewStatGraphButton.setOnAction(event -> {
            helper.navigateTo("/MoodStats.fxml");
            helper.loadSection("/MoodStats.fxml");
        });
        this.logoutBtn.setOnAction(event -> {
            LoginController.userObjectId = null;
            helper.navigateTo("/Login.fxml");
            helper.loadSection("/Login.fxml");
        });
    }

    /**
     * Add effects on the buttons so that they display
     * a different color when hovered over.
     */
    private void addHoverEffects() {
        applyHoverEffect(LogMoodButton);
        applyHoverEffect(ViewJournalEntryButton);
        applyHoverEffect(ViewStatGraphButton);
    }

    /**
     * CSS style for hover effect.
     * @param button -required button
     */
    private void applyHoverEffect(Button button) {
        // Default and hover styles with all necessary attributes
        String defaultStyle = "-fx-background-color: #93E1D8; -fx-text-fill: #223843; -fx-background-radius: 20px;";
        String hoverStyle = "-fx-background-color: #d1e7dd; -fx-text-fill: #223843; -fx-background-radius: 20px;";

        // Set default style
        button.setStyle(defaultStyle);

        // On hover
        button.setOnMouseEntered(event -> button.setStyle(hoverStyle));

        // On exit
        button.setOnMouseExited(event -> button.setStyle(defaultStyle));
    }
}
