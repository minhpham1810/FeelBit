/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2024
 * Instructor: Prof. Lily
 *
 * Name: Hasiba Hasin Royesh
 * Section: 9:00-10:00AM
 * Date: 09/20/2024
 * Time: 11:06 PM
 *
 * Project: csci205_final_project
 * Package: org.mahj.feelBitJournaling
 * Class: Suggestion
 *
 * Description:
 *
 * ****************************************
 */
package org.mahj.feelBitMoodTracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * The class that generates text in response of user's journal entry using Google Gemini API
 */
public class Suggestion {

    /**
     * Our API key
     */
    private static final String API_KEY = "AIzaSyDLay1OVMSqQD1wWsk70QpmnWOmj4otwGc";

    /**
     * The link used to call the API
     */
    private static final String ENDPOINT = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1" +
            ".5-flash-latest:generateContent?key=" + API_KEY;


    private String content;


    public Suggestion() {
        this.content = "";
    }


    public void generateSuggestion(JournalEntry journalEntry, TriggerEntry trigger, MoodEntry moodEntry) {
        try {
            URL url = new URL(ENDPOINT);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);


            JsonObject promptJson = new JsonObject();
            promptJson.addProperty("text",
                    "You are an API used by a mood tracking app. The user is prompted to input the following " +
                            "categories: \n" +
                            "- Emoji: describing the overall mood (i.e. sad, happy, confused, grateful, etc.) \n" +
                            "- Trigger: what caused that emoji (i.e. school, work, social, personal, love, etc.). There can be a " +
                            "customized trigger from the user.\n" +
                            "- Journal: user writes about their mood (likely a few sentences describing their daily " +
                            "experience in detail). This can be optional because we do not force the user to do this part. \n" +
                            "Generate a bulleted list of suggestions on how to make the user's day better using the given " +
                            "components. This list should be summarized so that it fits into the format of a smartphone " +
                            "screen. Here are the inputs: \n" +
                            "- Emoji: " + moodEntry.toString() + "\n" +
                            "- Trigger: " + trigger.toString() + "\n" +
                            "- Journal: " + journalEntry.toString()
            );


            JsonObject contentJson = new JsonObject();
            contentJson.add("parts", promptJson);


            JsonObject requestJson = new JsonObject();
            requestJson.add("contents", contentJson);


            System.out.println(requestJson);
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = requestJson.toString().getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }


            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                this.content = parseGeneratedText(response.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static String parseGeneratedText(String jsonResponse) {
        JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
        JsonArray candidates = jsonObject.getAsJsonArray("candidates");
        if (candidates != null && !candidates.isEmpty()) {
            JsonObject firstCandidate = candidates.get(0).getAsJsonObject();
            JsonObject content = firstCandidate.getAsJsonObject("content");
            JsonArray parts = content.getAsJsonArray("parts");
            if (parts != null && !parts.isEmpty()) {
                return parts.get(0).getAsJsonObject().get("text").getAsString();
            }
        }
        return "No response text available.";
    }

    public String toString() {
        return this.content;
    }
}

