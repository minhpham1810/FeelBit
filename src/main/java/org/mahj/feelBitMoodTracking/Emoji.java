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
 * Package: org.mahj.feelBitMoodTracking
 * Class: Emoji
 *
 * Description:
 *
 * ****************************************
 */
package org.mahj.feelBitMoodTracking;

public enum Emoji {
    SAD("😢"),
    ANGRY("😡"),
    HAPPY("😊"),
    CONFUSED("😕"),
    BLEH("😐"),
    GRATEFUL("🙏");

    private final String symbol;
    // Constructor to associate an emoji symbol with each mood
    Emoji(String symbol) {
        this.symbol = symbol;
    }

    // Getter method to retrieve the emoji symbol
    public String getSymbol() {
        return symbol;
    }

    // Method to get a description of the emoji
    public String getDescription() {
        switch (this) {
            case SAD:
                return "Feeling sad or down.";
            case ANGRY:
                return "Feeling angry or frustrated.";
            case HAPPY:
                return "Feeling happy or content.";
            case CONFUSED:
                return "Feeling uncertain or perplexed.";
            case BLEH:
                return "Feeling neutral or indifferent.";
            case GRATEFUL:
                return "Feeling thankful or appreciative.";
            default:
                return "Unknown mood.";
        }
    }
}