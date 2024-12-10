/* *****************************************
* CSCI 205 - Software Engineering and Design
* Fall 2024
* Instructor: Prof. Lily
*
* Name: Jean Marie Ngabonziza
* Section: 9am section
* Date: 12/5/24
* Time: 11:51 AM
*
* Project: csci205_final_project
* Package: org.mahj
* Class: CreateAccountController
*
* Description: The CreateAccountController class handles the logic and interactions for the "Create Account" page of
* the application. It validates user input, creates new accounts, and navigates between pages.
*
*
*
*/
package org.mahj;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.bson.Document;
import org.mahj.feelBitLogin.UserAccount;

public class CreateAccountController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView createAccLogo;

    @FXML
    private TextField crtAccUsername;

    @FXML
    private PasswordField crtAccPassword;

    @FXML
    private Button createAccountBtn;

    @FXML
    private Label popUpLabel;

    @FXML
    private AnchorPane displayPane;

    /**
     * Initializes the controller, setting up event handlers and hover effects.
     */
    @FXML
    void initialize() {
        assert createAccLogo != null : "fx:id=\"createAccLogo\" was not injected: check your FXML file 'CreateAccount.fxml'.";
        assert crtAccUsername != null : "fx:id=\"crtAccUsername\" was not injected: check your FXML file 'CreateAccount.fxml'.";
        assert crtAccPassword != null : "fx:id=\"crtAccPassword\" was not injected: check your FXML file 'CreateAccount.fxml'.";
        assert createAccountBtn != null : "fx:id=\"createAccountBtn\" was not injected: check your FXML file 'CreateAccount.fxml'.";
        assert popUpLabel != null : "fx:id=\"popUpLabel\" was not injected: check your FXML file 'CreateAccount.fxml'.";
        assert displayPane != null : "fx:id=\"displayPane\" was not injected: check your FXML file 'CreateAccount.fxml'.";

        initEventHandlers();
        addButtonHoverEffects();
    }

    /**
     * Sets up event handlers for the "Create Account" button.
     * Handles validation of input fields, account creation, and navigation.
     */
    private void initEventHandlers() {
        createAccountBtn.setOnAction(event -> {
            String username = crtAccUsername.getText().strip();
            String password = crtAccPassword.getText().strip();

            if (username.isEmpty() || password.isEmpty()) {
                popUpLabel.setTextFill(Color.RED);
                popUpLabel.setText("Username and Password cannot be empty!");
                return;
            }

            UserAccount userDatabase = new UserAccount();
            Document existingUser = userDatabase.getUserCollection()
                    .find(new Document("username", username))
                    .first();

            if (existingUser != null) {
                popUpLabel.setTextFill(Color.RED);
                popUpLabel.setText("Username already exists. Try a different one.");
            } else {
                Document newUser = new Document("username", username).append("password", password);
                userDatabase.getUserCollection().insertOne(newUser);
                popUpLabel.setTextFill(Color.GREEN);
                popUpLabel.setText("Account created successfully! Redirecting...");

                PauseTransition delay = new PauseTransition(Duration.seconds(2));
                delay.setOnFinished(e -> navigateTo("/Login.fxml"));
                delay.play();
            }
        });
    }

    /**
     * Adds hover and press effects to the "Create Account" button for a better user experience.
     */
    private void addButtonHoverEffects() {
        String defaultStyle = "-fx-background-color: #7F8281; -fx-background-radius: 10px; -fx-text-fill: #f5f0f0;";
        String hoverStyle = "-fx-background-color: #5A5D5C; -fx-background-radius: 10px; -fx-text-fill: #ffffff;";
        String pressedStyle = "-fx-background-color: #4A4C4B; -fx-background-radius: 10px; -fx-text-fill: #cccccc;";

        createAccountBtn.setStyle(defaultStyle);

        createAccountBtn.setOnMouseEntered(event -> createAccountBtn.setStyle(hoverStyle));
        createAccountBtn.setOnMouseExited(event -> createAccountBtn.setStyle(defaultStyle));
        createAccountBtn.setOnMousePressed(event -> createAccountBtn.setStyle(pressedStyle));
        createAccountBtn.setOnMouseReleased(event -> createAccountBtn.setStyle(hoverStyle));
    }

    /**
     * Navigates to the specified FXML page.
     *
     * @param fxmlFile The path to the FXML file to load.
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
}
