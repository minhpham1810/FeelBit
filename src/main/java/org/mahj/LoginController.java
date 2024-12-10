/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2024
 * Instructor: Prof. Lily
 *
 * Name: MINH PHAM
 * Section: YOUR SECTION
 * Date: 12/4/2024
 * Time: 12:52 AM
 *
 * Project: csci205_final_project
 * Package: org.mahj
 * Class: FeelBitController
 *
 * Description:
 *
 * ****************************************
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
import org.bson.types.ObjectId;
import org.mahj.feelBitLogin.UserAccount;
import org.bson.Document;
import com.mongodb.client.model.Filters;

/**
 * Implements the controller of login page.
 */
public class LoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Hyperlink createNewAccount;

    @FXML
    private AnchorPane displayPane;

    @FXML
    private Button loginBtn;

    @FXML
    private ImageView logo;

    @FXML
    private PasswordField passwordInput;

    @FXML
    private TextField usernameInput;

    @FXML
    private Label popUpLabel;

    public static String userObjectId;

    private Helper helper;

    @FXML
    void initialize() {
        assert createNewAccount != null : "fx:id=\"createNewAccount\" was not injected: check your FXML file 'Login.fxml'.";
        assert displayPane != null : "fx:id=\"displayPane\" was not injected: check your FXML file 'Login.fxml'.";
        assert loginBtn != null : "fx:id=\"loginBtn\" was not injected: check your FXML file 'Login.fxml'.";
        assert logo != null : "fx:id=\"logo\" was not injected: check your FXML file 'Login.fxml'.";
        assert passwordInput != null : "fx:id=\"passwordInput\" was not injected: check your FXML file 'Login.fxml'.";
        assert usernameInput != null : "fx:id=\"usernameInput\" was not injected: check your FXML file 'Login.fxml'.";
        assert popUpLabel != null : "fx:id=\"popUpLabel\" was not injected: check your FXML file 'Login.fxml'.";

        helper = new Helper(displayPane);
        initEventHandlers();
        addButtonHoverEffects();
    }

    /**
     * Implement the controllers of the login button.
     */
    public void initEventHandlers() {
        loginBtn.setOnAction(event -> {
            UserAccount userDatabase = new UserAccount();
            String username = usernameInput.getText().strip();
            String password = passwordInput.getText().strip();
            Document user = userDatabase.getUserCollection().find(Filters.eq("username", username)).first();

            if (!username.isEmpty() && user != null && user.getString("password").equals(password)) {
                Document query = new Document("username", username).append("password", password);
                Document userDoc = userDatabase.getUserCollection().find(query).first();
                if (userDoc != null) {
                    ObjectId objectId = userDoc.getObjectId("_id");
                    userObjectId = objectId.toHexString();
                }

                popUpLabel.setText("Login successful! Redirecting...");
                PauseTransition delay = new PauseTransition(Duration.seconds(1));
                delay.setOnFinished(e -> {
                    helper.navigateTo("/MainInterface.fxml");
                    helper.loadSection("/MainInterface.fxml");
                });
                delay.play();
            } else {
                usernameInput.clear();
                passwordInput.clear();
                popUpLabel.setTextFill(Color.RED);
                popUpLabel.setText("Incorrect username or password! Please try again.");
            }
        });

        createNewAccount.setOnAction(actionEvent -> {
            helper.navigateTo("/CreateAccount.fxml");
            helper.loadSection("/CreateAccount.fxml");
        });
    }

    /**
     * Implement the hover effect for the login button.
     */
    private void addButtonHoverEffects() {
        String defaultStyle = "-fx-background-color: #7F8281; -fx-background-radius: 20px; -fx-text-fill: #eee7e7;";
        String hoverStyle = "-fx-background-color: #5A5D5C; -fx-background-radius: 20px; -fx-text-fill: #ffffff;";
        String pressedStyle = "-fx-background-color: #4A4C4B; -fx-background-radius: 20px; -fx-text-fill: #cccccc;";

        loginBtn.setStyle(defaultStyle);

        loginBtn.setOnMouseEntered(event -> loginBtn.setStyle(hoverStyle));
        loginBtn.setOnMouseExited(event -> loginBtn.setStyle(defaultStyle));
        loginBtn.setOnMousePressed(event -> loginBtn.setStyle(pressedStyle));
        loginBtn.setOnMouseReleased(event -> loginBtn.setStyle(hoverStyle));
    }
}

