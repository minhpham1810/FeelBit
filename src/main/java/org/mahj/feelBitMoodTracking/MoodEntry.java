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
 * Class: MoodEntry
 *
 * Description:
 *
 * ****************************************
 */
package org.mahj.feelBitMoodTracking;

/**
 * Class representing a mood entry in the system.
 */
public class MoodEntry {

    private Emoji emoji;

    public void setEmoji(Emoji emoji) {
        this.emoji = emoji;
    }

    /**Constructor */
    public MoodEntry () {
        this.emoji = null;
    }

    /**
     * Returns a string representation of the MoodEntry object.
     *
     * @return a string describing the MoodEntry
     */
    @Override
    public String toString() {
        return this.emoji.toString();
    }
}

