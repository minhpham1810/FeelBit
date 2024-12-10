/* *****************************************  
* CSCI 205 - Software Engineering and Design  
* Fall 2024 
* Instructor: Prof. Lily 
* 
* Name: MINH PHAM
* Section: YOUR SECTION 
* Date: 12/6/2024 
* Time: 6:38 PM  
*   
* Project: csci205_final_project 
* Package: org.mahj.feelBitMoodTracking 
* Class: UserLog 
* 
* Description:  
*   
* **************************************** 
*/ 
package org.mahj.feelBitMoodTracking;


import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Class representing a user's journal log.
 * Stores all the required data to be serialized.
 */
public class UserLog implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String logId;
    private LocalDateTime date;
    private MoodEntry moodEntry;
    private JournalEntry journalEntry;
    private TriggerEntry triggerEntry;
    private Suggestion suggestion;

    public String getLogId() {
        return logId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public MoodEntry getMoodEntry() {
        return moodEntry;
    }

    public JournalEntry getJournalEntry() {
        return journalEntry;
    }

    public TriggerEntry getTriggerEntry() {
        return triggerEntry;
    }

    public Suggestion getSuggestion() {
        return suggestion;
    }

    public UserLog(String logId, MoodEntry moodEntry, JournalEntry journalEntry, TriggerEntry triggerEntry,
                   Suggestion suggestion) {
        this.date = LocalDateTime.now();
        this.logId = logId;
        this.moodEntry = moodEntry;
        this.journalEntry = journalEntry;
        this.triggerEntry = triggerEntry;
        this.suggestion = suggestion;
    }

    /**
     * Returns a string representation of the UserLog object.
     *
     * @return a string describing the UserLog
     */
    @Override
    public String toString() {
        return "UserLog{" +
                "userId = " + logId +
                ", date = " + date +
                ", moodEntry = " + moodEntry +
                ", triggerEntry = " + triggerEntry +
                ", journalEntry = " + journalEntry +
                ", suggestion = '" + suggestion + '\'' +
                '}';
    }
}