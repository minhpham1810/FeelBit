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
 * Class: TriggerEntry
 *
 * Description:
 *
 * ****************************************
 */
package org.mahj.feelBitMoodTracking;

/**
 * Class representing a trigger event that causes a mood.
 * A trigger is associated with a category (e.g., work, family) and a timestamp.
 */
public class TriggerEntry {
    /** Enum value for a trigger option */
    private Trigger trigger;  // Use Trigger (uppercase T)

    public void setTrigger(Trigger trigger) {
        this.trigger = trigger;
    }

    /**
     * Constructor to create a TriggerEntry.
     */
    public TriggerEntry() {
        this.trigger = null;

    }

    /**
     * Returns a string representation of the TriggerEntry object.
     *
     * @return a string describing the TriggerEntry
     */
    @Override
    public String toString() {
        return this.trigger.toString();
    }
}