package org.mahj;


import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.bson.Document;
import org.mahj.feelBitLogin.MongoDBConnect;

import static org.mahj.LoginController.userObjectId;

/**
 * Class that is used to implement the controllers in the moodStats page.
 */
public class MoodStatsController {


    @FXML
    private ResourceBundle resources;


    @FXML
    private URL location;


    @FXML
    private Label mainLabel;


    @FXML
    private PieChart moodPieChart;


    private Map<String, Integer> moodData;


    @FXML
    private AnchorPane displayPane;


    @FXML
    private Button backBtn;

    private Helper helper;


    @FXML
    void initialize() {
        assert mainLabel != null : "fx:id=\"MoodStatsController\" was not injected: check your FXML file 'MoodStats.fxml'.";
        assert backBtn != null : "fx:id=\"backBtn\" was not injected: check your FXML file 'MoodStats.fxml'.";
        assert displayPane != null : "fx:id=\"displayPane\" was not injected: check your FXML file 'MoodStats.fxml'.";

        // Initialize Helper with the displayPane
        helper = new Helper(displayPane);

        // Initialize the mood data map
        moodData = new HashMap<>();


        // Initialize the chart
        initEventHandlers();

        updateMoodData();
        updateChart();
    }

    /**
     * Method to update the mood data from the logs (this should be connected to your data source)
     */
    private void updateMoodData() {

        moodData.put("Happy", 0);
        moodData.put("Sad", 0);
        moodData.put("Angry", 0);
        moodData.put("Bleh", 0);
        moodData.put("Confused", 0);
        moodData.put("Grateful", 0);

        MongoCollection<Document> userLogDatabase = MongoDBConnect.getDatabase().getCollection("userLogs");
        for (Document doc: userLogDatabase.find(Filters.eq("logId", userObjectId))) {
            System.out.println(doc);
            String mood = doc.getString("moodEntry");
            switch (mood) {
                case "HAPPY":
                    moodData.put("Happy", moodData.get("Happy") + 1);
                case "SAD":
                    moodData.put("Sad", moodData.get("Sad") + 1);
                case "ANGRY":
                    moodData.put("Angry", moodData.get("Angry") + 1);
                case "BLEH":
                    moodData.put("Bleh", moodData.get("Bleh") + 1);
                case "CONFUSED":
                    moodData.put("Confused", moodData.get("Confused") + 1);
                case "GRATEFUL":
                    moodData.put("Grateful", moodData.get("Grateful") + 1);
            }
        }
        System.out.println(moodData);
    }

    /**
     * Method to update the pie chart based on the mood data.
     */
    private void updateChart() {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        for (Map.Entry<String, Integer> entry : moodData.entrySet()) {
            pieChartData.add(new PieChart.Data(entry.getKey(), entry.getValue()));
        }

        moodPieChart.setData(pieChartData);
    }

    /**
     * Implements the back button controllers.
     */
    private void initEventHandlers() {
        backBtn.setOnAction(event -> {
            helper.navigateTo("/MainInterface.fxml");
            helper.loadSection("/MainInterface.fxml");
        });
    }

}
