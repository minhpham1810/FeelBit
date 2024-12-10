/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2024
 * Instructor: Prof. Lily
 *
 * Name: MINH PHAM
 * Section: YOUR SECTION
 * Date: 11/13/2024
 * Time: 8:27 PM
 *
 * Project: csci205_final_project
 * Package: org.mahj.feelBitJournaling
 * Class: JournalEntry
 *
 * Description:
 *
 * ****************************************
 */
package org.mahj.feelBitMoodTracking;

/**
 * Class representing a journal entry.
 * This class manages to create, viewing, and deleting journal entries.
 */
public class JournalEntry {
    private String content;

    /**
     * Constructor to create a JournalEntry.
     *
     * @param content the content of the journal entry
     */
    public JournalEntry(String content) {
        this.content = content;

    }

    /**
     * Returns a string representation of the JournalEntry object.
     *
     * @return a string describing the JournalEntry
     */
    @Override
    public String toString() {
        return this.content;
    }
}
