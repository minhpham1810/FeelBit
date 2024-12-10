package org.mahj;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Class used to run the whole feelBit App.
 */
public class FeelBitMain extends Application {
    /** Home page controller */
    private MainInterfaceController mainInterfaceController;

    /** Journal History page controller */
    private JournalHistoryController journalHistoryController;

    /** Log emotion page controller */
    private LogEmotionController logEmotionController;

    /** MoodSuggestion page controller */
    private MoodSuggestionHubController moodSuggestionHubController;

    /** Create account page controller */
    private CreateAccountController createAcc;

    /** Login page controller */
    private LoginController loginController;

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Start method to run the project.
     * @param primaryStage - The stage where everything takes place
     */
    @Override
    public void start(Stage primaryStage) {
        // Login or create an account for first time users
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Login.fxml"));
            Parent root = fxmlLoader.load();
            this.loginController = fxmlLoader.getController();
            this.loginController.initEventHandlers();
            Scene scene = new Scene(root);
            primaryStage.setTitle("FeelBit");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
